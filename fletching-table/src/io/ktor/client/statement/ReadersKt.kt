package io.ktor.client.statement

import io.ktor.client.statement.ReadersKt.readBytes.1
import io.ktor.client.statement.ReadersKt.readBytes.3
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.io.Source
import kotlinx.io.SourcesKt

public suspend fun HttpResponse.readBytes(count: Int): ByteArray {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var3: ByteArray;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var3 = new byte[count];
         val var10000: ByteReadChannel = `$this$readBytes`.getRawContent();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readBytes`);
         `$continuation`.L$1 = var3;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var3);
         `$continuation`.I$0 = count;
         `$continuation`.I$1 = 0;
         `$continuation`.label = 1;
         if (ByteReadChannelOperationsKt.readFully$default(var10000, var3, 0, 0, `$continuation`, 6, null) === var8) {
            return var8;
         }
         break;
      case 1:
         val var5: Int = `$continuation`.I$1;
         count = `$continuation`.I$0;
         val it: ByteArray = `$continuation`.L$2 as ByteArray;
         var3 = `$continuation`.L$1 as ByteArray;
         `$this$readBytes` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return var3;
}

public suspend fun HttpResponse.readRawBytes(): ByteArray {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.client.statement.ReadersKt.readRawBytes.1) {
         `$continuation` = `$completion` as io.ktor.client.statement.ReadersKt.readRawBytes.1;
         if (((`$completion` as io.ktor.client.statement.ReadersKt.readRawBytes.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.client.statement.ReadersKt.readRawBytes.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$this$readRawBytes`.getRawContent();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readRawBytes`);
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readRemaining((ByteReadChannel)var10000, `$continuation`);
         if (var10000 === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readRawBytes` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return SourcesKt.readByteArray(var10000 as Source);
}

@Deprecated(message = "This method was renamed to readRawBytes() to reflect what it does.", replaceWith = @ReplaceWith(expression = "readRawBytes()", imports = []))
public suspend fun HttpResponse.readBytes(): ByteArray {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is 3) {
         `$continuation` = `$completion` as 3;
         if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new 3(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$this$readBytes`.getRawContent();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readBytes`);
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readRemaining((ByteReadChannel)var10000, `$continuation`);
         if (var10000 === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$readBytes` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return SourcesKt.readByteArray(var10000 as Source);
}

public suspend fun HttpResponse.discardRemaining() {
   val var10000: Any = ByteReadChannelOperationsKt.discard$default(`$this$discardRemaining`.getRawContent(), 0L, `$completion`, 1, null);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}
