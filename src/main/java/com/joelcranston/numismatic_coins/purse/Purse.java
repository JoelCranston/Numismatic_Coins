package com.joelcranston.numismatic_coins.purse;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

/**
 * The money a player carries outside their inventory, as one raw value in bronze. It survives
 * death and is only ever changed on the server, which syncs it to its owner. (NO: CurrencyComponent.)
 */
public record Purse(long balance) {

    public static final Purse EMPTY = new Purse(0);

    private static final Codec<Long> BALANCE_CODEC = Codec.LONG.validate(balance -> balance >= 0
            ? DataResult.success(balance)
            : DataResult.error(() -> "Negative purse balance: " + balance));

    public static final MapCodec<Purse> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            BALANCE_CODEC.optionalFieldOf("balance", 0L).forGetter(Purse::balance)
    ).apply(instance, Purse::new));

    public static final Codec<Purse> CODEC = MAP_CODEC.codec();

    public static final StreamCodec<ByteBuf, Purse> STREAM_CODEC = ByteBufCodecs.VAR_LONG.map(Purse::new, Purse::balance);

    public Purse {

        if (balance < 0) throw new IllegalArgumentException("Negative purse balance: " + balance);
    }
}
