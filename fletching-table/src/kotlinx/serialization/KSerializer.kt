package kotlinx.serialization

import kotlinx.serialization.descriptors.SerialDescriptor

public interface KSerializer<T> : SerializationStrategy<T>, DeserializationStrategy<T> {
   public val descriptor: SerialDescriptor
}
