@file:SourceDebugExtension(["SMAP\nDelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/DelayKt\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,159:1\n426#2,11:160\n426#2,11:171\n*S KotlinDebug\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/DelayKt\n*L\n103#1:160,11\n123#1:171,11\n*E\n"])

package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlin.time.DurationKt
import kotlin.time.DurationUnit
import kotlinx.coroutines.DelayKt.awaitCancellation.1

internal final val delay: Delay
   internal final get() {
      val var1: CoroutineContext.Element = `$this$delay`.get(ContinuationInterceptor.Key);
      var var10000: Delay = var1 as? Delay;
      if ((var1 as? Delay) == null) {
         var10000 = DefaultExecutorKt.getDefaultDelay();
      }

      return var10000;
   }


public suspend fun awaitCancellation(): Nothing {
   var `$continuation`: Continuation;
   label24: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label24;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$continuation`.label = 1;
         val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$continuation`), 1);
         `cancellable$iv`.initCancellability();
         val var10000: Any = `cancellable$iv`.getResult();
         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$continuation`);
         }

         if (var10000 === var8) {
            return var8;
         }
         break;
      case 1:
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   throw new KotlinNothingValueException();
}

public suspend fun delay(timeMillis: Long) {
   if (timeMillis <= 0L) {
      return Unit.INSTANCE;
   } else {
      val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
      `cancellable$iv`.initCancellability();
      val cont: CancellableContinuation = `cancellable$iv`;
      if (timeMillis < java.lang.Long.MAX_VALUE) {
         getDelay(cont.getContext()).scheduleResumeAfterDelay(timeMillis, cont);
      }

      val var10000: Any = `cancellable$iv`.getResult();
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}

public suspend fun delay(duration: Duration) {
   val var10000: Any = delay(toDelayMillis-LRDsOJo(duration), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

internal fun Duration.toDelayMillis(): Long {
   val var2: Boolean = Duration.isPositive-impl(`$this$toDelayMillis_u2dLRDsOJo`);
   val var10000: Long;
   if (var2) {
      var10000 = Duration.getInWholeMilliseconds-impl(
         Duration.plus-LRDsOJo(`$this$toDelayMillis_u2dLRDsOJo`, DurationKt.toDuration(999999L, DurationUnit.NANOSECONDS))
      );
   } else {
      if (var2) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = 0L;
   }

   return var10000;
}
