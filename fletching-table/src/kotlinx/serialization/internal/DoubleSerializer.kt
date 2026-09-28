package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object DoubleSerializer : KSerializer<java.lang.Double> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Double", PrimitiveKind.DOUBLE.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Double) {
      encoder.encodeDouble(value);
   }

   public open fun deserialize(decoder: Decoder): Double {
      return decoder.decodeDouble();
   }
}
