package io.ktor.websocket

import io.ktor.util.internal.ExceptionUtilsJvmKt
import kotlinx.coroutines.CopyableThrowable

public class ProtocolViolationException(violation: String) : Exception, CopyableThrowable<ProtocolViolationException> {
   public final val violation: String

   public open val message: String
      public open get() {
         return "Received illegal frame: ${this.violation}";
      }


   init {
      this.violation = violation;
   }

   public open fun createCopy(): ProtocolViolationException {
      val var1: ProtocolViolationException = new ProtocolViolationException(this.violation);
      ExceptionUtilsJvmKt.initCauseBridge(var1, this);
      return var1;
   }
}
