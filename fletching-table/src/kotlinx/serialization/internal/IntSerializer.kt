package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object IntSerializer : KSerializer<Integer> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Int", PrimitiveKind.INT.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Int) {
      encoder.encodeInt(value);
   }

   public open fun deserialize(decoder: Decoder): Int {
      return decoder.decodeInt();
   }
}
