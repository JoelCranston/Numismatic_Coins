package net.peanuuutz.tomlkt.internal

import java.time.LocalTime
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import net.peanuuutz.tomlkt.NativeDateTime_jvmKt
import net.peanuuutz.tomlkt.TomlDecoder
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlEncoder

internal object LocalTimeSerializer : KSerializer<LocalTime> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("net.peanuuutz.tomlkt.NativeLocalTime", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: LocalTime) {
      if (encoder is TomlEncoder) {
         (encoder as TomlEncoder).encodeTomlElement(TomlElementKt.TomlLiteral(value));
      } else {
         encoder.encodeString(value.toString());
      }
   }

   public open fun deserialize(decoder: Decoder): LocalTime {
      return if (decoder is TomlDecoder)
         TomlElementKt.toLocalTime(TomlElementKt.asTomlLiteral((decoder as TomlDecoder).decodeTomlElement()))
         else
         NativeDateTime_jvmKt.NativeLocalTime(decoder.decodeString());
   }
}
