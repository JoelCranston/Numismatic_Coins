package net.peanuuutz.tomlkt.internal.encoder

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

private abstract class AbstractTomlElementCompositeEncoder : AbstractTomlElementEncoder, TomlCompositeEncoder {
   open fun AbstractTomlElementCompositeEncoder(delegate: AbstractTomlElementEncoder) {
      super(delegate.getToml());
   }

   public override fun encodeInlineElement(descriptor: SerialDescriptor, index: Int): Encoder {
      return if (SerialDescriptorUtilsKt.isUnsignedInteger(descriptor.getElementDescriptor(index)))
         new TomlElementInlineElementEncoder(descriptor, index, this)
         else
         this;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
   }

   override fun encodeBooleanElement(descriptor: SerialDescriptor, index: Int, value: Boolean) {
      TomlCompositeEncoder.super.encodeBooleanElement(descriptor, index, value);
   }

   override fun encodeByteElement(descriptor: SerialDescriptor, index: Int, value: Byte) {
      TomlCompositeEncoder.super.encodeByteElement(descriptor, index, value);
   }

   override fun encodeShortElement(descriptor: SerialDescriptor, index: Int, value: Short) {
      TomlCompositeEncoder.super.encodeShortElement(descriptor, index, value);
   }

   override fun encodeIntElement(descriptor: SerialDescriptor, index: Int, value: Int) {
      TomlCompositeEncoder.super.encodeIntElement(descriptor, index, value);
   }

   override fun encodeLongElement(descriptor: SerialDescriptor, index: Int, value: Long) {
      TomlCompositeEncoder.super.encodeLongElement(descriptor, index, value);
   }

   override fun encodeFloatElement(descriptor: SerialDescriptor, index: Int, value: Float) {
      TomlCompositeEncoder.super.encodeFloatElement(descriptor, index, value);
   }

   override fun encodeDoubleElement(descriptor: SerialDescriptor, index: Int, value: Double) {
      TomlCompositeEncoder.super.encodeDoubleElement(descriptor, index, value);
   }

   override fun encodeCharElement(descriptor: SerialDescriptor, index: Int, value: Char) {
      TomlCompositeEncoder.super.encodeCharElement(descriptor, index, value);
   }

   override fun encodeStringElement(descriptor: SerialDescriptor, index: Int, value: java.lang.String) {
      TomlCompositeEncoder.super.encodeStringElement(descriptor, index, value);
   }

   override fun <T> encodeNullableSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<? super T>, value: T?) {
      TomlCompositeEncoder.super.encodeNullableSerializableElement(descriptor, index, serializer, value);
   }

   override fun <T> encodeSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<? super T>, value: T) {
      TomlCompositeEncoder.super.encodeSerializableElement(descriptor, index, serializer, value);
   }

   @ExperimentalSerializationApi
   override fun shouldEncodeElementDefault(descriptor: SerialDescriptor, index: Int): Boolean {
      return TomlCompositeEncoder.super.shouldEncodeElementDefault(descriptor, index);
   }
}
