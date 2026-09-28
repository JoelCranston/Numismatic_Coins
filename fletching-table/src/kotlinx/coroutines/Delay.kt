package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.SourceDebugExtension

@InternalCoroutinesApi
public interface Delay {
   @Deprecated(message = "Deprecated without replacement as an internal method never intended for public use", level = DeprecationLevel.ERROR)
   public open suspend fun delay(time: Long) {
   }

   public abstract fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
   }

   public open fun invokeOnTimeout(timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
   }

   // $VF: Class flags could not be determined
   @SourceDebugExtension(["SMAP\nDelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/Delay$DefaultImpls\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,159:1\n426#2,11:160\n*S KotlinDebug\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/Delay$DefaultImpls\n*L\n27#1:160,11\n*E\n"])
   internal class DefaultImpls {
      /** @deprecated */
      @Deprecated(message = "Deprecated without replacement as an internal method never intended for public use", level = DeprecationLevel.ERROR)
      @JvmStatic
      fun delay(`$this`: Delay, time: Long, `$completion`: Continuation<? super Unit>): Any {
         if (time <= 0L) {
            return Unit.INSTANCE;
         } else {
            val `cancellable$iv`: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
            `cancellable$iv`.initCancellability();
            `$this`.scheduleResumeAfterDelay(time, `cancellable$iv`);
            val var10000: Any = `cancellable$iv`.getResult();
            if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
               DebugProbesKt.probeCoroutineSuspended(`$completion`);
            }

            return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
         }
      }

      @JvmStatic
      fun invokeOnTimeout(`$this`: Delay, timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
         return DefaultExecutorKt.getDefaultDelay().invokeOnTimeout(timeMillis, block, context);
      }
   }
}
