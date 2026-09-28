package com.joelcranston.numismatic_coins.drops;

import java.util.Map;
import java.util.Optional;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

/**
 * Puts money bags and gold coins in structure chests. Each chest table gets one pool that rolls
 * one of the mod's own {@code numismatic_coins:chests/*} tables, so the chances and amounts are
 * data a data pack can change. Needs a reload to follow the {@code chestLoot} feature, as loot
 * tables are read then. (NO: NumismaticOverhaul#onInitialize.)
 */
public final class ChestLoot {

    private static final Map<ResourceKey<LootTable>, ResourceKey<LootTable>> COIN_TABLES = Map.of(
            BuiltInLootTables.DESERT_PYRAMID, coinTable("desert_pyramid"),
            BuiltInLootTables.SIMPLE_DUNGEON, coinTable("dungeon"),
            BuiltInLootTables.ABANDONED_MINESHAFT, coinTable("dungeon"),
            BuiltInLootTables.BASTION_TREASURE, coinTable("structure"),
            BuiltInLootTables.STRONGHOLD_CORRIDOR, coinTable("structure"),
            BuiltInLootTables.PILLAGER_OUTPOST, coinTable("structure"),
            BuiltInLootTables.BURIED_TREASURE, coinTable("structure"),
            BuiltInLootTables.STRONGHOLD_LIBRARY, coinTable("stronghold_library"));

    private ChestLoot() {}

    public static void register() {

        NumismaticCoins.xplat().addLootPools(ChestLoot::coinPoolFor);
    }

    private static Optional<LootPool.Builder> coinPoolFor(ResourceKey<LootTable> table) {

        ResourceKey<LootTable> coinTable = COIN_TABLES.get(table);
        if (coinTable == null || !Configs.isFeatureEnabled("chestLoot")) return Optional.empty();
        return Optional.of(LootPool.lootPool().add(NestedLootTable.lootTableReference(coinTable)));
    }

    private static ResourceKey<LootTable> coinTable(String name) {

        return ResourceKey.create(Registries.LOOT_TABLE, NumismaticCoins.id("chests/" + name));
    }
}
