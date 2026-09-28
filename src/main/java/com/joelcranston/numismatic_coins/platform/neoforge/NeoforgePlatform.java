package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import com.joelcranston.numismatic_coins.platform.Platform;
import com.joelcranston.numismatic_coins.platform.Registration;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoforgePlatform implements Platform {

    // The mod bus the entry point was given; set before this class is constructed.
    static IEventBus modEventBus;

    private final Registration registration = new NeoforgeRegistration(modEventBus);

    @Override
    public boolean isModLoaded(String modId) {

        return ModList.get().isLoaded(modId);
    }

    @Override
    public ModLoader loader() {

        return ModLoader.NEOFORGE;
    }

    @Override
    public String mcVersion() {

        return "";
    }

    @Override
    public boolean isDevelopmentEnvironment() {

        return !FMLLoader.getCurrent().isProduction();
    }

    @Override
    public Registration registration() {

        return this.registration;
    }

    @Override
    public CreativeModeTab.Builder creativeTabBuilder() {

        return CreativeModeTab.builder();
    }
}
*///?}
