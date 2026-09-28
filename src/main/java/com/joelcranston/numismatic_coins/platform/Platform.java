package com.joelcranston.numismatic_coins.platform;

import net.minecraft.world.item.CreativeModeTab;

public interface Platform {

    boolean isModLoaded(String modId);

    ModLoader loader();

    String mcVersion();

    boolean isDevelopmentEnvironment();

    default boolean isDebug() {

        return isDevelopmentEnvironment();
    }

    Registration registration();

    /** A creative tab builder the loader places among its own tab pages. */
    CreativeModeTab.Builder creativeTabBuilder();

    enum ModLoader {
        FABRIC, NEOFORGE, FORGE, QUILT
    }
}
