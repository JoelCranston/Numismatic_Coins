package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = JsonPrimitiveSerializer::class)
public sealed class JsonPrimitive protected constructor() : JsonElement() {
   public abstract val isString: Boolean
   public abstract val content: String

   public override fun toString(): String {
      return this.getContent();
   }

   public companion object {
      public fun serializer(): KSerializer<JsonPrimitive> {
         return JsonPrimitiveSerializer.INSTANCE;
      }
   }
}
