package com.joelcranston.numismatic_coins.item;

import com.joelcranston.numismatic_coins.currency.CoinMath;
import com.joelcranston.numismatic_coins.currency.Currency;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * The coins inside a money bag, per denomination. Counts rather than one raw value, so a bag gives
 * back the coins that went in. (NO: MoneyBagComponent.)
 */
public record MoneyBagContents(long bronze, long silver, long gold) {

    public static final MoneyBagContents EMPTY = new MoneyBagContents(0, 0, 0);

    /** Texture tiers the money bag's item model selects between. */
    public static final int SMALL_TIER = 0, MEDIUM_TIER = 1, LARGE_TIER = 2;

    private static final Codec<Long> COIN_COUNT_CODEC = Codec.LONG.validate(count -> count >= 0
            ? DataResult.success(count)
            : DataResult.error(() -> "Negative coin count: " + count));

    public static final Codec<MoneyBagContents> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            COIN_COUNT_CODEC.optionalFieldOf("bronze", 0L).forGetter(MoneyBagContents::bronze),
            COIN_COUNT_CODEC.optionalFieldOf("silver", 0L).forGetter(MoneyBagContents::silver),
            COIN_COUNT_CODEC.optionalFieldOf("gold", 0L).forGetter(MoneyBagContents::gold)
    ).apply(instance, MoneyBagContents::new));

    public static final StreamCodec<ByteBuf, MoneyBagContents> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG, MoneyBagContents::bronze,
            ByteBufCodecs.VAR_LONG, MoneyBagContents::silver,
            ByteBufCodecs.VAR_LONG, MoneyBagContents::gold,
            MoneyBagContents::new);

    /** Contents from bronze, silver and gold counts. */
    public static MoneyBagContents of(long[] coinCounts) {

        return new MoneyBagContents(coinCounts[0], coinCounts[1], coinCounts[2]);
    }

    /** The fewest coins that make up {@code rawValue}. */
    public static MoneyBagContents ofValue(long rawValue) {

        return of(CoinMath.split(rawValue));
    }

    /** A fresh array of the bronze, silver and gold counts; callers may change it. */
    public long[] coins() {

        return new long[]{this.bronze, this.silver, this.gold};
    }

    public long rawValue() {

        return CoinMath.combine(this.bronze, this.silver, this.gold);
    }

    /** Small below a silver coin's worth, medium below a gold coin's, large from there. (NO: the "size" predicate.) */
    public int sizeTier() {

        long rawValue = rawValue();
        if (rawValue >= Currency.GOLD.unitValue()) return LARGE_TIER;
        if (rawValue >= Currency.SILVER.unitValue()) return MEDIUM_TIER;
        return SMALL_TIER;
    }
}
