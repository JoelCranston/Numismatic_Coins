@file:SourceDebugExtension(["SMAP\nCancellableContinuation.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,498:1\n1#2:499\n*E\n"])

package kotlinx.coroutines

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.DispatchedContinuation

internal fun <T> CancellableContinuation<T>.invokeOnCancellation(handler: CancelHandler) {
   if (`$this$invokeOnCancellation` is CancellableContinuationImpl) {
      (`$this$invokeOnCancellation` as CancellableContinuationImpl).invokeOnCancellationInternal$kotlinx_coroutines_core(handler);
   } else {
      throw new UnsupportedOperationException("third-party implementation of CancellableContinuation is not supported");
   }
}

public suspend inline fun <T> suspendCancellableCoroutine(crossinline block: (CancellableContinuation<T>) -> Unit): T {
   val cancellable: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
   cancellable.initCancellability();
   block.invoke(cancellable);
   val var10000: Any = cancellable.getResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   return var10000;
}

fun <T> `suspendCancellableCoroutine$$forInline`(block: (CancellableContinuation<? super T>?) -> Unit, `$completion`: Continuation<? super T>): Any {
   InlineMarker.mark(0);
   val cancellable: CancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(`$completion`), 1);
   cancellable.initCancellability();
   block.invoke(cancellable);
   val var10000: Any = cancellable.getResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   InlineMarker.mark(1);
   return var10000;
}

internal suspend inline fun <T> suspendCancellableCoroutineReusable(crossinline block: (CancellableContinuationImpl<T>) -> Unit): T {
   val cancellable: CancellableContinuationImpl = getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(`$completion`));

   try {
      block.invoke(cancellable);
   } catch (var7: java.lang.Throwable) {
      cancellable.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
      throw var7;
   }

   val var10000: Any = cancellable.getResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   return var10000;
}

fun <T> `suspendCancellableCoroutineReusable$$forInline`(block: (CancellableContinuationImpl<? super T>?) -> Unit, `$completion`: Continuation<? super T>): Any {
   InlineMarker.mark(0);
   val cancellable: CancellableContinuationImpl = getOrCreateCancellableContinuation(IntrinsicsKt.intercepted(`$completion`));

   try {
      block.invoke(cancellable);
   } catch (var7: java.lang.Throwable) {
      cancellable.releaseClaimedReusableContinuation$kotlinx_coroutines_core();
      throw var7;
   }

   val var10000: Any = cancellable.getResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   InlineMarker.mark(1);
   return var10000;
}

internal fun <T> getOrCreateCancellableContinuation(delegate: Continuation<T>): CancellableContinuationImpl<T> {
   if (delegate !is DispatchedContinuation) {
      return new CancellableContinuationImpl(delegate, 1);
   } else {
      var var10000: CancellableContinuationImpl = (delegate as DispatchedContinuation).claimReusableCancellableContinuation$kotlinx_coroutines_core();
      if (var10000 != null) {
         var10000 = if (var10000.resetStateReusable()) var10000 else null;
         if (var10000 != null) {
            return var10000;
         }
      }

      return new CancellableContinuationImpl(delegate, 2);
   }
}

@InternalCoroutinesApi
public fun CancellableContinuation<*>.disposeOnCancellation(handle: DisposableHandle) {
   invokeOnCancellation(`$this$disposeOnCancellation`, new DisposeOnCancel(handle));
}
