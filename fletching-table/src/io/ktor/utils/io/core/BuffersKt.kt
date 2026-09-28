package io.ktor.utils.io.core

import kotlinx.io.Buffer
import kotlinx.io.SourcesKt

public fun Buffer.readBytes(count: Int = (int)`$this$readBytes`.getSize()): ByteArray {
   return SourcesKt.readByteArray(`$this$readBytes`, count);
}

@JvmSynthetic
fun `readBytes$default`(var0: Buffer, var1: Int, var2: Int, var3: Any): ByteArray {
   if ((var2 and 1) != 0) {
      var1 = (int)var0.getSize();
   }

   return readBytes(var0, var1);
}

internal fun Buffer.isEmpty(): Boolean {
   return `$this$isEmpty`.getSize() == 0L;
}
