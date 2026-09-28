package com.joelcranston.numismatic_coins.drops;

import java.util.List;
import java.util.function.Consumer;

import com.joelcranston.numismatic_coins.item.MoneyBagItem;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

/**
 * {@code numismatic_coins:money_bag}: a money bag holding a raw value picked evenly between
 * {@code min} and {@code max}, both included. Rolling zero gives nothing. (NO: MoneyBagLootEntry.)
 */
public class MoneyBagLootEntry extends LootPoolSingletonContainer {

    public static final MapCodec<MoneyBagLootEntry> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Codec.LONG.optionalFieldOf("min", 0L).forGetter(entry -> entry.minValue),
                            Codec.LONG.fieldOf("max").forGetter(entry -> entry.maxValue))
                    .and(singletonFields(instance))
                    .apply(instance, MoneyBagLootEntry::new));

    private final long minValue;
    private final long maxValue;

    private MoneyBagLootEntry(long minValue, long maxValue, int weight, int quality, List<LootItemCondition> conditions, List<LootItemFunction> functions) {

        super(weight, quality, conditions, functions);
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    @Override
    public MapCodec<MoneyBagLootEntry> codec() {

        return MAP_CODEC;
    }

    @Override
    protected void createItemStack(Consumer<ItemStack> output, LootContext context) {

        long spread = Math.max(0, this.maxValue - this.minValue);
        long value = this.minValue + (spread == 0 ? 0 : Math.floorMod(context.getRandom().nextLong(), spread + 1));
        if (value > 0) output.accept(MoneyBagItem.withValue(value));
    }
}
