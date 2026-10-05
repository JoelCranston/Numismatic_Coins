package com.joelcranston.numismatic_coins.drops;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Mobs drop coins from one extra loot table, {@code numismatic_coins:entities/mob_coins}, rolled for
 * every mob that drops its own loot. The table's conditions pick which mobs pay and how much, so a
 * data pack can change both; the {@code mobDrops} feature turns it off.
 */
public final class MobDrops {

    public static final ResourceKey<LootTable> MOB_COINS_TABLE = ResourceKey.create(Registries.LOOT_TABLE, NumismaticCoins.id("entities/mob_coins"));

    private MobDrops() {}

    /** Called after a dying mob has dropped its own loot table, on the server. */
    public static void dropCoins(LivingEntity mob, ServerLevel level, DamageSource source, boolean isKilledByPlayer) {

        if (!Configs.isFeatureEnabled("mobDrops")) return;
        mob.dropFromLootTable(level, source, isKilledByPlayer, MOB_COINS_TABLE);
    }
}
