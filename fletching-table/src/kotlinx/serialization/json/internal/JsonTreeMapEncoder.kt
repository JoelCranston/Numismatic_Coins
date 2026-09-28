package kotlinx.serialization.json.internal

import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonArraySerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectSerializer
import kotlinx.serialization.json.JsonPrimitive

private class JsonTreeMapEncoder(json: Json, nodeConsumer: (JsonElement) -> Unit) : JsonTreeEncoder(json, nodeConsumer) {
   private final lateinit var tag: String
   private final var isKey: Boolean = true

   public override fun putElement(key: String, element: JsonElement) {
      if (this.isKey) {
         if (element !is JsonPrimitive) {
            if (element is JsonObject) {
               throw JsonExceptionsKt.InvalidKeyKindException(JsonObjectSerializer.INSTANCE.getDescriptor());
            }

            if (element is JsonArray) {
               throw JsonExceptionsKt.InvalidKeyKindException(JsonArraySerializer.INSTANCE.getDescriptor());
            }

            throw new NoWhenBranchMatchedException();
         }

         this.tag = (element as JsonPrimitive).getContent();
         this.isKey = false;
      } else {
         val var3: java.util.Map = this.getContent();
         var var10000: java.lang.String = this.tag;
         if (this.tag == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tag");
            var10000 = null;
         }

         var3.put(var10000, element);
         this.isKey = true;
      }
   }

   public override fun getCurrent(): JsonElement {
      return new JsonObject(this.getContent());
   }
}
