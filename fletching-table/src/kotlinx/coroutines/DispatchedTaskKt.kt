@file:SourceDebugExtension(["SMAP\nDispatchedTask.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n+ 4 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,220:1\n184#1,17:238\n1#2:221\n236#3:222\n237#3,2:233\n239#3:237\n103#4,10:223\n114#4,2:235\n57#5,2:255\n*S KotlinDebug\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n*L\n174#1:238,17\n162#1:222\n162#1:233,2\n162#1:237\n162#1:223,10\n162#1:235,2\n204#1:255,2\n*E\n"])

package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.DispatchedContinuation
import kotlinx.coroutines.internal.DispatchedContinuationKt
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.internal.ThreadContextKt

internal const val MODE_ATOMIC: Int = 0

@PublishedApi
internal const val MODE_CANCELLABLE: Int = 1

internal const val MODE_CANCELLABLE_REUSABLE: Int = 2
internal const val MODE_UNDISPATCHED: Int = 4
internal const val MODE_UNINITIALIZED: Int = -1

internal final val isCancellableMode: Boolean
   internal final get() {
      return `$this$isCancellableMode` == 1 || `$this$isCancellableMode` == 2;
   }


internal final val isReusableMode: Boolean
   internal final get() {
      return `$this$isReusableMode` == 2;
   }


internal fun <T> DispatchedTask<T>.dispatch(mode: Int) {
   if (DebugKt.getASSERTIONS_ENABLED() && mode == -1) {
      throw new AssertionError();
   } else {
      val var6: Continuation = `$this$dispatch`.getDelegate$kotlinx_coroutines_core();
      val undispatched: Boolean = mode == 4;
      if (mode != 4 && var6 is DispatchedContinuation && isCancellableMode(mode) == isCancellableMode(`$this$dispatch`.resumeMode)) {
         val dispatcher: CoroutineDispatcher = (var6 as DispatchedContinuation).dispatcher;
         val context: CoroutineContext = (var6 as DispatchedContinuation).getContext();
         if (DispatchedContinuationKt.safeIsDispatchNeeded(dispatcher, context)) {
            DispatchedContinuationKt.safeDispatch(dispatcher, context, `$this$dispatch`);
         } else {
            resumeUnconfined(`$this$dispatch`);
         }
      } else {
         resume(`$this$dispatch`, var6, undispatched);
      }
   }
}

internal fun <T> DispatchedTask<T>.resume(delegate: Continuation<T>, undispatched: Boolean) {
   label46: {
      val state: Any = `$this$resume`.takeState$kotlinx_coroutines_core();
      val exception: java.lang.Throwable = `$this$resume`.getExceptionalResult$kotlinx_coroutines_core(state);
      val result: Any = if (exception != null)
         Result.constructor-impl(ResultKt.createFailure(exception))
         else
         Result.constructor-impl(`$this$resume`.getSuccessfulResult$kotlinx_coroutines_core(state));
      if (undispatched) {
         val `this_$iv`: DispatchedContinuation = delegate as DispatchedContinuation;
         val `continuation$iv$iv`: Continuation = (delegate as DispatchedContinuation).continuation;
         val `countOrElement$iv$iv`: Any = (delegate as DispatchedContinuation).countOrElement;
         val `context$iv$iv`: CoroutineContext = (delegate as DispatchedContinuation).continuation.getContext();
         val `oldValue$iv$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv$iv`, `countOrElement$iv$iv`);
         val `undispatchedCompletion$iv$iv`: UndispatchedCoroutine = if (`oldValue$iv$iv` != ThreadContextKt.NO_THREAD_ELEMENTS)
            CoroutineContextKt.updateUndispatchedCompletion(`continuation$iv$iv`, `context$iv$iv`, `oldValue$iv$iv`)
            else
            null;

         try {
            `this_$iv`.continuation.resumeWith(result);
         } catch (var16: java.lang.Throwable) {
            if (`undispatchedCompletion$iv$iv` == null || `undispatchedCompletion$iv$iv`.clearThreadContext()) {
               ThreadContextKt.restoreThreadContext(`context$iv$iv`, `oldValue$iv$iv`);
            }
         }

         if (`undispatchedCompletion$iv$iv` == null || `undispatchedCompletion$iv$iv`.clearThreadContext()) {
            ThreadContextKt.restoreThreadContext(`context$iv$iv`, `oldValue$iv$iv`);
         }
      } else {
         delegate.resumeWith(result);
      }
   }
}

private fun DispatchedTask<*>.resumeUnconfined() {
   label35: {
      val eventLoop: EventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      if (eventLoop.isUnconfinedLoopActive()) {
         eventLoop.dispatchUnconfined(`$this$resumeUnconfined`);
      } else {
         val `$this$runUnconfinedEventLoop$iv`: DispatchedTask = `$this$resumeUnconfined`;
         eventLoop.incrementUseCount(true);

         label54: {
            try {
               try {
                  resume(`$this$resumeUnconfined`, `$this$resumeUnconfined`.getDelegate$kotlinx_coroutines_core(), true);

                  while (true) {
                     if (!eventLoop.processUnconfinedEvent()) {
                        break label54;
                     }
                  }
               } catch (var6: java.lang.Throwable) {
                  `$this$runUnconfinedEventLoop$iv`.handleFatalException$kotlinx_coroutines_core(var6);
               }
            } catch (var7: java.lang.Throwable) {
               eventLoop.decrementUseCount(true);
            }

            eventLoop.decrementUseCount(true);
            return;
         }

         eventLoop.decrementUseCount(true);
      }
   }
}

internal inline fun DispatchedTask<*>.runUnconfinedEventLoop(eventLoop: EventLoop, block: () -> Unit) {
   label46: {
      eventLoop.incrementUseCount(true);

      label47: {
         try {
            try {
               block.invoke();

               while (true) {
                  if (!eventLoop.processUnconfinedEvent()) {
                     break label47;
                  }
               }
            } catch (var5: java.lang.Throwable) {
               `$this$runUnconfinedEventLoop`.handleFatalException$kotlinx_coroutines_core(var5);
            }
         } catch (var6: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            eventLoop.decrementUseCount(true);
            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         eventLoop.decrementUseCount(true);
         InlineMarker.finallyEnd(1);
         return;
      }

      InlineMarker.finallyStart(1);
      eventLoop.decrementUseCount(true);
      InlineMarker.finallyEnd(1);
   }
}

internal inline fun Continuation<*>.resumeWithStackTrace(exception: Throwable) {
   `$this$resumeWithStackTrace`.resumeWith(
      Result.constructor-impl(
         ResultKt.createFailure(
            if (DebugKt.getRECOVER_STACK_TRACES() && `$this$resumeWithStackTrace` is CoroutineStackFrame)
               StackTraceRecoveryKt.access$recoverFromStackFrame(exception, `$this$resumeWithStackTrace` as CoroutineStackFrame)
               else
               exception
         )
      )
   );
}
