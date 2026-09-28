package io.ktor.util

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.CoroutineContext

public object Identity : Encoder {
   public override fun encode(source: ByteReadChannel, coroutineContext: CoroutineContext): ByteReadChannel {
      return source;
   }

   public override fun encode(source: ByteWriteChannel, coroutineContext: CoroutineContext): ByteWriteChannel {
      return source;
   }

   public override fun decode(source: ByteReadChannel, coroutineContext: CoroutineContext): ByteReadChannel {
      return source;
   }
}
