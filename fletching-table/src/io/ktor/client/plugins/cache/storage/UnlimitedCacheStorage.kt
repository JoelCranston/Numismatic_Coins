package io.ktor.client.plugins.cache.storage

import io.ktor.client.plugins.cache.HttpCacheEntry
import io.ktor.http.Url
import io.ktor.util.collections.ConcurrentMap
import io.ktor.util.collections.ConcurrentSetKt
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUnlimitedCacheStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnlimitedCacheStorage.kt\nio/ktor/client/plugins/cache/storage/UnlimitedCacheStorage\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,64:1\n168#2,3:65\n*S KotlinDebug\n*F\n+ 1 UnlimitedCacheStorage.kt\nio/ktor/client/plugins/cache/storage/UnlimitedCacheStorage\n*L\n26#1:65,3\n*E\n"])
internal class UnlimitedCacheStorage : HttpCacheStorage {
   private final val store: ConcurrentMap<Url, MutableSet<HttpCacheEntry>> = new ConcurrentMap(0, 1, null)

   public override fun store(url: Url, value: HttpCacheEntry) {
      val data: java.util.Set = this.store.computeIfAbsent(url, UnlimitedCacheStorage::store$lambda$0);
      if (!data.add(value)) {
         data.remove(value);
         data.add(value);
      }
   }

   public override fun find(url: Url, varyKeys: Map<String, String>): HttpCacheEntry? {
      val var5: java.util.Iterator = this.store.computeIfAbsent(url, UnlimitedCacheStorage::find$lambda$0).iterator();

      var var17: Any;
      while (true) {
         if (!var5.hasNext()) {
            var17 = null;
            break;
         }

         val var6: Any = var5.next();
         val it: HttpCacheEntry = var6 as HttpCacheEntry;
         var var10000: Boolean;
         if (varyKeys.isEmpty()) {
            var10000 = true;
         } else {
            val var11: java.util.Iterator = varyKeys.entrySet().iterator();

            while (true) {
               if (!var11.hasNext()) {
                  var10000 = true;
                  break;
               }

               val `element$iv`: Entry = var11.next() as Entry;
               if (!(it.getVaryKeys().get(`element$iv`.getKey() as java.lang.String) == `element$iv`.getValue() as java.lang.String)) {
                  var10000 = false;
                  break;
               }
            }
         }

         if (var10000) {
            var17 = var6;
            break;
         }
      }

      return var17 as HttpCacheEntry;
   }

   public override fun findByUrl(url: Url): Set<HttpCacheEntry> {
      var var10000: java.util.Set = this.store.get(url);
      if (var10000 == null) {
         var10000 = SetsKt.emptySet();
      }

      return var10000;
   }

   @JvmStatic
   fun `store$lambda$0`(): java.util.Set {
      return ConcurrentSetKt.ConcurrentSet();
   }

   @JvmStatic
   fun `find$lambda$0`(): java.util.Set {
      return ConcurrentSetKt.ConcurrentSet();
   }
}
