package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptorsKt
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@PublishedApi
internal object JsonElementSerializer : KSerializer<JsonElement> {
   public open val descriptor: SerialDescriptor =
      SerialDescriptorsKt.buildSerialDescriptor(
         "kotlinx.serialization.json.JsonElement", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], JsonElementSerializer::descriptor$lambda$5
      )

   public open fun serialize(encoder: Encoder, value: JsonElement) {
      JsonElementSerializersKt.access$verify(encoder);
      if (value is JsonPrimitive) {
         encoder.encodeSerializableValue(JsonPrimitiveSerializer.INSTANCE, value);
      } else if (value is JsonObject) {
         encoder.encodeSerializableValue(JsonObjectSerializer.INSTANCE, value);
      } else {
         if (value !is JsonArray) {
            throw new NoWhenBranchMatchedException();
         }

         encoder.encodeSerializableValue(JsonArraySerializer.INSTANCE, value);
      }
   }

   public open fun deserialize(decoder: Decoder): JsonElement {
      return JsonElementSerializersKt.asJsonDecoder(decoder).decodeJsonElement();
   }

   @JvmStatic
   fun `descriptor$lambda$5$lambda$0`(): SerialDescriptor {
      return JsonPrimitiveSerializer.INSTANCE.getDescriptor();
   }

   @JvmStatic
   fun `descriptor$lambda$5$lambda$1`(): SerialDescriptor {
      return JsonNullSerializer.INSTANCE.getDescriptor();
   }

   @JvmStatic
   fun `descriptor$lambda$5$lambda$2`(): SerialDescriptor {
      return JsonLiteralSerializer.INSTANCE.getDescriptor();
   }

   @JvmStatic
   fun `descriptor$lambda$5$lambda$3`(): SerialDescriptor {
      return JsonObjectSerializer.INSTANCE.getDescriptor();
   }

   @JvmStatic
   fun `descriptor$lambda$5$lambda$4`(): SerialDescriptor {
      return JsonArraySerializer.INSTANCE.getDescriptor();
   }

   @JvmStatic
   fun ClassSerialDescriptorBuilder.`descriptor$lambda$5`(): Unit {
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "JsonPrimitive",
         JsonElementSerializersKt.access$defer(JsonElementSerializer::descriptor$lambda$5$lambda$0),
         null,
         false,
         12,
         null
      );
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "JsonNull",
         JsonElementSerializersKt.access$defer(JsonElementSerializer::descriptor$lambda$5$lambda$1),
         null,
         false,
         12,
         null
      );
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "JsonLiteral",
         JsonElementSerializersKt.access$defer(JsonElementSerializer::descriptor$lambda$5$lambda$2),
         null,
         false,
         12,
         null
      );
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "JsonObject",
         JsonElementSerializersKt.access$defer(JsonElementSerializer::descriptor$lambda$5$lambda$3),
         null,
         false,
         12,
         null
      );
      ClassSerialDescriptorBuilder.element$default(
         `$this$buildSerialDescriptor`,
         "JsonArray",
         JsonElementSerializersKt.access$defer(JsonElementSerializer::descriptor$lambda$5$lambda$4),
         null,
         false,
         12,
         null
      );
      return Unit.INSTANCE;
   }
}
