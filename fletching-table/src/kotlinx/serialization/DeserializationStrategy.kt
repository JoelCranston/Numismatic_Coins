package kotlinx.serialization

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder

public interface DeserializationStrategy<T> {
   public val descriptor: SerialDescriptor

   public abstract fun deserialize(decoder: Decoder): Any {
   }
}
