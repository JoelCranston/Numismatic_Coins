package com.joelcranston.numismatic_coins.network;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.purse.Purses;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

/**
 * Sent by the purse widget. The server trusts nothing in it: withdrawals are clamped to the
 * balance. (NO: RequestPurseActionC2SPacket.)
 */
public record RequestPurseAction(Action action, long amount) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<RequestPurseAction> TYPE = new CustomPacketPayload.Type<>(NumismaticCoins.id("request_purse_action"));

    public static final StreamCodec<ByteBuf, RequestPurseAction> STREAM_CODEC = StreamCodec.composite(
            Action.STREAM_CODEC, RequestPurseAction::action,
            ByteBufCodecs.VAR_LONG, RequestPurseAction::amount,
            RequestPurseAction::new);

    public static RequestPurseAction storeAll() {

        return new RequestPurseAction(Action.STORE_ALL, 0);
    }

    public static RequestPurseAction withdraw(long amount) {

        return new RequestPurseAction(Action.WITHDRAW, amount);
    }

    public static RequestPurseAction withdrawAll() {

        return new RequestPurseAction(Action.WITHDRAW_ALL, 0);
    }

    @Override
    public CustomPacketPayload.Type<RequestPurseAction> type() {

        return TYPE;
    }

    /** Runs on the server thread. */
    public static void handle(RequestPurseAction request, ServerPlayer player) {

        switch (request.action()) {
            case STORE_ALL -> Purses.storeAll(player);
            case WITHDRAW -> Purses.withdraw(player, request.amount());
            case WITHDRAW_ALL -> Purses.withdraw(player, Purses.balance(player));
        }
    }

    public enum Action {
        STORE_ALL, WITHDRAW, WITHDRAW_ALL;

        // An unknown id fails decoding, which disconnects the client that sent it.
        static final StreamCodec<ByteBuf, Action> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(Action::byId, Action::ordinal);

        private static Action byId(int id) {

            if (id < 0 || id >= values().length) throw new DecoderException("Unknown purse action: " + id);
            return values()[id];
        }
    }
}
