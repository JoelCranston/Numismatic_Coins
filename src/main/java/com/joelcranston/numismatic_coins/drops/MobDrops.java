package com.joelcranston.numismatic_coins.drops;

import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.item.CoinStacks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Mobs drop coins from one extra loot table, {@code numismatic_coins:entities/mob_coins}, rolled for
 * every mob that drops its own loot. The table's conditions pick which mobs pay and how much, so a
 * data pack can change both; the {@code mobDrops} feature turns it off.
 *
 * <p>Items in {@link #DROPPED_AS_COINS} that any mob's loot table drops, vanilla or modded, come out
 * as coins instead. The "Coin trades" data pack fills the tag with emeralds, so mobs stop dropping a
 * currency villagers no longer take while that pack is on.
 */
public final class MobDrops {

    public static final ResourceKey<LootTable> MOB_COINS_TABLE = ResourceKey.create(Registries.LOOT_TABLE, NumismaticCoins.id("entities/mob_coins"));

    /** Items a mob's loot drops as coins, each worth {@link #DROPPED_AS_COINS_UNIT_VALUE}. */
    public static final TagKey<Item> DROPPED_AS_COINS = TagKey.create(Registries.ITEM, NumismaticCoins.id("dropped_as_coins"));

    /** What a toolsmith pays for one emerald in the "Coin trades" data pack. */
    public static final long DROPPED_AS_COINS_UNIT_VALUE = Currency.SILVER.rawValue(10);

    private MobDrops() {}

    /** Called after a dying mob has dropped its own loot table, on the server. */
    public static void dropCoins(LivingEntity mob, ServerLevel level, DamageSource source, boolean isKilledByPlayer) {

        if (!Configs.isFeatureEnabled("mobDrops")) return;
        mob.dropFromLootTable(level, source, isKilledByPlayer, MOB_COINS_TABLE);
    }

    /**
     * Wraps a mob's loot output so stacks in {@link #DROPPED_AS_COINS} reach it as coins worth the
     * same, and every other stack passes through unchanged.
     */
    public static Consumer<ItemStack> payingTaggedDropsInCoins(Consumer<ItemStack> output) {

        return stack -> {
            if (!stack.is(DROPPED_AS_COINS)) {
                output.accept(stack);
                return;
            }
            long value = DROPPED_AS_COINS_UNIT_VALUE * stack.getCount();
            CoinStacks.forValue(value, MobCoinsLootEntry.MAX_DROPPED_STACKS).forEach(output);
        };
    }
}
