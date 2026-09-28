package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.internal.JsonExceptionsKt

@PublishedApi
internal object JsonPrimitiveSerializer : KSerializer<JsonPrimitive> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor$default(
         "kotlinx.serialization.json.JsonPrimitive", PrimitiveKind.STRING.INSTANCE, new SerialDescriptor[0], null, 8, null
      )

   public open fun serialize(encoder: Encoder, value: JsonPrimitive) {
      JsonElementSerializersKt.access$verify(encoder);
      if (value is JsonNull) {
         encoder.encodeSerializableValue(JsonNullSerializer.INSTANCE, JsonNull.INSTANCE);
      } else {
         encoder.encodeSerializableValue(JsonLiteralSerializer.INSTANCE, value as JsonLiteral);
      }
   }

   public open fun deserialize(decoder: Decoder): JsonPrimitive {
      val result: JsonElement = JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
      if (result !is JsonPrimitive) {
         throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected JSON element, expected JsonPrimitive, had ${result.getClass()::class}", result.toString());
      } else {
         return result as JsonPrimitive;
      }
   }
}
