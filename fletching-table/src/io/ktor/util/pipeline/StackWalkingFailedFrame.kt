package io.ktor.util.pipeline

import io.ktor.util.StackFramesJvmKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.jvm.internal.CoroutineStackFrame

internal object StackWalkingFailedFrame : CoroutineStackFrame, Continuation<?> {
   public open val callerFrame: CoroutineStackFrame?
      public open get() {
         return null;
      }


   public open val context: CoroutineContext
      public open get() {
         return EmptyCoroutineContext.INSTANCE;
      }


   public override fun getStackTraceElement(): StackTraceElement {
      return StackFramesJvmKt.createStackTraceElement(StackWalkingFailed::class, "failedToCaptureStackFrame", "StackWalkingFailed.kt", 8);
   }

   public override fun resumeWith(result: Result<Nothing>) {
      StackWalkingFailed.INSTANCE.failedToCaptureStackFrame();
   }
}
