package kotlinx.serialization.internal

import kotlin.time.Duration
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object DurationSerializer : KSerializer<Duration> {
   public open val descriptor: SerialDescriptor = (new PrimitiveSerialDescriptor("kotlin.time.Duration", PrimitiveKind.STRING.INSTANCE)) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Duration) {
      encoder.encodeString(Duration.toIsoString-impl(value));
   }

   public open fun deserialize(decoder: Decoder): Duration {
      return Duration.Companion.parseIsoString-UwyO8pc(decoder.decodeString());
   }
}
