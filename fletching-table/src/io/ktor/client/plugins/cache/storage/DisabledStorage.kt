package io.ktor.client.plugins.cache.storage

import io.ktor.http.Url

internal object DisabledStorage : CacheStorage {
   public override suspend fun store(url: Url, data: CachedResponseData) {
      return Unit.INSTANCE;
   }

   public override suspend fun find(url: Url, varyKeys: Map<String, String>): CachedResponseData? {
      return null;
   }

   public override suspend fun findAll(url: Url): Set<CachedResponseData> {
      return SetsKt.emptySet();
   }

   public override suspend fun remove(url: Url, varyKeys: Map<String, String>) {
      return Unit.INSTANCE;
   }

   public override suspend fun removeAll(url: Url) {
      return Unit.INSTANCE;
   }
}
