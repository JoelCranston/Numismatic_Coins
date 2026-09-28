package kotlinx.serialization

import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.internal.AbstractPolymorphicSerializer
import kotlinx.serialization.internal.AbstractPolymorphicSerializerKt

@InternalSerializationApi
public fun <T : Any> AbstractPolymorphicSerializer<T>.findPolymorphicSerializer(decoder: CompositeDecoder, klassName: String?): DeserializationStrategy<T> {
   val var10000: DeserializationStrategy = `$this$findPolymorphicSerializer`.findPolymorphicSerializerOrNull(decoder, klassName);
   if (var10000 == null) {
      AbstractPolymorphicSerializerKt.throwSubtypeNotRegistered(klassName, `$this$findPolymorphicSerializer`.getBaseClass());
      throw new KotlinNothingValueException();
   } else {
      return var10000;
   }
}

@InternalSerializationApi
public fun <T : Any> AbstractPolymorphicSerializer<T>.findPolymorphicSerializer(encoder: Encoder, value: T): SerializationStrategy<T> {
   val var10000: SerializationStrategy = `$this$findPolymorphicSerializer`.findPolymorphicSerializerOrNull(encoder, value);
   if (var10000 == null) {
      AbstractPolymorphicSerializerKt.throwSubtypeNotRegistered(value.getClass()::class, `$this$findPolymorphicSerializer`.getBaseClass());
      throw new KotlinNothingValueException();
   } else {
      return var10000;
   }
}
