package kotlinx.coroutines.debug.internal

import kotlin.coroutines.jvm.internal.CoroutineStackFrame

internal class StackTraceFrame(callerFrame: CoroutineStackFrame?, stackTraceElement: StackTraceElement) : CoroutineStackFrame {
   public open val callerFrame: CoroutineStackFrame?
   private final val stackTraceElement: StackTraceElement

   init {
      this.callerFrame = callerFrame;
      this.stackTraceElement = stackTraceElement;
   }

   public override fun getStackTraceElement(): StackTraceElement {
      return this.stackTraceElement;
   }
}
