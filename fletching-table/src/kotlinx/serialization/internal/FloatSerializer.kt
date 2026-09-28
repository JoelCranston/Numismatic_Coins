package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object FloatSerializer : KSerializer<java.lang.Float> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Float", PrimitiveKind.FLOAT.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Float) {
      encoder.encodeFloat(value);
   }

   public open fun deserialize(decoder: Decoder): Float {
      return decoder.decodeFloat();
   }
}
