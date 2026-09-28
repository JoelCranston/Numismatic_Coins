package io.ktor.util

import io.ktor.util.EncodersJvmKt.Deflate.1
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannelOperations_jvmKt
import java.nio.Buffer
import java.nio.ByteBuffer
import java.util.zip.Checksum
import java.util.zip.Inflater
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.GlobalScope

private const val GZIP_HEADER_SIZE: Int = 10
public final val Deflate: Encoder = (new 1()) as Encoder
public final val GZip: Encoder = (new io.ktor.util.EncodersJvmKt.GZip.1()) as Encoder
private final val InflateWriterCoroutineName: CoroutineName = new CoroutineName("encoder-inflate-writer")

private infix fun Int.has(flag: Int): Boolean {
   return (`$this$has` and flag) != 0;
}

private fun inflate(source: ByteReadChannel, gzip: Boolean = true, coroutineContext: CoroutineContext): ByteReadChannel {
   return ByteWriteChannelOperationsKt.writer$default(
         GlobalScope.INSTANCE, coroutineContext.plus(InflateWriterCoroutineName), false, new io.ktor.util.EncodersJvmKt.inflate.1(gzip, source, null), 2, null
      )
      .getChannel();
}

@JvmSynthetic
fun `inflate$default`(var0: ByteReadChannel, var1: Boolean, var2: CoroutineContext, var3: Int, var4: Any): ByteReadChannel {
   if ((var3 and 2) != 0) {
      var1 = true;
   }

   return inflate(var0, var1, var2);
}

private suspend fun Inflater.inflateTo(channel: ByteWriteChannel, buffer: ByteBuffer, checksum: Checksum): Int {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.util.EncodersJvmKt.inflateTo.1) {
         `$continuation` = `$completion` as io.ktor.util.EncodersJvmKt.inflateTo.1;
         if (((`$completion` as io.ktor.util.EncodersJvmKt.inflateTo.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.util.EncodersJvmKt.inflateTo.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var inflated: Int;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         ((Buffer)buffer).clear();
         inflated = `$this$inflateTo`.inflate(buffer.array(), buffer.position(), buffer.remaining());
         ((Buffer)buffer).position(buffer.position() + inflated);
         ((Buffer)buffer).flip();
         DeflaterKt.updateKeepPosition(checksum, buffer);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$inflateTo`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(channel);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(buffer);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(checksum);
         `$continuation`.I$0 = inflated;
         `$continuation`.label = 1;
         if (ByteWriteChannelOperations_jvmKt.writeFully(channel, buffer, `$continuation`) === var8) {
            return var8;
         }
         break;
      case 1:
         inflated = `$continuation`.I$0;
         checksum = `$continuation`.L$3 as Checksum;
         buffer = `$continuation`.L$2 as ByteBuffer;
         channel = `$continuation`.L$1 as ByteWriteChannel;
         `$this$inflateTo` = `$continuation`.L$0 as Inflater;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxInt(inflated);
}

@JvmSynthetic
fun `access$has`(`$receiver`: Int, flag: Int): Boolean {
   return has(`$receiver`, flag);
}

@JvmSynthetic
fun `access$inflateTo`(`$receiver`: Inflater, channel: ByteWriteChannel, buffer: ByteBuffer, checksum: Checksum, `$completion`: Continuation): Any {
   return inflateTo(`$receiver`, channel, buffer, checksum, `$completion`);
}

@JvmSynthetic
fun `access$inflate`(source: ByteReadChannel, gzip: Boolean, coroutineContext: CoroutineContext): ByteReadChannel {
   return inflate(source, gzip, coroutineContext);
}
