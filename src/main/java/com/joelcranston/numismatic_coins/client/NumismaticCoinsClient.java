package com.joelcranston.numismatic_coins.client;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.network.PurseChanged;
import com.joelcranston.numismatic_coins.network.SyncServerConfig;
import com.joelcranston.numismatic_coins.platform.ClientPlatform;
import com.joelcranston.numismatic_coins.registry.ModMenus;

/** Client-only setup. Nothing here may be loaded on a dedicated server. */
public final class NumismaticCoinsClient {

    private static final ClientPlatform CLIENT_PLATFORM = ClientPlatform.create();

    private NumismaticCoinsClient() {}

    public static void onInitializeClient() {

        NumismaticCoins.LOGGER.info("Initializing {} client on {}", NumismaticCoins.MOD_FRIENDLY_NAME, NumismaticCoins.xplat().loader());
        clientXplat().registerClientboundHandler(SyncServerConfig.TYPE, payload -> Configs.receiveFromServer(payload.settings()));
        clientXplat().registerClientboundHandler(PurseChanged.TYPE, MoneyMessages::show);
        PurseWidget.register();
        clientXplat().registerMenuScreen(ModMenus.PIGGY_BANK, PiggyBankScreen::new);
    }

    public static ClientPlatform clientXplat() {

        return CLIENT_PLATFORM;
    }
}
