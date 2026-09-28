package net.fabricmc.fabric.api.creativetab.v1;

import net.fabricmc.fabric.impl.creativetab.FabricCreativeModeTabBuilderImpl;
import net.minecraft.world.item.CreativeModeTab.Builder;

public final class FabricCreativeModeTab {
   private FabricCreativeModeTab() {
   }

   public static Builder builder() {
      return new FabricCreativeModeTabBuilderImpl();
   }
}
