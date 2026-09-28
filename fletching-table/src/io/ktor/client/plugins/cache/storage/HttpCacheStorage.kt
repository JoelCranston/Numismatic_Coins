package io.ktor.client.plugins.cache.storage

import io.ktor.client.plugins.cache.HttpCacheEntry
import io.ktor.http.Url

/** @deprecated */
@Deprecated(message = "Use new [CacheStorage] instead.", level = DeprecationLevel.ERROR)
public abstract class HttpCacheStorage {
   public abstract fun store(url: Url, value: HttpCacheEntry) {
   }

   public abstract fun find(url: Url, varyKeys: Map<String, String>): HttpCacheEntry? {
   }

   public abstract fun findByUrl(url: Url): Set<HttpCacheEntry> {
   }

   @JvmStatic
   fun `Unlimited$lambda$0`(): UnlimitedCacheStorage {
      return new UnlimitedCacheStorage();
   }

   public companion object {
      public final val Unlimited: () -> HttpCacheStorage
      public final val Disabled: HttpCacheStorage
   }
}
