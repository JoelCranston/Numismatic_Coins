package io.ktor.network.sockets

import io.ktor.network.selector.Selectable
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.CIOWriterKt.attachForWritingDirectImpl.1
import io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ReaderJob
import java.nio.channels.WritableByteChannel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

internal fun CoroutineScope.attachForWritingDirectImpl(
   channel: ByteChannel,
   nioChannel: WritableByteChannel,
   selectable: Selectable,
   selector: SelectorManager,
   socketOptions: TCPClientSocketOptions? = null
): ReaderJob {
   return ByteReadChannelOperationsKt.reader(
      `$this$attachForWritingDirectImpl`,
      Dispatchers.getIO().plus(new CoroutineName("cio-to-nio-writer")),
      channel,
      new 1(selectable, socketOptions, channel, selector, nioChannel, null)
   );
}

@JvmSynthetic
fun `attachForWritingDirectImpl$default`(
   var0: CoroutineScope,
   var1: ByteChannel,
   var2: WritableByteChannel,
   var3: Selectable,
   var4: SelectorManager,
   var5: SocketOptions.TCPClientSocketOptions,
   var6: Int,
   var7: Any
): ReaderJob {
   if ((var6 and 16) != 0) {
      var5 = null;
   }

   return attachForWritingDirectImpl(var0, var1, var2, var3, var4, var5);
}
