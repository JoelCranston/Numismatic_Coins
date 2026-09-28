package com.joelcranston.numismatic_coins.client;

import com.joelcranston.numismatic_coins.NumismaticCoins;
import com.joelcranston.numismatic_coins.platform.ClientPlatform;

//? fabric {
import com.joelcranston.numismatic_coins.platform.fabric.FabricClientPlatform;
//?} neoforge {
/*import com.joelcranston.numismatic_coins.platform.neoforge.NeoforgeClientPlatform;
 *///?}

/** Client-only setup. Nothing here may be loaded on a dedicated server. */
public final class NumismaticCoinsClient {

    private static final ClientPlatform CLIENT_PLATFORM = createClientPlatformInstance();

    private NumismaticCoinsClient() {}

    public static void onInitializeClient() {

        NumismaticCoins.LOGGER.info("Initializing {} client on {}", NumismaticCoins.MOD_FRIENDLY_NAME, NumismaticCoins.xplat().loader());
        PurseWidget.register();
    }

    public static ClientPlatform clientXplat() {

        return CLIENT_PLATFORM;
    }

    private static ClientPlatform createClientPlatformInstance() {

        //? fabric {
        return new FabricClientPlatform();
        //?} neoforge {
        /*return new NeoforgeClientPlatform();
         *///?}
    }
}
