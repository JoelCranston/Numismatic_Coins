@file:SourceDebugExtension(["SMAP\nCancellable.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Cancellable.kt\nkotlinx/coroutines/intrinsics/CancellableKt\n*L\n1#1,65:1\n45#1,6:66\n45#1,6:72\n45#1,6:78\n*S KotlinDebug\n*F\n+ 1 Cancellable.kt\nkotlinx/coroutines/intrinsics/CancellableKt\n*L\n15#1:66,6\n25#1:72,6\n34#1:78,6\n*E\n"])

package kotlinx.coroutines.intrinsics

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.DispatchException
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.DispatchedContinuationKt

@InternalCoroutinesApi
public fun <T> ((Continuation<T>) -> Any?).startCoroutineCancellable(completion: Continuation<T>) {
   try {
      DispatchedContinuationKt.resumeCancellableWith(
         IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(`$this$startCoroutineCancellable`, completion)),
         Result.constructor-impl(Unit.INSTANCE)
      );
   } catch (var5: java.lang.Throwable) {
      dispatcherFailure(completion, var5);
   }
}

internal fun <R, T> ((R, Continuation<T>) -> Any?).startCoroutineCancellable(receiver: R, completion: Continuation<T>) {
   try {
      DispatchedContinuationKt.resumeCancellableWith(
         IntrinsicsKt.intercepted(IntrinsicsKt.createCoroutineUnintercepted(`$this$startCoroutineCancellable`, receiver, completion)),
         Result.constructor-impl(Unit.INSTANCE)
      );
   } catch (var6: java.lang.Throwable) {
      dispatcherFailure(completion, var6);
   }
}

internal fun Continuation<Unit>.startCoroutineCancellable(fatalCompletion: Continuation<*>) {
   try {
      DispatchedContinuationKt.resumeCancellableWith(IntrinsicsKt.intercepted(`$this$startCoroutineCancellable`), Result.constructor-impl(Unit.INSTANCE));
   } catch (var5: java.lang.Throwable) {
      dispatcherFailure(fatalCompletion, var5);
   }
}

private inline fun runSafely(completion: Continuation<*>, block: () -> Unit) {
   try {
      block.invoke();
   } catch (var4: java.lang.Throwable) {
      dispatcherFailure(completion, var4);
   }
}

private fun dispatcherFailure(completion: Continuation<*>, e: Throwable) {
   val reportException: java.lang.Throwable = if (e is DispatchException) (e as DispatchException).getCause() else e;
   completion.resumeWith(Result.constructor-impl(ResultKt.createFailure(reportException)));
   throw reportException;
}
