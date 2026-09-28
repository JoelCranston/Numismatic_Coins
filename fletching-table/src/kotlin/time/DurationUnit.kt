package kotlin.time

import java.util.concurrent.TimeUnit
import kotlin.enums.EnumEntries

@SinceKotlin(version = "1.6")
public enum class DurationUnit(timeUnit: TimeUnit) {
   NANOSECONDS(TimeUnit.NANOSECONDS),
   MICROSECONDS(TimeUnit.MICROSECONDS),
   MILLISECONDS(TimeUnit.MILLISECONDS),
   SECONDS(TimeUnit.SECONDS),
   MINUTES(TimeUnit.MINUTES),
   HOURS(TimeUnit.HOURS),
   DAYS(TimeUnit.DAYS)
   internal final val timeUnit: TimeUnit

   init {
      this.timeUnit = timeUnit;
   }

   @JvmStatic
   fun getEntries(): EnumEntries<DurationUnit> {
      return $ENTRIES;
   }
}
