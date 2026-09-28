package kotlinx.serialization.json.internal

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

@JsonFriendModuleApi
public fun <T> readJson(json: Json, element: JsonElement, deserializer: DeserializationStrategy<T>): T {
   val var10000: AbstractJsonTreeDecoder;
   if (element is JsonObject) {
      var10000 = new JsonTreeDecoder(json, element as JsonObject, null, null, 12, null);
   } else if (element is JsonArray) {
      var10000 = new JsonTreeListDecoder(json, element as JsonArray);
   } else {
      if (element !is JsonLiteral && !(element == JsonNull.INSTANCE)) {
         throw new NoWhenBranchMatchedException();
      }

      var10000 = new JsonPrimitiveDecoder(json, element as JsonPrimitive, null, 4, null);
   }

   return (T)var10000.decodeSerializableValue(deserializer);
}

internal fun <T> Json.readPolymorphicJson(discriminator: String, element: JsonObject, deserializer: DeserializationStrategy<T>): T {
   return (T)new JsonTreeDecoder(`$this$readPolymorphicJson`, element, discriminator, deserializer.getDescriptor()).decodeSerializableValue(deserializer);
}
