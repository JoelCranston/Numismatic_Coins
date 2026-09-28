package io.ktor.util.cio

import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.jvm.javaio.BlockingKt
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.Writer
import java.nio.charset.Charset

public fun ByteWriteChannel.bufferedWriter(charset: Charset = Charsets.UTF_8): BufferedWriter {
   val var2: Writer = new OutputStreamWriter(BlockingKt.toOutputStream(`$this$bufferedWriter`), charset);
   return if (var2 is BufferedWriter) var2 as BufferedWriter else new BufferedWriter(var2, 8192);
}

@JvmSynthetic
fun `bufferedWriter$default`(var0: ByteWriteChannel, var1: Charset, var2: Int, var3: Any): BufferedWriter {
   if ((var2 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return bufferedWriter(var0, var1);
}

public fun ByteWriteChannel.writer(charset: Charset = Charsets.UTF_8): Writer {
   return new OutputStreamWriter(BlockingKt.toOutputStream(`$this$writer`), charset);
}

@JvmSynthetic
fun `writer$default`(var0: ByteWriteChannel, var1: Charset, var2: Int, var3: Any): Writer {
   if ((var2 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return writer(var0, var1);
}
