package io.ktor.client.request.forms

import kotlinx.io.Source

public class InputProvider(size: Long? = null, block: () -> Source) {
   public final val size: Long?
   public final val block: () -> Source

   init {
      this.size = size;
      this.block = block;
   }
}
