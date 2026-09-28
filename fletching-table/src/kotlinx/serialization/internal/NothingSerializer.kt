package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object NothingSerializer : KSerializer {
   public open val descriptor: SerialDescriptor = NothingSerialDescriptor.INSTANCE as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: Nothing) {
      throw new SerializationException("'kotlin.Nothing' cannot be serialized");
   }

   public open fun deserialize(decoder: Decoder): Nothing {
      throw new SerializationException("'kotlin.Nothing' does not have instances");
   }
}
