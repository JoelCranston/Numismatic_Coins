package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapConcat..inlined.map.1
import kotlinx.coroutines.flow.internal.ChannelFlowMerge
import kotlinx.coroutines.flow.internal.ChannelFlowTransformLatest
import kotlinx.coroutines.flow.internal.ChannelLimitedFlowMerge
import kotlinx.coroutines.internal.SystemPropsKt

@SourceDebugExtension(["SMAP\nMerge.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Merge.kt\nkotlinx/coroutines/flow/FlowKt__MergeKt\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 4 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,214:1\n49#2:215\n51#2:219\n49#2:220\n51#2:224\n46#3:216\n51#3:218\n46#3:221\n51#3:223\n105#4:217\n105#4:222\n105#4:225\n1#5:226\n*S KotlinDebug\n*F\n+ 1 Merge.kt\nkotlinx/coroutines/flow/FlowKt__MergeKt\n*L\n43#1:215\n43#1:219\n70#1:220\n70#1:224\n43#1:216\n43#1:218\n70#1:221\n70#1:223\n43#1:217\n70#1:222\n78#1:225\n*E\n"])
@JvmSynthetic
internal class FlowKt__MergeKt {
   @FlowPreview
   public const val DEFAULT_CONCURRENCY_PROPERTY_NAME: String

   @FlowPreview
   public final val DEFAULT_CONCURRENCY: Int = SystemPropsKt.systemProp("kotlinx.coroutines.flow.defaultConcurrency", 16, 1, Integer.MAX_VALUE)

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T, R> Flow<T>.flatMapConcat(transform: (T, Continuation<Flow<R>>) -> Any?): Flow<R> {
      return FlowKt.flattenConcat(new 1(`$this$flatMapConcat`, transform));
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T, R> Flow<T>.flatMapMerge(concurrency: Int = DEFAULT_CONCURRENCY, transform: (T, Continuation<Flow<R>>) -> Any?): Flow<R> {
      return FlowKt.flattenMerge(new kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapMerge..inlined.map.1(`$this$flatMapMerge`, transform), concurrency);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T> Flow<Flow<T>>.flattenConcat(): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__MergeKt.flattenConcat..inlined.unsafeFlow.1(`$this$flattenConcat`);
   }

   @JvmStatic
   public fun <T> Iterable<Flow<T>>.merge(): Flow<T> {
      return new ChannelLimitedFlowMerge(`$this$merge`, null, 0, null, 14, null);
   }

   @JvmStatic
   public fun <T> merge(vararg flows: Flow<T>): Flow<T> {
      return FlowKt.merge(ArraysKt.asIterable(flows));
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T> Flow<Flow<T>>.flattenMerge(concurrency: Int = DEFAULT_CONCURRENCY): Flow<T> {
      if (concurrency <= 0) {
         throw new IllegalArgumentException(("Expected positive concurrency level, but had $concurrency").toString());
      } else {
         return (Flow<T>)(if (concurrency == 1)
            FlowKt.flattenConcat(`$this$flattenMerge`)
            else
            new ChannelFlowMerge(`$this$flattenMerge`, concurrency, null, 0, null, 28, null));
      }
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T, R> Flow<T>.transformLatest(transform: (FlowCollector<R>, T, Continuation<Unit>) -> Any?): Flow<R> {
      return new ChannelFlowTransformLatest(transform, `$this$transformLatest`, null, 0, null, 28, null);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public inline fun <T, R> Flow<T>.flatMapLatest(crossinline transform: (T, Continuation<Flow<R>>) -> Any?): Flow<R> {
      return FlowKt.transformLatest(`$this$flatMapLatest`, new kotlinx.coroutines.flow.FlowKt__MergeKt.flatMapLatest.1(transform, null));
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T, R> Flow<T>.mapLatest(transform: (T, Continuation<R>) -> Any?): Flow<R> {
      return FlowKt.transformLatest(`$this$mapLatest`, new kotlinx.coroutines.flow.FlowKt__MergeKt.mapLatest.1(transform, null));
   }
}
