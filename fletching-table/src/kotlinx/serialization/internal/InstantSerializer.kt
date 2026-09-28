package kotlinx.serialization.internal

import kotlin.time.ExperimentalTime
import kotlin.time.Instant
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
@ExperimentalTime
internal object InstantSerializer : KSerializer<Instant> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.time.Instant", PrimitiveKind.STRING.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Instant) {
      encoder.encodeString(value.toString());
   }

   public open fun deserialize(decoder: Decoder): Instant {
      return Instant.Companion.parse(decoder.decodeString());
   }
}
