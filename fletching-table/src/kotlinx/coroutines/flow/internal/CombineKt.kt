@file:SourceDebugExtension(["SMAP\nCombine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Combine.kt\nkotlinx/coroutines/flow/internal/CombineKt\n+ 2 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,140:1\n105#2:141\n*S KotlinDebug\n*F\n+ 1 Combine.kt\nkotlinx/coroutines/flow/internal/CombineKt\n*L\n83#1:141\n*E\n"])

package kotlinx.coroutines.flow.internal

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.internal.CombineKt.combineInternal.2
import kotlinx.coroutines.flow.internal.CombineKt.zipImpl..inlined.unsafeFlow.1

@PublishedApi
internal suspend fun <R, T> FlowCollector<R>.combineInternal(
   flows: Array<out Flow<T>>,
   arrayFactory: () -> Array<T?>?,
   transform: (FlowCollector<R>, Array<T>, Continuation<Unit>) -> Any?
) {
   val var10000: Any = FlowCoroutineKt.flowScope(new 2(flows, arrayFactory, transform, `$this$combineInternal`, null), `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

internal fun <T1, T2, R> zipImpl(flow: Flow<T1>, flow2: Flow<T2>, transform: (T1, T2, Continuation<R>) -> Any?): Flow<R> {
   return new 1(flow2, flow, transform);
}
