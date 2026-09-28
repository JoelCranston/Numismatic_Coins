package io.ktor.utils.io

import kotlinx.io.Sink

public interface ByteWriteChannel {
   public open val autoFlush: Boolean
      public open get() {
         return false;
      }


   public val isClosedForWrite: Boolean
   public val closedCause: Throwable?
   public val writeBuffer: Sink

   public abstract suspend fun flush() {
   }

   public abstract suspend fun flushAndClose() {
   }

   public abstract fun cancel(cause: Throwable?) {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun getAutoFlush(`$this`: ByteWriteChannel): Boolean {
         return ByteWriteChannel.access$getAutoFlush$jd(`$this`);
      }
   }
}
