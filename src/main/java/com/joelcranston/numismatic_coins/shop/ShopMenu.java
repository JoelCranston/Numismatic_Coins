package com.joelcranston.numismatic_coins.shop;

import java.util.ArrayList;
import java.util.List;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.network.ShopAction;
import com.joelcranston.numismatic_coins.network.ShopScreenState;
import com.joelcranston.numismatic_coins.purse.Purses;
import com.joelcranston.numismatic_coins.registry.ModMenus;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.Nullable;

/**
 * The owner's view of a shop or pawn shop: its stock over the player's inventory, and the offers,
 * money held and hopper switch, which the client learns from {@link ShopScreenState}. An offer is
 * edited through a buffer holding a copy of the stack to sell or buy, set by clicking it with a
 * stack or by picking an existing offer. (NO: ShopScreenHandler, PawnShopScreenHandler.)
 */
public class ShopMenu extends AbstractContainerMenu {

    // Positions on textures/gui/shop_gui.png, from NO's ShopScreenHandler.
    private static final int STOCK_X = 8;
    private static final int STOCK_Y = 17;
    private static final int STOCK_COLUMNS = 9;
    private static final int SLOT_SPACING = 18;
    private static final int INVENTORY_X = 8;
    private static final int INVENTORY_Y = 85;
    private static final int PLAYER_SLOT_COUNT = 36;

    private final Player player;
    private final Container stock;
    // Set on the server only.
    private final @Nullable AbstractShopBlockEntity shop;

    // The client's copy of the shop's state; the server reads the shop itself.
    private List<ShopOffer> offers = List.of();
    private long storedValue;
    private boolean allowsTransfer;
    private ItemStack buffer = ItemStack.EMPTY;

    // Client only: the offers tab covers the stock, whose slots then take no clicks.
    private boolean showsStock = true;

    public ShopMenu(MenuType<?> type, int containerId, Inventory inventory, AbstractShopBlockEntity shop) {

        this(type, containerId, inventory, shop, shop);
    }

    private ShopMenu(MenuType<?> type, int containerId, Inventory inventory, Container stock, @Nullable AbstractShopBlockEntity shop) {

        super(type, containerId);
        checkContainerSize(stock, AbstractShopBlockEntity.STOCK_SLOT_COUNT);
        this.player = inventory.player;
        this.stock = stock;
        this.shop = shop;
        stock.startOpen(inventory.player);
        if (shop != null) shop.setBeingEdited(true);

        for (int slot = 0; slot < AbstractShopBlockEntity.STOCK_SLOT_COUNT; slot++) {
            int x = STOCK_X + slot % STOCK_COLUMNS * SLOT_SPACING;
            int y = STOCK_Y + slot / STOCK_COLUMNS * SLOT_SPACING;
            this.addSlot(new Slot(stock, slot, x, y) {

                @Override
                public boolean isActive() {

                    return ShopMenu.this.showsStock;
                }
            });
        }
        this.addStandardInventorySlots(inventory, INVENTORY_X, INVENTORY_Y);
    }

    /** The client's copy of a shop's menu, filled by the server's slot updates and {@link ShopScreenState}. */
    public static ShopMenu forShop(int containerId, Inventory inventory) {

        return new ShopMenu(ModMenus.SHOP.get(), containerId, inventory, new SimpleContainer(AbstractShopBlockEntity.STOCK_SLOT_COUNT), null);
    }

    /** The client's copy of a pawn shop's menu. */
    public static ShopMenu forPawnShop(int containerId, Inventory inventory) {

        return new ShopMenu(ModMenus.PAWN_SHOP.get(), containerId, inventory, new SimpleContainer(AbstractShopBlockEntity.STOCK_SLOT_COUNT), null);
    }

    /** Opens the shop's menu for its owner and sends the state the screen shows. */
    public static void open(ServerPlayer player, AbstractShopBlockEntity shop) {

        player.openMenu(shop);
        if (player.containerMenu instanceof ShopMenu menu) menu.sendState();
    }

    public List<ShopOffer> offers() {

        return this.shop != null ? this.shop.offers() : this.offers;
    }

    public long storedValue() {

        return this.shop != null ? this.shop.storedValue() : this.storedValue;
    }

    public boolean allowsTransfer() {

        return this.shop != null ? this.shop.allowsTransfer() : this.allowsTransfer;
    }

    public ItemStack buffer() {

        return this.buffer;
    }

    /** Whether the buffer's stack already has an offer, which submitting replaces. */
    public boolean hasOfferForBuffer() {

        return this.offers().stream().anyMatch(offer -> ItemStack.isSameItemSameComponents(offer.stack(), this.buffer));
    }

    public void setShowsStock(boolean showsStock) {

        this.showsStock = showsStock;
    }

    /** Client side: takes the state the server sent. */
    public void receiveState(ShopScreenState state) {

        this.offers = new ArrayList<>(state.offers());
        this.storedValue = state.storedValue();
        this.allowsTransfer = state.allowsTransfer();
        this.buffer = state.buffer();
    }

    /** Server side: carries out a button press from the owner's screen. */
    public void handle(ShopAction.Action action, long value) {

        if (this.shop == null) return;
        switch (action) {
            case LOAD_OFFER -> {
                List<ShopOffer> offers = this.shop.offers();
                if (value < 0 || value >= offers.size()) {
                    NumismaticCoins.LOGGER.warn("{} asked for shop offer {} of {}", this.player.getName().getString(), value, offers.size());
                    return;
                }
                this.buffer = offers.get((int) value).stack().copy();
            }
            case CREATE_OFFER -> {
                if (this.buffer.isEmpty() || value <= 0 || value > ShopOffer.MAX_PRICE) return;
                this.shop.putOffer(new ShopOffer(this.buffer, value));
            }
            case DELETE_OFFER -> this.shop.removeOffer(this.buffer);
            case INSERT_CURRENCY -> {
                // Only a pawn shop takes money in, to pay for what it buys.
                if (this.shop instanceof PawnShopBlockEntity) this.shop.addValue(Purses.takeFromInventory(this.player));
            }
            case EXTRACT_CURRENCY -> Purses.deposit(this.player, this.shop.takeStoredValue());
            case TOGGLE_TRANSFER -> this.shop.toggleTransfer();
            case CLICK_BUFFER -> this.buffer = this.getCarried().copy();
        }
        this.sendState();
    }

    private void sendState() {

        if (this.shop == null || !(this.player instanceof ServerPlayer serverPlayer)) return;
        ShopScreenState state = new ShopScreenState(this.containerId, this.shop.offers(), this.shop.storedValue(), this.shop.allowsTransfer(), this.buffer);
        NumismaticCoins.xplat().sendToPlayer(serverPlayer, state);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {

        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        int stockEnd = AbstractShopBlockEntity.STOCK_SLOT_COUNT;
        boolean isMoved = slotIndex < stockEnd
                ? this.moveItemStackTo(stack, stockEnd, stockEnd + PLAYER_SLOT_COUNT, true)
                : this.moveItemStackTo(stack, 0, stockEnd, false);
        if (!isMoved) return ItemStack.EMPTY;

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }
        return original;
    }

    @Override
    public boolean stillValid(Player player) {

        return this.stock.stillValid(player);
    }

    @Override
    public void removed(Player player) {

        super.removed(player);
        this.stock.stopOpen(player);
        if (this.shop != null) this.shop.setBeingEdited(false);
    }
}
