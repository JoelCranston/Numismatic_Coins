package com.glisco.numismatic_overhaul.platform.fabric;

//? fabric {

import com.glisco.numismatic_overhaul.NumismaticOverhaul;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ModInitializer;

@Entrypoint("main")
public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		NumismaticOverhaul.onInitialize();
	}
}
//?}
