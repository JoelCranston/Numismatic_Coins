package io.ktor.util.collections

import io.ktor.utils.io.InternalAPI

@InternalAPI
public class SerializedMapValue<T>(key: String, serialize: (Any) -> String?, deserialize: (String) -> Any) {
   internal final val key: String
   internal final val serialize: (Any) -> String?
   internal final val deserialize: (String) -> Any

   init {
      this.key = key;
      this.serialize = serialize;
      this.deserialize = deserialize;
   }
}
