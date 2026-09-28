package io.ktor.client.plugins.cache.storage

import io.ktor.http.Url

public interface CacheStorage {
   public abstract suspend fun store(url: Url, data: CachedResponseData) {
   }

   public abstract suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
   }

   public abstract suspend fun findAll(url: Url): Set<CachedResponseData> {
   }

   public abstract suspend fun remove(url: Url, varyKeys: Map<String, String>) {
   }

   public abstract suspend fun removeAll(url: Url) {
   }

   public companion object {
      public final val Unlimited: () -> CacheStorage = CacheStorage.Companion::Unlimited$lambda$0
      public final val Disabled: CacheStorage = DisabledStorage.INSTANCE as CacheStorage

      @JvmStatic
      fun `Unlimited$lambda$0`(): UnlimitedStorage {
         return new UnlimitedStorage();
      }
   }
}
