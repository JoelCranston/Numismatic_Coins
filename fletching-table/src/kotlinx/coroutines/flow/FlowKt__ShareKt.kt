package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CompletableDeferredKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.DebugKt
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharing.1
import kotlinx.coroutines.flow.internal.ChannelFlow
import kotlinx.coroutines.internal.Symbol

@SourceDebugExtension(["SMAP\nShare.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Share.kt\nkotlinx/coroutines/flow/FlowKt__ShareKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,429:1\n1#2:430\n*E\n"])
@JvmSynthetic
internal class FlowKt__ShareKt {
   @JvmStatic
   public fun <T> Flow<T>.shareIn(scope: CoroutineScope, started: SharingStarted, replay: Int = 0): SharedFlow<T> {
      val config: SharingConfig = configureSharing$FlowKt__ShareKt(`$this$shareIn`, replay);
      val shared: MutableSharedFlow = SharedFlowKt.MutableSharedFlow(replay, config.extraBufferCapacity, config.onBufferOverflow);
      return new ReadonlySharedFlow(
         shared, launchSharing$FlowKt__ShareKt(scope, config.context, config.upstream, shared, started, (Symbol)SharedFlowKt.NO_VALUE)
      );
   }

   @JvmStatic
   private fun <T> Flow<T>.configureSharing(replay: Int): SharingConfig<T> {
      if (DebugKt.getASSERTIONS_ENABLED() && replay < 0) {
         throw new AssertionError();
      } else {
         val var4: Int = RangesKt.coerceAtLeast(replay, Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core()) - replay;
         if (`$this$configureSharing` is ChannelFlow) {
            val upstream: Flow = (`$this$configureSharing` as ChannelFlow).dropChannelOperators();
            if (upstream != null) {
               val var10000: SharingConfig = new SharingConfig;
               var var10003: Int;
               switch (((ChannelFlow)$this$configureSharing).capacity) {
                  case -3:
                  case -2:
                  case 0:
                     var10003 = if ((`$this$configureSharing` as ChannelFlow).onBufferOverflow === BufferOverflow.SUSPEND)
                        (if ((`$this$configureSharing` as ChannelFlow).capacity == 0) 0 else var4)
                        else
                        (if (replay == 0) 1 else 0);
                     break;
                  case -1:
                  default:
                     var10003 = (`$this$configureSharing` as ChannelFlow).capacity;
               }

               var10000./* $VF: Unable to resugar constructor */<init>(
                  upstream, var10003, (`$this$configureSharing` as ChannelFlow).onBufferOverflow, (`$this$configureSharing` as ChannelFlow).context
               );
               return var10000;
            }
         }

         return new SharingConfig(`$this$configureSharing`, var4, BufferOverflow.SUSPEND, EmptyCoroutineContext.INSTANCE);
      }
   }

   @JvmStatic
   private fun <T> CoroutineScope.launchSharing(
      context: CoroutineContext,
      upstream: Flow<T>,
      shared: MutableSharedFlow<T>,
      started: SharingStarted,
      initialValue: T
   ): Job {
      return BuildersKt.launch(
         `$this$launchSharing`,
         context,
         if (started == SharingStarted.Companion.getEagerly()) CoroutineStart.DEFAULT else CoroutineStart.UNDISPATCHED,
         new 1(started, upstream, shared, initialValue, null)
      );
   }

   @JvmStatic
   public fun <T> Flow<T>.stateIn(scope: CoroutineScope, started: SharingStarted, initialValue: T): StateFlow<T> {
      val config: SharingConfig = configureSharing$FlowKt__ShareKt(`$this$stateIn`, 1);
      val state: MutableStateFlow = StateFlowKt.MutableStateFlow(initialValue);
      return new ReadonlyStateFlow(state, launchSharing$FlowKt__ShareKt(scope, config.context, config.upstream, state, started, initialValue));
   }

   @JvmStatic
   public suspend fun <T> Flow<T>.stateIn(scope: CoroutineScope): StateFlow<T> {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is kotlinx.coroutines.flow.FlowKt__ShareKt.stateIn.1) {
            `$continuation` = `$completion` as kotlinx.coroutines.flow.FlowKt__ShareKt.stateIn.1;
            if (((`$completion` as kotlinx.coroutines.flow.FlowKt__ShareKt.stateIn.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new kotlinx.coroutines.flow.FlowKt__ShareKt.stateIn.1(`$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val config: SharingConfig = configureSharing$FlowKt__ShareKt(`$this$stateIn`, 1);
            val result: CompletableDeferred = CompletableDeferredKt.CompletableDeferred(scope.getCoroutineContext().get(Job.Key));
            launchSharingDeferred$FlowKt__ShareKt(scope, config.context, config.upstream, result);
            `$continuation`.label = 1;
            var10000 = result.await(`$continuation`);
            if (var10000 === var8) {
               return var8;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var5: Any = (var10000 as Result).unbox-impl();
      ResultKt.throwOnFailure(var5);
      return var5;
   }

   @JvmStatic
   private fun <T> CoroutineScope.launchSharingDeferred(context: CoroutineContext, upstream: Flow<T>, result: CompletableDeferred<Result<StateFlow<T>>>) {
      BuildersKt.launch$default(
         `$this$launchSharingDeferred`, context, null, new kotlinx.coroutines.flow.FlowKt__ShareKt.launchSharingDeferred.1(upstream, result, null), 2, null
      );
   }

   @JvmStatic
   public fun <T> MutableSharedFlow<T>.asSharedFlow(): SharedFlow<T> {
      return new ReadonlySharedFlow(`$this$asSharedFlow`, null);
   }

   @JvmStatic
   public fun <T> MutableStateFlow<T>.asStateFlow(): StateFlow<T> {
      return new ReadonlyStateFlow(`$this$asStateFlow`, null);
   }

   @JvmStatic
   public fun <T> SharedFlow<T>.onSubscription(action: (FlowCollector<T>, Continuation<Unit>) -> Any?): SharedFlow<T> {
      return new SubscribedSharedFlow(`$this$onSubscription`, action);
   }
}
