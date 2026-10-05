package com.joelcranston.numismatic_coins.trade;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;

/** The loot functions coin trades use in their {@code given_item_modifiers}. */
public final class ModLootFunctions {

    public static final Supplier<MapCodec<EnchantmentPriceFunction>> ENCHANTMENT_PRICE = NumismaticCoins.xplat().registration()
            .<MapCodec<? extends LootItemFunction>, MapCodec<EnchantmentPriceFunction>>register(
                    Registries.LOOT_FUNCTION_TYPE, "enchantment_price", id -> EnchantmentPriceFunction.MAP_CODEC);
    public static final Supplier<MapCodec<SetRandomItemFunction>> SET_RANDOM_ITEM = NumismaticCoins.xplat().registration()
            .<MapCodec<? extends LootItemFunction>, MapCodec<SetRandomItemFunction>>register(
                    Registries.LOOT_FUNCTION_TYPE, "set_random_item", id -> SetRandomItemFunction.MAP_CODEC);

    private ModLootFunctions() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
