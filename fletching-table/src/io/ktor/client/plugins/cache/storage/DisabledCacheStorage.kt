package io.ktor.client.plugins.cache.storage

import io.ktor.client.plugins.cache.HttpCacheEntry
import io.ktor.http.Url

internal object DisabledCacheStorage : HttpCacheStorage {
   public override fun store(url: Url, value: HttpCacheEntry) {
   }

   public override fun find(url: Url, varyKeys: Map<String, String>): HttpCacheEntry? {
      return null;
   }

   public override fun findByUrl(url: Url): Set<HttpCacheEntry> {
      return SetsKt.emptySet();
   }
}
