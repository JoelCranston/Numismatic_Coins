@file:SourceDebugExtension(["SMAP\nReadersJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ReadersJvm.kt\nio/ktor/util/cio/ReadersJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,27:1\n1#2:28\n*E\n"])

package io.ktor.util.cio

import io.ktor.util.cio.ReadersJvmKt.pass.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperations_jvmKt
import io.ktor.utils.io.InternalAPI
import java.nio.Buffer
import java.nio.ByteBuffer
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.InlineMarker
import kotlin.jvm.internal.SourceDebugExtension

@InternalAPI
public suspend inline fun ByteReadChannel.pass(buffer: ByteBuffer, block: (ByteBuffer) -> Unit) {
   var `$continuation`: Continuation;
   label37: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label37;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `$i$f$pass`: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         `$i$f$pass` = 0;
         break;
      case 1:
         `$i$f$pass` = `$continuation`.I$0;
         block = `$continuation`.L$2 as Function1;
         buffer = `$continuation`.L$1 as ByteBuffer;
         `$this$pass` = `$continuation`.L$0 as ByteReadChannel;
         ResultKt.throwOnFailure(`$result`);
         ((Buffer)buffer).flip();
         block.invoke(buffer);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (!$this$pass.isClosedForRead()) {
      ((Buffer)buffer).clear();
      `$continuation`.L$0 = `$this$pass`;
      `$continuation`.L$1 = buffer;
      `$continuation`.L$2 = block;
      `$continuation`.I$0 = `$i$f$pass`;
      `$continuation`.label = 1;
      if (ByteReadChannelOperations_jvmKt.readAvailable(`$this$pass`, buffer, `$continuation`) === var9) {
         return var9;
      }

      ((Buffer)buffer).flip();
      block.invoke(buffer);
   }

   val var10000: java.lang.Throwable = `$this$pass`.getClosedCause();
   if (var10000 != null) {
      throw var10000;
   } else {
      return Unit.INSTANCE;
   }
}

@InternalAPI
fun ByteReadChannel.`pass$$forInline`(buffer: ByteBuffer, block: (ByteBuffer?) -> Unit, `$completion`: Continuation<? super Unit>): Any {
   while (!$this$pass.isClosedForRead()) {
      ((Buffer)buffer).clear();
      InlineMarker.mark(0);
      ByteReadChannelOperations_jvmKt.readAvailable(`$this$pass`, buffer, `$completion`);
      InlineMarker.mark(1);
      ((Buffer)buffer).flip();
      block.invoke(buffer);
   }

   val var5: java.lang.Throwable = `$this$pass`.getClosedCause();
   if (var5 != null) {
      throw var5 as java.lang.Throwable;
   } else {
      return Unit.INSTANCE;
   }
}
