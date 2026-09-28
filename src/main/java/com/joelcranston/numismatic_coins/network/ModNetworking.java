package com.joelcranston.numismatic_coins.network;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.platform.Platform;

public final class ModNetworking {

    private ModNetworking() {}

    public static void register() {

        Platform platform = NumismaticCoins.xplat();
        platform.registerServerboundPayload(RequestPurseAction.TYPE, RequestPurseAction.STREAM_CODEC, RequestPurseAction::handle);
        platform.registerClientboundPayload(SyncServerConfig.TYPE, SyncServerConfig.STREAM_CODEC);
        platform.registerClientboundPayload(PurseChanged.TYPE, PurseChanged.STREAM_CODEC);
        platform.onPlayerJoin(player -> platform.sendToPlayer(player, new SyncServerConfig(Configs.server())));
    }
}
