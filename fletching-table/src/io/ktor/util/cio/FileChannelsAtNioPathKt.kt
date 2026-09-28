package io.ktor.util.cio

import io.ktor.util.cio.FileChannelsAtNioPathKt.readChannel.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import java.nio.file.Files
import java.nio.file.Path
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.Dispatchers

public fun Path.readChannel(start: Long = 0L, endInclusive: Long = -1L, coroutineContext: CoroutineContext = Dispatchers.getIO() as CoroutineContext): ByteReadChannel {
   return ByteWriteChannelOperationsKt.writer(
         CoroutineScopeKt.CoroutineScope(coroutineContext),
         new CoroutineName("file-reader").plus(coroutineContext),
         false,
         new 1(start, endInclusive, Files.size(`$this$readChannel`), `$this$readChannel`, null)
      )
      .getChannel();
}

@JvmSynthetic
fun `readChannel$default`(var0: Path, var1: Long, var3: Long, var5: CoroutineContext, var6: Int, var7: Any): ByteReadChannel {
   if ((var6 and 1) != 0) {
      var1 = 0L;
   }

   if ((var6 and 2) != 0) {
      var3 = -1L;
   }

   if ((var6 and 4) != 0) {
      var5 = Dispatchers.getIO();
   }

   return readChannel(var0, var1, var3, var5);
}
