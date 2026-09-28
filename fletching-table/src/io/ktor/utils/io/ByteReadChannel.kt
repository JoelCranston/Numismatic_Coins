package io.ktor.utils.io

import io.ktor.utils.io.ByteReadChannel.Companion.Empty.1
import kotlinx.io.Source

public interface ByteReadChannel {
   public val closedCause: Throwable?
   public val isClosedForRead: Boolean
   public val readBuffer: Source

   public abstract suspend fun awaitContent(min: Int = ...): Boolean {
   }

   public abstract fun cancel(cause: Throwable?) {
   }

   public companion object {
      public final val Empty: ByteReadChannel = (new 1()) as ByteReadChannel
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
