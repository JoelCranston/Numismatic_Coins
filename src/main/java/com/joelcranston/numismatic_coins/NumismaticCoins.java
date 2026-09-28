package com.joelcranston.numismatic_coins;

import com.joelcranston.numismatic_coins.command.ModCommands;
import com.joelcranston.numismatic_coins.config.Configs;
import com.joelcranston.numismatic_coins.network.ModNetworking;
import com.joelcranston.numismatic_coins.platform.Platform;
import com.joelcranston.numismatic_coins.registry.ModCreativeTabs;
import com.joelcranston.numismatic_coins.registry.ModDataComponents;
import com.joelcranston.numismatic_coins.registry.ModItems;
import com.joelcranston.numismatic_coins.trade.CoinTrades;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import com.joelcranston.numismatic_coins.platform.fabric.FabricPlatform;
//?} neoforge {
/*import com.joelcranston.numismatic_coins.platform.neoforge.NeoforgePlatform;
 *///?}

public class NumismaticCoins {

    public static final String MOD_ID = /*$ mod_id*/ "numismatic_coins";
    public static final String MOD_VERSION = /*$ mod_version*/ "0.1.0";
    public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Numismatic Coins";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    private static final Platform PLATFORM = createPlatformInstance();

    public static void onInitialize() {

        LOGGER.info("Initializing {} {} on {}", MOD_FRIENDLY_NAME, MOD_VERSION, xplat().loader());
        Configs.load();
        // In dependency order: Fabric registers each entry as its class loads.
        ModDataComponents.register();
        ModItems.register();
        ModCreativeTabs.register();
        xplat().purseStorage();
        xplat().registerFeatureCondition();
        ModNetworking.register();
        ModCommands.register();
        CoinTrades.register();
    }

    public static Platform xplat() {

        return PLATFORM;
    }

    private static Platform createPlatformInstance() {

        //? fabric {
        return new FabricPlatform();
        //?} neoforge {
        /*return new NeoforgePlatform();
         *///?}
    }

    public static Identifier id(String path) {

        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
