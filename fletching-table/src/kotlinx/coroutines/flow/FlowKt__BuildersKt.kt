package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.1
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.10
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.2
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.3
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.4
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.5
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.6
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.7
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.8
import kotlinx.coroutines.flow.FlowKt__BuildersKt.asFlow..inlined.unsafeFlow.9

@SourceDebugExtension(["SMAP\nBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Builders.kt\nkotlinx/coroutines/flow/FlowKt__BuildersKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,350:1\n105#2:351\n105#2:352\n105#2:353\n105#2:354\n105#2:355\n105#2:356\n105#2:357\n105#2:358\n105#2:359\n105#2:360\n105#2:361\n105#2:362\n*S KotlinDebug\n*F\n+ 1 Builders.kt\nkotlinx/coroutines/flow/FlowKt__BuildersKt\n*L\n64#1:351\n78#1:352\n85#1:353\n94#1:354\n103#1:355\n118#1:356\n127#1:357\n149#1:358\n160#1:359\n171#1:360\n180#1:361\n189#1:362\n*E\n"])
@JvmSynthetic
internal class FlowKt__BuildersKt {
   @JvmStatic
   public fun <T> flow(block: (FlowCollector<T>, Continuation<Unit>) -> Any?): Flow<T> {
      return new SafeFlow(block);
   }

   @JvmStatic
   public fun <T> (() -> T).asFlow(): Flow<T> {
      return new 1(`$this$asFlow`);
   }

   @JvmStatic
   public fun <T> ((Continuation<T>) -> Any?).asFlow(): Flow<T> {
      return new 2(`$this$asFlow`);
   }

   @JvmStatic
   public fun <T> Iterable<T>.asFlow(): Flow<T> {
      return new 3(`$this$asFlow`);
   }

   @JvmStatic
   public fun <T> Iterator<T>.asFlow(): Flow<T> {
      return new 4(`$this$asFlow`);
   }

   @JvmStatic
   public fun <T> Sequence<T>.asFlow(): Flow<T> {
      return new 5(`$this$asFlow`);
   }

   @JvmStatic
   public fun <T> flowOf(vararg elements: T): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.1(elements);
   }

   @JvmStatic
   public fun <T> flowOf(value: T): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__BuildersKt.flowOf..inlined.unsafeFlow.2(value);
   }

   @JvmStatic
   public fun <T> emptyFlow(): Flow<T> {
      return EmptyFlow.INSTANCE;
   }

   @JvmStatic
   public fun <T> Array<T>.asFlow(): Flow<T> {
      return new 6(`$this$asFlow`);
   }

   @JvmStatic
   public fun IntArray.asFlow(): Flow<Int> {
      return new 7(`$this$asFlow`);
   }

   @JvmStatic
   public fun LongArray.asFlow(): Flow<Long> {
      return new 8(`$this$asFlow`);
   }

   @JvmStatic
   public fun IntRange.asFlow(): Flow<Int> {
      return new 9(`$this$asFlow`);
   }

   @JvmStatic
   public fun LongRange.asFlow(): Flow<Long> {
      return new 10(`$this$asFlow`);
   }

   @JvmStatic
   public fun <T> channelFlow(block: (ProducerScope<T>, Continuation<Unit>) -> Any?): Flow<T> {
      return new ChannelFlowBuilder(block, null, 0, null, 14, null);
   }

   @JvmStatic
   public fun <T> callbackFlow(block: (ProducerScope<T>, Continuation<Unit>) -> Any?): Flow<T> {
      return new CallbackFlowBuilder(block, null, 0, null, 14, null);
   }
}
