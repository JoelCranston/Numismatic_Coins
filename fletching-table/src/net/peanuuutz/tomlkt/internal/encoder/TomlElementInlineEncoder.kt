package net.peanuuutz.tomlkt.internal.encoder

import net.peanuuutz.tomlkt.TomlElementKt

private class TomlElementInlineEncoder(delegate: AbstractTomlElementEncoder) : AbstractTomlInlineEncoder(delegate) {
   public override fun encodeByte(value: Byte) {
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-7apg3OU(UByte.constructor-impl(value)));
   }

   public override fun encodeShort(value: Short) {
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-xj2QHRw(UShort.constructor-impl(value)));
   }

   public override fun encodeInt(value: Int) {
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-WZ4Q5Ns(UInt.constructor-impl(value)));
   }

   public override fun encodeLong(value: Long) {
      this.getDelegate().setElement(TomlElementKt.TomlLiteral-VKZWuLQ(ULong.constructor-impl(value)));
   }
}
