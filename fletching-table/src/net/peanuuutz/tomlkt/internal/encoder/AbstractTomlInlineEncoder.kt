package net.peanuuutz.tomlkt.internal.encoder

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlEncoder
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

internal abstract class AbstractTomlInlineEncoder<E extends AbstractTomlEncoder> : TomlEncoder {
   protected final val delegate: Any
   public open val serializersModule: SerializersModule
   public open val toml: Toml

   open fun AbstractTomlInlineEncoder(delegate: E) {
      this.delegate = (E)delegate;
   }

   public override fun encodeInline(descriptor: SerialDescriptor): Encoder {
      return if (SerialDescriptorUtilsKt.isUnsignedInteger(descriptor)) this else this.delegate;
   }

   public override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
      return this.delegate.beginCollection(descriptor, collectionSize);
   }

   public override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
      this.delegate.encodeSerializableValue(serializer, value);
   }

   public override fun encodeTomlElement(value: TomlElement) {
      this.delegate.encodeTomlElement(value);
   }

   @ExperimentalSerializationApi
   public override fun encodeNotNullMark() {
      this.delegate.encodeNotNullMark();
   }

   @ExperimentalSerializationApi
   public override fun encodeNull() {
      this.delegate.encodeNull();
   }

   public override fun encodeBoolean(value: Boolean) {
      this.delegate.encodeBoolean(value);
   }

   public override fun encodeByte(value: Byte) {
      this.delegate.encodeByte(value);
   }

   public override fun encodeShort(value: Short) {
      this.delegate.encodeShort(value);
   }

   public override fun encodeChar(value: Char) {
      this.delegate.encodeChar(value);
   }

   public override fun encodeInt(value: Int) {
      this.delegate.encodeInt(value);
   }

   public override fun encodeLong(value: Long) {
      this.delegate.encodeLong(value);
   }

   public override fun encodeFloat(value: Float) {
      this.delegate.encodeFloat(value);
   }

   public override fun encodeDouble(value: Double) {
      this.delegate.encodeDouble(value);
   }

   public override fun encodeString(value: String) {
      this.delegate.encodeString(value);
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
      this.delegate.encodeEnum(enumDescriptor, index);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      return this.delegate.beginStructure(descriptor);
   }

   @ExperimentalSerializationApi
   public override fun <T : Any> encodeNullableSerializableValue(serializer: SerializationStrategy<T>, value: T?) {
      this.delegate.encodeNullableSerializableValue(serializer, value);
   }
}
