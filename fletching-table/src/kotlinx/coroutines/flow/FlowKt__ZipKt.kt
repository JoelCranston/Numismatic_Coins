package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.unsafeFlow.1
import kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.2
import kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.3
import kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.4
import kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.5
import kotlinx.coroutines.flow.internal.CombineKt

@SourceDebugExtension(["SMAP\nZip.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Zip.kt\nkotlinx/coroutines/flow/FlowKt__ZipKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,328:1\n268#1,3:330\n268#1,3:333\n257#1:336\n259#1:338\n268#1,3:339\n257#1:342\n259#1:344\n268#1,3:345\n257#1:348\n259#1:350\n268#1,3:351\n105#2:329\n105#2:337\n105#2:343\n105#2:349\n105#2:354\n105#2:355\n105#2:360\n37#3:356\n36#3,3:357\n37#3:361\n36#3,3:362\n*S KotlinDebug\n*F\n+ 1 Zip.kt\nkotlinx/coroutines/flow/FlowKt__ZipKt\n*L\n71#1:330,3\n99#1:333,3\n115#1:336\n115#1:338\n134#1:339,3\n152#1:342\n152#1:344\n173#1:345,3\n193#1:348\n193#1:350\n216#1:351,3\n28#1:329\n115#1:337\n152#1:343\n193#1:349\n233#1:354\n257#1:355\n284#1:360\n283#1:356\n283#1:357,3\n302#1:361\n302#1:362,3\n*E\n"])
@JvmSynthetic
internal class FlowKt__ZipKt {
   @JvmName(name = "flowCombine")
   @JvmStatic
   public fun <T1, T2, R> Flow<T1>.combine(flow: Flow<T2>, transform: (T1, T2, Continuation<R>) -> Any?): Flow<R> {
      return new 1(`$this$combine`, flow, transform);
   }

   @JvmStatic
   public fun <T1, T2, R> combine(flow: Flow<T1>, flow2: Flow<T2>, transform: (T1, T2, Continuation<R>) -> Any?): Flow<R> {
      return FlowKt.flowCombine(flow, flow2, transform);
   }

   @JvmName(name = "flowCombineTransform")
   @JvmStatic
   public fun <T1, T2, R> Flow<T1>.combineTransform(flow: Flow<T2>, transform: (FlowCollector<R>, T1, T2, Continuation<Unit>) -> Any?): Flow<R> {
      return FlowKt.flow(
         new kotlinx.coroutines.flow.FlowKt__ZipKt.combineTransform..inlined.combineTransformUnsafe.FlowKt__ZipKt.1(
            new Flow[]{`$this$combineTransform`, flow}, null, transform
         )
      );
   }

   @JvmStatic
   public fun <T1, T2, R> combineTransform(flow: Flow<T1>, flow2: Flow<T2>, transform: (FlowCollector<R>, T1, T2, Continuation<Unit>) -> Any?): Flow<R> {
      return FlowKt.flow(new 2(new Flow[]{flow, flow2}, null, transform));
   }

   @JvmStatic
   public fun <T1, T2, T3, R> combine(flow: Flow<T1>, flow2: Flow<T2>, flow3: Flow<T3>, transform: (T1, T2, T3, Continuation<R>) -> Any?): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.1(new Flow[]{flow, flow2, flow3}, transform);
   }

   @JvmStatic
   public fun <T1, T2, T3, R> combineTransform(
      flow: Flow<T1>,
      flow2: Flow<T2>,
      flow3: Flow<T3>,
      transform: (FlowCollector<R>, T1, T2, T3, Continuation<Unit>) -> Any?
   ): Flow<R> {
      return FlowKt.flow(new 3(new Flow[]{flow, flow2, flow3}, null, transform));
   }

   @JvmStatic
   public fun <T1, T2, T3, T4, R> combine(
      flow: Flow<T1>,
      flow2: Flow<T2>,
      flow3: Flow<T3>,
      flow4: Flow<T4>,
      transform: (T1, T2, T3, T4, Continuation<R>) -> Any?
   ): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.2(new Flow[]{flow, flow2, flow3, flow4}, transform);
   }

   @JvmStatic
   public fun <T1, T2, T3, T4, R> combineTransform(
      flow: Flow<T1>,
      flow2: Flow<T2>,
      flow3: Flow<T3>,
      flow4: Flow<T4>,
      transform: (FlowCollector<R>, T1, T2, T3, T4, Continuation<Unit>) -> Any?
   ): Flow<R> {
      return FlowKt.flow(new 4(new Flow[]{flow, flow2, flow3, flow4}, null, transform));
   }

   @JvmStatic
   public fun <T1, T2, T3, T4, T5, R> combine(
      flow: Flow<T1>,
      flow2: Flow<T2>,
      flow3: Flow<T3>,
      flow4: Flow<T4>,
      flow5: Flow<T5>,
      transform: (T1, T2, T3, T4, T5, Continuation<R>) -> Any?
   ): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__ZipKt.combine..inlined.combineUnsafe.FlowKt__ZipKt.3(new Flow[]{flow, flow2, flow3, flow4, flow5}, transform);
   }

   @JvmStatic
   public fun <T1, T2, T3, T4, T5, R> combineTransform(
      flow: Flow<T1>,
      flow2: Flow<T2>,
      flow3: Flow<T3>,
      flow4: Flow<T4>,
      flow5: Flow<T5>,
      transform: (FlowCollector<R>, T1, T2, T3, T4, T5, Continuation<Unit>) -> Any?
   ): Flow<R> {
      return FlowKt.flow(new 5(new Flow[]{flow, flow2, flow3, flow4, flow5}, null, transform));
   }

   @JvmStatic
   private fun <T> nullArrayFactory(): () -> Array<T>? {
      return kotlinx.coroutines.flow.FlowKt__ZipKt.nullArrayFactory.1.INSTANCE;
   }

   @JvmStatic
   public fun <T1, T2, R> Flow<T1>.zip(other: Flow<T2>, transform: (T1, T2, Continuation<R>) -> Any?): Flow<R> {
      return CombineKt.zipImpl(`$this$zip`, other, transform);
   }
}
