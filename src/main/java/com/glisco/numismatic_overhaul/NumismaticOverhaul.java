package com.glisco.numismatic_overhaul;

import com.glisco.numismatic_overhaul.platform.Platform;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import com.glisco.numismatic_overhaul.platform.fabric.FabricPlatform;
//?} neoforge {
/*import com.glisco.numismatic_overhaul.platform.neoforge.NeoforgePlatform;
 *///?}

public class NumismaticOverhaul {

	public static final String MOD_ID = /*$ mod_id*/ "numismatic_overhaul";
	public static final String MOD_VERSION = /*$ mod_version*/ "0.4.0";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/ "Numismatic Overhaul";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} {} on {}", MOD_FRIENDLY_NAME, MOD_VERSION, xplat().loader());
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} client on {}", MOD_FRIENDLY_NAME, xplat().loader());
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
