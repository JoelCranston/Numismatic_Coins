package com.joelcranston.numismatic_coins.drops;

import java.util.List;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.config.NumismaticConfig;
import com.joelcranston.numismatic_coins.item.CoinStacks;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

/**
 * {@code numismatic_coins:mob_coins}: the coins a mob drops, worth {@code base_value} spread by the
 * {@code money_mob_drop_variance_percentage} game rule, scaled by the mob's health when the config
 * says so, and by the config's mob drop multiplier. (NO: LivingEntityMixin#injectCoins.)
 */
public class MobCoinsLootEntry extends LootPoolSingletonContainer {

    public static final MapCodec<MobCoinsLootEntry> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(Codec.LONG.fieldOf("base_value").forGetter(entry -> entry.baseValue))
                    .and(singletonFields(instance))
                    .apply(instance, MobCoinsLootEntry::new));

    /** A drop worth more than this many stacks comes as one money bag. (NO: LivingEntityMixin#injectCoins.) */
    public static final int MAX_DROPPED_STACKS = 4;

    // A variance this small or smaller is treated as none.
    private static final float MIN_VARIANCE = 0.02f;
    // The health at which health scaling leaves the value as it is: a player's.
    private static final float REFERENCE_MAX_HEALTH = 20f;

    private final long baseValue;

    private MobCoinsLootEntry(long baseValue, int weight, int quality, List<LootItemCondition> conditions, List<LootItemFunction> functions) {

        super(weight, quality, conditions, functions);
        this.baseValue = baseValue;
    }

    @Override
    public MapCodec<MobCoinsLootEntry> codec() {

        return MAP_CODEC;
    }

    @Override
    protected void createItemStack(Consumer<ItemStack> output, LootContext context) {

        NumismaticConfig.MobDrops settings = Configs.server().mobDrops();
        int variancePercentage = context.getLevel().getGameRules().get(ModGameRules.MONEY_MOB_DROP_VARIANCE_PERCENTAGE.get());
        double factor = varianceFactor(variancePercentage / 100f, context.getRandom()) * settings.multiplier;
        if (settings.scaleOnHealth && context.getOptionalParameter(LootContextParams.THIS_ENTITY) instanceof LivingEntity mob) {
            factor *= mob.getMaxHealth() / (REFERENCE_MAX_HEALTH * settings.healthScaleReduction);
        }
        long value = (long) Math.max(0, this.baseValue * factor);
        CoinStacks.forValue(value, MAX_DROPPED_STACKS).forEach(output);
    }

    private static float varianceFactor(float variance, RandomSource random) {

        if (variance <= MIN_VARIANCE) return 1f;
        return 1f - variance + random.nextFloat() * 2 * variance;
    }
}
