package io.ktor.util.date

import kotlin.jvm.internal.Intrinsics
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
public data class GMTDate(seconds: Int, minutes: Int, hours: Int, dayOfWeek: WeekDay, dayOfMonth: Int, dayOfYear: Int, month: Month, year: Int, timestamp: Long) :
   java.lang.Comparable<GMTDate> {
   public final val seconds: Int
   public final val minutes: Int
   public final val hours: Int
   public final val dayOfWeek: WeekDay
   public final val dayOfMonth: Int
   public final val dayOfYear: Int
   public final val month: Month
   public final val year: Int
   public final val timestamp: Long

   init {
      this.seconds = seconds;
      this.minutes = minutes;
      this.hours = hours;
      this.dayOfWeek = dayOfWeek;
      this.dayOfMonth = dayOfMonth;
      this.dayOfYear = dayOfYear;
      this.month = month;
      this.year = year;
      this.timestamp = timestamp;
   }

   public open operator fun compareTo(other: GMTDate): Int {
      return Intrinsics.compare(this.timestamp, other.timestamp);
   }

   public fun copy(): GMTDate {
      return DateJvmKt.GMTDate$default(null, 1, null);
   }

   public operator fun component1(): Int {
      return this.seconds;
   }

   public operator fun component2(): Int {
      return this.minutes;
   }

   public operator fun component3(): Int {
      return this.hours;
   }

   public operator fun component4(): WeekDay {
      return this.dayOfWeek;
   }

   public operator fun component5(): Int {
      return this.dayOfMonth;
   }

   public operator fun component6(): Int {
      return this.dayOfYear;
   }

   public operator fun component7(): Month {
      return this.month;
   }

   public operator fun component8(): Int {
      return this.year;
   }

   public operator fun component9(): Long {
      return this.timestamp;
   }

   public fun copy(
      seconds: Int = this.seconds,
      minutes: Int = this.minutes,
      hours: Int = this.hours,
      dayOfWeek: WeekDay = this.dayOfWeek,
      dayOfMonth: Int = this.dayOfMonth,
      dayOfYear: Int = this.dayOfYear,
      month: Month = this.month,
      year: Int = this.year,
      timestamp: Long = this.timestamp
   ): GMTDate {
      return new GMTDate(seconds, minutes, hours, dayOfWeek, dayOfMonth, dayOfYear, month, year, timestamp);
   }

   public override fun toString(): String {
      return "GMTDate(seconds=${this.seconds}, minutes=${this.minutes}, hours=${this.hours}, dayOfWeek=${this.dayOfWeek}, dayOfMonth=${this.dayOfMonth}, dayOfYear=${this.dayOfYear}, month=${this.month}, year=${this.year}, timestamp=${this.timestamp})";
   }

   public override fun hashCode(): Int {
      return (
               (
                        (
                                 (
                                          (
                                                   ((Integer.hashCode(this.seconds) * 31 + Integer.hashCode(this.minutes)) * 31 + Integer.hashCode(this.hours))
                                                         * 31
                                                      + this.dayOfWeek.hashCode()
                                                )
                                                * 31
                                             + Integer.hashCode(this.dayOfMonth)
                                       )
                                       * 31
                                    + Integer.hashCode(this.dayOfYear)
                              )
                              * 31
                           + this.month.hashCode()
                     )
                     * 31
                  + Integer.hashCode(this.year)
            )
            * 31
         + java.lang.Long.hashCode(this.timestamp);
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is GMTDate) {
         return false;
      } else {
         val var2: GMTDate = other as GMTDate;
         if (this.seconds != (other as GMTDate).seconds) {
            return false;
         } else if (this.minutes != var2.minutes) {
            return false;
         } else if (this.hours != var2.hours) {
            return false;
         } else if (this.dayOfWeek != var2.dayOfWeek) {
            return false;
         } else if (this.dayOfMonth != var2.dayOfMonth) {
            return false;
         } else if (this.dayOfYear != var2.dayOfYear) {
            return false;
         } else if (this.month != var2.month) {
            return false;
         } else if (this.year != var2.year) {
            return false;
         } else {
            return this.timestamp == var2.timestamp;
         }
      }
   }

   public companion object {
      public final val START: GMTDate

      public fun serializer(): KSerializer<GMTDate> {
         return GMTDate.$serializer.INSTANCE;
      }
   }
}
