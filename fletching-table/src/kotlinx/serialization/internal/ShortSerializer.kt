package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object ShortSerializer : KSerializer<java.lang.Short> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Short", PrimitiveKind.SHORT.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Short) {
      encoder.encodeShort(value);
   }

   public open fun deserialize(decoder: Decoder): Short {
      return decoder.decodeShort();
   }
}
