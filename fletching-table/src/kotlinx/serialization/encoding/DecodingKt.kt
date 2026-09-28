package kotlinx.serialization.encoding

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor

internal inline fun <T : Any> Decoder.decodeIfNullable(deserializer: DeserializationStrategy<T?>, block: () -> T?): T? {
   return (T)(if (!deserializer.getDescriptor().isNullable() && !`$this$decodeIfNullable`.decodeNotNullMark())
      `$this$decodeIfNullable`.decodeNull()
      else
      block.invoke());
}

public inline fun <T> Decoder.decodeStructure(descriptor: SerialDescriptor, crossinline block: (CompositeDecoder) -> T): T {
   val composite: CompositeDecoder = `$this$decodeStructure`.beginStructure(descriptor);
   val result: Any = block.invoke(composite);
   composite.endStructure(descriptor);
   return (T)result;
}
