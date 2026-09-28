package kotlinx.coroutines.flow.internal

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.internal.ChannelFlowOperator.collectWithContextUndispatched.2

internal abstract class ChannelFlowOperator<S, T> : ChannelFlow<T> {
   protected final val flow: Flow<Any>

   open fun ChannelFlowOperator(flow: Flow<? extends S>, context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow) {
      super(context, capacity, onBufferOverflow);
      this.flow = flow;
   }

   protected abstract suspend fun flowCollect(collector: FlowCollector<Any>) {
   }

   private suspend fun collectWithContextUndispatched(collector: FlowCollector<Any>, newContext: CoroutineContext) {
      return ChannelFlowKt.withContextUndispatched$default(
         newContext,
         ChannelFlowKt.access$withUndispatchedContextCollector(collector, `$completion`.getContext()),
         null,
         new 2(this, null),
         `$completion`,
         4,
         null
      );
   }

   protected override suspend fun collectTo(scope: ProducerScope<Any>) {
      return collectTo$suspendImpl(this, scope, `$completion`);
   }

   public override suspend fun collect(collector: FlowCollector<Any>) {
      return collect$suspendImpl(this, collector, `$completion`);
   }

   public override fun toString(): String {
      return "${this.flow} -> ${super.toString()}";
   }
}
