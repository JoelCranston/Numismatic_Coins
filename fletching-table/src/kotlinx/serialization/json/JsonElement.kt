package kotlinx.serialization.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable(with = JsonElementSerializer::class)
public sealed class JsonElement protected constructor() {
   public companion object {
      public fun serializer(): KSerializer<JsonElement> {
         return JsonElementSerializer.INSTANCE;
      }
   }
}
