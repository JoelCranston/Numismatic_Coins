package kotlinx.coroutines.flow.internal

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProduceKt
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge.collectTo.2.1

@SourceDebugExtension(["SMAP\nMerge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Merge.kt\nkotlinx/coroutines/flow/internal/ChannelLimitedFlowMerge\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,96:1\n1863#2,2:97\n*S KotlinDebug\n*F\n+ 1 Merge.kt\nkotlinx/coroutines/flow/internal/ChannelLimitedFlowMerge\n*L\n91#1:97,2\n*E\n"])
internal class ChannelLimitedFlowMerge<T>(flows: Iterable<Flow<Any>>,
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = -2,
   onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) : ChannelFlow(context, capacity, onBufferOverflow) {
   private final val flows: Iterable<Flow<Any>>

   init {
      this.flows = flows;
   }

   protected override fun create(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): ChannelFlow<Any> {
      return new ChannelLimitedFlowMerge<>(this.flows, context, capacity, onBufferOverflow);
   }

   public override fun produceImpl(scope: CoroutineScope): ReceiveChannel<Any> {
      return ProduceKt.produce(scope, this.context, this.capacity, this.getCollectToFun$kotlinx_coroutines_core());
   }

   protected override suspend fun collectTo(scope: ProducerScope<Any>) {
      val collector: SendingCollector = new SendingCollector(scope);

      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         BuildersKt.launch$default(scope, null, null, new 1(`element$iv` as Flow<? extends T>, collector, null), 3, null);
      }

      return Unit.INSTANCE;
   }
}
