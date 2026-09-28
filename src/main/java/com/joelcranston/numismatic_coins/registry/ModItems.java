package com.joelcranston.numismatic_coins.registry;

import java.util.function.Function;
import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.currency.Currency;
import com.joelcranston.numismatic_coins.item.CoinItem;
import com.joelcranston.numismatic_coins.item.MoneyBagItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public final class ModItems {

    /** A coin stack holds up to 99; the money bag exists for anything larger. (NO: CoinItem.) */
    public static final int COIN_STACK_SIZE = 99;

    public static final Supplier<CoinItem> BRONZE_COIN = registerCoin("bronze_coin", Currency.BRONZE);
    public static final Supplier<CoinItem> SILVER_COIN = registerCoin("silver_coin", Currency.SILVER);
    public static final Supplier<CoinItem> GOLD_COIN = registerCoin("gold_coin", Currency.GOLD);
    public static final Supplier<MoneyBagItem> MONEY_BAG = register("money_bag", properties -> new MoneyBagItem(properties.stacksTo(1)));

    private ModItems() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }

    public static CoinItem coin(Currency currency) {

        return switch (currency) {
            case BRONZE -> BRONZE_COIN.get();
            case SILVER -> SILVER_COIN.get();
            case GOLD -> GOLD_COIN.get();
        };
    }

    private static Supplier<CoinItem> registerCoin(String name, Currency currency) {

        return register(name, properties -> new CoinItem(currency, properties.stacksTo(COIN_STACK_SIZE)));
    }

    private static <T extends Item> Supplier<T> register(String name, Function<Item.Properties, T> itemFactory) {

        return NumismaticCoins.xplat().registration().register(Registries.ITEM, name,
                (Identifier id) -> itemFactory.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, id))));
    }
}
