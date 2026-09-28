package io.ktor.http

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

public object UrlSerializer : KSerializer<Url> {
   public open val descriptor: SerialDescriptor = SerialDescriptorsKt.PrimitiveSerialDescriptor("io.ktor.http.Url", PrimitiveKind.STRING.INSTANCE)

   public open fun deserialize(decoder: Decoder): Url {
      return URLUtilsKt.Url(decoder.decodeString());
   }

   public open fun serialize(encoder: Encoder, value: Url) {
      encoder.encodeString(value.toString());
   }
}
