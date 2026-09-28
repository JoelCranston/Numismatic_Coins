@file:SourceDebugExtension(["SMAP\nDateJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DateJvm.kt\nio/ktor/util/date/DateJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,86:1\n1#2:87\n*E\n"])

package io.ktor.util.date

import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.jvm.internal.SourceDebugExtension

private final val GMT_TIMEZONE: TimeZone = TimeZone.getTimeZone("GMT")

public fun GMTDate(timestamp: Long? = null): GMTDate {
   val var10000: Calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
   return toDate(var10000, timestamp);
}

@JvmSynthetic
fun `GMTDate$default`(var0: java.lang.Long, var1: Int, var2: Any): GMTDate {
   if ((var1 and 1) != 0) {
      var0 = null;
   }

   return GMTDate(var0);
}

public fun GMTDate(seconds: Int, minutes: Int, hours: Int, dayOfMonth: Int, month: Month, year: Int): GMTDate {
   val var10000: Calendar = Calendar.getInstance(GMT_TIMEZONE, Locale.ROOT);
   var10000.set(1, year);
   var10000.set(2, month.ordinal());
   var10000.set(5, dayOfMonth);
   var10000.set(11, hours);
   var10000.set(12, minutes);
   var10000.set(13, seconds);
   var10000.set(14, 0);
   return toDate(var10000, null);
}

public fun Calendar.toDate(timestamp: Long?): GMTDate {
   if (timestamp != null) {
      `$this$toDate`.setTimeInMillis(timestamp.longValue());
   }

   return new GMTDate(
      `$this$toDate`.get(13),
      `$this$toDate`.get(12),
      `$this$toDate`.get(11),
      WeekDay.Companion.from((`$this$toDate`.get(7) + 7 - 2) % 7),
      `$this$toDate`.get(5),
      `$this$toDate`.get(6),
      Month.Companion.from(`$this$toDate`.get(2)),
      `$this$toDate`.get(1),
      `$this$toDate`.getTimeInMillis() + (`$this$toDate`.get(15) + `$this$toDate`.get(16))
   );
}

public fun GMTDate.toJvmDate(): Date {
   return new Date(`$this$toJvmDate`.getTimestamp());
}

public fun getTimeMillis(): Long {
   return System.currentTimeMillis();
}
