package com.joelcranston.numismatic_coins.registry;

import java.util.Set;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.block.PiggyBankBlockEntity;
import com.joelcranston.numismatic_coins.shop.ShopBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntities {

    public static final Supplier<BlockEntityType<PiggyBankBlockEntity>> PIGGY_BANK = NumismaticCoins.xplat().registration()
            .<BlockEntityType<?>, BlockEntityType<PiggyBankBlockEntity>>register(Registries.BLOCK_ENTITY_TYPE, "piggy_bank",
            id -> NumismaticCoins.xplat().blockEntityType(PiggyBankBlockEntity::new, Set.<Block>copyOf(ModBlocks.piggyBanks())));

    public static final Supplier<BlockEntityType<ShopBlockEntity>> SHOP = NumismaticCoins.xplat().registration()
            .<BlockEntityType<?>, BlockEntityType<ShopBlockEntity>>register(Registries.BLOCK_ENTITY_TYPE, "shop",
            id -> NumismaticCoins.xplat().blockEntityType(ShopBlockEntity::new, Set.<Block>of(ModBlocks.SHOP.get(), ModBlocks.INEXHAUSTIBLE_SHOP.get())));

    private ModBlockEntities() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
