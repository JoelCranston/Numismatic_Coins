package io.ktor.http.cio

import io.ktor.http.cio.internals.CharArrayBuilder
import java.io.Closeable

public abstract class HttpMessage : Closeable {
   public final val headers: HttpHeadersMap
   private final val builder: CharArrayBuilder

   open fun HttpMessage(headers: HttpHeadersMap, builder: CharArrayBuilder) {
      this.headers = headers;
      this.builder = builder;
   }

   public fun release() {
      this.builder.release();
      this.headers.release();
   }

   public override fun close() {
      this.release();
   }
}
