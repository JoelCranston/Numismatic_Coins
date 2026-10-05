package com.joelcranston.numismatic_coins.drops;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.GameRule;
import net.minecraft.world.level.gamerules.GameRuleCategory;
import net.minecraft.world.level.gamerules.GameRuleType;
import net.minecraft.world.level.gamerules.GameRuleTypeVisitor;

/** The mod's game rules, set per world with {@code /gamerule}. */
public final class ModGameRules {

    private static final int MAX_PERCENTAGE = 100;

    /** The share of the purse, in percent, a player drops as coins on death. (NO: moneyDropPercentage.) */
    public static final Supplier<GameRule<Integer>> MONEY_DROP_PERCENTAGE = registerPercentage(
            "money_drop_percentage", GameRuleCategory.PLAYER, 10);

    /**
     * How far, in percent either way, a mob's coin drop strays from its base value; 2 or less
     * means no spread. (NO: moneyMobDropVariancePercentage.)
     */
    public static final Supplier<GameRule<Integer>> MONEY_MOB_DROP_VARIANCE_PERCENTAGE = registerPercentage(
            "money_mob_drop_variance_percentage", GameRuleCategory.MOBS, 50);

    private ModGameRules() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }

    private static Supplier<GameRule<Integer>> registerPercentage(String name, GameRuleCategory category, int defaultValue) {

        return NumismaticCoins.xplat().registration().<GameRule<?>, GameRule<Integer>>register(Registries.GAME_RULE, name,
                id -> new GameRule<>(category, GameRuleType.INT, IntegerArgumentType.integer(0, MAX_PERCENTAGE),
                        GameRuleTypeVisitor::visitInteger, Codec.intRange(0, MAX_PERCENTAGE), value -> value, defaultValue, FeatureFlagSet.of()));
    }
}
