package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = JsonNullSerializer::class)
public object JsonNull : JsonPrimitive() {
   public open val isString: Boolean
      public open get() {
         return false;
      }


   public open val content: String = "null"

   public fun serializer(): KSerializer<JsonNull> {
      return JsonNullSerializer.INSTANCE;
   }
}
