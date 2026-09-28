package kotlinx.serialization.internal

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@ExperimentalUuidApi
internal object UuidSerializer : KSerializer<Uuid> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.uuid.Uuid", PrimitiveKind.STRING.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Uuid) {
      encoder.encodeString(value.toString());
   }

   public open fun deserialize(decoder: Decoder): Uuid {
      return Uuid.Companion.parse(decoder.decodeString());
   }
}
