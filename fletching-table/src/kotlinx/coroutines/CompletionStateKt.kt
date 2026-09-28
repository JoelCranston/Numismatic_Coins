@file:SourceDebugExtension(["SMAP\nCompletionState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CompletionState.kt\nkotlinx/coroutines/CompletionStateKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,51:1\n1#2:52\n57#3,2:53\n57#3,2:55\n*S KotlinDebug\n*F\n+ 1 CompletionState.kt\nkotlinx/coroutines/CompletionStateKt\n*L\n11#1:53,2\n16#1:55,2\n*E\n"])

package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.StackTraceRecoveryKt

internal fun <T> Result<T>.toState(): Any? {
   val var10000: java.lang.Throwable = Result.exceptionOrNull-impl(`$this$toState`);
   return if (var10000 == null) `$this$toState` else new CompletedExceptionally(var10000, false, 2, null);
}

internal fun <T> Result<T>.toState(caller: CancellableContinuation<*>): Any? {
   val var10000: java.lang.Throwable = Result.exceptionOrNull-impl(`$this$toState`);
   return if (var10000 == null)
      `$this$toState`
      else
      new CompletedExceptionally(
         if (DebugKt.getRECOVER_STACK_TRACES() && caller as Continuation is CoroutineStackFrame)
            StackTraceRecoveryKt.access$recoverFromStackFrame(var10000, caller as CoroutineStackFrame)
            else
            var10000,
         false,
         2,
         null
      );
}

internal fun <T> recoverResult(state: Any?, uCont: Continuation<T>): Result<T> {
   val var10000: Any;
   if (state is CompletedExceptionally) {
      val `exception$iv`: java.lang.Throwable = (state as CompletedExceptionally).cause;
      var10000 = Result.constructor-impl(
         ResultKt.createFailure(
            if (DebugKt.getRECOVER_STACK_TRACES() && uCont is CoroutineStackFrame)
               StackTraceRecoveryKt.access$recoverFromStackFrame(`exception$iv`, uCont as CoroutineStackFrame)
               else
               `exception$iv`
         )
      );
   } else {
      var10000 = Result.constructor-impl(state);
   }

   return var10000;
}
