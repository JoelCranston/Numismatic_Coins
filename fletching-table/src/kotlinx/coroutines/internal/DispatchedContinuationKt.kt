@file:SourceDebugExtension(["SMAP\nDispatchedContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuationKt\n+ 2 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuation\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 DispatchedTask.kt\nkotlinx/coroutines/DispatchedTaskKt\n+ 5 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,313:1\n293#1,5:321\n298#1,12:327\n310#1:395\n297#1:397\n298#1,12:399\n310#1:428\n207#2,7:314\n214#2,23:342\n237#2,2:375\n239#2:379\n217#2:380\n219#2:396\n1#3:326\n1#3:398\n1#3:429\n184#4,3:339\n187#4,14:381\n184#4,17:411\n184#4,17:430\n103#5,10:365\n114#5,2:377\n*S KotlinDebug\n*F\n+ 1 DispatchedContinuation.kt\nkotlinx/coroutines/internal/DispatchedContinuationKt\n*L\n278#1:321,5\n278#1:327,12\n278#1:395\n283#1:397\n283#1:399,12\n283#1:428\n278#1:314,7\n278#1:342,23\n278#1:375,2\n278#1:379\n278#1:380\n278#1:396\n278#1:326\n283#1:398\n278#1:339,3\n278#1:381,14\n283#1:411,17\n309#1:430,17\n278#1:365,10\n278#1:377,2\n*E\n"])

package kotlinx.coroutines.internal

import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletionStateKt
import kotlinx.coroutines.CoroutineContextKt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DispatchException
import kotlinx.coroutines.DispatchedTask
import kotlinx.coroutines.EventLoop
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.ThreadLocalEventLoop
import kotlinx.coroutines.UndispatchedCoroutine

private final val UNDEFINED: Symbol = new Symbol("UNDEFINED")
internal final val REUSABLE_CLAIMED: Symbol = new Symbol("REUSABLE_CLAIMED")

internal fun CoroutineDispatcher.safeDispatch(context: CoroutineContext, runnable: Runnable) {
   try {
      `$this$safeDispatch`.dispatch(context, runnable);
   } catch (var4: java.lang.Throwable) {
      throw new DispatchException(var4, `$this$safeDispatch`, context);
   }
}

internal fun CoroutineDispatcher.safeIsDispatchNeeded(context: CoroutineContext): Boolean {
   try {
      return `$this$safeIsDispatchNeeded`.isDispatchNeeded(context);
   } catch (var3: java.lang.Throwable) {
      throw new DispatchException(var3, `$this$safeIsDispatchNeeded`, context);
   }
}

@InternalCoroutinesApi
public fun <T> Continuation<T>.resumeCancellableWith(result: Result<T>) {
   label98: {
      if (`$this$resumeCancellableWith` is DispatchedContinuation) {
         val `this_$iv`: DispatchedContinuation = `$this$resumeCancellableWith` as DispatchedContinuation;
         val `state$iv`: Any = CompletionStateKt.toState(result);
         if (safeIsDispatchNeeded(`this_$iv`.dispatcher, `this_$iv`.getContext())) {
            `this_$iv`._state = `state$iv`;
            `this_$iv`.resumeMode = 1;
            safeDispatch(`this_$iv`.dispatcher, `this_$iv`.getContext(), `this_$iv`);
         } else {
            if (DebugKt.getASSERTIONS_ENABLED() && false) {
               throw new AssertionError();
            }

            val var33: EventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
            if (var33.isUnconfinedLoopActive()) {
               `this_$iv`._state = `state$iv`;
               `this_$iv`.resumeMode = 1;
               var33.dispatchUnconfined(`this_$iv`);
            } else {
               label202: {
                  val `$this$runUnconfinedEventLoop$iv$iv$iv`: DispatchedTask = `this_$iv`;
                  var33.incrementUseCount(true);

                  label87: {
                     try {
                        try {
                           val `continuation$iv$iv$iv`: Job = `this_$iv`.getContext().get(Job.Key);
                           val var38: Boolean;
                           if (`continuation$iv$iv$iv` != null && !`continuation$iv$iv$iv`.isActive()) {
                              val `countOrElement$iv$iv$iv`: CancellationException = `continuation$iv$iv$iv`.getCancellationException();
                              `this_$iv`.cancelCompletedResult$kotlinx_coroutines_core(`state$iv`, `countOrElement$iv$iv$iv`);
                              `this_$iv`.resumeWith(Result.constructor-impl(ResultKt.createFailure(`countOrElement$iv$iv$iv`)));
                              var38 = true;
                           } else {
                              var38 = false;
                           }

                           if (!var38) {
                              val `this_$iv$iv`: DispatchedContinuation = `this_$iv`;
                              val var35: Continuation = `this_$iv`.continuation;
                              val var36: Any = `this_$iv`.countOrElement;
                              val `context$iv$iv$iv`: CoroutineContext = `this_$iv`.continuation.getContext();
                              val `oldValue$iv$iv$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv$iv$iv`, var36);
                              val `undispatchedCompletion$iv$iv$iv`: UndispatchedCoroutine = if (`oldValue$iv$iv$iv` != ThreadContextKt.NO_THREAD_ELEMENTS)
                                 CoroutineContextKt.updateUndispatchedCompletion(var35, `context$iv$iv$iv`, `oldValue$iv$iv$iv`)
                                 else
                                 null;

                              try {
                                 `this_$iv$iv`.continuation.resumeWith(result);
                              } catch (var24: java.lang.Throwable) {
                                 if (`undispatchedCompletion$iv$iv$iv` == null || `undispatchedCompletion$iv$iv$iv`.clearThreadContext()) {
                                    ThreadContextKt.restoreThreadContext(`context$iv$iv$iv`, `oldValue$iv$iv$iv`);
                                 }
                              }

                              if (`undispatchedCompletion$iv$iv$iv` == null || `undispatchedCompletion$iv$iv$iv`.clearThreadContext()) {
                                 ThreadContextKt.restoreThreadContext(`context$iv$iv$iv`, `oldValue$iv$iv$iv`);
                              }
                           }

                           while (true) {
                              if (!var33.processUnconfinedEvent()) {
                                 break label87;
                              }
                           }
                        } catch (var25: java.lang.Throwable) {
                           `$this$runUnconfinedEventLoop$iv$iv$iv`.handleFatalException$kotlinx_coroutines_core(var25);
                        }
                     } catch (var26: java.lang.Throwable) {
                        var33.decrementUseCount(true);
                     }

                     var33.decrementUseCount(true);
                     break label202;
                  }

                  var33.decrementUseCount(true);
               }
            }
         }
      } else {
         `$this$resumeCancellableWith`.resumeWith(result);
      }
   }
}

internal fun DispatchedContinuation<Unit>.yieldUndispatched(): Boolean {
   val `contState$iv`: Unit = Unit.INSTANCE;
   label28:
   if (DebugKt.getASSERTIONS_ENABLED() && false) {
      throw new AssertionError();
   } else {
      val var15: EventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      val var10000: Boolean;
      if (var15.isUnconfinedQueueEmpty()) {
         var10000 = false;
      } else if (var15.isUnconfinedLoopActive()) {
         `$this$yieldUndispatched`._state = `contState$iv`;
         `$this$yieldUndispatched`.resumeMode = 1;
         var15.dispatchUnconfined(`$this$yieldUndispatched`);
         var10000 = true;
      } else {
         val `$this$runUnconfinedEventLoop$iv$iv`: DispatchedTask = `$this$yieldUndispatched`;
         var15.incrementUseCount(true);

         label40: {
            label39: {
               try {
                  try {
                     `$this$yieldUndispatched`.run();

                     while (true) {
                        if (!var15.processUnconfinedEvent()) {
                           break label39;
                        }
                     }
                  } catch (var11: java.lang.Throwable) {
                     `$this$runUnconfinedEventLoop$iv$iv`.handleFatalException$kotlinx_coroutines_core(var11);
                  }
               } catch (var12: java.lang.Throwable) {
                  var15.decrementUseCount(true);
               }

               var15.decrementUseCount(true);
               break label40;
            }

            var15.decrementUseCount(true);
         }

         var10000 = false;
      }

      return var10000;
   }
}

private inline fun DispatchedContinuation<*>.executeUnconfined(contState: Any?, mode: Int, doYield: Boolean = false, block: () -> Unit): Boolean {
   if (DebugKt.getASSERTIONS_ENABLED() && mode == -1) {
      throw new AssertionError();
   } else {
      val var14: EventLoop = ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      if (doYield && var14.isUnconfinedQueueEmpty()) {
         return false;
      } else {
         label79: {
            val var10000: Boolean;
            if (var14.isUnconfinedLoopActive()) {
               `$this$executeUnconfined`._state = contState;
               `$this$executeUnconfined`.resumeMode = mode;
               var14.dispatchUnconfined(`$this$executeUnconfined`);
               var10000 = true;
            } else {
               val `$this$runUnconfinedEventLoop$iv`: DispatchedTask = `$this$executeUnconfined`;
               var14.incrementUseCount(true);

               label45: {
                  label44: {
                     try {
                        try {
                           block.invoke();

                           while (true) {
                              if (!var14.processUnconfinedEvent()) {
                                 break label44;
                              }
                           }
                        } catch (var10: java.lang.Throwable) {
                           `$this$runUnconfinedEventLoop$iv`.handleFatalException$kotlinx_coroutines_core(var10);
                        }
                     } catch (var11: java.lang.Throwable) {
                        InlineMarker.finallyStart(1);
                        var14.decrementUseCount(true);
                        InlineMarker.finallyEnd(1);
                     }

                     InlineMarker.finallyStart(1);
                     var14.decrementUseCount(true);
                     InlineMarker.finallyEnd(1);
                     break label45;
                  }

                  InlineMarker.finallyStart(1);
                  var14.decrementUseCount(true);
                  InlineMarker.finallyEnd(1);
               }

               var10000 = false;
            }

            return var10000;
         }
      }
   }
}

@JvmSynthetic
fun DispatchedContinuation.`executeUnconfined$default`(
   contState: Any, mode: Int, doYield: Boolean, block: Function0, `$i$f$executeUnconfined`: Int, eventLoop: Any
): Boolean {
   if ((`$i$f$executeUnconfined` and 4) != 0) {
      doYield = false;
   }

   if (DebugKt.getASSERTIONS_ENABLED() && mode == -1) {
      throw new AssertionError();
   } else {
      eventLoop = (int)ThreadLocalEventLoop.INSTANCE.getEventLoop$kotlinx_coroutines_core();
      if (doYield && eventLoop.isUnconfinedQueueEmpty()) {
         return false;
      } else {
         label85: {
            val var10000: Boolean;
            if (eventLoop.isUnconfinedLoopActive()) {
               `$this$executeUnconfined_u24default`._state = contState;
               `$this$executeUnconfined_u24default`.resumeMode = mode;
               eventLoop.dispatchUnconfined(`$this$executeUnconfined_u24default`);
               var10000 = true;
            } else {
               val `$this$runUnconfinedEventLoop$iv`: DispatchedTask = `$this$executeUnconfined_u24default`;
               eventLoop.incrementUseCount(true);

               label48: {
                  label47: {
                     try {
                        try {
                           block.invoke();

                           while (true) {
                              if (!eventLoop.processUnconfinedEvent()) {
                                 break label47;
                              }
                           }
                        } catch (var10: java.lang.Throwable) {
                           `$this$runUnconfinedEventLoop$iv`.handleFatalException$kotlinx_coroutines_core(var10);
                        }
                     } catch (var11: java.lang.Throwable) {
                        InlineMarker.finallyStart(1);
                        eventLoop.decrementUseCount(true);
                        InlineMarker.finallyEnd(1);
                     }

                     InlineMarker.finallyStart(1);
                     eventLoop.decrementUseCount(true);
                     InlineMarker.finallyEnd(1);
                     break label48;
                  }

                  InlineMarker.finallyStart(1);
                  eventLoop.decrementUseCount(true);
                  InlineMarker.finallyEnd(1);
               }

               var10000 = false;
            }

            return var10000;
         }
      }
   }
}

@JvmSynthetic
fun `access$getUNDEFINED$p`(): Symbol {
   return UNDEFINED;
}
