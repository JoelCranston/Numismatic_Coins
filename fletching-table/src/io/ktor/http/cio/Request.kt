package io.ktor.http.cio

import io.ktor.http.HttpMethod
import io.ktor.http.cio.internals.CharArrayBuilder

public class Request internal constructor(method: HttpMethod, uri: CharSequence, version: CharSequence, headers: HttpHeadersMap, builder: CharArrayBuilder) : HttpMessage(
      headers, builder
   ) {
   public final val method: HttpMethod
   public final val uri: CharSequence
   public final val version: CharSequence

   init {
      this.method = method;
      this.uri = uri;
      this.version = version;
   }
}
