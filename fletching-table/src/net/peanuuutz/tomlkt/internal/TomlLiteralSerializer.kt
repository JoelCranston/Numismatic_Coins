package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.TomlDecoderKt
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlEncoderKt
import net.peanuuutz.tomlkt.TomlLiteral

internal object TomlLiteralSerializer : KSerializer<TomlLiteral> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("net.peanuuutz.tomlkt.TomlLiteral", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: TomlLiteral) {
      TomlEncoderKt.asTomlEncoder(encoder).encodeTomlElement(value);
   }

   public open fun deserialize(decoder: Decoder): TomlLiteral {
      return TomlElementKt.asTomlLiteral(TomlDecoderKt.asTomlDecoder(decoder).decodeTomlElement());
   }
}
