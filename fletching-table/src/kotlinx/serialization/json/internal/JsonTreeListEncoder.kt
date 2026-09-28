package kotlinx.serialization.json.internal

import java.util.ArrayList
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

private class JsonTreeListEncoder(json: Json, nodeConsumer: (JsonElement) -> Unit) : AbstractJsonTreeEncoder(json, nodeConsumer) {
   private final val array: ArrayList<JsonElement> = new ArrayList()

   protected override fun elementName(descriptor: SerialDescriptor, index: Int): String {
      return java.lang.String.valueOf(index);
   }

   public override fun putElement(key: String, element: JsonElement) {
      this.array.add(Integer.parseInt(key), element);
   }

   public override fun getCurrent(): JsonElement {
      return new JsonArray(this.array);
   }
}
