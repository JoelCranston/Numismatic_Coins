package it.krzeminski.snakeyaml.engine.kmp.internal

internal fun String.toBigInteger(radix: Int = 10): Number {
   return Math_jvmKt.createBigInteger(`$this$toBigInteger`, radix);
}

@JvmSynthetic
fun `toBigInteger$default`(var0: java.lang.String, var1: Int, var2: Int, var3: Any): java.lang.Number {
   if ((var2 and 1) != 0) {
      var1 = 10;
   }

   return toBigInteger(var0, var1);
}
