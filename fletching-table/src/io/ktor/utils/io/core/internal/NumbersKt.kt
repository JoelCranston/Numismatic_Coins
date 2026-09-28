package io.ktor.utils.io.core.internal

@PublishedApi
internal inline fun Long.toIntOrFail(name: String): Int {
   if (`$this$toIntOrFail` >= 2147483647L) {
      failLongToIntConversion(`$this$toIntOrFail`, name);
      throw new KotlinNothingValueException();
   } else {
      return (int)`$this$toIntOrFail`;
   }
}

@PublishedApi
internal fun failLongToIntConversion(value: Long, name: String): Nothing {
   throw new IllegalArgumentException("Long value $value of $name doesn't fit into 32-bit integer");
}
