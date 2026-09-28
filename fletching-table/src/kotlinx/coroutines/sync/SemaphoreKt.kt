package kotlinx.coroutines.sync

import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.internal.Symbol
import kotlinx.coroutines.internal.SystemPropsKt
import kotlinx.coroutines.sync.SemaphoreKt.withPermit.1

private final val MAX_SPIN_CYCLES: Int = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, null)
private final val PERMIT: Symbol = new Symbol("PERMIT")
private final val TAKEN: Symbol = new Symbol("TAKEN")
private final val BROKEN: Symbol = new Symbol("BROKEN")
private final val CANCELLED: Symbol = new Symbol("CANCELLED")
private final val SEGMENT_SIZE: Int = SystemPropsKt.systemProp$default("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, null)

public fun Semaphore(permits: Int, acquiredPermits: Int = 0): Semaphore {
   return new SemaphoreImpl(permits, acquiredPermits);
}

@JvmSynthetic
fun `Semaphore$default`(var0: Int, var1: Int, var2: Int, var3: Any): Semaphore {
   if ((var2 and 2) != 0) {
      var1 = 0;
   }

   return Semaphore(var0, var1);
}

public suspend inline fun <T> Semaphore.withPermit(action: () -> T): T {
   contract {
      callsInPlace(action, InvocationKind.EXACTLY_ONCE)
   }

   label32: {
      var `$continuation`: Continuation;
      label30: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label30;
            }
         }

         `$continuation` = new 1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = `$this$withPermit`;
            `$continuation`.L$1 = action;
            `$continuation`.label = 1;
            if (`$this$withPermit`.acquire(`$continuation`) === var8) {
               return var8;
            }
            break;
         case 1:
            action = `$continuation`.L$1 as Function0;
            `$this$withPermit` = `$continuation`.L$0 as Semaphore;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         val var4: Any = action.invoke();
      } catch (var9: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$withPermit`.release();
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$withPermit`.release();
      InlineMarker.finallyEnd(1);
   }
}

fun <T> Semaphore.`withPermit$$forInline`(action: () -> T, `$completion`: Continuation<? super T>): Any {
   label15: {
      InlineMarker.mark(0);
      `$this$withPermit`.acquire(`$completion`);
      InlineMarker.mark(1);

      try {
         val var4: Any = action.invoke();
      } catch (var6: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         `$this$withPermit`.release();
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      `$this$withPermit`.release();
      InlineMarker.finallyEnd(1);
   }
}

private fun createSegment(id: Long, prev: SemaphoreSegment?): SemaphoreSegment {
   return new SemaphoreSegment(id, prev, 0);
}

@JvmSynthetic
fun `access$createSegment`(id: Long, prev: SemaphoreSegment): SemaphoreSegment {
   return createSegment(id, prev);
}

@JvmSynthetic
fun `access$getSEGMENT_SIZE$p`(): Int {
   return SEGMENT_SIZE;
}

@JvmSynthetic
fun `access$getPERMIT$p`(): Symbol {
   return PERMIT;
}

@JvmSynthetic
fun `access$getTAKEN$p`(): Symbol {
   return TAKEN;
}

@JvmSynthetic
fun `access$getMAX_SPIN_CYCLES$p`(): Int {
   return MAX_SPIN_CYCLES;
}

@JvmSynthetic
fun `access$getBROKEN$p`(): Symbol {
   return BROKEN;
}

@JvmSynthetic
fun `access$getCANCELLED$p`(): Symbol {
   return CANCELLED;
}
