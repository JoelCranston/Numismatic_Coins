package net.peanuuutz.tomlkt.internal.decoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlElementKt

@SourceDebugExtension(["SMAP\nTomlElementDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/TomlElementInlineElementDecoder\n+ 2 AbstractTomlDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/AbstractTomlDecoderKt\n*L\n1#1,413:1\n168#2,4:414\n168#2,4:418\n168#2,4:422\n168#2,4:426\n*S KotlinDebug\n*F\n+ 1 TomlElementDecoder.kt\nnet/peanuuutz/tomlkt/internal/decoder/TomlElementInlineElementDecoder\n*L\n228#1:414,4\n240#1:418,4\n252#1:422,4\n264#1:426,4\n*E\n"])
private class TomlElementInlineElementDecoder(parentDescriptor: SerialDescriptor, elementIndex: Int, delegate: AbstractTomlElementCompositeDecoder) : AbstractTomlInlineElementDecoder(
      parentDescriptor, elementIndex, delegate
   ) {
   public override fun decodeByte(): Byte {
      val var10000: Byte;
      if (!this.getDecodedNotNullMark()) {
         val value: TomlCompositeDecoder = this.getDelegate();
         val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
         val `index$iv`: Int = this.getElementIndex();
         value.beginElement(`descriptor$iv`, `index$iv`);
         val `value$iv`: Byte = UStringsKt.toUByte(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var7: Byte = UStringsKt.toUByte(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         this.setDecodedNotNullMark(false);
         var10000 = var7;
      }

      return var10000;
   }

   public override fun decodeShort(): Short {
      val var10000: Short;
      if (!this.getDecodedNotNullMark()) {
         val value: TomlCompositeDecoder = this.getDelegate();
         val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
         val `index$iv`: Int = this.getElementIndex();
         value.beginElement(`descriptor$iv`, `index$iv`);
         val `value$iv`: Short = UStringsKt.toUShort(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var7: Short = UStringsKt.toUShort(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         this.setDecodedNotNullMark(false);
         var10000 = var7;
      }

      return var10000;
   }

   public override fun decodeInt(): Int {
      val var10000: Int;
      if (!this.getDecodedNotNullMark()) {
         val value: TomlCompositeDecoder = this.getDelegate();
         val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
         val `index$iv`: Int = this.getElementIndex();
         value.beginElement(`descriptor$iv`, `index$iv`);
         val `value$iv`: Int = UStringsKt.toUInt(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var7: Int = UStringsKt.toUInt(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         this.setDecodedNotNullMark(false);
         var10000 = var7;
      }

      return var10000;
   }

   public override fun decodeLong(): Long {
      val var10000: Long;
      if (!this.getDecodedNotNullMark()) {
         val value: TomlCompositeDecoder = this.getDelegate();
         val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
         val `index$iv`: Int = this.getElementIndex();
         value.beginElement(`descriptor$iv`, `index$iv`);
         val `value$iv`: Long = UStringsKt.toULong(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         value.endElement(`descriptor$iv`, `index$iv`);
         var10000 = `value$iv`;
      } else {
         val var8: Long = UStringsKt.toULong(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
         this.setDecodedNotNullMark(false);
         var10000 = var8;
      }

      return var10000;
   }
}
