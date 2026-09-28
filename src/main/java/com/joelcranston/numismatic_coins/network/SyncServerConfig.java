package com.joelcranston.numismatic_coins.network;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.config.NumismaticConfig;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

/** The server's features and mob drop settings, sent to each player as they join. */
public record SyncServerConfig(NumismaticConfig.ServerSettings settings) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SyncServerConfig> TYPE = new CustomPacketPayload.Type<>(NumismaticCoins.id("sync_server_config"));

    // Sent as the same JSON the file holds, so a new option needs no codec change.
    public static final StreamCodec<ByteBuf, SyncServerConfig> STREAM_CODEC = ByteBufCodecs.STRING_UTF8.map(
            json -> new SyncServerConfig(Configs.GSON.fromJson(json, NumismaticConfig.ServerSettings.class)),
            payload -> Configs.GSON.toJson(payload.settings()));

    @Override
    public CustomPacketPayload.Type<SyncServerConfig> type() {

        return TYPE;
    }
}
