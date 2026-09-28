package kotlinx.serialization.json

import kotlin.jvm.functions.Function0
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElementSerializersKt.defer.1

private fun verify(encoder: Encoder) {
   asJsonEncoder(encoder);
}

private fun verify(decoder: Decoder) {
   asJsonDecoder(decoder);
}

internal fun Decoder.asJsonDecoder(): JsonDecoder {
   val var10000: JsonDecoder = `$this$asJsonDecoder` as? JsonDecoder;
   if ((`$this$asJsonDecoder` as? JsonDecoder) == null) {
      throw new IllegalStateException(
         "This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got ${`$this$asJsonDecoder`.getClass()::class}"
      );
   } else {
      return var10000;
   }
}

internal fun Encoder.asJsonEncoder(): JsonEncoder {
   val var10000: JsonEncoder = `$this$asJsonEncoder` as? JsonEncoder;
   if ((`$this$asJsonEncoder` as? JsonEncoder) == null) {
      throw new IllegalStateException(
         "This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got ${`$this$asJsonEncoder`.getClass()::class}"
      );
   } else {
      return var10000;
   }
}

private fun defer(deferred: () -> SerialDescriptor): SerialDescriptor {
   return new 1(deferred);
}

@JvmSynthetic
fun `access$verify`(encoder: Encoder) {
   verify(encoder);
}

@JvmSynthetic
fun `access$defer`(deferred: Function0): SerialDescriptor {
   return defer(deferred);
}

@JvmSynthetic
fun `access$verify`(decoder: Decoder) {
   verify(decoder);
}
