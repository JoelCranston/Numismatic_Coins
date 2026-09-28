package kotlinx.coroutines.flow

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.CallbackFlowBuilder.collectTo.1
import kotlinx.coroutines.flow.internal.ChannelFlow

private class CallbackFlowBuilder<T>(block: (ProducerScope<Any>, Continuation<Unit>) -> Any?,
   context: CoroutineContext = EmptyCoroutineContext.INSTANCE as CoroutineContext,
   capacity: Int = -2,
   onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) : ChannelFlowBuilder(block, context, capacity, onBufferOverflow) {
   private final val block: (ProducerScope<Any>, Continuation<Unit>) -> Any?

   init {
      this.block = block;
   }

   protected override suspend fun collectTo(scope: ProducerScope<Any>) {
      var `$continuation`: Continuation;
      label24: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label24;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = scope;
            `$continuation`.label = 1;
            if (super.collectTo(scope, `$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            scope = `$continuation`.L$0 as ProducerScope;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      if (!scope.isClosedForSend()) {
         throw new IllegalStateException(
            "'awaitClose { yourCallbackOrListener.cancel() }' should be used in the end of callbackFlow block.\nOtherwise, a callback/listener may leak in case of external cancellation.\nSee callbackFlow API documentation for the details."
         );
      } else {
         return Unit.INSTANCE;
      }
   }

   protected override fun create(context: CoroutineContext, capacity: Int, onBufferOverflow: BufferOverflow): ChannelFlow<Any> {
      return new CallbackFlowBuilder<>(this.block, context, capacity, onBufferOverflow);
   }
}
