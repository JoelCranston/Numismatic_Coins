package kotlinx.coroutines

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.DispatchedContinuation
import kotlinx.coroutines.internal.StackTraceRecoveryKt
import kotlinx.coroutines.internal.ThreadContextKt
import kotlinx.coroutines.scheduling.Task

@SourceDebugExtension(["SMAP\nDispatchedTask.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTask\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n+ 4 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 5 StackTraceRecovery.kt\nkotlinx/coroutines/internal/StackTraceRecoveryKt\n*L\n1#1,220:1\n1#2:221\n103#3,10:222\n114#3,2:236\n204#4:232\n205#4:235\n57#5,2:233\n*S KotlinDebug\n*F\n+ 1 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTask\n*L\n82#1:222,10\n82#1:236,2\n95#1:232\n95#1:235\n95#1:233,2\n*E\n"])
internal abstract class DispatchedTask<T> : Task {
   public final var resumeMode: Int
      private set

   internal abstract val delegate: Continuation<Any>

   open fun DispatchedTask(resumeMode: Int) {
      this.resumeMode = resumeMode;
   }

   internal abstract fun takeState(): Any? {
   }

   internal open fun cancelCompletedResult(takenState: Any?, cause: Throwable) {
   }

   internal open fun <T> getSuccessfulResult(state: Any?): T {
      return (T)state;
   }

   internal open fun getExceptionalResult(state: Any?): Throwable? {
      return if ((state as? CompletedExceptionally) != null) (state as? CompletedExceptionally).cause else null;
   }

   public override fun run() {
      if (DebugKt.getASSERTIONS_ENABLED() && this.resumeMode == -1) {
         throw new AssertionError();
      } else {
         try {
            label83: {
               val var10000: Continuation = this.getDelegate$kotlinx_coroutines_core();
               val continuation: Continuation = (var10000 as DispatchedContinuation).continuation;
               val `countOrElement$iv`: Any = (var10000 as DispatchedContinuation).countOrElement;
               val `context$iv`: CoroutineContext = (var10000 as DispatchedContinuation).continuation.getContext();
               val `oldValue$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv`, `countOrElement$iv`);
               val `undispatchedCompletion$iv`: UndispatchedCoroutine = if (`oldValue$iv` != ThreadContextKt.NO_THREAD_ELEMENTS)
                  CoroutineContextKt.updateUndispatchedCompletion(continuation, `context$iv`, `oldValue$iv`)
                  else
                  null;

               try {
                  val context: CoroutineContext = continuation.getContext();
                  val state: Any = this.takeState$kotlinx_coroutines_core();
                  val exception: java.lang.Throwable = this.getExceptionalResult$kotlinx_coroutines_core(state);
                  val job: Job = if (exception == null && DispatchedTaskKt.isCancellableMode(this.resumeMode)) context.get(Job.Key) else null;
                  if (job != null && !job.isActive()) {
                     val cause: CancellationException = job.getCancellationException();
                     this.cancelCompletedResult$kotlinx_coroutines_core(state, cause);
                     continuation.resumeWith(
                        Result.constructor-impl(
                           ResultKt.createFailure(
                              if (DebugKt.getRECOVER_STACK_TRACES() && continuation is CoroutineStackFrame)
                                 StackTraceRecoveryKt.access$recoverFromStackFrame(cause, continuation as CoroutineStackFrame)
                                 else
                                 cause
                           )
                        )
                     );
                  } else if (exception != null) {
                     continuation.resumeWith(Result.constructor-impl(ResultKt.createFailure(exception)));
                  } else {
                     continuation.resumeWith(Result.constructor-impl(this.getSuccessfulResult$kotlinx_coroutines_core(state)));
                  }
               } catch (var17: java.lang.Throwable) {
                  if (`undispatchedCompletion$iv` == null || `undispatchedCompletion$iv`.clearThreadContext()) {
                     ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
                  }
               }

               if (`undispatchedCompletion$iv` == null || `undispatchedCompletion$iv`.clearThreadContext()) {
                  ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
               }
            }
         } catch (var18: DispatchException) {
            CoroutineExceptionHandlerKt.handleCoroutineException(this.getDelegate$kotlinx_coroutines_core().getContext(), var18.getCause());
         } catch (var19: java.lang.Throwable) {
            this.handleFatalException$kotlinx_coroutines_core(var19);
         }
      }
   }

   internal fun handleFatalException(exception: Throwable) {
      CoroutineExceptionHandlerKt.handleCoroutineException(
         this.getDelegate$kotlinx_coroutines_core().getContext(),
         new CoroutinesInternalError(
            "Fatal exception in coroutines machinery for $this. Please read KDoc to 'handleFatalException' method and report this incident to maintainers",
            exception
         )
      );
   }
}
