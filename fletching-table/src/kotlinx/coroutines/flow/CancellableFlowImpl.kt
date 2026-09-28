package kotlinx.coroutines.flow

import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.flow.CancellableFlowImpl.collect.2

private class CancellableFlowImpl<T>(flow: Flow<Any>) : CancellableFlow<T> {
   private final val flow: Flow<Any>

   init {
      this.flow = flow;
   }

   public override suspend fun collect(collector: FlowCollector<Any>) {
      val var10000: Any = this.flow.collect(new 2<>(collector), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
