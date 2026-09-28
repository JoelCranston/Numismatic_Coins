package kotlinx.coroutines

import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.DebugProbesKt
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.internal.ScopeCoroutine
import kotlinx.coroutines.internal.ThreadContextKt
import kotlinx.coroutines.intrinsics.CancellableKt
import kotlinx.coroutines.intrinsics.UndispatchedKt

@SourceDebugExtension(["SMAP\nBuilders.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Builders.common.kt\nkotlinx/coroutines/BuildersKt__Builders_commonKt\n+ 2 CoroutineContext.kt\nkotlinx/coroutines/CoroutineContextKt\n*L\n1#1,268:1\n91#2,5:269\n*S KotlinDebug\n*F\n+ 1 Builders.common.kt\nkotlinx/coroutines/BuildersKt__Builders_commonKt\n*L\n164#1:269,5\n*E\n"])
@JvmSynthetic
internal class BuildersKt__Builders_commonKt {
   private const val UNDECIDED: Int = 0
   private const val SUSPENDED: Int = 1
   private const val RESUMED: Int = 2

   @JvmStatic
   public fun CoroutineScope.launch(
      context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
      start: CoroutineStart = CoroutineStart.DEFAULT,
      block: (CoroutineScope, Continuation<Unit>) -> Any?
   ): Job {
      val newContext: CoroutineContext = CoroutineContextKt.newCoroutineContext(`$this$launch`, context);
      val coroutine: StandaloneCoroutine = if (start.isLazy()) new LazyStandaloneCoroutine(newContext, block) else new StandaloneCoroutine(newContext, true);
      coroutine.start(start, coroutine, block);
      return coroutine;
   }

   @JvmStatic
   public fun <T> CoroutineScope.async(
      context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
      start: CoroutineStart = CoroutineStart.DEFAULT,
      block: (CoroutineScope, Continuation<T>) -> Any?
   ): Deferred<T> {
      val newContext: CoroutineContext = CoroutineContextKt.newCoroutineContext(`$this$async`, context);
      val coroutine: DeferredCoroutine = if (start.isLazy()) new LazyDeferredCoroutine(newContext, block) else new DeferredCoroutine(newContext, true);
      coroutine.start(start, coroutine, block);
      return coroutine;
   }

   @JvmStatic
   public suspend fun <T> withContext(context: CoroutineContext, block: (CoroutineScope, Continuation<T>) -> Any?): T {
      contract {
         callsInPlace(block, InvocationKind.EXACTLY_ONCE)
      }

      label26: {
         val oldContext: CoroutineContext = `$completion`.getContext();
         val newContext: CoroutineContext = CoroutineContextKt.newCoroutineContext(oldContext, context);
         JobKt.ensureActive(newContext);
         val var10000: Any;
         if (newContext === oldContext) {
            val coroutine: ScopeCoroutine = new ScopeCoroutine(newContext, `$completion`);
            var10000 = UndispatchedKt.startUndispatchedOrReturn(coroutine, coroutine, block);
         } else if (newContext.get(ContinuationInterceptor.Key) == oldContext.get(ContinuationInterceptor.Key)) {
            val var16: UndispatchedCoroutine = new UndispatchedCoroutine(newContext, `$completion`);
            val `context$iv`: CoroutineContext = var16.getContext();
            val `oldValue$iv`: Any = ThreadContextKt.updateThreadContext(`context$iv`, null);

            try {
               val var13: Any = UndispatchedKt.startUndispatchedOrReturn(var16, var16, block);
            } catch (var14: java.lang.Throwable) {
               ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
            }

            var10000 = `context$iv`;
            ThreadContextKt.restoreThreadContext(`context$iv`, `oldValue$iv`);
         } else {
            val var17: DispatchedCoroutine = new DispatchedCoroutine(newContext, `$completion`);
            CancellableKt.startCoroutineCancellable(block, var17, var17);
            var10000 = var17.getResult$kotlinx_coroutines_core();
         }

         if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(`$completion`);
         }

         return var10000;
      }
   }

   @JvmStatic
   public suspend inline operator fun <T> CoroutineDispatcher.invoke(noinline block: (CoroutineScope, Continuation<T>) -> Any?): T {
      return BuildersKt.withContext(`$this$invoke`, block, `$completion`);
   }

   @JvmStatic
   fun <T> CoroutineDispatcher.`invoke$$forInline`(block: (CoroutineScope?, Continuation<? super T>?) -> Any, `$completion`: Continuation<? super T>): Any {
      var var10000: CoroutineContext = `$this$invoke`;
      InlineMarker.mark(0);
      var10000 = (CoroutineContext)BuildersKt.withContext(var10000, block, `$completion`);
      InlineMarker.mark(1);
      return var10000;
   }
}
