package kotlin.time

@ExperimentalTime
private class UnboundLocalDateTime(year: Int, month: Int, day: Int, hour: Int, minute: Int, second: Int, nanosecond: Int) {
   public final val year: Int
   public final val month: Int
   public final val day: Int
   public final val hour: Int
   public final val minute: Int
   public final val second: Int
   public final val nanosecond: Int

   init {
      this.year = year;
      this.month = month;
      this.day = day;
      this.hour = hour;
      this.minute = minute;
      this.second = second;
      this.nanosecond = nanosecond;
   }

   public inline fun <T> toInstant(offsetSeconds: Int, buildInstant: (Long, Int) -> T): T {
      val `$this$toInstant_u24lambda_u240`: UnboundLocalDateTime = this;
      val y: Long = this.getYear();
      var total: Long = 365 * y;
      if (y >= 0L) {
         total = total + (y + 3) / 4 - (y + 99) / 100 + (y + 399) / 400;
      } else {
         total = total - (y / -4 - y / -100 + y / -400);
      }

      total = total + (367 * `$this$toInstant_u24lambda_u240`.getMonth() - 362) / 12 + (`$this$toInstant_u24lambda_u240`.getDay() - 1);
      if (`$this$toInstant_u24lambda_u240`.getMonth() > 2) {
         total += -1L;
         if (!InstantKt.isLeapYear(`$this$toInstant_u24lambda_u240`.getYear())) {
            total += -1L;
         }
      }

      return (T)buildInstant.invoke(
         (total - (long)719528) * (long)86400
            + (long)(
               `$this$toInstant_u24lambda_u240`.getHour() * 3600
                  + `$this$toInstant_u24lambda_u240`.getMinute() * 60
                  + `$this$toInstant_u24lambda_u240`.getSecond()
            )
            - (long)offsetSeconds,
         this.getNanosecond()
      );
   }

   public override fun toString(): String {
      return "UnboundLocalDateTime(${this.year}-${this.month}-${this.day} ${this.hour}:${this.minute}:${this.second}.${this.nanosecond})";
   }

   public companion object {
      public fun fromInstant(instant: Instant): UnboundLocalDateTime {
         val localSecond: Long = instant.getEpochSeconds();
         var hours: Long = localSecond / 86400L;
         if ((localSecond xor 86400L) < 0L && localSecond / 86400L * 86400L != localSecond) {
            hours += -1L;
         }

         val secsOfDay: Int = (int)(
            localSecond % 86400L + (86400L and ((localSecond % 86400L xor 86400L) and (localSecond % 86400L or -(localSecond % 86400L))) shr 63)
         );
         val var29: UnboundLocalDateTime.Companion = this;
         var var32: Long = hours + 719528 - 60;
         var adjust: Long = 0L;
         if (var32 < 0L) {
            adjust = ((var32 + 1L) / 146097 - 1L) * 400;
            var32 += -((var32 + 1L) / 146097 - 1L) * 146097;
         }

         var var34: Long = (400 * var32 + 591) / 146097;
         var doyEst: Long = var32
            - (
               365 * ((400 * var32 + 591) / 146097)
                  + (400 * var32 + 591) / 146097 / 4
                  - (400 * var32 + 591) / 146097 / 100
                  + (400 * var32 + 591) / 146097 / 400
            );
         if (var32
               - (
                  365 * ((400 * var32 + 591) / 146097)
                     + (400 * var32 + 591) / 146097 / 4
                     - (400 * var32 + 591) / 146097 / 100
                     + (400 * var32 + 591) / 146097 / 400
               )
            < 0L) {
            var34 += -1L;
            doyEst = var32 - (365 * var34 + var34 / 4 - var34 / 100 + var34 / 400);
         }

         return new UnboundLocalDateTime(
            (int)(var34 + adjust + ((int)doyEst * 5 + 2) / 153 / 10),
            (((int)doyEst * 5 + 2) / 153 + 2) % 12 + 1,
            (int)doyEst - (((int)doyEst * 5 + 2) / 153 * 306 + 5) / 10 + 1,
            secsOfDay / 3600,
            (secsOfDay - secsOfDay / 3600 * 3600) / 60,
            secsOfDay - secsOfDay / 3600 * 3600 - (secsOfDay - secsOfDay / 3600 * 3600) / 60 * 60,
            instant.getNanosecondsOfSecond()
         );
      }
   }
}
