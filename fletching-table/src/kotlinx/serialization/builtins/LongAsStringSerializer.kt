package kotlinx.serialization.builtins

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object LongAsStringSerializer : KSerializer<java.lang.Long> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("kotlinx.serialization.LongAsStringSerializer", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: Long) {
      encoder.encodeString(java.lang.String.valueOf(value));
   }

   public open fun deserialize(decoder: Decoder): Long {
      return java.lang.Long.parseLong(decoder.decodeString());
   }
}
