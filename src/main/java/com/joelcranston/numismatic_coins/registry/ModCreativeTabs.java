package com.joelcranston.numismatic_coins.registry;

import java.util.function.Supplier;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.item.MoneyBagItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public final class ModCreativeTabs {

    /** The raw value of the example money bag the tab shows: one of each coin. */
    private static final long EXAMPLE_BAG_VALUE = 10_101;

    public static final Supplier<CreativeModeTab> MAIN = NumismaticCoins.xplat().registration().register(
            Registries.CREATIVE_MODE_TAB, "main",
            id -> NumismaticCoins.xplat().creativeTabBuilder()
                    .title(Component.translatable("itemGroup.numismatic_coins.main"))
                    .icon(() -> new ItemStack(ModItems.GOLD_COIN.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BRONZE_COIN.get());
                        output.accept(ModItems.SILVER_COIN.get());
                        output.accept(ModItems.GOLD_COIN.get());
                        output.accept(MoneyBagItem.withValue(EXAMPLE_BAG_VALUE));
                        output.accept(ModBlocks.SHOP.get());
                        output.accept(ModBlocks.PAWN_SHOP.get());
                        output.accept(ModBlocks.INEXHAUSTIBLE_SHOP.get());
                        output.accept(ModBlocks.INEXHAUSTIBLE_PAWN_SHOP.get());
                        ModBlocks.piggyBanks().forEach(output::accept);
                    })
                    .build());

    private ModCreativeTabs() {}

    /** Loads the class, which registers its entries. */
    public static void register() {

    }
}
