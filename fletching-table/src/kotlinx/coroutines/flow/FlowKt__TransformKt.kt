package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KClass
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.FlowKt__TransformKt.filter..inlined.unsafeTransform.1
import kotlinx.coroutines.flow.FlowKt__TransformKt.filterIsInstance..inlined.filter.2

@SourceDebugExtension(["SMAP\nTransform.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 3 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n17#1:174\n19#1:178\n17#1:179\n19#1:183\n46#2:168\n51#2:170\n46#2:171\n51#2:173\n46#2:175\n51#2:177\n46#2:180\n51#2:182\n46#2:184\n51#2:186\n46#2:187\n51#2:189\n46#2:190\n51#2:192\n46#2:194\n51#2:196\n105#3:169\n105#3:172\n105#3:176\n105#3:181\n105#3:185\n105#3:188\n105#3:191\n105#3:193\n105#3:195\n105#3:197\n105#3:198\n105#3:200\n1#4:199\n*S KotlinDebug\n*F\n+ 1 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n*L\n32#1:174\n32#1:178\n37#1:179\n37#1:183\n17#1:168\n17#1:170\n24#1:171\n24#1:173\n32#1:175\n32#1:177\n37#1:180\n37#1:182\n42#1:184\n42#1:186\n49#1:187\n49#1:189\n56#1:190\n56#1:192\n74#1:194\n74#1:196\n17#1:169\n24#1:172\n32#1:176\n37#1:181\n42#1:185\n49#1:188\n56#1:191\n64#1:193\n74#1:195\n101#1:197\n121#1:198\n152#1:200\n*E\n"])
@JvmSynthetic
internal class FlowKt__TransformKt {
   @JvmStatic
   public inline fun <T> Flow<T>.filter(crossinline predicate: (T, Continuation<Boolean>) -> Any?): Flow<T> {
      return new 1(`$this$filter`, predicate);
   }

   @JvmStatic
   public inline fun <T> Flow<T>.filterNot(crossinline predicate: (T, Continuation<Boolean>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.filterNot..inlined.unsafeTransform.1(`$this$filterNot`, predicate);
   }

   @JvmStatic
   public fun <R : Any> Flow<*>.filterIsInstance(klass: KClass<R>): Flow<R> {
      return new 2(`$this$filterIsInstance`, klass);
   }

   @JvmStatic
   public fun <T : Any> Flow<T?>.filterNotNull(): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.filterNotNull..inlined.unsafeTransform.1(`$this$filterNotNull`);
   }

   @JvmStatic
   public inline fun <T, R> Flow<T>.map(crossinline transform: (T, Continuation<R>) -> Any?): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.map..inlined.unsafeTransform.1(`$this$map`, transform);
   }

   @JvmStatic
   public inline fun <T, R : Any> Flow<T>.mapNotNull(crossinline transform: (T, Continuation<R?>) -> Any?): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.mapNotNull..inlined.unsafeTransform.1(`$this$mapNotNull`, transform);
   }

   @JvmStatic
   public fun <T> Flow<T>.withIndex(): Flow<IndexedValue<T>> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.withIndex..inlined.unsafeFlow.1(`$this$withIndex`);
   }

   @JvmStatic
   public fun <T> Flow<T>.onEach(action: (T, Continuation<Unit>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.onEach..inlined.unsafeTransform.1(`$this$onEach`, action);
   }

   @JvmStatic
   public fun <T, R> Flow<T>.scan(initial: R, operation: (R, T, Continuation<R>) -> Any?): Flow<R> {
      return (Flow<R>)FlowKt.runningFold(`$this$scan`, initial, operation);
   }

   @JvmStatic
   public fun <T, R> Flow<T>.runningFold(initial: R, operation: (R, T, Continuation<R>) -> Any?): Flow<R> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.runningFold..inlined.unsafeFlow.1(initial, `$this$runningFold`, operation);
   }

   @JvmStatic
   public fun <T> Flow<T>.runningReduce(operation: (T, T, Continuation<T>) -> Any?): Flow<T> {
      return new kotlinx.coroutines.flow.FlowKt__TransformKt.runningReduce..inlined.unsafeFlow.1(`$this$runningReduce`, operation);
   }

   @ExperimentalCoroutinesApi
   @JvmStatic
   public fun <T> Flow<T>.chunked(size: Int): Flow<List<T>> {
      if (size < 1) {
         throw new IllegalArgumentException(("Expected positive chunk size, but got $size").toString());
      } else {
         return new kotlinx.coroutines.flow.FlowKt__TransformKt.chunked..inlined.unsafeFlow.1(`$this$chunked`, size);
      }
   }
}
