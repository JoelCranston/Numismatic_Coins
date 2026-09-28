package com.joelcranston.numismatic_coins.platform.neoforge;

//? neoforge {

/*import com.joelcranston.numismatic_coins.NumismaticCoins;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod (NumismaticCoins.MOD_ID)
public class NeoforgeEntrypoint {

    public NeoforgeEntrypoint(IEventBus modEventBus, ModContainer modContainer, Dist dist) {

        // Before anything touches NumismaticCoins, whose platform instance registers on this bus.
        NeoforgePlatform.modEventBus = modEventBus;
        NumismaticCoins.onInitialize();
        // Client setup runs here rather than in FMLClientSetupEvent, as client payload handlers must be in before registration ends.
        if (dist.isClient()) NeoforgeClientEntrypoint.onInitializeClient(modContainer);
    }
}
*///?}
