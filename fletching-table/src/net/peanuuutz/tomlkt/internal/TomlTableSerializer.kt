package net.peanuuutz.tomlkt.internal

import kotlin.jvm.internal.StringCompanionObject
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.BuiltinSerializersKt
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.TomlDecoderKt
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlEncoderKt
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.internal.TomlTableSerializer.descriptor.1

internal object TomlTableSerializer : KSerializer<TomlTable> {
   private final val delegate: KSerializer<Map<String, TomlElement>> =
      BuiltinSerializersKt.MapSerializer(BuiltinSerializersKt.serializer(StringCompanionObject.INSTANCE), TomlElement.Companion.serializer())
      public open val descriptor: SerialDescriptor = (new 1()) as SerialDescriptor

   public open fun serialize(encoder: Encoder, value: TomlTable) {
      delegate.serialize(TomlEncoderKt.asTomlEncoder(encoder), value);
   }

   public open fun deserialize(decoder: Decoder): TomlTable {
      return TomlElementKt.asTomlTable(TomlDecoderKt.asTomlDecoder(decoder).decodeTomlElement());
   }
}
