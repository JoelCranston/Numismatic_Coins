package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.internal.ChannelFlow

private open class ChannelFlowBuilder<T>(block: (ProducerScope<Any>, Continuation<Unit>) -> Any?,
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = -2,
   onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) : ChannelFlow(context, capacity, onBufferOverflow) {
   private final val block: (ProducerScope<Any>, Continuation<Unit>) -> Any?

   init {
      this.block = block;
   }

   protected override fun create(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): ChannelFlow<Any> {
      return new ChannelFlowBuilder<>(this.block, context, capacity, onBufferOverflow);
   }

   protected override suspend fun collectTo(scope: ProducerScope<Any>) {
      return collectTo$suspendImpl(this, scope, `$completion`);
   }

   public override fun toString(): String {
      return "block[${this.block}] -> ${super.toString()}";
   }
}
