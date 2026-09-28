package net.peanuuutz.tomlkt.internal

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlDecoderKt
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlEncoderKt
import net.peanuuutz.tomlkt.internal.TomlArraySerializer.descriptor.1

internal object TomlArraySerializer : KSerializer<TomlArray> {
   private final val delegate: KSerializer<List<TomlElement>> = BuiltinSerializersKt.ListSerializer(TomlElement.Companion.serializer())
   public open val descriptor: SerialDescriptor = (new 1()) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: TomlArray) {
      delegate.serialize(TomlEncoderKt.asTomlEncoder(encoder), value);
   }

   public open fun deserialize(decoder: Decoder): TomlArray {
      return TomlElementKt.asTomlArray(TomlDecoderKt.asTomlDecoder(decoder).decodeTomlElement());
   }
}
