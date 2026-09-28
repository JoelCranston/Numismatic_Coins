package net.peanuuutz.tomlkt.internal.decoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

@SourceDebugExtension(["SMAP\nTomlElementDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlElementCompositeDecoder\n+ 2 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlDecoderKt\n*L\n1#1,413:1\n168#2,4:414\n*S KotlinDebug\n*F\n+ 1 TomlElementDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlElementCompositeDecoder\n*L\n207#1:414,4\n*E\n"])
private abstract class AbstractTomlElementCompositeDecoder : AbstractTomlElementDecoder, TomlCompositeDecoder {
   open fun AbstractTomlElementCompositeDecoder(delegate: AbstractTomlElementDecoder) {
      super(delegate.getToml());
   }

   public override fun decodeInlineElement(descriptor: SerialDescriptor, index: Int): Decoder {
      return if (SerialDescriptorUtilsKt.isUnsignedInteger(descriptor.getElementDescriptor(index)))
         new TomlElementInlineElementDecoder(descriptor, index, this)
         else
         this;
   }

   public override fun <T : Any> decodeNullableSerializableElement(
      descriptor: SerialDescriptor,
      index: Int,
      deserializer: DeserializationStrategy<T?>,
      previousValue: T?
   ): T? {
      val `$this$decodeElement$iv`: TomlCompositeDecoder = this;
      this.beginElement(descriptor, index);
      val `value$iv`: Any = if (this.getElement() is TomlNull) null else this.decodeSerializableValue(deserializer);
      `$this$decodeElement$iv`.endElement(descriptor, index);
      return (T)`value$iv`;
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
   }

   override fun decodeBooleanElement(descriptor: SerialDescriptor, index: Int): Boolean {
      return TomlCompositeDecoder.super.decodeBooleanElement(descriptor, index);
   }

   override fun decodeByteElement(descriptor: SerialDescriptor, index: Int): Byte {
      return TomlCompositeDecoder.super.decodeByteElement(descriptor, index);
   }

   override fun decodeShortElement(descriptor: SerialDescriptor, index: Int): Short {
      return TomlCompositeDecoder.super.decodeShortElement(descriptor, index);
   }

   override fun decodeIntElement(descriptor: SerialDescriptor, index: Int): Int {
      return TomlCompositeDecoder.super.decodeIntElement(descriptor, index);
   }

   override fun decodeLongElement(descriptor: SerialDescriptor, index: Int): Long {
      return TomlCompositeDecoder.super.decodeLongElement(descriptor, index);
   }

   override fun decodeFloatElement(descriptor: SerialDescriptor, index: Int): Float {
      return TomlCompositeDecoder.super.decodeFloatElement(descriptor, index);
   }

   override fun decodeDoubleElement(descriptor: SerialDescriptor, index: Int): Double {
      return TomlCompositeDecoder.super.decodeDoubleElement(descriptor, index);
   }

   override fun decodeCharElement(descriptor: SerialDescriptor, index: Int): Char {
      return TomlCompositeDecoder.super.decodeCharElement(descriptor, index);
   }

   override fun decodeStringElement(descriptor: SerialDescriptor, index: Int): java.lang.String {
      return TomlCompositeDecoder.super.decodeStringElement(descriptor, index);
   }

   override fun <T> decodeSerializableElement(descriptor: SerialDescriptor, index: Int, deserializer: DeserializationStrategy<? extends T>, previousValue: T?): T {
      return TomlCompositeDecoder.super.decodeSerializableElement(descriptor, index, deserializer, (T)previousValue);
   }

   @ExperimentalSerializationApi
   override fun decodeSequentially(): Boolean {
      return TomlCompositeDecoder.super.decodeSequentially();
   }

   override fun decodeCollectionSize(descriptor: SerialDescriptor): Int {
      return TomlCompositeDecoder.super.decodeCollectionSize(descriptor);
   }
}
