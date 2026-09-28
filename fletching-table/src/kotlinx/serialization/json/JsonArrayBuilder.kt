package kotlinx.serialization.json

import java.util.ArrayList
import kotlinx.serialization.ExperimentalSerializationApi

@JsonDslMarker
public class JsonArrayBuilder @PublishedApi  internal constructor() {
   private final val content: MutableList<JsonElement> = (new ArrayList()) as java.util.List

   public fun add(element: JsonElement): Boolean {
      this.content.add(element);
      return true;
   }

   @ExperimentalSerializationApi
   public fun addAll(elements: Collection<JsonElement>): Boolean {
      return this.content.addAll(elements);
   }

   @PublishedApi
   internal fun build(): JsonArray {
      return new JsonArray(this.content);
   }
}
