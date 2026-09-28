package net.peanuuutz.tomlkt.internal

import java.time.LocalDate
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

internal object LocalDateSerializer : KSerializer<LocalDate> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("net.peanuuutz.tomlkt.NativeLocalDate", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: LocalDate) {
      if (encoder is TomlEncoder) {
         (encoder as TomlEncoder).encodeTomlElement(TomlElementKt.TomlLiteral(value));
      } else {
         encoder.encodeString(value.toString());
      }
   }

   public open fun deserialize(decoder: Decoder): LocalDate {
      return if (decoder is TomlDecoder)
         TomlElementKt.toLocalDate(TomlElementKt.asTomlLiteral((decoder as TomlDecoder).decodeTomlElement()))
         else
         NativeDateTime_jvmKt.NativeLocalDate(decoder.decodeString());
   }
}
