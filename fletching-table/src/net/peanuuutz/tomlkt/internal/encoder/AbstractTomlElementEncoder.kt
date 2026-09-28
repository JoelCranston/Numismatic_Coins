package net.peanuuutz.tomlkt.internal.encoder

import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt

internal abstract class AbstractTomlElementEncoder : AbstractTomlEncoder {
   public final lateinit var element: TomlElement
      internal set

   open fun AbstractTomlElementEncoder(toml: Toml) {
      super(toml);
   }

   public override fun encodeBoolean(value: Boolean) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeByte(value: Byte) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeShort(value: Short) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeInt(value: Int) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeLong(value: Long) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeFloat(value: Float) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeDouble(value: Double) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeChar(value: Char) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeString(value: String) {
      this.setElement(TomlElementKt.TomlLiteral(value));
   }

   public override fun encodeNull() {
      this.setElement(TomlNull.INSTANCE);
   }

   public override fun encodeTomlElement(value: TomlElement) {
      this.setElement(value);
   }

   public override fun encodeInline(descriptor: SerialDescriptor): Encoder {
      return if (SerialDescriptorUtilsKt.isUnsignedInteger(descriptor)) new TomlElementInlineEncoder(this) else this;
   }

   public override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
      this.encodeString(enumDescriptor.getElementName(index));
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
      return TomlElementEncoderKt.access$beginStructurePolymorphically(this, descriptor);
   }

   public override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
      return TomlElementEncoderKt.access$beginCollectionPolymorphically(this, descriptor, collectionSize);
   }

   public override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
      if (!SerialDescriptorUtilsKt.isTomlElement(serializer)) {
         super.encodeSerializableValue(serializer, value);
      } else {
         this.encodeTomlElement(value as TomlElement);
      }
   }
}
