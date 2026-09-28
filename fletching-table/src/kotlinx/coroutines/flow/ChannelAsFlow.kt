package kotlinx.coroutines.flow

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.atomicfu.AtomicBoolean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.flow.internal.SendingCollector

@SourceDebugExtension(["SMAP\nChannels.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Channels.kt\nkotlinx/coroutines/flow/ChannelAsFlow\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,158:1\n1#2:159\n*E\n"])
private class ChannelAsFlow<T>(channel: ReceiveChannel<Any>,
   consume: Boolean,
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = -3,
   onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) : ChannelFlow(context, capacity, onBufferOverflow) {
   private final val channel: ReceiveChannel<Any>
   private final val consume: Boolean
   private final val consumed: AtomicBoolean

   init {
      this.channel = channel;
      this.consume = consume;
   }

   private fun markConsumed() {
      if (this.consume && getConsumed$volatile$FU().getAndSet(this, 1) == 1) {
         throw new IllegalStateException("ReceiveChannel.consumeAsFlow can be collected just once".toString());
      }
   }

   protected override fun create(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): ChannelFlow<Any> {
      return new ChannelAsFlow<>(this.channel, this.consume, context, capacity, onBufferOverflow);
   }

   public override fun dropChannelOperators(): Flow<Any> {
      return new ChannelAsFlow<>(this.channel, this.consume, null, 0, null, 28, null);
   }

   protected override suspend fun collectTo(scope: ProducerScope<Any>) {
      val var10000: Any = FlowKt__ChannelsKt.access$emitAllImpl$FlowKt__ChannelsKt(new SendingCollector(scope), this.channel, this.consume, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   public override fun produceImpl(scope: CoroutineScope): ReceiveChannel<Any> {
      this.markConsumed();
      return if (this.capacity == -3) this.channel else super.produceImpl(scope);
   }

   public override suspend fun collect(collector: FlowCollector<Any>) {
      if (this.capacity == -3) {
         this.markConsumed();
         val var3: Any = FlowKt__ChannelsKt.access$emitAllImpl$FlowKt__ChannelsKt(collector, this.channel, this.consume, `$completion`);
         return if (var3 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var3 else Unit.INSTANCE;
      } else {
         val var10000: Any = super.collect(collector, `$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }

   protected override fun additionalToStringProps(): String {
      return "channel=${this.channel}";
   }
}
