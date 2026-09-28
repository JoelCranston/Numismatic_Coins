package com.joelcranston.numismatic_coins.currency;

import java.util.Locale;

/** The three coin denominations, each worth a hundred of the one below. (NO: Currency.) */
public enum Currency {

    BRONZE(1, 0xae5b3c),
    SILVER(100, 0x617174),
    GOLD(10_000, 0xbd9838);

    /** How many of one coin make one of the next. */
    public static final int EXCHANGE_RATE = 100;

    private final long unitValue;
    private final int nameColor;

    Currency(long unitValue, int nameColor) {

        this.unitValue = unitValue;
        this.nameColor = nameColor;
    }

    /** The value of one coin of this kind in bronze, the unit every raw value is counted in. */
    public long unitValue() {

        return this.unitValue;
    }

    public long rawValue(long amount) {

        return amount * this.unitValue;
    }

    public int nameColor() {

        return this.nameColor;
    }

    public String translationKey() {

        return "currency.numismatic_coins." + name().toLowerCase(Locale.ROOT);
    }
}
