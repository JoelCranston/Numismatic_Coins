@file:SourceDebugExtension(["SMAP\nSharedFlow.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SharedFlow.kt\nkotlinx/coroutines/flow/SharedFlowKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,746:1\n1#2:747\n*E\n"])

package kotlinx.coroutines.flow

import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.internal.ChannelFlowOperatorImpl
import kotlinx.coroutines.internal.Symbol

internal final val NO_VALUE: Symbol = new Symbol("NO_VALUE")

public fun <T> MutableSharedFlow(replay: Int = 0, extraBufferCapacity: Int = 0, onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND): MutableSharedFlow<
      T
   > {
   if (replay < 0) {
      throw new IllegalArgumentException(("replay cannot be negative, but was $replay").toString());
   } else if (extraBufferCapacity < 0) {
      throw new IllegalArgumentException(("extraBufferCapacity cannot be negative, but was $extraBufferCapacity").toString());
   } else if (replay <= 0 && extraBufferCapacity <= 0 && onBufferOverflow != BufferOverflow.SUSPEND) {
      throw new IllegalArgumentException(
         ("replay or extraBufferCapacity must be positive with non-default onBufferOverflow strategy $onBufferOverflow").toString()
      );
   } else {
      return new SharedFlowImpl(replay, if (replay + extraBufferCapacity < 0) Integer.MAX_VALUE else replay + extraBufferCapacity, onBufferOverflow);
   }
}

@JvmSynthetic
fun `MutableSharedFlow$default`(var0: Int, var1: Int, var2: BufferOverflow, var3: Int, var4: Any): MutableSharedFlow {
   if ((var3 and 1) != 0) {
      var0 = 0;
   }

   if ((var3 and 2) != 0) {
      var1 = 0;
   }

   if ((var3 and 4) != 0) {
      var2 = BufferOverflow.SUSPEND;
   }

   return MutableSharedFlow(var0, var1, var2);
}

private fun Array<Any?>.getBufferAt(index: Long): Any? {
   return `$this$getBufferAt`[(int)index and `$this$getBufferAt`.length - 1];
}

private fun Array<Any?>.setBufferAt(index: Long, item: Any?) {
   `$this$setBufferAt`[(int)index and `$this$setBufferAt`.length - 1] = item;
}

internal fun <T> SharedFlow<T>.fuseSharedFlow(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): Flow<T> {
   return (Flow<T>)(if ((capacity == 0 || capacity == -3) && onBufferOverflow === BufferOverflow.SUSPEND)
      `$this$fuseSharedFlow`
      else
      new ChannelFlowOperatorImpl(`$this$fuseSharedFlow`, context, capacity, onBufferOverflow));
}

@JvmSynthetic
fun `access$getBufferAt`(`$receiver`: Array<Any>, index: Long): Any {
   return getBufferAt(`$receiver`, index);
}

@JvmSynthetic
fun `access$setBufferAt`(`$receiver`: Array<Any>, index: Long, item: Any) {
   setBufferAt(`$receiver`, index, item);
}
