package com.joelcranston.numismatic_coins.shop;

import com.joelcranston.numismatic_coins.registry.ModBlockEntities;
import com.joelcranston.numismatic_coins.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A pawn shop: it buys what its offers name, paying from the money its owner puts in, and keeps
 * what it buys in its stock, which hoppers may empty. (NO: PawnShopBlockEntity.)
 */
public class PawnShopBlockEntity extends AbstractShopBlockEntity {

    public PawnShopBlockEntity(BlockPos pos, BlockState state) {

        super(ModBlockEntities.PAWN_SHOP.get(), pos, state);
    }

    @Override
    protected AbstractShopMerchant createMerchant() {

        return new PawnShopMerchant(this);
    }

    @Override
    protected boolean hoppersTakeStock() {

        return true;
    }

    @Override
    protected Component getDefaultName() {

        return Component.translatable("gui.numismatic_coins.pawn_shop.inventory_title");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {

        return new ShopMenu(ModMenus.PAWN_SHOP.get(), containerId, inventory, this);
    }
}
