package kotlinx.coroutines.flow

import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelayKt
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ProduceKt
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.FlowKt__DelayKt.debounceInternal.1
import kotlinx.coroutines.flow.FlowKt__DelayKt.sample.2
import kotlinx.coroutines.flow.internal.FlowCoroutineKt

@SourceDebugExtension(["SMAP\nDelay.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Delay.kt\nkotlinx/coroutines/flow/FlowKt__DelayKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,407:1\n1#2:408\n*E\n"])
@JvmSynthetic
internal class FlowKt__DelayKt {
   @FlowPreview
   @JvmStatic
   public fun <T> Flow<T>.debounce(timeoutMillis: Long): Flow<T> {
      if (timeoutMillis < 0L) {
         throw new IllegalArgumentException("Debounce timeout should not be negative".toString());
      } else {
         return if (timeoutMillis == 0L)
            `$this$debounce`
            else
            debounceInternal$FlowKt__DelayKt(`$this$debounce`, FlowKt__DelayKt::debounce$lambda$1$FlowKt__DelayKt);
      }
   }

   @FlowPreview
   @OverloadResolutionByLambdaReturnType
   @JvmStatic
   public fun <T> Flow<T>.debounce(timeoutMillis: (T) -> Long): Flow<T> {
      return debounceInternal$FlowKt__DelayKt(`$this$debounce`, timeoutMillis);
   }

   @FlowPreview
   @JvmStatic
   public fun <T> Flow<T>.debounce(timeout: Duration): Flow<T> {
      return FlowKt.debounce(`$this$debounce_u2dHG0u8IE`, DelayKt.toDelayMillis-LRDsOJo(timeout));
   }

   @FlowPreview
   @JvmName(name = "debounceDuration")
   @OverloadResolutionByLambdaReturnType
   @JvmStatic
   public fun <T> Flow<T>.debounce(timeout: (T) -> Duration): Flow<T> {
      return (Flow<T>)debounceInternal$FlowKt__DelayKt(`$this$debounce`, FlowKt__DelayKt::debounce$lambda$2$FlowKt__DelayKt);
   }

   @JvmStatic
   private fun <T> Flow<T>.debounceInternal(timeoutMillisSelector: (T) -> Long): Flow<T> {
      return FlowCoroutineKt.scopedFlow(new 1(timeoutMillisSelector, `$this$debounceInternal`, null));
   }

   @FlowPreview
   @JvmStatic
   public fun <T> Flow<T>.sample(periodMillis: Long): Flow<T> {
      if (periodMillis <= 0L) {
         throw new IllegalArgumentException("Sample period should be positive".toString());
      } else {
         return FlowCoroutineKt.scopedFlow(new 2(periodMillis, `$this$sample`, null));
      }
   }

   @JvmStatic
   internal fun CoroutineScope.fixedPeriodTicker(delayMillis: Long): ReceiveChannel<Unit> {
      return ProduceKt.produce$default(
         `$this$fixedPeriodTicker`, null, 0, new kotlinx.coroutines.flow.FlowKt__DelayKt.fixedPeriodTicker.1(delayMillis, null), 1, null
      );
   }

   @FlowPreview
   @JvmStatic
   public fun <T> Flow<T>.sample(period: Duration): Flow<T> {
      return FlowKt.sample(`$this$sample_u2dHG0u8IE`, DelayKt.toDelayMillis-LRDsOJo(period));
   }

   @FlowPreview
   @JvmStatic
   public fun <T> Flow<T>.timeout(timeout: Duration): Flow<T> {
      return timeoutInternal-HG0u8IE$FlowKt__DelayKt(`$this$timeout_u2dHG0u8IE`, timeout);
   }

   @JvmStatic
   private fun <T> Flow<T>.timeoutInternal(timeout: Duration): Flow<T> {
      return FlowCoroutineKt.scopedFlow(new kotlinx.coroutines.flow.FlowKt__DelayKt.timeoutInternal.1(timeout, `$this$timeoutInternal_u2dHG0u8IE`, null));
   }

   @JvmStatic
   fun `debounce$lambda$1$FlowKt__DelayKt`(`$timeoutMillis`: Long, it: Any): Long {
      return `$timeoutMillis`;
   }

   @JvmStatic
   fun `debounce$lambda$2$FlowKt__DelayKt`(`$timeout`: Function1, emittedItem: Any): Long {
      return DelayKt.toDelayMillis-LRDsOJo((`$timeout`.invoke(emittedItem) as Duration).unbox-impl());
   }
}
