@file:SourceDebugExtension(["SMAP\nUndispatched.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Undispatched.kt\nkotlinx/coroutines/intrinsics/UndispatchedKt\n+ 2 ProbesSupport.kt\nkotlinx/coroutines/internal/ProbesSupportKt\n+ 3 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 4 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,105:1\n8#2:106\n11#2,2:110\n91#3,3:107\n95#3:112\n57#4,2:113\n57#4,2:115\n57#4,2:117\n*S KotlinDebug\n*F\n+ 1 Undispatched.kt\nkotlinx/coroutines/intrinsics/UndispatchedKt\n*L\n14#1:106\n19#1:110,2\n18#1:107,3\n18#1:112\n88#1:113,2\n89#1:115,2\n103#1:117,2\n*E\n"])

package kotlinx.coroutines.intrinsics

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.BaseContinuationImpl
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.TypeIntrinsics
import kotlinx.coroutines.CompletedExceptionally
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DispatchException
import kotlinx.coroutines.JobSupportKt
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.internal.ScopeCoroutine
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.internal.ThreadContextKt

internal fun <R, T> ((R, Continuation<T>) -> Any?).startCoroutineUndispatched(receiver: R, completion: Continuation<T>) {
   label39: {
      val actualCompletion: Continuation = DebugProbesKt.probeCoroutineCreated(completion);

      var `context$iv`: CoroutineContext;
      try {
         `context$iv` = actualCompletion.getContext();
         val `oldValue$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv`, null);

         try {
            DebugProbesKt.probeCoroutineResumed(actualCompletion);
            if (`$this$startCoroutineUndispatched` !is BaseContinuationImpl) {
               IntrinsicsKt.wrapWithContinuationImpl(`$this$startCoroutineUndispatched`, receiver, actualCompletion);
            } else {
               (TypeIntrinsics.beforeCheckcastToFunctionOfArity(`$this$startCoroutineUndispatched`, 2) as Function2).invoke(receiver, actualCompletion);
            }
         } catch (var13: java.lang.Throwable) {
            ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
         }

         ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
      } catch (var14: java.lang.Throwable) {
         actualCompletion.resumeWith(
            Result.constructor-impl(ResultKt.createFailure(if (var14 is DispatchException) (var14 as DispatchException).getCause() else var14))
         );
         return;
      }

      if (`context$iv` != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         actualCompletion.resumeWith(Result.constructor-impl(`context$iv`));
      }
   }
}

internal fun <T, R> ScopeCoroutine<T>.startUndispatchedOrReturn(receiver: R, block: (R, Continuation<T>) -> Any?): Any? {
   return startUndspatched(`$this$startUndispatchedOrReturn`, true, receiver, block);
}

internal fun <T, R> ScopeCoroutine<T>.startUndispatchedOrReturnIgnoreTimeout(receiver: R, block: (R, Continuation<T>) -> Any?): Any? {
   return startUndspatched(`$this$startUndispatchedOrReturnIgnoreTimeout`, false, receiver, block);
}

private fun <T, R> ScopeCoroutine<T>.startUndspatched(alwaysRethrow: Boolean, receiver: R, block: (R, Continuation<T>) -> Any?): Any? {
   var state: Any;
   try {
      state = if (block !is BaseContinuationImpl)
         IntrinsicsKt.wrapWithContinuationImpl(block, receiver, `$this$startUndspatched`)
         else
         (TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2) as Function2).invoke(receiver, `$this$startUndspatched`);
   } catch (var9: DispatchException) {
      dispatchExceptionAndMakeCompleting(`$this$startUndspatched`, var9);
      throw new KotlinNothingValueException();
   } catch (var10: java.lang.Throwable) {
      state = new CompletedExceptionally(var10, false, 2, null);
   }

   if (state === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      return IntrinsicsKt.getCOROUTINE_SUSPENDED();
   } else {
      state = `$this$startUndspatched`.makeCompletingOnce$kotlinx_coroutines_core(state);
      if (state === JobSupportKt.COMPLETING_WAITING_CHILDREN) {
         return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      } else {
         `$this$startUndspatched`.afterCompletionUndispatched();
         val var10000: Any;
         if (state is CompletedExceptionally) {
            if (alwaysRethrow || notOwnTimeout(`$this$startUndspatched`, (state as CompletedExceptionally).cause)) {
               val var12: java.lang.Throwable = (state as CompletedExceptionally).cause;
               val var13: Continuation = `$this$startUndspatched`.uCont;
               throw if (DebugKt.getRECOVER_STACK_TRACES() && var13 is CoroutineStackFrame)
                  StackTraceRecoveryKt.access$recoverFromStackFrame(var12, var13 as CoroutineStackFrame)
                  else
                  var12;
            }

            if (state is CompletedExceptionally) {
               val `exception$iv`: java.lang.Throwable = (state as CompletedExceptionally).cause;
               val `continuation$iv`: Continuation = `$this$startUndspatched`.uCont;
               throw if (DebugKt.getRECOVER_STACK_TRACES() && `continuation$iv` is CoroutineStackFrame)
                  StackTraceRecoveryKt.access$recoverFromStackFrame(`exception$iv`, `continuation$iv` as CoroutineStackFrame)
                  else
                  `exception$iv`;
            }

            var10000 = state;
         } else {
            var10000 = JobSupportKt.unboxState(state);
         }

         return var10000;
      }
   }
}

private fun ScopeCoroutine<*>.notOwnTimeout(cause: Throwable): Boolean {
   return cause !is TimeoutCancellationException || (cause as TimeoutCancellationException).coroutine != `$this$notOwnTimeout`;
}

private fun ScopeCoroutine<*>.dispatchExceptionAndMakeCompleting(e: DispatchException): Nothing {
   `$this$dispatchExceptionAndMakeCompleting`.makeCompleting$kotlinx_coroutines_core(new CompletedExceptionally(e.getCause(), false, 2, null));
   val `exception$iv`: java.lang.Throwable = e.getCause();
   val `continuation$iv`: Continuation = `$this$dispatchExceptionAndMakeCompleting`.uCont;
   throw if (DebugKt.getRECOVER_STACK_TRACES() && `continuation$iv` is CoroutineStackFrame)
      StackTraceRecoveryKt.access$recoverFromStackFrame(`exception$iv`, `continuation$iv` as CoroutineStackFrame)
      else
      `exception$iv`;
}
