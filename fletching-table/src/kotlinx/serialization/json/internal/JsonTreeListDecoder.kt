package kotlinx.serialization.json.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement

private class JsonTreeListDecoder(json: Json, value: JsonArray) : AbstractJsonTreeDecoder(json, value, null, 4) {
   public open val value: JsonArray
   private final val size: Int
   private final var currentIndex: Int

   init {
      this.value = value;
      this.size = this.getValue().size();
      this.currentIndex = -1;
   }

   protected override fun elementName(descriptor: SerialDescriptor, index: Int): String {
      return java.lang.String.valueOf(index);
   }

   protected override fun currentElement(tag: String): JsonElement {
      return this.getValue().get(Integer.parseInt(tag));
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      if (this.currentIndex < this.size - 1) {
         val var2: Int = this.currentIndex++;
         return this.currentIndex;
      } else {
         return -1;
      }
   }
}
