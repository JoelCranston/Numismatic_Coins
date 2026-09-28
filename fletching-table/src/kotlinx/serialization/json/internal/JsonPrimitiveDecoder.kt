package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@SourceDebugExtension(["SMAP\nTreeJsonDecoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonDecoder.kt\nkotlinx/serialization/json/internal/JsonPrimitiveDecoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,348:1\n1#2:349\n*E\n"])
private class JsonPrimitiveDecoder(json: Json, value: JsonElement, polymorphicDiscriminator: String? = null) : AbstractJsonTreeDecoder(
      json, value, polymorphicDiscriminator
   ) {
   public open val value: JsonElement

   init {
      this.value = value;
      this.pushTag("primitive");
   }

   public override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
      return 0;
   }

   protected override fun currentElement(tag: String): JsonElement {
      if (tag != "primitive") {
         throw new IllegalArgumentException("This input can only handle primitives with 'primitive' tag".toString());
      } else {
         return this.getValue();
      }
   }
}
