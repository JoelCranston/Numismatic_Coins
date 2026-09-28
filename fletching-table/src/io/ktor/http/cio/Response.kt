package io.ktor.http.cio

import io.ktor.http.cio.internals.CharArrayBuilder

public class Response internal constructor(version: CharSequence, status: Int, statusText: CharSequence, headers: HttpHeadersMap, builder: CharArrayBuilder) : HttpMessage(
      headers, builder
   ) {
   public final val version: CharSequence
   public final val status: Int
   public final val statusText: CharSequence

   init {
      this.version = version;
      this.status = status;
      this.statusText = statusText;
   }
}
