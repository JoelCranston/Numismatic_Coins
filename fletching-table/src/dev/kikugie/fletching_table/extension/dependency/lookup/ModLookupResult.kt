package dev.kikugie.fletching_table.extension.dependency.lookup

import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion
import kotlin.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class ModLookupResult(timestamp: Instant, limit: Int, query: ModQuery, results: List<ModVersion>) {
   public final val timestamp: Instant
   public final val limit: Int
   public final val query: ModQuery
   public final val results: List<ModVersion>

   init {
      this.timestamp = timestamp;
      this.limit = limit;
      this.query = query;
      this.results = results;
   }

   public operator fun component1(): Instant {
      return this.timestamp;
   }

   public operator fun component2(): Int {
      return this.limit;
   }

   public operator fun component3(): ModQuery {
      return this.query;
   }

   public operator fun component4(): List<ModVersion> {
      return this.results;
   }

   public fun copy(timestamp: Instant = this.timestamp, limit: Int = this.limit, query: ModQuery = this.query, results: List<ModVersion> = this.results): ModLookupResult {
      return new ModLookupResult(timestamp, limit, query, results);
   }

   public override fun toString(): String {
      return "ModLookupResult(timestamp=${this.timestamp}, limit=${this.limit}, query=${this.query}, results=${this.results})";
   }

   public override fun hashCode(): Int {
      return ((this.timestamp.hashCode() * 31 + Integer.hashCode(this.limit)) * 31 + this.query.hashCode()) * 31 + this.results.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is ModLookupResult) {
         return false;
      } else {
         val var2: ModLookupResult = other as ModLookupResult;
         if (!(this.timestamp == (other as ModLookupResult).timestamp)) {
            return false;
         } else if (this.limit != var2.limit) {
            return false;
         } else if (!(this.query == var2.query)) {
            return false;
         } else {
            return this.results == var2.results;
         }
      }
   }

   public companion object {
      public fun serializer(): KSerializer<ModLookupResult> {
         return ModLookupResult.$serializer.INSTANCE;
      }
   }
}
