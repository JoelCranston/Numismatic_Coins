package kotlinx.coroutines.flow.internal

import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.DebugStringsKt
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProduceKt
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.internal.ChannelFlow.collectToFun.1

@InternalCoroutinesApi
@SourceDebugExtension(["SMAP\nChannelFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ChannelFlow.kt\nkotlinx/coroutines/flow/internal/ChannelFlow\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,241:1\n1#2:242\n*E\n"])
public abstract class ChannelFlow<T> : FusibleFlow<T> {
   public final val context: CoroutineContext
   public final val capacity: Int
   public final val onBufferOverflow: BufferOverflow

   internal final val collectToFun: (ProducerScope<Any>, Continuation<Unit>) -> Any?
      internal final get() {
         return new 1(this, null);
      }


   internal final val produceCapacity: Int
      internal final get() {
         return if (this.capacity == -3) -2 else this.capacity;
      }


   open fun ChannelFlow(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow) {
      this.context = context;
      this.capacity = capacity;
      this.onBufferOverflow = onBufferOverflow;
      if (DebugKt.getASSERTIONS_ENABLED() && this.capacity == -1) {
         throw new AssertionError();
      }
   }

   public open fun dropChannelOperators(): Flow<Any>? {
      return null;
   }

   public override fun fuse(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): Flow<Any> {
      if (DebugKt.getASSERTIONS_ENABLED() && capacity == -1) {
         throw new AssertionError();
      } else {
         val var8: CoroutineContext = context.plus(this.context);
         val var9: Int;
         val var10: BufferOverflow;
         if (onBufferOverflow != BufferOverflow.SUSPEND) {
            var9 = capacity;
            var10 = onBufferOverflow;
         } else {
            val var10000: Int;
            if (this.capacity == -3) {
               var10000 = capacity;
            } else if (capacity == -3) {
               var10000 = this.capacity;
            } else if (this.capacity == -2) {
               var10000 = capacity;
            } else if (capacity == -2) {
               var10000 = this.capacity;
            } else {
               if (DebugKt.getASSERTIONS_ENABLED() && this.capacity < 0) {
                  throw new AssertionError();
               }

               if (DebugKt.getASSERTIONS_ENABLED() && capacity < 0) {
                  throw new AssertionError();
               }

               var10000 = if (this.capacity + capacity >= 0) this.capacity + capacity else Integer.MAX_VALUE;
            }

            var9 = var10000;
            var10 = this.onBufferOverflow;
         }

         return if (var8 == this.context && var9 == this.capacity && var10 === this.onBufferOverflow) this else this.create(var8, var9, var10);
      }
   }

   protected abstract fun create(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): ChannelFlow<Any> {
   }

   protected abstract suspend fun collectTo(scope: ProducerScope<Any>) {
   }

   public open fun produceImpl(scope: CoroutineScope): ReceiveChannel<Any> {
      return ProduceKt.produce$default(
         scope,
         this.context,
         this.getProduceCapacity$kotlinx_coroutines_core(),
         this.onBufferOverflow,
         CoroutineStart.ATOMIC,
         null,
         this.getCollectToFun$kotlinx_coroutines_core(),
         16,
         null
      );
   }

   public override suspend fun collect(collector: FlowCollector<Any>) {
      return collect$suspendImpl(this, collector, `$completion`);
   }

   protected open fun additionalToStringProps(): String? {
      return null;
   }

   public override fun toString(): String {
      val props: ArrayList = new ArrayList(4);
      val var10000: java.lang.String = this.additionalToStringProps();
      if (var10000 != null) {
         props.add(var10000);
      }

      if (this.context != EmptyCoroutineContext.INSTANCE) {
         props.add("context=${this.context}");
      }

      if (this.capacity != -3) {
         props.add("capacity=${this.capacity}");
      }

      if (this.onBufferOverflow != BufferOverflow.SUSPEND) {
         props.add("onBufferOverflow=${this.onBufferOverflow}");
      }

      return "${DebugStringsKt.getClassSimpleName(this)}[${CollectionsKt.joinToString$default(props, ", ", null, null, 0, null, null, 62, null)}]";
   }
}
