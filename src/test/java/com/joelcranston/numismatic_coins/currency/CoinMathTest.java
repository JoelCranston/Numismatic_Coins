package com.joelcranston.numismatic_coins.currency;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CoinMathTest {

    @Test
    void splitsIntoFewestCoins() {

        assertArrayEquals(new long[]{0, 0, 0}, CoinMath.split(0));
        assertArrayEquals(new long[]{99, 0, 0}, CoinMath.split(99));
        assertArrayEquals(new long[]{0, 1, 0}, CoinMath.split(100));
        assertArrayEquals(new long[]{45, 23, 1}, CoinMath.split(12_345));
        assertArrayEquals(new long[]{0, 0, 250}, CoinMath.split(2_500_000));
    }

    @Test
    void splitThenCombineRoundTrips() {

        for (long value : new long[]{0, 1, 99, 100, 101, 9_999, 10_000, 10_001, 123_456_789L, Long.MAX_VALUE / 2}) {
            assertEquals(value, CoinMath.combine(CoinMath.split(value)), "value " + value);
        }
    }

    @Test
    void combineCountsEachDenomination() {

        assertEquals(10_203, CoinMath.combine(3, 2, 1));
        assertEquals(250, CoinMath.combine(new long[]{150, 1, 0}));
    }

    @Test
    void rejectsBadInput() {

        assertThrows(IllegalArgumentException.class, () -> CoinMath.split(-1));
        assertThrows(IllegalArgumentException.class, () -> CoinMath.combine(new long[]{1, 2}));
    }

    @Test
    void compactnessIsPerDenomination() {

        assertTrue(CoinMath.isCompact(new long[]{99, 99, 99}));
        assertFalse(CoinMath.isCompact(new long[]{100, 0, 0}));
        assertFalse(CoinMath.isCompact(new long[]{0, 0, 100}));
    }

    @Test
    void stacksAreLargestFirstAndCapped() {

        assertEquals(List.of(), CoinMath.toStacks(0, 99));
        assertEquals(
            List.of(new CoinMath.CoinStack(Currency.GOLD, 1), new CoinMath.CoinStack(Currency.SILVER, 23), new CoinMath.CoinStack(Currency.BRONZE, 45)),
            CoinMath.toStacks(12_345, 99));
        assertEquals(
            List.of(new CoinMath.CoinStack(Currency.GOLD, 99), new CoinMath.CoinStack(Currency.GOLD, 51)),
            CoinMath.toStacks(1_500_000, 99));
        assertEquals(
            List.of(new CoinMath.CoinStack(Currency.BRONZE, 99), new CoinMath.CoinStack(Currency.BRONZE, 51)),
            CoinMath.toStacks(new long[]{150, 0, 0}, 99));
    }
}
