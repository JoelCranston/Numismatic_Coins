@file:SourceDebugExtension(["SMAP\nDurationJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DurationJvm.kt\nkotlin/time/DurationJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,28:1\n1#2:29\n*E\n"])

package kotlin.time

import java.math.RoundingMode
import java.text.DecimalFormat
import kotlin.jvm.internal.SourceDebugExtension

internal final val durationAssertionsEnabled: Boolean = Duration.class.desiredAssertionStatus()
private final val precisionFormats: Array<ThreadLocal<DecimalFormat>>
private ThreadLocal<DecimalFormat>[] precisionFormats;

private fun createFormatForDecimals(decimals: Int): DecimalFormat {
   val var1: DecimalFormat = new DecimalFormat("0");
   if (decimals > 0) {
      var1.setMinimumFractionDigits(decimals);
   }

   var1.setRoundingMode(RoundingMode.HALF_UP);
   return var1;
}

internal fun formatToExactDecimals(value: Double, decimals: Int): String {
   val var8: DecimalFormat;
   if (decimals < precisionFormats.length) {
      val var4: ThreadLocal = precisionFormats[decimals];
      var var10000: Any = precisionFormats[decimals].get();
      if (var10000 == null) {
         val var7: DecimalFormat = createFormatForDecimals(decimals);
         var4.set(var7);
         var10000 = var7;
      }

      var8 = var10000 as DecimalFormat;
   } else {
      var8 = createFormatForDecimals(decimals);
   }

   val var9: java.lang.String = var8.format(value);
   return var9;
}
