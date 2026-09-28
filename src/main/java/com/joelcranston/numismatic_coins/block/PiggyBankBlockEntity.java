package com.joelcranston.numismatic_coins.block;

import com.joelcranston.numismatic_coins.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/** A piggy bank's coins: one slot each for bronze, silver and gold. (NO: PiggyBankBlockEntity.) */
public class PiggyBankBlockEntity extends RandomizableContainerBlockEntity {

    private NonNullList<ItemStack> items = NonNullList.withSize(PiggyBankMenu.COIN_SLOT_COUNT, ItemStack.EMPTY);

    public PiggyBankBlockEntity(BlockPos pos, BlockState state) {

        super(ModBlockEntities.PIGGY_BANK.get(), pos, state);
    }

    @Override
    public int getContainerSize() {

        return PiggyBankMenu.COIN_SLOT_COUNT;
    }

    @Override
    public boolean canPlaceItem(int slot, ItemStack stack) {

        return PiggyBankMenu.acceptsCoin(slot, stack);
    }

    @Override
    protected Component getDefaultName() {

        return Component.translatable("container.numismatic_coins.piggy_bank");
    }

    @Override
    public NonNullList<ItemStack> getItems() {

        return this.items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {

        this.items = items;
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {

        return new PiggyBankMenu(containerId, inventory, this);
    }

    @Override
    protected void loadAdditional(ValueInput input) {

        super.loadAdditional(input);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(input)) ContainerHelper.loadAllItems(input, this.items);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {

        super.saveAdditional(output);
        if (!this.trySaveLootTable(output)) ContainerHelper.saveAllItems(output, this.items);
    }
}
