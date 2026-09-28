package com.joelcranston.numismatic_coins.network;

import com.joelcranston.numismatic_coins.NumismaticCoins;

public final class ModNetworking {

    private ModNetworking() {}

    public static void register() {

        NumismaticCoins.xplat().registerServerboundPayload(RequestPurseAction.TYPE, RequestPurseAction.STREAM_CODEC, RequestPurseAction::handle);
    }
}
