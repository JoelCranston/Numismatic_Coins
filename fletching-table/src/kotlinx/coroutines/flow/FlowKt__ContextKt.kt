package kotlinx.coroutines.flow

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl
import kotlinx.coroutines.flow.internal.FusibleFlow

@JvmSynthetic
internal class FlowKt__ContextKt {
   @JvmStatic
   public fun <T> Flow<T>.buffer(capacity: Int = -2, onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND): Flow<T> {
      if (capacity < 0 && capacity != -2 && capacity != -1) {
         throw new IllegalArgumentException(("Buffer size should be non-negative, BUFFERED, or CONFLATED, but was $capacity").toString());
      } else if (capacity == -1 && onBufferOverflow != BufferOverflow.SUSPEND) {
         throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
      } else {
         var capacityx: Int = capacity;
         var onBufferOverflowx: BufferOverflow = onBufferOverflow;
         if (capacity == -1) {
            capacityx = 0;
            onBufferOverflowx = BufferOverflow.DROP_OLDEST;
         }

         return (Flow<T>)(if (`$this$buffer` is FusibleFlow)
            FusibleFlow.DefaultImpls.fuse$default(`$this$buffer` as FusibleFlow, null, capacityx, onBufferOverflowx, 1, null)
            else
            new ChannelFlowOperatorImpl(`$this$buffer`, null, capacityx, onBufferOverflowx, 2, null));
      }
   }

   @JvmStatic
   public fun <T> Flow<T>.conflate(): Flow<T> {
      return FlowKt.buffer$default(`$this$conflate`, -1, null, 2, null);
   }

   @JvmStatic
   public fun <T> Flow<T>.flowOn(context: CoroutineContext): Flow<T> {
      checkFlowContext$FlowKt__ContextKt(context);
      return (Flow<T>)(if (context == EmptyCoroutineContext.INSTANCE)
         `$this$flowOn`
         else
         (
            if (`$this$flowOn` is FusibleFlow)
               FusibleFlow.DefaultImpls.fuse$default(`$this$flowOn` as FusibleFlow, context, 0, null, 6, null)
               else
               new ChannelFlowOperatorImpl(`$this$flowOn`, context, 0, null, 12, null)
         ));
   }

   @JvmStatic
   public fun <T> Flow<T>.cancellable(): Flow<T> {
      return (Flow<T>)(if (`$this$cancellable` is CancellableFlow) `$this$cancellable` else new CancellableFlowImpl(`$this$cancellable`));
   }

   @JvmStatic
   private fun checkFlowContext(context: CoroutineContext) {
      if (context.get(Job.Key) != null) {
         throw new IllegalArgumentException(("Flow context cannot contain job in it. Had $context").toString());
      }
   }
}
