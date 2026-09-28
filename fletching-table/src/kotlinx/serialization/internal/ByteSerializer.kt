package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object ByteSerializer : KSerializer<java.lang.Byte> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.Byte", PrimitiveKind.BYTE.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Byte) {
      encoder.encodeByte(value);
   }

   public open fun deserialize(decoder: Decoder): Byte {
      return decoder.decodeByte();
   }
}
