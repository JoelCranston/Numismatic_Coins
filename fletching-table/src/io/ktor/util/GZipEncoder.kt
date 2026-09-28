package io.ktor.util

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.CoroutineContext

public object GZipEncoder : ContentEncoder, Encoder {
   public open val name: String = "gzip"

   public override fun encode(source: ByteReadChannel, coroutineContext: CoroutineContext): ByteReadChannel {
      return this.$$delegate_0.encode(source, coroutineContext);
   }

   public override fun encode(source: ByteWriteChannel, coroutineContext: CoroutineContext): ByteWriteChannel {
      return this.$$delegate_0.encode(source, coroutineContext);
   }

   public override fun decode(source: ByteReadChannel, coroutineContext: CoroutineContext): ByteReadChannel {
      return this.$$delegate_0.decode(source, coroutineContext);
   }
}
