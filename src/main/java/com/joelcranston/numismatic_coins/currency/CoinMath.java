package com.joelcranston.numismatic_coins.currency;

import java.util.ArrayList;
import java.util.List;

/**
 * Pure arithmetic on raw values and coin counts, kept free of game classes so it can be unit
 * tested. Coin counts are always {@code long[3]} in {@link Currency} order: bronze, silver, gold.
 * (NO: CurrencyResolver, CurrencyConverter.)
 */
public final class CoinMath {

    private CoinMath() {}

    /** Splits a raw value into the fewest coins, e.g. 12345 into {45, 23, 1}. */
    public static long[] split(long rawValue) {

        if (rawValue < 0) throw new IllegalArgumentException("Negative value: " + rawValue);
        long gold = rawValue / Currency.GOLD.unitValue();
        rawValue -= gold * Currency.GOLD.unitValue();
        long silver = rawValue / Currency.SILVER.unitValue();
        rawValue -= silver * Currency.SILVER.unitValue();
        return new long[]{rawValue, silver, gold};
    }

    /** The raw value of a set of coin counts. */
    public static long combine(long[] coins) {

        if (coins.length != 3) throw new IllegalArgumentException("Expected 3 coin counts, got " + coins.length);
        return combine(coins[0], coins[1], coins[2]);
    }

    public static long combine(long bronze, long silver, long gold) {

        return Currency.BRONZE.rawValue(bronze) + Currency.SILVER.rawValue(silver) + Currency.GOLD.rawValue(gold);
    }

    public static long[] add(long[] first, long[] second) {

        return new long[]{first[0] + second[0], first[1] + second[1], first[2] + second[2]};
    }

    /** True when every count is below the exchange rate, so the coins are already in fewest form. */
    public static boolean isCompact(long[] coins) {

        for (long count : coins) {
            if (count >= Currency.EXCHANGE_RATE) return false;
        }
        return true;
    }

    /**
     * A raw value as (currency, count) stacks, largest coin first, each no bigger than
     * {@code maxStackSize}. Zero yields an empty list.
     */
    public static List<CoinStack> toStacks(long rawValue, int maxStackSize) {

        return toStacks(split(rawValue), maxStackSize);
    }

    /** Coin counts as stacks, largest coin first, each no bigger than {@code maxStackSize}. */
    public static List<CoinStack> toStacks(long[] coins, int maxStackSize) {

        List<CoinStack> stacks = new ArrayList<>();
        for (int denomination = coins.length - 1; denomination >= 0; denomination--) {
            long remaining = coins[denomination];
            while (remaining > 0) {
                int count = (int) Math.min(remaining, maxStackSize);
                stacks.add(new CoinStack(Currency.values()[denomination], count));
                remaining -= count;
            }
        }
        return stacks;
    }

    /** One stack's worth of a single coin kind. */
    public record CoinStack(Currency currency, int count) {

        public long rawValue() {

            return this.currency.rawValue(this.count);
        }
    }
}
