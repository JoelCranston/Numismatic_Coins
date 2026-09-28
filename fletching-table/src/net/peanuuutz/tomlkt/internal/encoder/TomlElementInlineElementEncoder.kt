package net.peanuuutz.tomlkt.internal.encoder

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import net.peanuuutz.tomlkt.TomlElementKt

@SourceDebugExtension(["SMAP\nTomlElementEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/TomlElementInlineElementEncoder\n+ 2 AbstractTomlEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/AbstractTomlEncoderKt\n*L\n1#1,324:1\n228#2,4:325\n228#2,4:329\n228#2,4:333\n228#2,4:337\n*S KotlinDebug\n*F\n+ 1 TomlElementEncoder.kt\nnet/peanuuutz/tomlkt/internal/encoder/TomlElementInlineElementEncoder\n*L\n172#1:325,4\n178#1:329,4\n184#1:333,4\n190#1:337,4\n*E\n"])
private class TomlElementInlineElementEncoder(parentDescriptor: SerialDescriptor, elementIndex: Int, delegate: AbstractTomlElementCompositeEncoder) : AbstractTomlInlineElementEncoder(
      parentDescriptor, elementIndex, delegate
   ) {
   public override fun encodeByte(value: Byte) {
      val `$this$encodeElement$iv`: TomlCompositeEncoder = this.getDelegate();
      val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
      val `index$iv`: Int = this.getElementIndex();
      `$this$encodeElement$iv`.beginElement(`descriptor$iv`, `index$iv`);
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-7apg3OU(UByte.constructor-impl(value)));
      `$this$encodeElement$iv`.endElement(`descriptor$iv`, `index$iv`);
   }

   public override fun encodeShort(value: Short) {
      val `$this$encodeElement$iv`: TomlCompositeEncoder = this.getDelegate();
      val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
      val `index$iv`: Int = this.getElementIndex();
      `$this$encodeElement$iv`.beginElement(`descriptor$iv`, `index$iv`);
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-xj2QHRw(UShort.constructor-impl(value)));
      `$this$encodeElement$iv`.endElement(`descriptor$iv`, `index$iv`);
   }

   public override fun encodeInt(value: Int) {
      val `$this$encodeElement$iv`: TomlCompositeEncoder = this.getDelegate();
      val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
      val `index$iv`: Int = this.getElementIndex();
      `$this$encodeElement$iv`.beginElement(`descriptor$iv`, `index$iv`);
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-WZ4Q5Ns(UInt.constructor-impl(value)));
      `$this$encodeElement$iv`.endElement(`descriptor$iv`, `index$iv`);
   }

   public override fun encodeLong(value: Long) {
      val `$this$encodeElement$iv`: TomlCompositeEncoder = this.getDelegate();
      val `descriptor$iv`: SerialDescriptor = this.getParentDescriptor();
      val `index$iv`: Int = this.getElementIndex();
      `$this$encodeElement$iv`.beginElement(`descriptor$iv`, `index$iv`);
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-VKZWuLQ(ULong.constructor-impl(value)));
      `$this$encodeElement$iv`.endElement(`descriptor$iv`, `index$iv`);
   }
}
