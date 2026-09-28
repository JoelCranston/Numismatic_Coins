package com.joelcranston.numismatic_coins.item;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import net.minecraft.world.item.ItemStack;

/** An item that holds money: a coin stack or a money bag. */
public interface CurrencyItem {

    /** The coins the stack holds, as bronze, silver and gold counts. */
    long[] coinCounts(ItemStack stack);

    /** The stack's raw value in bronze. */
    default long rawValue(ItemStack stack) {

        return CoinMath.combine(coinCounts(stack));
    }

    static boolean isCurrency(ItemStack stack) {

        return stack.getItem() instanceof CurrencyItem;
    }
}
