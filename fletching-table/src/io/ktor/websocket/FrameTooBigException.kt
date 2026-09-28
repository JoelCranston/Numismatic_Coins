package io.ktor.websocket

import io.ktor.util.internal.ExceptionUtilsJvmKt
import kotlinx.coroutines.CopyableThrowable

public class FrameTooBigException(frameSize: Long) : Exception, CopyableThrowable<FrameTooBigException> {
   public final val frameSize: Long

   public open val message: String
      public open get() {
         return "Frame is too big: ${this.frameSize}";
      }


   init {
      this.frameSize = frameSize;
   }

   public open fun createCopy(): FrameTooBigException {
      val var1: FrameTooBigException = new FrameTooBigException(this.frameSize);
      ExceptionUtilsJvmKt.initCauseBridge(var1, this);
      return var1;
   }
}
