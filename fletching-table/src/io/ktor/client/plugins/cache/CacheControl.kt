package io.ktor.client.plugins.cache

import io.ktor.http.HeaderValue

internal object CacheControl {
   internal final val NO_STORE: HeaderValue = new HeaderValue("no-store", null, 2, null)
   internal final val NO_CACHE: HeaderValue = new HeaderValue("no-cache", null, 2, null)
   internal final val PRIVATE: HeaderValue = new HeaderValue("private", null, 2, null)
   internal final val ONLY_IF_CACHED: HeaderValue = new HeaderValue("only-if-cached", null, 2, null)
   internal final val MUST_REVALIDATE: HeaderValue = new HeaderValue("must-revalidate", null, 2, null)
}
