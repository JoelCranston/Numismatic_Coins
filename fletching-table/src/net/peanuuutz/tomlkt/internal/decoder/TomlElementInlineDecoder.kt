package net.peanuuutz.tomlkt.internal.decoder

import net.peanuuutz.tomlkt.TomlElementKt

private class TomlElementInlineDecoder(delegate: AbstractTomlElementDecoder) : AbstractTomlInlineDecoder(delegate) {
   public override fun decodeByte(): Byte {
      return UStringsKt.toUByte(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
   }

   public override fun decodeShort(): Short {
      return UStringsKt.toUShort(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
   }

   public override fun decodeInt(): Int {
      return UStringsKt.toUInt(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
   }

   public override fun decodeLong(): Long {
      return UStringsKt.toULong(TomlElementKt.asTomlLiteral(this.getDelegate().getElement()).getContent());
   }
}
