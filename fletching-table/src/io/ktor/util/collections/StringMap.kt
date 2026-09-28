package io.ktor.util.collections

import io.ktor.utils.io.InternalAPI

@InternalAPI
public interface StringMap {
   public abstract operator fun set(key: String, value: String) {
   }

   public abstract operator fun get(key: String): String? {
   }

   public abstract fun remove(key: String): String? {
   }
}
