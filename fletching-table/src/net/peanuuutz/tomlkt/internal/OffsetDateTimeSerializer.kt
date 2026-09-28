package net.peanuuutz.tomlkt.internal

import java.time.OffsetDateTime
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

internal object OffsetDateTimeSerializer : KSerializer<OffsetDateTime> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.PrimitiveSerialDescriptor("net.peanuuutz.tomlkt.NativeOffsetDateTime", PrimitiveKind.STRING.INSTANCE)

   public open fun serialize(encoder: Encoder, value: OffsetDateTime) {
      if (encoder is TomlEncoder) {
         (encoder as TomlEncoder).encodeTomlElement(TomlElementKt.TomlLiteral(value));
      } else {
         encoder.encodeString(value.toString());
      }
   }

   public open fun deserialize(decoder: Decoder): OffsetDateTime {
      return if (decoder is TomlDecoder)
         TomlElementKt.toOffsetDateTime(TomlElementKt.asTomlLiteral((decoder as TomlDecoder).decodeTomlElement()))
         else
         NativeDateTime_jvmKt.NativeOffsetDateTime(decoder.decodeString());
   }
}
