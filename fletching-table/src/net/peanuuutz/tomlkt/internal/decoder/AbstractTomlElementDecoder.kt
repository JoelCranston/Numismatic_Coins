package net.peanuuutz.tomlkt.internal.decoder

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.internal.SerialDescriptorUtilsKt
import net.peanuuutz.tomlkt.internal.TomlSerializationExceptionsKt

internal abstract class AbstractTomlElementDecoder : AbstractTomlDecoder {
   public abstract val element: TomlElement

   open fun AbstractTomlElementDecoder(toml: Toml) {
      super(toml);
   }

   public override fun decodeBoolean(): Boolean {
      return TomlElementKt.toBoolean(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeByte(): Byte {
      return TomlElementKt.toByte(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeShort(): Short {
      return TomlElementKt.toShort(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeInt(): Int {
      return TomlElementKt.toInt(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeLong(): Long {
      return TomlElementKt.toLong(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeFloat(): Float {
      return TomlElementKt.toFloat(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeDouble(): Double {
      return TomlElementKt.toDouble(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeChar(): Char {
      return TomlElementKt.toChar(TomlElementKt.asTomlLiteral(this.getElement()));
   }

   public override fun decodeString(): String {
      return TomlElementKt.asTomlLiteral(this.getElement()).getContent();
   }

   public override fun decodeNull(): Nothing? {
      return TomlElementKt.asTomlNull(this.getElement()).getContent();
   }

   public override fun decodeNotNullMark(): Boolean {
      return this.getElement() !is TomlNull;
   }

   public override fun decodeTomlElement(): TomlElement {
      return this.getElement();
   }

   public override fun decodeInline(descriptor: SerialDescriptor): Decoder {
      return if (SerialDescriptorUtilsKt.isUnsignedInteger(descriptor)) new TomlElementInlineDecoder(this) else this;
   }

   public override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
      return enumDescriptor.getElementIndex(TomlElementKt.asTomlLiteral(this.getElement()).getContent());
   }

   public override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
      val discriminator: java.lang.String = this.getCurrentDiscriminator();
      this.setCurrentDiscriminator(null);
      val kind: SerialKind = descriptor.getKind();
      val var10000: CompositeDecoder;
      if (kind == StructureKind.CLASS.INSTANCE || kind is PolymorphicKind || kind == StructureKind.OBJECT.INSTANCE) {
         var10000 = new TomlElementClassDecoder(this, TomlElementKt.asTomlTable(this.getElement()), discriminator);
      } else if (kind == StructureKind.LIST.INSTANCE) {
         var10000 = new TomlElementArrayDecoder(this, TomlElementKt.asTomlArray(this.getElement()));
      } else {
         if (!(kind == StructureKind.MAP.INSTANCE)) {
            TomlSerializationExceptionsKt.throwUnsupportedSerialKind(kind);
            throw new KotlinNothingValueException();
         }

         var10000 = new TomlElementMapDecoder(this, TomlElementKt.asTomlTable(this.getElement()));
      }

      return var10000;
   }

   public override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>): T {
      return (T)(if (!SerialDescriptorUtilsKt.isTomlElement(deserializer)) super.decodeSerializableValue(deserializer) else this.decodeTomlElement() as Any);
   }
}
