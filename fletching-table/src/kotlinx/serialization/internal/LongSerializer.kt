package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object LongSerializer : KSerializer<java.lang.Long> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Long", PrimitiveKind.LONG.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Long) {
      encoder.encodeLong(value);
   }

   public open fun deserialize(decoder: Decoder): Long {
      return decoder.decodeLong();
   }
}
