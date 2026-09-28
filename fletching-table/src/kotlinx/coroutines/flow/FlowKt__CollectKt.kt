package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.InlineMarker
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.FlowKt__CollectKt.collectIndexed.2
import kotlinx.coroutines.flow.FlowKt__CollectKt.launchIn.1
import kotlinx.coroutines.flow.internal.NopCollector

@JvmSynthetic
internal class FlowKt__CollectKt {
   @JvmStatic
   public suspend fun Flow<*>.collect() {
      val var10000: Any = `$this$collect`.collect(NopCollector.INSTANCE, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @JvmStatic
   public fun <T> Flow<T>.launchIn(scope: CoroutineScope): Job {
      return BuildersKt.launch$default(scope, null, null, new 1(`$this$launchIn`, null), 3, null);
   }

   @JvmStatic
   public suspend inline fun <T> Flow<T>.collectIndexed(crossinline action: (Int, T, Continuation<Unit>) -> Any?) {
      val var10000: Any = `$this$collectIndexed`.collect(new 2(action), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @JvmStatic
   fun <T> Flow<? extends T>.`collectIndexed$$forInline`(action: (Int?, T?, Continuation<? super Unit>?) -> Any, `$completion`: Continuation<? super Unit>): Any {
      val var10001: FlowCollector = new 2(action);
      InlineMarker.mark(0);
      `$this$collectIndexed`.collect(var10001, `$completion`);
      InlineMarker.mark(1);
      return Unit.INSTANCE;
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.collectLatest(action: (T, Continuation<Unit>) -> Any?) {
      val var10000: Any = FlowKt.collect(FlowKt.buffer$default(FlowKt.mapLatest(`$this$collectLatest`, action), 0, null, 2, null), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   @JvmStatic
   public suspend fun <T> FlowCollector<T>.emitAll(flow: Flow<T>) {
      FlowKt.ensureActive(`$this$emitAll`);
      val var10000: Any = flow.collect(`$this$emitAll`, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
