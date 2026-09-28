package io.ktor.client.request.forms

import io.ktor.utils.io.ByteReadChannel

public class ChannelProvider(size: Long? = null, block: () -> ByteReadChannel) {
   public final val size: Long?
   public final val block: () -> ByteReadChannel

   init {
      this.size = size;
      this.block = block;
   }
}
