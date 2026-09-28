package kotlinx.coroutines.flow

import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.Ref
import kotlinx.coroutines.flow.DistinctFlowImpl.collect.2
import kotlinx.coroutines.flow.internal.NullSurrogateKt

private class DistinctFlowImpl<T>(upstream: Flow<Any>, keySelector: (Any) -> Any?, areEquivalent: (Any?, Any?) -> Boolean) : Flow<T> {
   private final val upstream: Flow<Any>
   public final val keySelector: (Any) -> Any?
   public final val areEquivalent: (Any?, Any?) -> Boolean

   init {
      this.upstream = upstream;
      this.keySelector = keySelector;
      this.areEquivalent = areEquivalent;
   }

   public override suspend fun collect(collector: FlowCollector<Any>) {
      val previousKey: Ref.ObjectRef = new Ref.ObjectRef();
      previousKey.element = (T)NullSurrogateKt.NULL;
      val var10000: Any = this.upstream.collect(new 2<>(this, previousKey, collector), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
