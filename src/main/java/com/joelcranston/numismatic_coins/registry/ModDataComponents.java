package com.joelcranston.numismatic_coins.registry;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.item.MoneyBagContents;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

public final class ModDataComponents {

    /** The coins inside a money bag. */
    public static final Supplier<DataComponentType<MoneyBagContents>> MONEY_BAG = NumismaticCoins.xplat().registration().register(
            Registries.DATA_COMPONENT_TYPE, "money_bag",
            id -> DataComponentType.<MoneyBagContents>builder()
                    .persistent(MoneyBagContents.CODEC)
                    .networkSynchronized(MoneyBagContents.STREAM_CODEC)
                    .build());

    private ModDataComponents() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
