package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.TomlDecoderKt
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlEncoderKt

internal object TomlElementSerializer : KSerializer<TomlElement> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor$default(
         "net.peanuuutz.tomlkt.TomlElement", SerialKind.CONTEXTUAL.INSTANCE, new SerialDescriptor[0], null, 8, null
      )

   public open fun serialize(encoder: Encoder, value: TomlElement) {
      TomlEncoderKt.asTomlEncoder(encoder).encodeTomlElement(value);
   }

   public open fun deserialize(decoder: Decoder): TomlElement {
      return TomlDecoderKt.asTomlDecoder(decoder).decodeTomlElement();
   }
}
