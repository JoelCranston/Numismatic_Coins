package kotlinx.coroutines

import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.Ref
import kotlin.time.Duration
import kotlin.time.DurationKt
import kotlin.time.DurationUnit
import kotlinx.coroutines.TimeoutKt.withTimeoutOrNull.1
import kotlinx.coroutines.intrinsics.UndispatchedKt

public suspend fun <T> withTimeout(timeMillis: Long, block: (CoroutineScope, Continuation<T>) -> Any?): T {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   if (timeMillis <= 0L) {
      throw new TimeoutCancellationException("Timed out immediately");
   } else {
      val var10000: Any = setupTimeout(new TimeoutCoroutine(timeMillis, `$completion`), block);
      if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
         DebugProbesKt.probeCoroutineSuspended(`$completion`);
      }

      return var10000;
   }
}

public suspend fun <T> withTimeout(timeout: Duration, block: (CoroutineScope, Continuation<T>) -> Any?): T {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   return withTimeout(DelayKt.toDelayMillis-LRDsOJo(timeout), block, `$completion`);
}

public suspend fun <T> withTimeoutOrNull(timeMillis: Long, block: (CoroutineScope, Continuation<T>) -> Any?): T? {
   var `$continuation`: Continuation;
   label61: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label61;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var coroutine: Ref.ObjectRef;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (timeMillis <= 0L) {
            return null;
         }

         coroutine = new Ref.ObjectRef();

         try {
            `$continuation`.L$0 = block;
            `$continuation`.L$1 = coroutine;
            `$continuation`.J$0 = timeMillis;
            `$continuation`.label = 1;
            val timeoutCoroutine: TimeoutCoroutine = new TimeoutCoroutine(timeMillis, `$continuation`);
            coroutine.element = (T)timeoutCoroutine;
            var10000 = setupTimeout(timeoutCoroutine, block);
            if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               DebugProbesKt.probeCoroutineSuspended(`$continuation`);
            }
         } catch (var13: TimeoutCancellationException) {
            if (var13.coroutine === coroutine.element) {
               return null;
            }

            throw var13;
         }

         if (var10000 === var10) {
            return var10;
         }
         break;
      case 1:
         coroutine = `$continuation`.L$1 as Ref.ObjectRef;
         block = `$continuation`.L$0 as Function2;

         try {
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         } catch (var12: TimeoutCancellationException) {
            if (var12.coroutine === coroutine.element) {
               return null;
            }

            throw var12;
         }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   try {
      return var10000;
   } catch (var11: TimeoutCancellationException) {
      if (var11.coroutine === coroutine.element) {
         return null;
      } else {
         throw var11;
      }
   }
}

public suspend fun <T> withTimeoutOrNull(timeout: Duration, block: (CoroutineScope, Continuation<T>) -> Any?): T? {
   return withTimeoutOrNull(DelayKt.toDelayMillis-LRDsOJo(timeout), block, `$completion`);
}

private fun <U, T : U> setupTimeout(coroutine: TimeoutCoroutine<U, T>, block: (CoroutineScope, Continuation<T>) -> Any?): Any? {
   JobKt.disposeOnCompletion(coroutine, DelayKt.getDelay(coroutine.uCont.getContext()).invokeOnTimeout(coroutine.time, coroutine, coroutine.getContext()));
   return UndispatchedKt.startUndispatchedOrReturnIgnoreTimeout(coroutine, coroutine, block);
}

internal fun TimeoutCancellationException(time: Long, delay: Delay, coroutine: Job): TimeoutCancellationException {
   var var5: java.lang.String;
   label15: {
      val var10000: DelayWithTimeoutDiagnostics = delay as? DelayWithTimeoutDiagnostics;
      if ((delay as? DelayWithTimeoutDiagnostics) != null) {
         var5 = var10000.timeoutMessage-LRDsOJo(DurationKt.toDuration(time, DurationUnit.MILLISECONDS));
         if (var5 != null) {
            break label15;
         }
      }

      var5 = "Timed out waiting for $time ms";
   }

   return new TimeoutCancellationException(var5, coroutine);
}
