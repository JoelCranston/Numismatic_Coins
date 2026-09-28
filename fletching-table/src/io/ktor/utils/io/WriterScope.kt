package io.ktor.utils.io

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

public class WriterScope(channel: ByteWriteChannel, coroutineContext: CoroutineContext) : CoroutineScope {
   public final val channel: ByteWriteChannel
   public open val coroutineContext: CoroutineContext

   init {
      this.channel = channel;
      this.coroutineContext = coroutineContext;
   }
}
