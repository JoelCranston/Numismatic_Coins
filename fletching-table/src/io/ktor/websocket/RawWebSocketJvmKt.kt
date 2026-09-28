package io.ktor.websocket

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.CoroutineContext

public fun RawWebSocket(
   input: ByteReadChannel,
   output: ByteWriteChannel,
   maxFrameSize: Long = 2147483647L,
   masking: Boolean = false,
   coroutineContext: CoroutineContext
): WebSocketSession {
   return new RawWebSocketJvm(input, output, maxFrameSize, masking, coroutineContext, null, 32, null);
}

@JvmSynthetic
fun `RawWebSocket$default`(var0: ByteReadChannel, var1: ByteWriteChannel, var2: Long, var4: Boolean, var5: CoroutineContext, var6: Int, var7: Any): WebSocketSession {
   if ((var6 and 4) != 0) {
      var2 = 2147483647L;
   }

   if ((var6 and 8) != 0) {
      var4 = false;
   }

   return RawWebSocket(var0, var1, var2, var4, var5);
}
