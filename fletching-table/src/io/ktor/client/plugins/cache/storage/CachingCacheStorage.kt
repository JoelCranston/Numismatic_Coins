package io.ktor.client.plugins.cache.storage

import io.ktor.client.plugins.cache.storage.CachingCacheStorage.store.1
import io.ktor.http.Url
import io.ktor.util.collections.ConcurrentMap
import java.util.Map.Entry
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nFileCacheStorage.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FileCacheStorage.kt\nio/ktor/client/plugins/cache/storage/CachingCacheStorage\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,262:1\n168#2,3:263\n*S KotlinDebug\n*F\n+ 1 FileCacheStorage.kt\nio/ktor/client/plugins/cache/storage/CachingCacheStorage\n*L\n53#1:263,3\n*E\n"])
internal class CachingCacheStorage(delegate: CacheStorage) : CacheStorage {
   private final val delegate: CacheStorage
   private final val store: ConcurrentMap<Url, Set<CachedResponseData>>

   init {
      this.delegate = delegate;
      this.store = new ConcurrentMap<>(0, 1, null);
   }

   public override suspend fun store(url: Url, data: CachedResponseData) {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      var var4: java.util.Map;
      var var5: Url;
      var var10000: Any;
      label22: {
         val `$result`: Any = `$continuation`.result;
         val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               var10000 = this.delegate;
               `$continuation`.L$0 = url;
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(data);
               `$continuation`.label = 1;
               if (((CacheStorage)var10000).store(url, data, `$continuation`) === var9) {
                  return var9;
               }
               break;
            case 1:
               data = `$continuation`.L$1 as CachedResponseData;
               url = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               break;
            case 2:
               var5 = `$continuation`.L$3 as Url;
               var4 = `$continuation`.L$2 as java.util.Map;
               data = `$continuation`.L$1 as CachedResponseData;
               url = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label22;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var10000 = this.store;
         var5 = url;
         var4 = (java.util.Map)var10000;
         val var14: CacheStorage = this.delegate;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(url);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(data);
         `$continuation`.L$2 = var10000;
         `$continuation`.L$3 = url;
         `$continuation`.label = 2;
         var10000 = var14.findAll(url, `$continuation`);
         if (var10000 === var9) {
            return var9;
         }
      }

      var4.put(var5, var10000);
      return Unit.INSTANCE;
   }

   public override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
      var `$continuation`: Continuation;
      label58: {
         if (`$completion` is io.ktor.client.plugins.cache.storage.CachingCacheStorage.find.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.find.1;
            if (((`$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.find.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label58;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cache.storage.CachingCacheStorage.find.1(this, `$completion`);
      }

      label52: {
         val `$result`: Any = `$continuation`.result;
         val var23: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var18: java.util.Map;
         var var19: Url;
         var var10000: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               if (this.store.containsKey(url)) {
                  break label52;
               }

               var10000 = this.store;
               var19 = url;
               var18 = (java.util.Map)var10000;
               val var25: CacheStorage = this.delegate;
               `$continuation`.L$0 = url;
               `$continuation`.L$1 = varyKeys;
               `$continuation`.L$2 = var10000;
               `$continuation`.L$3 = url;
               `$continuation`.label = 1;
               var10000 = var25.findAll(url, `$continuation`);
               if (var10000 === var23) {
                  return var23;
               }
               break;
            case 1:
               var19 = `$continuation`.L$3 as Url;
               var18 = `$continuation`.L$2 as java.util.Map;
               varyKeys = `$continuation`.L$1 as java.util.Map;
               url = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var18.put(var19, var10000);
      }

      val var6: java.util.Iterator = MapsKt.getValue(this.store, url).iterator();

      var var27: Any;
      while (true) {
         if (!var6.hasNext()) {
            var27 = null;
            break;
         }

         val var7: Any = var6.next();
         val it: CachedResponseData = var7 as CachedResponseData;
         var var26: Boolean;
         if (varyKeys.isEmpty()) {
            var26 = true;
         } else {
            val var12: java.util.Iterator = varyKeys.entrySet().iterator();

            while (true) {
               if (!var12.hasNext()) {
                  var26 = true;
                  break;
               }

               val `element$iv`: Entry = var12.next() as Entry;
               if (!(it.getVaryKeys().get(`element$iv`.getKey() as java.lang.String) == `element$iv`.getValue() as java.lang.String)) {
                  var26 = false;
                  break;
               }
            }
         }

         if (var26) {
            var27 = var7;
            break;
         }
      }

      return var27;
   }

   public override suspend fun findAll(url: Url): Set<CachedResponseData> {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is io.ktor.client.plugins.cache.storage.CachingCacheStorage.findAll.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.findAll.1;
            if (((`$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.findAll.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cache.storage.CachingCacheStorage.findAll.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var3: java.util.Map;
      var var4: Url;
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (this.store.containsKey(url)) {
               return MapsKt.getValue(this.store, url);
            }

            var10000 = this.store;
            var4 = url;
            var3 = (java.util.Map)var10000;
            val var10: CacheStorage = this.delegate;
            `$continuation`.L$0 = url;
            `$continuation`.L$1 = var10000;
            `$continuation`.L$2 = url;
            `$continuation`.label = 1;
            var10000 = var10.findAll(url, `$continuation`);
            if (var10000 === var8) {
               return var8;
            }
            break;
         case 1:
            var4 = `$continuation`.L$2 as Url;
            var3 = `$continuation`.L$1 as java.util.Map;
            url = `$continuation`.L$0 as Url;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var3.put(var4, var10000);
      return MapsKt.getValue(this.store, url);
   }

   public override suspend fun remove(url: Url, varyKeys: Map<String, String>) {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is io.ktor.client.plugins.cache.storage.CachingCacheStorage.remove.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.remove.1;
            if (((`$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.remove.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cache.storage.CachingCacheStorage.remove.1(this, `$completion`);
      }

      var var4: java.util.Map;
      var var5: Url;
      var var10000: Any;
      label22: {
         val `$result`: Any = `$continuation`.result;
         val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               var10000 = this.delegate;
               `$continuation`.L$0 = url;
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(varyKeys);
               `$continuation`.label = 1;
               if (((CacheStorage)var10000).remove(url, varyKeys, `$continuation`) === var9) {
                  return var9;
               }
               break;
            case 1:
               varyKeys = `$continuation`.L$1 as java.util.Map;
               url = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               break;
            case 2:
               var5 = `$continuation`.L$3 as Url;
               var4 = `$continuation`.L$2 as java.util.Map;
               varyKeys = `$continuation`.L$1 as java.util.Map;
               url = `$continuation`.L$0 as Url;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break label22;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var10000 = this.store;
         var5 = url;
         var4 = (java.util.Map)var10000;
         val var14: CacheStorage = this.delegate;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(url);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(varyKeys);
         `$continuation`.L$2 = var10000;
         `$continuation`.L$3 = url;
         `$continuation`.label = 2;
         var10000 = var14.findAll(url, `$continuation`);
         if (var10000 === var9) {
            return var9;
         }
      }

      var4.put(var5, var10000);
      return Unit.INSTANCE;
   }

   public override suspend fun removeAll(url: Url) {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is io.ktor.client.plugins.cache.storage.CachingCacheStorage.removeAll.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.removeAll.1;
            if (((`$completion` as io.ktor.client.plugins.cache.storage.CachingCacheStorage.removeAll.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cache.storage.CachingCacheStorage.removeAll.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10000: CacheStorage = this.delegate;
            `$continuation`.L$0 = url;
            `$continuation`.label = 1;
            if (var10000.removeAll(url, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            url = `$continuation`.L$0 as Url;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      this.store.remove(url);
      return Unit.INSTANCE;
   }
}
