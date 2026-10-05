package net.fabricmc.fabric.api.loot.v3;

import java.util.List;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import org.jspecify.annotations.Nullable;

public final class LootTableEvents {
   public static final Event<LootTableEvents.Replace> REPLACE = EventFactory.createArrayBacked(
      LootTableEvents.Replace.class, listeners -> (key, original, source, holder) -> {
         for (LootTableEvents.Replace listener : listeners) {
            LootTable replaced = listener.replaceLootTable(key, original, source, holder);
            if (replaced != null) {
               return replaced;
            }
         }

         return null;
      }
   );
   public static final Event<LootTableEvents.Modify> MODIFY = EventFactory.createArrayBacked(
      LootTableEvents.Modify.class, listeners -> (key, tableBuilder, source, holder) -> {
         for (LootTableEvents.Modify listener : listeners) {
            listener.modifyLootTable(key, tableBuilder, source, holder);
         }
      }
   );
   public static final Event<LootTableEvents.Loaded> ALL_LOADED = EventFactory.createArrayBacked(
      LootTableEvents.Loaded.class, listeners -> (resourceManager, lootManager) -> {
         for (LootTableEvents.Loaded listener : listeners) {
            listener.onLootTablesLoaded(resourceManager, lootManager);
         }
      }
   );
   public static final Event<LootTableEvents.ModifyDrops> MODIFY_DROPS = EventFactory.createArrayBacked(
      LootTableEvents.ModifyDrops.class, listeners -> (holder, context, drops) -> {
         for (LootTableEvents.ModifyDrops listener : listeners) {
            listener.modifyLootTableDrops(holder, context, drops);
         }
      }
   );

   private LootTableEvents() {
   }

   @FunctionalInterface
   public interface Loaded {
      void onLootTablesLoaded(ResourceManager var1, Registry<LootTable> var2);
   }

   @FunctionalInterface
   public interface Modify {
      void modifyLootTable(ResourceKey<LootTable> var1, Builder var2, LootTableSource var3, Provider var4);
   }

   @FunctionalInterface
   public interface ModifyDrops {
      void modifyLootTableDrops(Holder<LootTable> var1, LootContext var2, List<ItemStack> var3);
   }

   @FunctionalInterface
   public interface Replace {
      @Nullable
      LootTable replaceLootTable(ResourceKey<LootTable> var1, LootTable var2, LootTableSource var3, Provider var4);
   }
}
