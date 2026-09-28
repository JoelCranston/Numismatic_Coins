package net.peanuuutz.tomlkt.internal.decoder

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlDecoder
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

internal abstract class AbstractTomlInlineDecoder<D extends AbstractTomlDecoder> : TomlDecoder {
   protected final val delegate: Any
   public open val serializersModule: SerializersModule
   public open val toml: Toml

   open fun AbstractTomlInlineDecoder(delegate: D) {
      this.delegate = (D)delegate;
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return if (SerialDescriptorUtilsKt.isUnsignedInteger(descriptor)) this else this.delegate;
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      return (T)this.delegate.decodeSerializableValue(deserializer);
   }

   public override fun decodeTomlElement(): TomlElement {
      return this.delegate.decodeTomlElement();
   }

   @ExperimentalSerializationApi
   public override fun decodeNotNullMark(): Boolean {
      return this.delegate.decodeNotNullMark();
   }

   @ExperimentalSerializationApi
   public override fun decodeNull(): Nothing? {
      return this.delegate.decodeNull();
   }

   public override fun decodeBoolean(): Boolean {
      return this.delegate.decodeBoolean();
   }

   public override fun decodeByte(): Byte {
      return this.delegate.decodeByte();
   }

   public override fun decodeShort(): Short {
      return this.delegate.decodeShort();
   }

   public override fun decodeChar(): Char {
      return this.delegate.decodeChar();
   }

   public override fun decodeInt(): Int {
      return this.delegate.decodeInt();
   }

   public override fun decodeLong(): Long {
      return this.delegate.decodeLong();
   }

   public override fun decodeFloat(): Float {
      return this.delegate.decodeFloat();
   }

   public override fun decodeDouble(): Double {
      return this.delegate.decodeDouble();
   }

   public override fun decodeString(): String {
      return this.delegate.decodeString();
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      return this.delegate.decodeEnum(enumDescriptor);
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      return this.delegate.beginStructure(descriptor);
   }

   @ExperimentalSerializationApi
   public override fun <T : Any> decodeNullableSerializableValue(deserializer: DeserializationStrategy<T?>): T? {
      return (T)this.delegate.decodeNullableSerializableValue(deserializer);
   }
}
