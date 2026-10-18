package net.fabricmc.fabric.api.creativetab.v1;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.impl.creativetab.CreativeModeTabEventsImpl;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public final class CreativeModeTabEvents {
   public static final Event<CreativeModeTabEvents.ModifyOutputAll> MODIFY_OUTPUT_ALL = EventFactory.createArrayBacked(
      CreativeModeTabEvents.ModifyOutputAll.class, callbacks -> (tab, output) -> {
         for (CreativeModeTabEvents.ModifyOutputAll callback : callbacks) {
            callback.modifyOutput(tab, output);
         }
      }
   );

   private CreativeModeTabEvents() {
   }

   public static Event<CreativeModeTabEvents.ModifyOutput> modifyOutputEvent(ResourceKey<CreativeModeTab> resourceKey) {
      return CreativeModeTabEventsImpl.getOrCreateModifyOutputEvent(resourceKey);
   }

   @FunctionalInterface
   public interface ModifyOutput {
      void modifyOutput(FabricCreativeModeTabOutput var1);
   }

   @FunctionalInterface
   public interface ModifyOutputAll {
      void modifyOutput(CreativeModeTab var1, FabricCreativeModeTabOutput var2);
   }
}
