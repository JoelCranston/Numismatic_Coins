package kotlinx.coroutines.flow

import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.TypeIntrinsics

@JvmSynthetic
internal class FlowKt__DistinctKt {
   private final val defaultKeySelector: (Any?) -> Any? = FlowKt__DistinctKt::defaultKeySelector$lambda$0$FlowKt__DistinctKt
   private final val defaultAreEquivalent: (Any?, Any?) -> Boolean = FlowKt__DistinctKt::defaultAreEquivalent$lambda$1$FlowKt__DistinctKt

   @JvmStatic
   public fun <T> Flow<T>.distinctUntilChanged(): Flow<T> {
      return if (`$this$distinctUntilChanged` is StateFlow)
         `$this$distinctUntilChanged`
         else
         distinctUntilChangedBy$FlowKt__DistinctKt(`$this$distinctUntilChanged`, defaultKeySelector, defaultAreEquivalent);
   }

   @JvmStatic
   public fun <T> Flow<T>.distinctUntilChanged(areEquivalent: (T, T) -> Boolean): Flow<T> {
      val var10001: Function1 = defaultKeySelector;
      return distinctUntilChangedBy$FlowKt__DistinctKt(
         `$this$distinctUntilChanged`, var10001, TypeIntrinsics.beforeCheckcastToFunctionOfArity(areEquivalent, 2) as (Any?, Any?) -> java.lang.Boolean
      );
   }

   @JvmStatic
   public fun <T, K> Flow<T>.distinctUntilChangedBy(keySelector: (T) -> K): Flow<T> {
      return distinctUntilChangedBy$FlowKt__DistinctKt(`$this$distinctUntilChangedBy`, keySelector, defaultAreEquivalent);
   }

   @JvmStatic
   private fun <T> Flow<T>.distinctUntilChangedBy(keySelector: (T) -> Any?, areEquivalent: (Any?, Any?) -> Boolean): Flow<T> {
      return (Flow<T>)(if (`$this$distinctUntilChangedBy` is DistinctFlowImpl
            && (`$this$distinctUntilChangedBy` as DistinctFlowImpl).keySelector === keySelector
            && (`$this$distinctUntilChangedBy` as DistinctFlowImpl).areEquivalent === areEquivalent)
         `$this$distinctUntilChangedBy`
         else
         new DistinctFlowImpl(`$this$distinctUntilChangedBy`, keySelector, areEquivalent));
   }

   @JvmStatic
   fun `defaultKeySelector$lambda$0$FlowKt__DistinctKt`(it: Any): Any {
      return it;
   }

   @JvmStatic
   fun `defaultAreEquivalent$lambda$1$FlowKt__DistinctKt`(old: Any, var1: Any): Boolean {
      return old == var1;
   }
}
