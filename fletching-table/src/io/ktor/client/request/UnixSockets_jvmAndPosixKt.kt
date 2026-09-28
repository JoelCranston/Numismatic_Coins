package io.ktor.client.request

public fun HttpRequestBuilder.unixSocket(path: String) {
   `$this$unixSocket`.setCapability(UnixSocketCapability.INSTANCE, new UnixSocketSettings(path));
}
