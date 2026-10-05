package com.joelcranston.numismatic_coins.item;

import net.minecraft.world.inventory.tooltip.TooltipComponent;

/**
 * Coins shown in a tooltip as a coin icon and a count for each denomination held. The client draws
 * it with {@code ClientCoinTooltip}. (NO: CurrencyTooltipData.)
 *
 * @param coinCounts bronze, silver and gold counts, in {@code Currency} order
 */
public record CoinTooltip(long[] coinCounts) implements TooltipComponent {

    /** Whether there is a coin to show. */
    public static boolean hasCoins(long[] coinCounts) {

        for (long count : coinCounts) {
            if (count > 0) return true;
        }
        return false;
    }
}
