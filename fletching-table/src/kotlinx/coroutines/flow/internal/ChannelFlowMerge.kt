package kotlinx.coroutines.flow.internal

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProduceKt
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.internal.ChannelFlowMerge.collectTo.2
import kotlinx.coroutines.sync.SemaphoreKt

internal class ChannelFlowMerge<T>(flow: Flow<Flow<Any>>,
   concurrency: Int,
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = -2,
   onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) : ChannelFlow(context, capacity, onBufferOverflow) {
   private final val flow: Flow<Flow<Any>>
   private final val concurrency: Int

   init {
      this.flow = flow;
      this.concurrency = concurrency;
   }

   protected override fun create(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): ChannelFlow<Any> {
      return new ChannelFlowMerge<>(this.flow, this.concurrency, context, capacity, onBufferOverflow);
   }

   public override fun produceImpl(scope: CoroutineScope): ReceiveChannel<Any> {
      return ProduceKt.produce(scope, this.context, this.capacity, this.getCollectToFun$kotlinx_coroutines_core());
   }

   protected override suspend fun collectTo(scope: ProducerScope<Any>) {
      val var10000: Any = this.flow
         .collect(
            new 2<>(`$completion`.getContext().get(Job.Key), SemaphoreKt.Semaphore$default(this.concurrency, 0, 2, null), scope, new SendingCollector<>(scope)),
            `$completion`
         );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   protected override fun additionalToStringProps(): String {
      return "concurrency=${this.concurrency}";
   }
}
