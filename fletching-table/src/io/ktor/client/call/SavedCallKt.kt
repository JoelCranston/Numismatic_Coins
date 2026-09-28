package io.ktor.client.call

import io.ktor.client.call.SavedCallKt.save.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.io.Source
import kotlinx.io.SourcesKt

public suspend fun HttpClientCall.save(): HttpClientCall {
   var `$continuation`: Continuation;
   label24: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label24;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (`$this$save` is SavedHttpCall) {
            return `$this$save`;
         }

         var10000 = `$this$save`.getResponse().getRawContent();
         `$continuation`.L$0 = `$this$save`;
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readRemaining((ByteReadChannel)var10000, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         `$this$save` = `$continuation`.L$0 as HttpClientCall;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return new SavedHttpCall(`$this$save`.getClient(), `$this$save`.getRequest(), `$this$save`.getResponse(), SourcesKt.readByteArray(var10000 as Source));
}
