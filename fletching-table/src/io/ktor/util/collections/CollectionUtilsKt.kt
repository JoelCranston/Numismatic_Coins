package io.ktor.util.collections

import java.util.ArrayList
import java.util.Arrays
import java.util.LinkedHashMap

@Deprecated(message = "Will be dropped with new memory model enabled by default", replaceWith = @ReplaceWith(expression = "mutableListOf(values)", imports = []), level = DeprecationLevel.ERROR)
public fun <T> sharedListOf(vararg values: Any): MutableList<Any> {
   return (java.util.List<T>)CollectionsKt.mutableListOf(Arrays.copyOf(values, values.length));
}

@Deprecated(message = "Will be dropped with new memory model enabled by default", replaceWith = @ReplaceWith(expression = "mutableMapOf()", imports = []), level = DeprecationLevel.ERROR)
public fun <K : Any, V : Any> sharedMap(initialCapacity: Int = 8): MutableMap<Any, Any> {
   return new LinkedHashMap(initialCapacity);
}

/** @deprecated */
@JvmSynthetic
fun `sharedMap$default`(var0: Int, var1: Int, var2: Any): java.util.Map {
   if ((var1 and 1) != 0) {
      var0 = 8;
   }

   return sharedMap(var0);
}

@Deprecated(message = "Will be dropped with new memory model enabled by default", replaceWith = @ReplaceWith(expression = "mutableListOf<V>()", imports = []), level = DeprecationLevel.ERROR)
public fun <V> sharedList(): MutableList<Any> {
   return new ArrayList();
}
