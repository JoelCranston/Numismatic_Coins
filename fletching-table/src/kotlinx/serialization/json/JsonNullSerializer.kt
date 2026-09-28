package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.internal.JsonDecodingException

@PublishedApi
internal object JsonNullSerializer : KSerializer<JsonNull> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor$default("kotlinx.serialization.json.JsonNull", SerialKind.ENUM.INSTANCE, new SerialDescriptor[0], null, 8, null)

   public open fun serialize(encoder: Encoder, value: JsonNull) {
      JsonElementSerializersKt.access$verify(encoder);
      encoder.encodeNull();
   }

   public open fun deserialize(decoder: Decoder): JsonNull {
      JsonElementSerializersKt.access$verify(decoder);
      if (decoder.decodeNotNullMark()) {
         throw new JsonDecodingException("Expected 'null' literal");
      } else {
         decoder.decodeNull();
         return JsonNull.INSTANCE;
      }
   }
}
