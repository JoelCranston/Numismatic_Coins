package it.krzeminski.snakeyaml.engine.kmp.internal

import java.math.BigDecimal
import java.math.BigInteger
import java.math.MathContext

internal fun createBigInteger(value: String, radix: Int = 10): Number {
   return new BigInteger(value, radix);
}

@JvmSynthetic
fun `createBigInteger$default`(var0: java.lang.String, var1: Int, var2: Int, var3: Any): java.lang.Number {
   if ((var2 and 2) != 0) {
      var1 = 10;
   }

   return createBigInteger(var0, var1);
}

internal fun createBigDecimal(value: String): Number {
   return new BigDecimal(value, MathContext.UNLIMITED);
}

internal fun isInteger(value: Number): Boolean {
   return value is Int || value is java.lang.Long || value is java.lang.Short || value is java.lang.Byte;
}
