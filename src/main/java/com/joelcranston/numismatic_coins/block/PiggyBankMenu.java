package com.joelcranston.numismatic_coins.block;

import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.item.CoinItem;
import com.joelcranston.numismatic_coins.registry.ModMenus;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/** The piggy bank's menu: its three coin slots over the player's inventory. (NO: PiggyBankScreenHandler.) */
public class PiggyBankMenu extends AbstractContainerMenu {

    public static final int COIN_SLOT_COUNT = Currency.values().length;

    // Positions on textures/gui/piggy_bank.png, from NO's piggy_bank.xml.
    public static final int FIRST_COIN_SLOT_X = 62;
    public static final int COIN_SLOT_Y = 26;
    public static final int SLOT_SPACING = 18;
    private static final int INVENTORY_X = 8;
    private static final int INVENTORY_Y = 63;
    private static final int PLAYER_SLOT_COUNT = 36;

    private final Container container;

    /** The client's copy, filled by the server's slot updates. */
    public PiggyBankMenu(int containerId, Inventory inventory) {

        this(containerId, inventory, new SimpleContainer(COIN_SLOT_COUNT));
    }

    public PiggyBankMenu(int containerId, Inventory inventory, Container container) {

        super(ModMenus.PIGGY_BANK.get(), containerId);
        checkContainerSize(container, COIN_SLOT_COUNT);
        this.container = container;
        container.startOpen(inventory.player);

        for (int slot = 0; slot < COIN_SLOT_COUNT; slot++) {
            this.addSlot(new Slot(container, slot, FIRST_COIN_SLOT_X + slot * SLOT_SPACING, COIN_SLOT_Y) {

                @Override
                public boolean mayPlace(ItemStack stack) {

                    return acceptsCoin(this.getContainerSlot(), stack);
                }
            });
        }
        this.addStandardInventorySlots(inventory, INVENTORY_X, INVENTORY_Y);
    }

    /** Each slot holds only its own coin: bronze, silver, then gold. */
    public static boolean acceptsCoin(int slot, ItemStack stack) {

        return stack.getItem() instanceof CoinItem coin && coin.currency().ordinal() == slot;
    }

    @Override
    public ItemStack quickMoveStack(Player player, int slotIndex) {

        Slot slot = this.slots.get(slotIndex);
        if (!slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack original = stack.copy();
        boolean isMoved = slotIndex < COIN_SLOT_COUNT
                ? this.moveItemStackTo(stack, COIN_SLOT_COUNT, COIN_SLOT_COUNT + PLAYER_SLOT_COUNT, true)
                : this.moveItemStackTo(stack, 0, COIN_SLOT_COUNT, false);
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

        return this.container.stillValid(player);
    }

    @Override
    public void removed(Player player) {

        super.removed(player);
        this.container.stopOpen(player);
    }
}
