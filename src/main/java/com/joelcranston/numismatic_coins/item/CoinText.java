package com.joelcranston.numismatic_coins.item;

import com.joelcranston.numismatic_coins.currency.Currency;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;

/** Player-facing text for amounts of money. */
public final class CoinText {

    private CoinText() {}

    /** The style a coin's name and amount are drawn in. */
    public static Style style(Currency currency) {

        return Style.EMPTY.withColor(currency.nameColor());
    }

    /**
     * Coin counts as "1 Gold 23 Silver 45 Bronze", largest coin first, each in its coin's colour.
     * Denominations with no coins are left out; no coins at all reads "Empty".
     */
    public static Component coinCounts(long[] coinCounts) {

        MutableComponent text = Component.empty();
        boolean isFirstPart = true;
        for (int denomination = coinCounts.length - 1; denomination >= 0; denomination--) {
            if (coinCounts[denomination] <= 0) continue;
            Currency currency = Currency.values()[denomination];
            if (!isFirstPart) text.append(" ");
            text.append(Component.literal(Long.toString(coinCounts[denomination])).withStyle(style(currency)))
                    .append(" ")
                    .append(Component.translatable(currency.translationKey()).withStyle(style(currency)));
            isFirstPart = false;
        }
        if (isFirstPart) {
            return Component.translatable("numismatic_coins.empty").withStyle(ChatFormatting.GRAY);
        }
        return text;
    }
}
