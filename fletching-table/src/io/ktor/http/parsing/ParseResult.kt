package io.ktor.http.parsing

internal class ParseResult(mapping: Map<String, List<String>>) {
   private final val mapping: Map<String, List<String>>

   init {
      this.mapping = mapping;
   }

   public operator fun get(key: String): String? {
      val var10000: java.util.List = this.mapping.get(key);
      return if (var10000 != null) CollectionsKt.firstOrNull(var10000) else null;
   }

   public fun getAll(key: String): List<String> {
      var var10000: java.util.List = this.mapping.get(key);
      if (var10000 == null) {
         var10000 = CollectionsKt.emptyList();
      }

      return var10000;
   }

   public fun contains(key: String): Boolean {
      return this.mapping.containsKey(key);
   }
}
