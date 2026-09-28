@file:SourceDebugExtension(["SMAP\nWriting.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Writing.kt\nio/ktor/utils/io/jvm/javaio/WritingKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,32:1\n1#2:33\n*E\n"])

package io.ktor.utils.io.jvm.javaio

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.jvm.javaio.WritingKt.copyTo.1
import java.io.OutputStream
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.BuffersJvmKt

public suspend fun ByteReadChannel.copyTo(out: OutputStream, limit: Long = ...): Long {
   var `$continuation`: Continuation;
   label48: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label48;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10: Long;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (limit < 0L) {
            throw new IllegalArgumentException(("Limit shouldn't be negative: $limit").toString());
         }

         var10 = 0L;
         break;
      case 1:
         var10 = `$continuation`.J$1;
         limit = `$continuation`.J$0;
         out = `$continuation`.L$1 as OutputStream;
         `$this$copyTo` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         var10 = var10 + `$this$copyTo`.getReadBuffer().getBuffer().getSize();
         BuffersJvmKt.readTo$default(`$this$copyTo`.getReadBuffer().getBuffer(), out, 0L, 2, null);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (!$this$copyTo.isClosedForRead()) {
      if (`$this$copyTo`.getReadBuffer().exhausted()) {
         `$continuation`.L$0 = `$this$copyTo`;
         `$continuation`.L$1 = out;
         `$continuation`.J$0 = limit;
         `$continuation`.J$1 = var10;
         `$continuation`.label = 1;
         if (ByteReadChannel.awaitContent$default(`$this$copyTo`, 0, `$continuation`, 1, null) === var9) {
            return var9;
         }
      }

      var10 += `$this$copyTo`.getReadBuffer().getBuffer().getSize();
      BuffersJvmKt.readTo$default(`$this$copyTo`.getReadBuffer().getBuffer(), out, 0L, 2, null);
   }

   return Boxing.boxLong(var10);
}

@JvmSynthetic
fun `copyTo$default`(var0: ByteReadChannel, var1: OutputStream, var2: Long, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 2) != 0) {
      var2 = java.lang.Long.MAX_VALUE;
   }

   return copyTo(var0, var1, var2, var4);
}
