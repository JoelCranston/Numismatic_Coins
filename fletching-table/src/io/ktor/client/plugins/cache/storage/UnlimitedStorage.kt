package io.ktor.client.plugins.cache.storage

import io.ktor.http.Url
import io.ktor.util.collections.ConcurrentMap
import io.ktor.util.collections.ConcurrentSetKt
import java.util.Map.Entry
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nUnlimitedCacheStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnlimitedCacheStorage.kt\nio/ktor/client/plugins/cache/storage/UnlimitedStorage\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,64:1\n168#2,3:65\n168#2,3:68\n*S KotlinDebug\n*F\n+ 1 UnlimitedCacheStorage.kt\nio/ktor/client/plugins/cache/storage/UnlimitedStorage\n*L\n48#1:65,3\n56#1:68,3\n*E\n"])
internal class UnlimitedStorage : CacheStorage {
   private final val store: ConcurrentMap<Url, MutableSet<CachedResponseData>> = new ConcurrentMap(0, 1, null)

   public override suspend fun store(url: Url, data: CachedResponseData) {
      val cache: java.util.Set = this.store.computeIfAbsent(url, UnlimitedStorage::store$lambda$0);
      if (!cache.add(data)) {
         cache.remove(data);
         cache.add(data);
      }

      return Unit.INSTANCE;
   }

   public override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
      val var6: java.util.Iterator = this.store.computeIfAbsent(url, UnlimitedStorage::find$lambda$0).iterator();

      var var18: Any;
      while (true) {
         if (!var6.hasNext()) {
            var18 = null;
            break;
         }

         val var7: Any = var6.next();
         val it: CachedResponseData = var7 as CachedResponseData;
         var var10000: Boolean;
         if (varyKeys.isEmpty()) {
            var10000 = true;
         } else {
            val var12: java.util.Iterator = varyKeys.entrySet().iterator();

            while (true) {
               if (!var12.hasNext()) {
                  var10000 = true;
                  break;
               }

               val `element$iv`: Entry = var12.next() as Entry;
               if (!(it.getVaryKeys().get(`element$iv`.getKey() as java.lang.String) == `element$iv`.getValue() as java.lang.String)) {
                  var10000 = false;
                  break;
               }
            }
         }

         if (var10000) {
            var18 = var7;
            break;
         }
      }

      return var18;
   }

   public override suspend fun findAll(url: Url): Set<CachedResponseData> {
      var var10000: java.util.Set = this.store.get(url);
      if (var10000 == null) {
         var10000 = SetsKt.emptySet();
      }

      return var10000;
   }

   public override suspend fun remove(url: Url, varyKeys: Map<String, String>) {
      val var10000: java.util.Set = this.store.get(url);
      if (var10000 != null) {
         Boxing.boxBoolean(CollectionsKt.removeAll(var10000, UnlimitedStorage::remove$lambda$0));
      }

      return Unit.INSTANCE;
   }

   public override suspend fun removeAll(url: Url) {
      this.store.remove(url);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `store$lambda$0`(): java.util.Set {
      return ConcurrentSetKt.ConcurrentSet();
   }

   @JvmStatic
   fun `find$lambda$0`(): java.util.Set {
      return ConcurrentSetKt.ConcurrentSet();
   }

   @JvmStatic
   fun `remove$lambda$0`(`$varyKeys`: java.util.Map, entry: CachedResponseData): Boolean {
      var var10000: Boolean;
      if (`$varyKeys`.isEmpty()) {
         var10000 = true;
      } else {
         val var4: java.util.Iterator = `$varyKeys`.entrySet().iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = true;
               break;
            }

            val `element$iv`: Entry = var4.next() as Entry;
            if (!(entry.getVaryKeys().get(`element$iv`.getKey() as java.lang.String) == `element$iv`.getValue() as java.lang.String)) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000 && `$varyKeys`.size() == entry.getVaryKeys().size();
   }
}
