package io.ktor.utils.io.jvm.nio

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.jvm.javaio.RawSourceChannel
import java.nio.channels.ReadableByteChannel
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.io.RawSource

public fun ReadableByteChannel.toByteReadChannel(context: CoroutineContext = Dispatchers.getIO() as CoroutineContext): ByteReadChannel {
   return new RawSourceChannel(asSource(`$this$toByteReadChannel`), context);
}

@JvmSynthetic
fun `toByteReadChannel$default`(var0: ReadableByteChannel, var1: CoroutineContext, var2: Int, var3: Any): ByteReadChannel {
   if ((var2 and 1) != 0) {
      var1 = Dispatchers.getIO();
   }

   return toByteReadChannel(var0, var1);
}

public fun ReadableByteChannel.asSource(): RawSource {
   return new ReadableByteChannelSource(`$this$asSource`);
}
