package kotlinx.serialization.internal

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.internal.InlineClassDescriptorKt.InlinePrimitiveDescriptor.1

@InternalSerializationApi
public fun <T> InlinePrimitiveDescriptor(name: String, primitiveSerializer: KSerializer<T>): SerialDescriptor {
   return new InlineClassDescriptor(name, new 1(primitiveSerializer));
}
