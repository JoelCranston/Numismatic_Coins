package com.glisco.numismatic_overhaul.platform.fabric;

//? fabric {

import com.glisco.numismatic_overhaul.NumismaticOverhaul;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		NumismaticOverhaul.onInitializeClient();
	}

}
//?}
