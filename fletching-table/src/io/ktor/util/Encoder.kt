package io.ktor.util

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public interface Encoder {
   public abstract fun encode(source: ByteReadChannel, coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext): ByteReadChannel {
   }

   public abstract fun encode(source: ByteWriteChannel, coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext): ByteWriteChannel {
   }

   public abstract fun decode(source: ByteReadChannel, coroutineContext: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext): ByteReadChannel {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
