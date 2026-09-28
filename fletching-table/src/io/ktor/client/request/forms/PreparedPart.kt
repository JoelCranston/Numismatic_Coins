package io.ktor.client.request.forms

import io.ktor.utils.io.ByteReadChannel
import kotlinx.io.Source

private sealed class PreparedPart protected constructor(headers: ByteArray, size: Long?) {
   public final val headers: ByteArray
   public final val size: Long?

   init {
      this.headers = headers;
      this.size = size;
   }

   public class ChannelPart(headers: ByteArray, provider: () -> ByteReadChannel, size: Long?) : PreparedPart(headers, size) {
      public final val provider: () -> ByteReadChannel

      init {
         this.provider = provider;
      }
   }

   public class InputPart(headers: ByteArray, provider: () -> Source, size: Long?) : PreparedPart(headers, size) {
      public final val provider: () -> Source

      init {
         this.provider = provider;
      }
   }
}
