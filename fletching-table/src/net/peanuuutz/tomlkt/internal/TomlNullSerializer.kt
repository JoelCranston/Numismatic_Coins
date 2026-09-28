package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.TomlDecoderKt
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlEncoderKt
import net.peanuuutz.tomlkt.TomlNull

internal object TomlNullSerializer : KSerializer<TomlNull> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor$default("net.peanuuutz.tomlkt.TomlNull", SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], null, 8, null)

   public open fun serialize(encoder: Encoder, value: TomlNull) {
      TomlEncoderKt.asTomlEncoder(encoder).encodeTomlElement(value);
   }

   public open fun deserialize(decoder: Decoder): TomlNull {
      return TomlElementKt.asTomlNull(TomlDecoderKt.asTomlDecoder(decoder).decodeTomlElement());
   }
}
