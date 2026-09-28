package io.ktor.util

public object Hash {
   public fun combine(vararg objects: Any): Int {
      return ArraysKt.toList(objects).hashCode();
   }
}
