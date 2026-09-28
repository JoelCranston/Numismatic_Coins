package io.ktor.utils.io

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineScope

public class ReaderScope(channel: ByteReadChannel, coroutineContext: CoroutineContext) : CoroutineScope {
   public final val channel: ByteReadChannel
   public open val coroutineContext: CoroutineContext

   init {
      this.channel = channel;
      this.coroutineContext = coroutineContext;
   }
}
