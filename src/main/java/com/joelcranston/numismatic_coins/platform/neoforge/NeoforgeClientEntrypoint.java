package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import com.joelcranston.numismatic_coins.client.ConfigScreen;
import com.joelcranston.numismatic_coins.client.NumismaticCoinsClient;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// Kept apart from NeoforgeEntrypoint so a dedicated server never loads client classes.
final class NeoforgeClientEntrypoint {

    private NeoforgeClientEntrypoint() {}

    static void onInitializeClient(ModContainer modContainer) {

        NumismaticCoinsClient.onInitializeClient();
        modContainer.registerExtensionPoint(IConfigScreenFactory.class, (container, parent) -> new ConfigScreen(parent));
    }
}
*///?}
