package io.ktor.utils.io.core

import kotlinx.io.Source

public final val endOfInput: Boolean
   public final get() {
      return `$this$endOfInput`.exhausted();
   }


public fun Source.readAvailable(buffer: ByteArray, offset: Int = 0, length: Int = buffer.length - offset): Int {
   val result: Int = `$this$readAvailable`.readAtMostTo(buffer, offset, offset + length);
   return if (result == -1) 0 else result;
}

@JvmSynthetic
fun `readAvailable$default`(var0: Source, var1: ByteArray, var2: Int, var3: Int, var4: Int, var5: Any): Int {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length - var2;
   }

   return readAvailable(var0, var1, var2, var3);
}
