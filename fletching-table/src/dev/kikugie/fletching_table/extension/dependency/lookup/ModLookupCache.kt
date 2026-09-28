package dev.kikugie.fletching_table.extension.dependency.lookup

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public data class ModLookupCache(curseforgeIds: Map<String, Int>, lookupEntries: List<ModLookupResult>) {
   @SerialName("curseforge_mappings")
   public final val curseforgeIds: Map<String, Int>

   @SerialName("lookup_entries")
   public final val lookupEntries: List<ModLookupResult>

   init {
      this.curseforgeIds = curseforgeIds;
      this.lookupEntries = lookupEntries;
   }

   public operator fun component1(): Map<String, Int> {
      return this.curseforgeIds;
   }

   public operator fun component2(): List<ModLookupResult> {
      return this.lookupEntries;
   }

   public fun copy(curseforgeIds: Map<String, Int> = this.curseforgeIds, lookupEntries: List<ModLookupResult> = this.lookupEntries): ModLookupCache {
      return new ModLookupCache(curseforgeIds, lookupEntries);
   }

   public override fun toString(): String {
      return "ModLookupCache(curseforgeIds=${this.curseforgeIds}, lookupEntries=${this.lookupEntries})";
   }

   public override fun hashCode(): Int {
      return this.curseforgeIds.hashCode() * 31 + this.lookupEntries.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ModLookupCache) {
         return false;
      } else {
         val var2: ModLookupCache = other as ModLookupCache;
         if (!(this.curseforgeIds == (other as ModLookupCache).curseforgeIds)) {
            return false;
         } else {
            return this.lookupEntries == var2.lookupEntries;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<ModLookupCache> {
         return ModLookupCache.$serializer.INSTANCE;
      }
   }
}
