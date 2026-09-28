package io.ktor.util.date

internal class GMTDateBuilder {
   public final var seconds: Int?
   public final var minutes: Int?
   public final var hours: Int?
   public final var dayOfMonth: Int?
   public final lateinit var month: Month
   public final var year: Int?

   public fun build(): GMTDate {
      val var10000: Int = this.seconds;
      val var1: Int = var10000;
      val var10001: Int = this.minutes;
      val var2: Int = var10001;
      val var10002: Int = this.hours;
      val var3: Int = var10002;
      val var10003: Int = this.dayOfMonth;
      val var4: Int = var10003;
      val var10004: Month = this.getMonth();
      val var10005: Int = this.year;
      return DateJvmKt.GMTDate(var1, var2, var3, var4, var10004, var10005);
   }
}
