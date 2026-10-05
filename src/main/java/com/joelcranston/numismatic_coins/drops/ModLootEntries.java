package com.joelcranston.numismatic_coins.drops;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;

/** The loot pool entry types the mod's loot tables use. */
public final class ModLootEntries {

    public static final Supplier<MapCodec<MoneyBagLootEntry>> MONEY_BAG = NumismaticCoins.xplat().registration()
            .<MapCodec<? extends LootPoolEntryContainer>, MapCodec<MoneyBagLootEntry>>register(
                    Registries.LOOT_POOL_ENTRY_TYPE, "money_bag", id -> MoneyBagLootEntry.MAP_CODEC);
    public static final Supplier<MapCodec<MobCoinsLootEntry>> MOB_COINS = NumismaticCoins.xplat().registration()
            .<MapCodec<? extends LootPoolEntryContainer>, MapCodec<MobCoinsLootEntry>>register(
                    Registries.LOOT_POOL_ENTRY_TYPE, "mob_coins", id -> MobCoinsLootEntry.MAP_CODEC);

    private ModLootEntries() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
