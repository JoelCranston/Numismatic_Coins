package io.ktor.util

public fun Attributes(concurrent: Boolean = false): Attributes {
   return if (concurrent) new ConcurrentSafeAttributes() else new HashMapAttributes();
}

@JvmSynthetic
fun `Attributes$default`(var0: Boolean, var1: Int, var2: Any): Attributes {
   if ((var1 and 1) != 0) {
      var0 = false;
   }

   return Attributes(var0);
}
