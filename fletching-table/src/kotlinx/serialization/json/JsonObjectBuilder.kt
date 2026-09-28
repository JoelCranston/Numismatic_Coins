package kotlinx.serialization.json

import java.util.LinkedHashMap

@JsonDslMarker
public class JsonObjectBuilder @PublishedApi  internal constructor() {
   private final val content: MutableMap<String, JsonElement> = (new LinkedHashMap()) as java.util.Map

   public fun put(key: String, element: JsonElement): JsonElement? {
      return this.content.put(key, element);
   }

   @PublishedApi
   internal fun build(): JsonObject {
      return new JsonObject(this.content);
   }
}
