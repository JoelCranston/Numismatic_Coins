package net.fabricmc.fabric.api.resource.v1;

import net.minecraft.server.packs.repository.PackSource;
import org.slf4j.LoggerFactory;

public interface FabricResource {
   default PackSource getFabricPackSource() {
      LoggerFactory.getLogger(FabricResource.class).error("Unknown Resource implementation {}, returning DEFAULT as the source", this.getClass().getName());
      return PackSource.DEFAULT;
   }
}
