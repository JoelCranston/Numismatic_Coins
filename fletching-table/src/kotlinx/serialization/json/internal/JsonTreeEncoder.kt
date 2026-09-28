package kotlinx.serialization.json.internal

import java.util.LinkedHashMap
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

private open class JsonTreeEncoder(json: Json, nodeConsumer: (JsonElement) -> Unit) : AbstractJsonTreeEncoder(json, nodeConsumer) {
   protected final val content: MutableMap<String, JsonElement> = (new LinkedHashMap()) as java.util.Map

   public override fun putElement(key: String, element: JsonElement) {
      this.content.put(key, element);
   }

   public override fun <T : Any> encodeNullableSerializableElement(descriptor: SerialDescriptor, index: Int, serializer: SerializationStrategy<T>, value: T?) {
      if (value != null || this.configuration.getExplicitNulls()) {
         super.encodeNullableSerializableElement(descriptor, index, serializer, value);
      }
   }

   public override fun getCurrent(): JsonElement {
      return new JsonObject(this.content);
   }
}
