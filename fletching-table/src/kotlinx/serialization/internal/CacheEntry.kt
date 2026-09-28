package kotlinx.serialization.internal

import kotlinx.serialization.KSerializer

private class CacheEntry<T>(serializer: KSerializer<Any>?) {
   public final val serializer: KSerializer<Any>?

   init {
      this.serializer = serializer;
   }
}
