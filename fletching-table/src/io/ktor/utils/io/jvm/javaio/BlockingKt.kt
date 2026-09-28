package io.ktor.utils.io.jvm.javaio

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.jvm.javaio.BlockingKt.toInputStream.1
import java.io.InputStream
import java.io.OutputStream
import kotlinx.coroutines.Job

public fun ByteReadChannel.toInputStream(parent: Job? = null): InputStream {
   return new 1(`$this$toInputStream`);
}

@JvmSynthetic
fun `toInputStream$default`(var0: ByteReadChannel, var1: Job, var2: Int, var3: Any): InputStream {
   if ((var2 and 1) != 0) {
      var1 = null;
   }

   return toInputStream(var0, var1);
}

public fun ByteWriteChannel.toOutputStream(): OutputStream {
   return new io.ktor.utils.io.jvm.javaio.BlockingKt.toOutputStream.1(`$this$toOutputStream`);
}
