package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import com.joelcranston.numismatic_coins.client.ConfigScreen;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

// Mod Menu is optional; this class is only loaded when it is installed.
public class FabricModMenuEntrypoint implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {

        return ConfigScreen::new;
    }
}
//?}
