package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import com.joelcranston.numismatic_coins.platform.Platform;
import com.joelcranston.numismatic_coins.platform.Registration;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.item.CreativeModeTab;

public class FabricPlatform implements Platform {

    private final Registration registration = new FabricRegistration();

    @Override
    public boolean isModLoaded(String modId) {

        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public ModLoader loader() {

        return ModLoader.FABRIC;
    }

    @Override
    public String mcVersion() {

        return FabricLoader.getInstance().getRawGameVersion();
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public Registration registration() {

        return this.registration;
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {

        return FabricCreativeModeTab.builder();
    }
}
//?}
