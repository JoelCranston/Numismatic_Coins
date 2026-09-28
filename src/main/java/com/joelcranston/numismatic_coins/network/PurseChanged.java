package com.joelcranston.numismatic_coins.network;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/**
 * Tells a player how much went into or out of their purse; the client shows it where its money
 * message option says. (NO: CurrencyComponent#modify.)
 *
 * @param change positive for a deposit, negative for a withdrawal
 */
public record PurseChanged(long change) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<PurseChanged> TYPE = new CustomPacketPayload.Type<>(NumismaticCoins.id("purse_changed"));

    public static final StreamCodec<ByteBuf, PurseChanged> STREAM_CODEC = ByteBufCodecs.VAR_LONG.map(PurseChanged::new, PurseChanged::change);

    @Override
    public CustomPacketPayload.Type<PurseChanged> type() {

        return TYPE;
    }
}
