package com.joelcranston.numismatic_coins.trade;

import java.util.List;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

/**
 * {@code numismatic_coins:set_random_item}: replaces the item with one picked at random from
 * {@code options} (a tag or a list), keeping the count. Lets one villager trade sell any wool,
 * any bed, and so on. An empty set leaves the item as it is. (NO: SellTagAdapter.)
 */
public class SetRandomItemFunction extends LootItemConditionalFunction {

    public static final MapCodec<SetRandomItemFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            instance -> commonFields(instance)
                    .and(RegistryCodecs.homogeneousList(Registries.ITEM).fieldOf("options").forGetter(function -> function.options))
                    .apply(instance, SetRandomItemFunction::new));

    private final HolderSet<Item> options;

    private SetRandomItemFunction(List<LootItemCondition> predicates, HolderSet<Item> options) {

        super(predicates);
        this.options = options;
    }

    @Override
    public MapCodec<SetRandomItemFunction> codec() {

        return MAP_CODEC;
    }

    @Override
    protected ItemStack run(ItemStack stack, LootContext context) {

        return this.options.getRandomElement(context.getRandom())
                .map(item -> new ItemStack(item, stack.getCount()))
                .orElse(stack);
    }
}
