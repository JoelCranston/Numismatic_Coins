package io.ktor.client.call

import io.ktor.http.HttpMethod

internal fun checkContentLength(contentLength: Long?, bodySize: Long, method: HttpMethod) {
   if (contentLength != null && contentLength >= 0L && !(method == HttpMethod.Companion.getHead())) {
      if (contentLength != bodySize) {
         throw new IllegalStateException(("Content-Length mismatch: expected ${contentLength} bytes, but received $bodySize bytes").toString());
      }
   }
}
