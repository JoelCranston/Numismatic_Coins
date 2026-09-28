package kotlinx.coroutines.flow.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.internal.UndispatchedContextCollector.emitRef.1
import kotlinx.coroutines.internal.ThreadContextKt

private class UndispatchedContextCollector<T>(downstream: FlowCollector<Any>, emitContext: CoroutineContext) : FlowCollector<T> {
   private final val emitContext: CoroutineContext
   private final val countOrElement: Any
   private final val emitRef: (Any, Continuation<Unit>) -> Any?

   init {
      this.emitContext = emitContext;
      this.countOrElement = ThreadContextKt.threadContextElements(this.emitContext);
      this.emitRef = new 1(downstream, null);
   }

   public override suspend fun emit(value: Any) {
      val var10000: Any = ChannelFlowKt.withContextUndispatched(this.emitContext, value, this.countOrElement, this.emitRef, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
