package net.fabricmc.fabric.api.resource.v1;

import net.fabricmc.fabric.api.resource.v1.pack.PackActivationType;
import net.fabricmc.fabric.impl.resource.ResourceLoaderImpl;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.PreparableReloadListener.StateKey;
import net.minecraft.world.flag.FeatureFlagSet;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@NonExtendable
public interface ResourceLoader {
   StateKey<Provider> REGISTRY_LOOKUP_KEY = new StateKey();
   StateKey<FeatureFlagSet> FEATURE_FLAG_SET_KEY = new StateKey();

   static ResourceLoader get(PackType type) {
      return ResourceLoaderImpl.get(type);
   }

   void registerReloadListener(Identifier var1, PreparableReloadListener var2);

   void addListenerOrdering(Identifier var1, Identifier var2);

   static boolean registerBuiltinPack(Identifier id, ModContainer container, PackActivationType activationType) {
      return ResourceLoaderImpl.registerBuiltinPack(id, "resourcepacks/" + id.getPath(), container, activationType);
   }

   static boolean registerBuiltinPack(Identifier id, ModContainer container, Component displayName, PackActivationType activationType) {
      return ResourceLoaderImpl.registerBuiltinPack(id, "resourcepacks/" + id.getPath(), container, displayName, activationType);
   }
}
