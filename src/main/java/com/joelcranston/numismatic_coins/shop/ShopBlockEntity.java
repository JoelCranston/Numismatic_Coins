package com.joelcranston.numismatic_coins.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.item.CoinStacks;
import com.joelcranston.numismatic_coins.registry.ModBlockEntities;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.core.UUIDUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.TagValueOutput;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import org.jspecify.annotations.Nullable;

/**
 * A shop's stock, offers, earnings and owner. Clients get the offers, for the item turning on top,
 * and the owner, who alone can break it; the stock and earnings stay on the server.
 * (NO: ShopBlockEntity.)
 */
public class ShopBlockEntity extends BaseContainerBlockEntity implements WorldlyContainer {

    public static final int STOCK_SLOT_COUNT = 27;
    /** The most offers a shop holds: what the owner's offer list shows. (NO: ShopBlockEntity#addOrReplaceOffer.) */
    public static final int MAX_OFFERS = 24;

    private static final String OFFERS_KEY = "offers";
    private static final String STORED_VALUE_KEY = "stored_value";
    private static final String OWNER_KEY = "owner";
    private static final String ALLOWS_TRANSFER_KEY = "allows_transfer";
    private static final Codec<List<ShopOffer>> OFFERS_CODEC = ShopOffer.CODEC.listOf();
    private static final int[] ALL_SLOTS = IntStream.range(0, STOCK_SLOT_COUNT).toArray();
    private static final int[] NO_SLOTS = new int[0];

    private NonNullList<ItemStack> stock = NonNullList.withSize(STOCK_SLOT_COUNT, ItemStack.EMPTY);
    private List<ShopOffer> offers = new ArrayList<>();
    private long storedValue;
    @Nullable
    private UUID owner;
    private boolean allowsTransfer;
    private boolean isBeingEdited;
    private final ShopMerchant merchant;

    public ShopBlockEntity(BlockPos pos, BlockState state) {

        super(ModBlockEntities.SHOP.get(), pos, state);
        this.merchant = new ShopMerchant(this);
    }

    public boolean isInexhaustible() {

        return this.getBlockState().getBlock() instanceof ShopBlock shop && shop.isInexhaustible();
    }

    public ShopMerchant merchant() {

        return this.merchant;
    }

    /** Whether the owner is editing it or someone is buying, either of which keeps everyone else out. */
    public boolean isBusy() {

        return this.isBeingEdited || this.merchant.getTradingPlayer() != null;
    }

    public void setBeingEdited(boolean isBeingEdited) {

        this.isBeingEdited = isBeingEdited;
    }

    public List<ShopOffer> offers() {

        return List.copyOf(this.offers);
    }

    public long storedValue() {

        return this.storedValue;
    }

    public boolean allowsTransfer() {

        return this.allowsTransfer;
    }

    public boolean isOwner(Player player) {

        return player.getUUID().equals(this.owner);
    }

    public void setOwner(UUID owner) {

        this.owner = owner;
        this.setChanged();
    }

    /** Adds {@code offer}, or replaces the offer for the same stack. False when the shop is full. */
    public boolean putOffer(ShopOffer offer) {

        for (int index = 0; index < this.offers.size(); index++) {
            if (!ItemStack.isSameItemSameComponents(this.offers.get(index).stack(), offer.stack())) continue;
            this.offers.set(index, offer);
            this.offersChanged();
            return true;
        }
        if (this.offers.size() >= MAX_OFFERS) return false;
        this.offers.add(offer);
        this.offersChanged();
        return true;
    }

    public void removeOffer(ItemStack stack) {

        if (this.offers.removeIf(offer -> ItemStack.isSameItemSameComponents(offer.stack(), stack))) this.offersChanged();
    }

    public void toggleTransfer() {

        this.allowsTransfer = !this.allowsTransfer;
        this.setChanged();
    }

    public void addValue(long value) {

        this.storedValue += value;
        this.setChanged();
    }

    /** Empties the earnings and returns what they held. */
    public long takeStoredValue() {

        long value = this.storedValue;
        this.storedValue = 0;
        this.setChanged();
        return value;
    }

    /** The stock itself, which trades take from. */
    public NonNullList<ItemStack> stock() {

        return this.stock;
    }

    private void offersChanged() {

        this.setChanged();
        if (this.level != null) this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), Block.UPDATE_CLIENTS);
    }

    @Override
    public int getContainerSize() {

        return STOCK_SLOT_COUNT;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {

        return this.stock;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {

        this.stock = items;
    }

    @Override
    protected Component getDefaultName() {

        return Component.translatable("gui.numismatic_coins.shop.inventory_title");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {

        return new ShopMenu(containerId, inventory, this);
    }

    @Override
    public boolean stillValid(Player player) {

        return this.isOwner(player) && Container.stillValidBlockEntity(this, player);
    }

    @Override
    public int[] getSlotsForFace(Direction side) {

        return this.allowsTransfer ? ALL_SLOTS : NO_SLOTS;
    }

    @Override
    public boolean canPlaceItemThroughFace(int slot, ItemStack stack, @Nullable Direction side) {

        return this.allowsTransfer;
    }

    @Override
    public boolean canTakeItemThroughFace(int slot, ItemStack stack, Direction side) {

        return false;
    }

    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {

        super.preRemoveSideEffects(pos, state);
        this.merchant.setTradingPlayer(null);
        if (this.level == null) return;
        for (ItemStack coins : CoinStacks.forValue(this.storedValue, Integer.MAX_VALUE)) {
            Containers.dropItemStack(this.level, pos.getX(), pos.getY(), pos.getZ(), coins);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input) {

        super.loadAdditional(input);
        this.stock = NonNullList.withSize(STOCK_SLOT_COUNT, ItemStack.EMPTY);
        ContainerHelper.loadAllItems(input, this.stock);
        this.offers = new ArrayList<>(input.read(OFFERS_KEY, OFFERS_CODEC).orElse(List.of()));
        this.storedValue = input.getLongOr(STORED_VALUE_KEY, 0);
        this.owner = input.read(OWNER_KEY, UUIDUtil.CODEC).orElse(null);
        this.allowsTransfer = input.getBooleanOr(ALLOWS_TRANSFER_KEY, false);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {

        super.saveAdditional(output);
        ContainerHelper.saveAllItems(output, this.stock);
        output.store(OFFERS_KEY, OFFERS_CODEC, this.offers);
        output.putLong(STORED_VALUE_KEY, this.storedValue);
        output.storeNullable(OWNER_KEY, UUIDUtil.CODEC, this.owner);
        output.putBoolean(ALLOWS_TRANSFER_KEY, this.allowsTransfer);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {

        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {

        try (ProblemReporter.ScopedCollector reporter = new ProblemReporter.ScopedCollector(this.problemPath(), NumismaticCoins.LOGGER)) {
            TagValueOutput output = TagValueOutput.createWithContext(reporter, registries);
            output.store(OFFERS_KEY, OFFERS_CODEC, this.offers);
            output.storeNullable(OWNER_KEY, UUIDUtil.CODEC, this.owner);
            return output.buildResult();
        }
    }
}
