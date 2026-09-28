package io.ktor.http

public fun HeadersBuilder.etag(entityTag: String) {
   `$this$etag`.set(HttpHeaders.INSTANCE.getETag(), entityTag);
}
