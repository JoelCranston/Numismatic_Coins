package io.ktor.http.content

import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder

public interface Version {
   public abstract fun check(requestHeaders: Headers): VersionCheckResult {
   }

   public abstract fun appendHeadersTo(builder: HeadersBuilder) {
   }
}
