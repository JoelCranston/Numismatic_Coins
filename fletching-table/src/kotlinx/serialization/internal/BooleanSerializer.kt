package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object BooleanSerializer : KSerializer<java.lang.Boolean> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Boolean", PrimitiveKind.BOOLEAN.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Boolean) {
      encoder.encodeBoolean(value);
   }

   public open fun deserialize(decoder: Decoder): Boolean {
      return decoder.decodeBoolean();
   }
}
