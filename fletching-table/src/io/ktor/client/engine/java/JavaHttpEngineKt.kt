package io.ktor.client.engine.java

import java.time.Instant
import java.time.temporal.ChronoUnit

internal fun isTimeoutInfinite(timeoutMs: Long, now: Instant = Instant.now()): Boolean {
   if (timeoutMs == java.lang.Long.MAX_VALUE) {
      return true;
   } else {
      var var3: Boolean;
      try {
         now.plus(timeoutMs, ChronoUnit.MILLIS).toEpochMilli();
         var3 = false;
      } catch (var5: ArithmeticException) {
         var3 = true;
      }

      return var3;
   }
}

@JvmSynthetic
fun `isTimeoutInfinite$default`(var0: Long, var2: Instant, var3: Int, var4: Any): Boolean {
   if ((var3 and 2) != 0) {
      var2 = Instant.now();
   }

   return isTimeoutInfinite(var0, var2);
}
