package io.ktor.util.cio

import io.ktor.util.cio.ReadersKt.toByteArray.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelKt
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import kotlin.contracts.InvocationKind
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.InlineMarker
import kotlinx.io.Source
import kotlinx.io.SourcesKt

public suspend fun ByteReadChannel.toByteArray(limit: Int = ...): ByteArray {
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
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         val var10001: Long = limit;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$toByteArray`);
         `$continuation`.I$0 = limit;
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readRemaining(`$this$toByteArray`, var10001, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         limit = `$continuation`.I$0;
         `$this$toByteArray` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return SourcesKt.readByteArray(var10000 as Source);
}

@JvmSynthetic
fun `toByteArray$default`(var0: ByteReadChannel, var1: Int, var2: Continuation, var3: Int, var4: Any): Any {
   if ((var3 and 1) != 0) {
      var1 = Integer.MAX_VALUE;
   }

   return toByteArray(var0, var1, var2);
}

public inline fun ByteWriteChannel.use(block: (ByteWriteChannel) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   label19: {
      try {
         try {
            block.invoke(`$this$use`);
         } catch (var4: java.lang.Throwable) {
            ByteWriteChannelOperationsKt.close(`$this$use`, var4);
            throw var4;
         }
      } catch (var5: java.lang.Throwable) {
         InlineMarker.finallyStart(1);
         ByteWriteChannelKt.close(`$this$use`);
         InlineMarker.finallyEnd(1);
      }

      InlineMarker.finallyStart(1);
      ByteWriteChannelKt.close(`$this$use`);
      InlineMarker.finallyEnd(1);
   }
}
