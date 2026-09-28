package kotlinx.coroutines.selects

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher

@PublishedApi
internal suspend inline fun <R> selectOld(crossinline builder: (SelectBuilder<R>) -> Unit): R {
   val scope: SelectBuilderImpl = new SelectBuilderImpl(`$completion`);

   try {
      builder.invoke(scope);
   } catch (var7: java.lang.Throwable) {
      scope.handleBuilderException(var7);
   }

   val var10000: Any = scope.getResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   return var10000;
}

@PublishedApi
fun <R> `selectOld$$forInline`(builder: (SelectBuilder<? super R>?) -> Unit, `$completion`: Continuation<? super R>): Any {
   InlineMarker.mark(0);
   val scope: SelectBuilderImpl = new SelectBuilderImpl(`$completion`);

   try {
      builder.invoke(scope);
   } catch (var7: java.lang.Throwable) {
      scope.handleBuilderException(var7);
   }

   val var10000: Any = scope.getResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   InlineMarker.mark(1);
   return var10000;
}

@PublishedApi
internal suspend inline fun <R> selectUnbiasedOld(crossinline builder: (SelectBuilder<R>) -> Unit): R {
   val scope: UnbiasedSelectBuilderImpl = new UnbiasedSelectBuilderImpl(`$completion`);

   try {
      builder.invoke(scope);
   } catch (var7: java.lang.Throwable) {
      scope.handleBuilderException(var7);
   }

   val var10000: Any = scope.initSelectResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   return var10000;
}

@PublishedApi
fun <R> `selectUnbiasedOld$$forInline`(builder: (SelectBuilder<? super R>?) -> Unit, `$completion`: Continuation<? super R>): Any {
   InlineMarker.mark(0);
   val scope: UnbiasedSelectBuilderImpl = new UnbiasedSelectBuilderImpl(`$completion`);

   try {
      builder.invoke(scope);
   } catch (var7: java.lang.Throwable) {
      scope.handleBuilderException(var7);
   }

   val var10000: Any = scope.initSelectResult();
   if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
      DebugProbesKt.probeCoroutineSuspended(`$completion`);
   }

   InlineMarker.mark(1);
   return var10000;
}

private fun <T> CancellableContinuation<T>.resumeUndispatched(result: T) {
   val dispatcher: CoroutineDispatcher = `$this$resumeUndispatched`.getContext().get(CoroutineDispatcher.Key);
   if (dispatcher != null) {
      `$this$resumeUndispatched`.resumeUndispatched(dispatcher, result);
   } else {
      `$this$resumeUndispatched`.resumeWith(Result.constructor-impl(result));
   }
}

private fun CancellableContinuation<*>.resumeUndispatchedWithException(exception: Throwable) {
   val dispatcher: CoroutineDispatcher = `$this$resumeUndispatchedWithException`.getContext().get(CoroutineDispatcher.Key);
   if (dispatcher != null) {
      `$this$resumeUndispatchedWithException`.resumeUndispatchedWithException(dispatcher, exception);
   } else {
      `$this$resumeUndispatchedWithException`.resumeWith(Result.constructor-impl(ResultKt.createFailure(exception)));
   }
}

@JvmSynthetic
fun `access$resumeUndispatchedWithException`(`$receiver`: CancellableContinuation, exception: java.lang.Throwable) {
   resumeUndispatchedWithException(`$receiver`, exception);
}

@JvmSynthetic
fun `access$resumeUndispatched`(`$receiver`: CancellableContinuation, result: Any) {
   resumeUndispatched(`$receiver`, result);
}
