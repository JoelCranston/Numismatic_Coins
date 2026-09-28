package kotlinx.coroutines.internal

import java.util.concurrent.CancellationException
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.jvm.internal.CoroutineStackFrame
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicRef
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CancellableContinuationImpl
import kotlinx.coroutines.CompletionStateKt
import kotlinx.coroutines.CoroutineContextKt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DebugStringsKt
import kotlinx.coroutines.DispatchedTask
import kotlinx.coroutines.EventLoop
import kotlinx.coroutines.Job
import kotlinx.coroutines.ThreadLocalEventLoop
import kotlinx.coroutines.UndispatchedCoroutine

@SourceDebugExtension(["SMAP\nDispatchedContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuationKt\n+ 4 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 5 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,313:1\n224#1,8:377\n236#1:385\n237#1,2:396\n239#1:400\n1#2:314\n1#2:320\n1#2:361\n293#3,5:315\n298#3,12:321\n310#3:355\n293#3,5:356\n298#3,12:362\n310#3:415\n184#4,3:333\n187#4,14:341\n184#4,3:374\n187#4,14:401\n91#5,5:336\n103#5,10:386\n114#5,2:398\n103#5,13:416\n*S KotlinDebug\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n*L\n214#1:377,8\n215#1:385\n215#1:396,2\n215#1:400\n195#1:320\n213#1:361\n195#1:315,5\n195#1:321,12\n195#1:355\n213#1:356,5\n213#1:362,12\n213#1:415\n195#1:333,3\n195#1:341,14\n213#1:374,3\n213#1:401,14\n196#1:336,5\n215#1:386,10\n215#1:398,2\n236#1:416,13\n*E\n"])
internal class DispatchedContinuation<T>(dispatcher: CoroutineDispatcher, continuation: Continuation<Any>) : DispatchedTask(-1),
   CoroutineStackFrame,
   Continuation<T> {
   internal final val dispatcher: CoroutineDispatcher
   public final val continuation: Continuation<Any>

   internal final var _state: Any?
      private set

   public open val callerFrame: CoroutineStackFrame?
      public open get() {
         return this.continuation as? CoroutineStackFrame;
      }


   internal final val countOrElement: Any
   private final val _reusableCancellableContinuation: AtomicRef<Any?>

   private final val reusableCancellableContinuation: CancellableContinuationImpl<*>?
      private final get() {
         val var1: Any = get_reusableCancellableContinuation$volatile$FU().get(this);
         return var1 as? CancellableContinuationImpl;
      }


   internal open val delegate: Continuation<Any>
      internal open get() {
         return this;
      }


   public open val context: CoroutineContext

   init {
      this.dispatcher = dispatcher;
      this.continuation = continuation;
      this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
      this.countOrElement = ThreadContextKt.threadContextElements(this.getContext());
   }

   public override fun getStackTraceElement(): StackTraceElement? {
      return null;
   }

   internal fun isReusable(): Boolean {
      return get_reusableCancellableContinuation$volatile$FU().get(this) != null;
   }

   internal fun awaitReusability() {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_reusableCancellableContinuation$volatile$FU();

      do {
      } while (handler$atomicfu$iv.get(this) == DispatchedContinuationKt.REUSABLE_CLAIMED);
   }

   internal fun release() {
      this.awaitReusability$kotlinx_coroutines_core();
      val var10000: CancellableContinuationImpl = this.getReusableCancellableContinuation();
      if (var10000 != null) {
         var10000.detachChild$kotlinx_coroutines_core();
      }
   }

   internal fun claimReusableCancellableContinuation(): CancellableContinuationImpl<Any>? {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_reusableCancellableContinuation$volatile$FU();

      while (true) {
         val state: Any = `handler$atomicfu$iv`.get(this);
         if (state == null) {
            get_reusableCancellableContinuation$volatile$FU().set(this, DispatchedContinuationKt.REUSABLE_CLAIMED);
            return null;
         }

         if (state is CancellableContinuationImpl) {
            if (get_reusableCancellableContinuation$volatile$FU().compareAndSet(this, state, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
               return state as CancellableContinuationImpl<T>;
            }
         } else if (state != DispatchedContinuationKt.REUSABLE_CLAIMED && state !is java.lang.Throwable) {
            throw new IllegalStateException(("Inconsistent state $state").toString());
         }
      }
   }

   internal fun tryReleaseClaimedContinuation(continuation: CancellableContinuation<*>): Throwable? {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_reusableCancellableContinuation$volatile$FU();

      do {
         val state: Any = `handler$atomicfu$iv`.get(this);
         if (state != DispatchedContinuationKt.REUSABLE_CLAIMED) {
            if (state is java.lang.Throwable) {
               if (!get_reusableCancellableContinuation$volatile$FU().compareAndSet(this, state, null)) {
                  throw new IllegalArgumentException("Failed requirement.".toString());
               }

               return state as java.lang.Throwable;
            }

            throw new IllegalStateException(("Inconsistent state $state").toString());
         }
      } while (!get_reusableCancellableContinuation$volatile$FU().compareAndSet(this, DispatchedContinuationKt.REUSABLE_CLAIMED, continuation));

      return null;
   }

   internal fun postponeCancellation(cause: Throwable): Boolean {
      val `handler$atomicfu$iv`: AtomicReferenceFieldUpdater = get_reusableCancellableContinuation$volatile$FU();

      while (true) {
         val state: Any = `handler$atomicfu$iv`.get(this);
         if (state == DispatchedContinuationKt.REUSABLE_CLAIMED) {
            if (get_reusableCancellableContinuation$volatile$FU().compareAndSet(this, DispatchedContinuationKt.REUSABLE_CLAIMED, cause)) {
               return true;
            }
         } else {
            if (state is java.lang.Throwable) {
               return true;
            }

            if (get_reusableCancellableContinuation$volatile$FU().compareAndSet(this, state, null)) {
               return false;
            }
         }
      }
   }

   internal override fun takeState(): Any? {
      val state: Any = this._state;
      if (DebugKt.getASSERTIONS_ENABLED() && state === DispatchedContinuationKt.access$getUNDEFINED$p()) {
         throw new AssertionError();
      } else {
         this._state = DispatchedContinuationKt.access$getUNDEFINED$p();
         return state;
      }
   }

   public override fun resumeWith(result: Result<Any>) {
      label57: {
         val state: Any = CompletionStateKt.toState(result);
         if (DispatchedContinuationKt.safeIsDispatchNeeded(this.dispatcher, this.getContext())) {
            this._state = state;
            this.resumeMode = 0;
            DispatchedContinuationKt.safeDispatch(this.dispatcher, this.getContext(), this);
         } else {
            if (DebugKt.getASSERTIONS_ENABLED() && false) {
               throw new AssertionError();
            }

            val var27: EventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if (var27.isUnconfinedLoopActive()) {
               this._state = state;
               this.resumeMode = 0;
               var27.dispatchUnconfined(this);
            } else {
               label115: {
                  val `$this$runUnconfinedEventLoop$iv$iv`: DispatchedTask = this;
                  var27.incrementUseCount(true);

                  label48: {
                     try {
                        try {
                           val `context$iv`: CoroutineContext = this.getContext();
                           val `oldValue$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv`, this.countOrElement);

                           try {
                              this.continuation.resumeWith(result);
                           } catch (var18: java.lang.Throwable) {
                              ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
                           }

                           ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);

                           while (true) {
                              if (!var27.processUnconfinedEvent()) {
                                 break label48;
                              }
                           }
                        } catch (var19: java.lang.Throwable) {
                           `$this$runUnconfinedEventLoop$iv$iv`.handleFatalException$kotlinx_coroutines_core(var19);
                        }
                     } catch (var20: java.lang.Throwable) {
                        var27.decrementUseCount(true);
                     }

                     var27.decrementUseCount(true);
                     break label115;
                  }

                  var27.decrementUseCount(true);
               }
            }
         }
      }
   }

   internal inline fun resumeCancellableWith(result: Result<Any>) {
      label93: {
         val state: Any = CompletionStateKt.toState(result);
         if (DispatchedContinuationKt.safeIsDispatchNeeded(this.dispatcher, this.getContext())) {
            this._state = state;
            this.resumeMode = 1;
            DispatchedContinuationKt.safeDispatch(this.dispatcher, this.getContext(), this);
         } else {
            if (DebugKt.getASSERTIONS_ENABLED() && false) {
               throw new AssertionError();
            }

            val var32: EventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if (var32.isUnconfinedLoopActive()) {
               this._state = state;
               this.resumeMode = 1;
               var32.dispatchUnconfined(this);
            } else {
               label195: {
                  val `$this$runUnconfinedEventLoop$iv$iv`: DispatchedTask = this;
                  var32.incrementUseCount(true);

                  label84: {
                     try {
                        try {
                           val `continuation$iv$iv`: Job = this.getContext().get(Job.Key);
                           val var10000: Boolean;
                           if (`continuation$iv$iv` != null && !`continuation$iv$iv`.isActive()) {
                              val `countOrElement$iv$iv`: CancellationException = `continuation$iv$iv`.getCancellationException();
                              this.cancelCompletedResult$kotlinx_coroutines_core(state, `countOrElement$iv$iv`);
                              this.resumeWith(Result.constructor-impl(ResultKt.createFailure(`countOrElement$iv$iv`)));
                              var10000 = true;
                           } else {
                              var10000 = false;
                           }

                           if (!var10000) {
                              val `this_$iv`: DispatchedContinuation = this;
                              val var34: Continuation = this.continuation;
                              val var35: Any = this.countOrElement;
                              val `context$iv$iv`: CoroutineContext = this.continuation.getContext();
                              val `oldValue$iv$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv$iv`, var35);
                              val `undispatchedCompletion$iv$iv`: UndispatchedCoroutine = if (`oldValue$iv$iv` != ThreadContextKt.NO_THREAD_ELEMENTS)
                                 CoroutineContextKt.updateUndispatchedCompletion(var34, `context$iv$iv`, `oldValue$iv$iv`)
                                 else
                                 null;

                              try {
                                 `this_$iv`.continuation.resumeWith(result);
                              } catch (var23: java.lang.Throwable) {
                                 InlineMarker.finallyStart(1);
                                 if (`undispatchedCompletion$iv$iv` == null || `undispatchedCompletion$iv$iv`.clearThreadContext()) {
                                    ThreadContextKt.restoreThreadContext(`context$iv$iv`, `oldValue$iv$iv`);
                                 }

                                 InlineMarker.finallyEnd(1);
                              }

                              InlineMarker.finallyStart(1);
                              if (`undispatchedCompletion$iv$iv` == null || `undispatchedCompletion$iv$iv`.clearThreadContext()) {
                                 ThreadContextKt.restoreThreadContext(`context$iv$iv`, `oldValue$iv$iv`);
                              }

                              InlineMarker.finallyEnd(1);
                           }

                           while (true) {
                              if (!var32.processUnconfinedEvent()) {
                                 break label84;
                              }
                           }
                        } catch (var24: java.lang.Throwable) {
                           `$this$runUnconfinedEventLoop$iv$iv`.handleFatalException$kotlinx_coroutines_core(var24);
                        }
                     } catch (var25: java.lang.Throwable) {
                        InlineMarker.finallyStart(1);
                        var32.decrementUseCount(true);
                        InlineMarker.finallyEnd(1);
                     }

                     InlineMarker.finallyStart(1);
                     var32.decrementUseCount(true);
                     InlineMarker.finallyEnd(1);
                     break label195;
                  }

                  InlineMarker.finallyStart(1);
                  var32.decrementUseCount(true);
                  InlineMarker.finallyEnd(1);
               }
            }
         }
      }
   }

   internal inline fun resumeCancelled(state: Any?): Boolean {
      val job: Job = this.getContext().get(Job.Key);
      if (job != null && !job.isActive()) {
         val cause: CancellationException = job.getCancellationException();
         this.cancelCompletedResult$kotlinx_coroutines_core(state, cause);
         this.resumeWith(Result.constructor-impl(ResultKt.createFailure(cause)));
         return true;
      } else {
         return false;
      }
   }

   internal inline fun resumeUndispatchedWith(result: Result<Any>) {
      label52: {
         val `continuation$iv`: Continuation = this.continuation;
         val `countOrElement$iv`: Any = this.countOrElement;
         val `context$iv`: CoroutineContext = this.continuation.getContext();
         val `oldValue$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv`, `countOrElement$iv`);
         val `undispatchedCompletion$iv`: UndispatchedCoroutine = if (`oldValue$iv` != ThreadContextKt.NO_THREAD_ELEMENTS)
            CoroutineContextKt.updateUndispatchedCompletion(`continuation$iv`, `context$iv`, `oldValue$iv`)
            else
            null;

         try {
            this.continuation.resumeWith(result);
         } catch (var11: java.lang.Throwable) {
            InlineMarker.finallyStart(1);
            if (`undispatchedCompletion$iv` == null || `undispatchedCompletion$iv`.clearThreadContext()) {
               ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
            }

            InlineMarker.finallyEnd(1);
         }

         InlineMarker.finallyStart(1);
         if (`undispatchedCompletion$iv` == null || `undispatchedCompletion$iv`.clearThreadContext()) {
            ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
         }

         InlineMarker.finallyEnd(1);
      }
   }

   internal fun dispatchYield(context: CoroutineContext, value: Any) {
      this._state = value;
      this.resumeMode = 1;
      this.dispatcher.dispatchYield(context, this);
   }

   public override fun toString(): String {
      return "DispatchedContinuation[${this.dispatcher}, ${DebugStringsKt.toDebugString(this.continuation)}]";
   }
}
