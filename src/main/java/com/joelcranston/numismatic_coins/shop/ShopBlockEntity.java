package com.joelcranston.numismatic_coins.shop;

import com.joelcranston.numismatic_coins.registry.ModBlockEntities;
import com.joelcranston.numismatic_coins.registry.ModMenus;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A shop: it sells its stock for its offers' prices, keeping the earnings until the owner takes
 * them, and lets hoppers fill the stock. (NO: ShopBlockEntity.)
 */
public class ShopBlockEntity extends AbstractShopBlockEntity {

    public ShopBlockEntity(BlockPos pos, BlockState state) {

        super(ModBlockEntities.SHOP.get(), pos, state);
    }

    @Override
    protected AbstractShopMerchant createMerchant() {

        return new ShopMerchant(this);
    }

    @Override
    protected boolean hoppersTakeStock() {

        return false;
    }

    @Override
    protected Component getDefaultName() {

        return Component.translatable("gui.numismatic_coins.shop.inventory_title");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {

        return new ShopMenu(ModMenus.SHOP.get(), containerId, inventory, this);
    }
}
