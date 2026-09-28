package com.joelcranston.numismatic_coins.platform.fabric;

//? fabric {

import com.joelcranston.numismatic_coins.NumismaticCoins;
import dev.kikugie.fletching_table.annotation.fabric.Entrypoint;
import net.fabricmc.api.ClientModInitializer;

@Entrypoint("client")
public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		NumismaticCoins.onInitializeClient();
	}

}
//?}
