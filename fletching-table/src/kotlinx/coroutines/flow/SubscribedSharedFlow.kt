package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.flow.SubscribedSharedFlow.collect.1

private class SubscribedSharedFlow<T>(sharedFlow: SharedFlow<Any>, action: (FlowCollector<Any>, Continuation<Unit>) -> Any?) : SharedFlow<T> {
   private final val sharedFlow: SharedFlow<Any>
   private final val action: (FlowCollector<Any>, Continuation<Unit>) -> Any?
   public open val replayCache: List<Any>

   init {
      this.sharedFlow = sharedFlow;
      this.action = action;
   }

   public override suspend fun collect(collector: FlowCollector<Any>): Nothing {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10000: SharedFlow = this.sharedFlow;
            val var10001: FlowCollector = new SubscribedFlowCollector<>(collector, this.action);
            `$continuation`.label = 1;
            if (var10000.collect(var10001, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      throw new KotlinNothingValueException();
   }
}
