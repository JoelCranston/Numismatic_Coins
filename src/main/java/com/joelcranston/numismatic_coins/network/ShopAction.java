package com.joelcranston.numismatic_coins.network;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.shop.ShopMenu;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

/**
 * A button press on the owner's shop or pawn shop screen. It acts only on the shop menu the player has open,
 * which checks the value itself. (NO: ShopScreenHandlerRequestC2SPacket,
 * PawnShopScreenHandlerRequestC2SPacket.)
 *
 * @param value the offer's index for {@link Action#LOAD_OFFER}, the price for {@link Action#CREATE_OFFER}
 */
public record ShopAction(Action action, long value) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<ShopAction> TYPE = new CustomPacketPayload.Type<>(NumismaticCoins.id("shop_action"));

    public static final StreamCodec<ByteBuf, ShopAction> STREAM_CODEC = StreamCodec.composite(
            Action.STREAM_CODEC, ShopAction::action,
            ByteBufCodecs.VAR_LONG, ShopAction::value,
            ShopAction::new);

    public ShopAction(Action action) {

        this(action, 0);
    }

    @Override
    public CustomPacketPayload.Type<ShopAction> type() {

        return TYPE;
    }

    /** Runs on the server thread. */
    public static void handle(ShopAction request, ServerPlayer player) {

        if (!(player.containerMenu instanceof ShopMenu menu) || !menu.stillValid(player)) return;
        menu.handle(request.action(), request.value());
    }

    public enum Action {
        LOAD_OFFER, CREATE_OFFER, DELETE_OFFER, INSERT_CURRENCY, EXTRACT_CURRENCY, TOGGLE_TRANSFER, CLICK_BUFFER;

        // An unknown id fails decoding, which disconnects the client that sent it.
        static final StreamCodec<ByteBuf, Action> STREAM_CODEC = ByteBufCodecs.VAR_INT.map(Action::byId, Action::ordinal);

        private static Action byId(int id) {

            if (id < 0 || id >= values().length) throw new DecoderException("Unknown shop action: " + id);
            return values()[id];
        }
    }
}
