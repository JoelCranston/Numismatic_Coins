package kotlinx.serialization

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Encoder

public interface SerializationStrategy<T> {
   public val descriptor: SerialDescriptor

   public abstract fun serialize(encoder: Encoder, value: Any) {
   }
}
