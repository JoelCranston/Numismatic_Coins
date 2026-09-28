package kotlinx.serialization.json.internal

import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonObject

private class JsonTreeMapDecoder(json: Json, value: JsonObject) : JsonTreeDecoder(json, value, null, null, 12) {
   public open val value: JsonObject
   private final val keys: List<String>
   private final val size: Int
   private final var position: Int

   init {
      this.value = value;
      this.keys = CollectionsKt.toList(this.getValue().keySet());
      this.size = this.keys.size() * 2;
      this.position = -1;
   }

   protected override fun elementName(descriptor: SerialDescriptor, index: Int): String {
      return this.keys.get(index / 2);
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      if (this.position < this.size - 1) {
         val var2: Int = this.position++;
         return this.position;
      } else {
         return -1;
      }
   }

   protected override fun currentElement(tag: String): JsonElement {
      return if (this.position % 2 == 0) JsonElementKt.JsonPrimitive(tag) else MapsKt.getValue(this.getValue(), tag);
   }

   public override fun endStructure(descriptor: SerialDescriptor) {
   }
}
