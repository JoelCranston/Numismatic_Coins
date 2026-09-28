package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement

@SourceDebugExtension(["SMAP\nTreeJsonEncoder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TreeJsonEncoder.kt\nkotlinx/serialization/json/internal/JsonPrimitiveEncoder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,279:1\n1#2:280\n*E\n"])
private class JsonPrimitiveEncoder(json: Json, nodeConsumer: (JsonElement) -> Unit) : AbstractJsonTreeEncoder(json, nodeConsumer) {
   private final var content: JsonElement?

   init {
      this.pushTag("primitive");
   }

   public override fun putElement(key: String, element: JsonElement) {
      if (key != "primitive") {
         throw new IllegalArgumentException("This output can only consume primitives with 'primitive' tag".toString());
      } else if (this.content != null) {
         throw new IllegalArgumentException("Primitive element was already recorded. Does call to .encodeXxx happen more than once?".toString());
      } else {
         this.content = element;
         this.getNodeConsumer().invoke(element);
      }
   }

   public override fun getCurrent(): JsonElement {
      if (this.content == null) {
         throw new IllegalArgumentException("Primitive element has not been recorded. Is call to .encodeXxx is missing in serializer?".toString());
      } else {
         return this.content;
      }
   }
}
