package net.fabricmc.fabric.api.resource.v1;

import java.util.function.Function;
import net.fabricmc.fabric.impl.resource.DataResourceLoaderImpl;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ServerAdvancementManager;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.PreparableReloadListener.StateKey;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.ApiStatus.NonExtendable;

@NonExtendable
public interface DataResourceLoader extends ResourceLoader {
   StateKey<RecipeManager> RECIPE_MANAGER_KEY = new StateKey();
   StateKey<ServerAdvancementManager> ADVANCEMENT_LOADER_KEY = new StateKey();
   StateKey<DataResourceStore.Mutable> DATA_RESOURCE_STORE_KEY = new StateKey();

   static DataResourceLoader get() {
      return DataResourceLoaderImpl.INSTANCE;
   }

   void registerReloadListener(Identifier var1, Function<Provider, PreparableReloadListener> var2);
}
