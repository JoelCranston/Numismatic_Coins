package io.ktor.client.request.forms

import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.core.StringsKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.random.Random
import kotlinx.io.Source

private final val RN_BYTES: ByteArray = StringsKt.toByteArray$default("\r\n", null, 1, null)

private fun generateBoundary(): String {
   val var0: StringBuilder = new StringBuilder();
   val `$this$generateBoundary_u24lambda_u240`: StringBuilder = var0;
   val var3: Byte = 32;

   for (int var4 = 0; var4 < var3; var4++) {
      val var10001: java.lang.String = Integer.toString(Random.Default.nextInt(), CharsKt.checkRadix(16));
      `$this$generateBoundary_u24lambda_u240`.append(var10001);
   }

   return kotlin.text.StringsKt.take(var0.toString(), 70);
}

private suspend fun Source.copyTo(channel: ByteWriteChannel) {
   val var10000: Any = ByteWriteChannelOperationsKt.writePacket(channel, `$this$copyTo`, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getRN_BYTES$p`(): ByteArray {
   return RN_BYTES;
}

@JvmSynthetic
fun `access$generateBoundary`(): java.lang.String {
   return generateBoundary();
}

@JvmSynthetic
fun `access$copyTo`(`$receiver`: Source, channel: ByteWriteChannel, `$completion`: Continuation): Any {
   return copyTo(`$receiver`, channel, `$completion`);
}
