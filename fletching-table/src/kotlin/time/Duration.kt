package kotlin.time

import kotlin.contracts.InvocationKind
import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.math.MathKt

@JvmInline
@SinceKotlin(version = "1.6")
@SourceDebugExtension(["SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1062:1\n37#1:1063\n37#1:1064\n37#1:1065\n37#1:1066\n37#1:1067\n500#1:1068\n517#1:1076\n170#2,6:1069\n1#3:1075\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/Duration\n*L\n38#1:1063\n39#1:1064\n274#1:1065\n294#1:1066\n478#1:1067\n727#1:1068\n818#1:1076\n769#1:1069,6\n*E\n"])
public inline class Duration : java.lang.Comparable<Duration> {
   private final val rawValue: Long

   private final val value: Long
      private final get() {
         return var0 shr 1;
      }


   private final val unitDiscriminator: Int
      private final inline get() {
         return (int)var0 and 1;
      }


   private final val storageUnit: DurationUnit
      private final get() {
         return if (isInNanos-impl(var0)) DurationUnit.NANOSECONDS else DurationUnit.MILLISECONDS;
      }


   public final val absoluteValue: Duration
      public final get() {
         return if (isNegative-impl(var0)) unaryMinus-UwyO8pc(var0) else var0;
      }


   @PublishedApi
   internal final val hoursComponent: Int
      internal final get() {
         return if (isInfinite-impl(var0)) 0 else (int)(getInWholeHours-impl(var0) % 24);
      }


   @PublishedApi
   internal final val minutesComponent: Int
      internal final get() {
         return if (isInfinite-impl(var0)) 0 else (int)(getInWholeMinutes-impl(var0) % 60);
      }


   @PublishedApi
   internal final val secondsComponent: Int
      internal final get() {
         return if (isInfinite-impl(var0)) 0 else (int)(getInWholeSeconds-impl(var0) % 60);
      }


   @PublishedApi
   internal final val nanosecondsComponent: Int
      internal final get() {
         return if (isInfinite-impl(var0))
            0
            else
            (if (isInMillis-impl(var0)) (int)DurationKt.access$millisToNanos(getValue-impl(var0) % (long)1000) else (int)(getValue-impl(var0) % 1000000000));
      }


   public final val inWholeDays: Long
      public final get() {
         return toLong-impl(var0, DurationUnit.DAYS);
      }


   public final val inWholeHours: Long
      public final get() {
         return toLong-impl(var0, DurationUnit.HOURS);
      }


   public final val inWholeMinutes: Long
      public final get() {
         return toLong-impl(var0, DurationUnit.MINUTES);
      }


   public final val inWholeSeconds: Long
      public final get() {
         return toLong-impl(var0, DurationUnit.SECONDS);
      }


   public final val inWholeMilliseconds: Long
      public final get() {
         return if (isInMillis-impl(var0) && isFinite-impl(var0)) getValue-impl(var0) else toLong-impl(var0, DurationUnit.MILLISECONDS);
      }


   public final val inWholeMicroseconds: Long
      public final get() {
         return toLong-impl(var0, DurationUnit.MICROSECONDS);
      }


   public final val inWholeNanoseconds: Long
      public final get() {
         val value: Long = getValue-impl(var0);
         return if (isInNanos-impl(var0))
            value
            else
            (
               if (value > 9223372036854L)
                  java.lang.Long.MAX_VALUE
                  else
                  (if (value < -9223372036854L) java.lang.Long.MIN_VALUE else DurationKt.access$millisToNanos(value))
            );
      }


   @JvmStatic
   private fun isInNanos(): Boolean {
      return ((int)var0 and 1) == 0;
   }

   @JvmStatic
   private fun isInMillis(): Boolean {
      return ((int)var0 and 1) == 1;
   }

   @JvmStatic
   public operator fun unaryMinus(): Duration {
      return DurationKt.access$durationOf(-getValue-impl(var0), (int)var0 and 1);
   }

   @JvmStatic
   public operator fun plus(other: Duration): Duration {
      if (isInfinite-impl(var0)) {
         if (!isFinite-impl(var2) && (var0 xor var2) < 0L) {
            throw new IllegalArgumentException("Summing infinite durations of different signs yields an undefined result.");
         } else {
            return var0;
         }
      } else if (isInfinite-impl(var2)) {
         return var2;
      } else {
         val var8: Long;
         if (((int)var0 and 1) == ((int)var2 and 1)) {
            val result: Long = getValue-impl(var0) + getValue-impl(var2);
            var8 = if (isInNanos-impl(var0)) DurationKt.access$durationOfNanosNormalized(result) else DurationKt.access$durationOfMillisNormalized(result);
         } else {
            var8 = if (isInMillis-impl(var0))
               addValuesMixedRanges-UwyO8pc(var0, getValue-impl(var0), getValue-impl(var2))
               else
               addValuesMixedRanges-UwyO8pc(var0, getValue-impl(var2), getValue-impl(var0));
         }

         return var8;
      }
   }

   @JvmStatic
   private fun addValuesMixedRanges(thisMillis: Long, otherNanos: Long): Duration {
      val otherMillis: Long = DurationKt.access$nanosToMillis(otherNanos);
      return if (-4611686018426L <= thisMillis + otherMillis && thisMillis + otherMillis < 4611686018427L)
         DurationKt.access$durationOfNanos(
            DurationKt.access$millisToNanos(thisMillis + otherMillis) + (otherNanos - DurationKt.access$millisToNanos(otherMillis))
         )
         else
         DurationKt.access$durationOfMillis(RangesKt.coerceIn(thisMillis + otherMillis, -4611686018427387903L, 4611686018427387903L));
   }

   @JvmStatic
   public operator fun minus(other: Duration): Duration {
      return plus-LRDsOJo(var0, unaryMinus-UwyO8pc(var2));
   }

   @JvmStatic
   public operator fun times(scale: Int): Duration {
      if (isInfinite-impl(var0)) {
         if (scale == 0) {
            throw new IllegalArgumentException("Multiplying infinite duration by zero yields an undefined result.");
         } else {
            return if (scale > 0) var0 else unaryMinus-UwyO8pc(var0);
         }
      } else if (scale == 0) {
         return ZERO;
      } else {
         val value: Long = getValue-impl(var0);
         val result: Long = value * scale;
         val var10000: Long;
         if (isInNanos-impl(var0)) {
            if (-2147483647L <= value && value < 2147483648L) {
               var10000 = DurationKt.access$durationOfNanos(result);
            } else if (result / scale == value) {
               var10000 = DurationKt.access$durationOfNanosNormalized(result);
            } else {
               val millis: Long = DurationKt.access$nanosToMillis(value);
               val totalMillis: Long = millis * scale + DurationKt.access$nanosToMillis((value - DurationKt.access$millisToNanos(millis)) * (long)scale);
               var10000 = if (millis * scale / scale == millis && (totalMillis xor millis * scale) >= 0L)
                  DurationKt.access$durationOfMillis(RangesKt.coerceIn(totalMillis, new LongRange(-4611686018427387903L, 4611686018427387903L)))
                  else
                  (if (MathKt.getSign(value) * MathKt.getSign(scale) > 0) INFINITE else NEG_INFINITE);
            }
         } else {
            var10000 = if (result / scale == value)
               DurationKt.access$durationOfMillis(RangesKt.coerceIn(result, new LongRange(-4611686018427387903L, 4611686018427387903L)))
               else
               (if (MathKt.getSign(value) * MathKt.getSign(scale) > 0) INFINITE else NEG_INFINITE);
         }

         return var10000;
      }
   }

   @JvmStatic
   public operator fun times(scale: Double): Duration {
      val intScale: Int = MathKt.roundToInt(scale);
      if (intScale == scale) {
         return times-UwyO8pc(var0, intScale);
      } else {
         val unit: DurationUnit = getStorageUnit-impl(var0);
         return DurationKt.toDuration(toDouble-impl(var0, unit) * scale, unit);
      }
   }

   @JvmStatic
   public operator fun div(scale: Int): Duration {
      if (scale == 0) {
         val var10000: Long;
         if (isPositive-impl(var0)) {
            var10000 = INFINITE;
         } else {
            if (!isNegative-impl(var0)) {
               throw new IllegalArgumentException("Dividing zero duration by zero yields an undefined result.");
            }

            var10000 = NEG_INFINITE;
         }

         return var10000;
      } else if (isInNanos-impl(var0)) {
         return DurationKt.access$durationOfNanos(getValue-impl(var0) / (long)scale);
      } else {
         label32:
         if (isInfinite-impl(var0)) {
            return times-UwyO8pc(var0, MathKt.getSign(scale));
         } else {
            val result: Long = getValue-impl(var0) / scale;
            return if (-4611686018426L <= result && result < 4611686018427L)
               DurationKt.access$durationOfNanos(
                  DurationKt.access$millisToNanos(result) + DurationKt.access$millisToNanos(getValue-impl(var0) - result * (long)scale) / (long)scale
               )
               else
               DurationKt.access$durationOfMillis(result);
         }
      }
   }

   @JvmStatic
   public operator fun div(scale: Double): Duration {
      val intScale: Int = MathKt.roundToInt(scale);
      if (intScale == scale && intScale != 0) {
         return div-UwyO8pc(var0, intScale);
      } else {
         val unit: DurationUnit = getStorageUnit-impl(var0);
         return DurationKt.toDuration(toDouble-impl(var0, unit) / scale, unit);
      }
   }

   @JvmStatic
   public operator fun div(other: Duration): Double {
      val coarserUnit: DurationUnit = ComparisonsKt.maxOf(getStorageUnit-impl(var0), getStorageUnit-impl(var2));
      return toDouble-impl(var0, coarserUnit) / toDouble-impl(var2, coarserUnit);
   }

   @JvmStatic
   internal fun truncateTo(unit: DurationUnit): Duration {
      val storageUnit: DurationUnit = getStorageUnit-impl(var0);
      return if (unit.compareTo(storageUnit) > 0 && !isInfinite-impl(var0))
         DurationKt.toDuration(getValue-impl(var0) - getValue-impl(var0) % DurationUnitKt.convertDurationUnit(1L, unit, storageUnit), storageUnit)
         else
         var0;
   }

   @JvmStatic
   public fun isNegative(): Boolean {
      return var0 < 0L;
   }

   @JvmStatic
   public fun isPositive(): Boolean {
      return var0 > 0L;
   }

   @JvmStatic
   public fun isInfinite(): Boolean {
      return var0 == INFINITE || var0 == NEG_INFINITE;
   }

   @JvmStatic
   public fun isFinite(): Boolean {
      return !isInfinite-impl(var0);
   }

   @JvmStatic
   public open operator fun compareTo(other: Duration): Int {
      if ((var0 xor var2) >= 0L && ((int)(var0 xor var2) and 1) != 0) {
         return if (isNegative-impl(var0)) -(((int)var0 and 1) - ((int)var2 and 1)) else ((int)var0 and 1) - ((int)var2 and 1);
      } else {
         return Intrinsics.compare(var0, var2);
      }
   }

   fun `compareTo-LRDsOJo`(other: Long): Int {
      return compareTo-LRDsOJo(this.rawValue, other);
   }

   @JvmStatic
   public inline fun <T> toComponents(action: (Long, Int, Int, Int, Int) -> T): T {
      contract {
         callsInPlace(action, InvocationKind.EXACTLY_ONCE)
      }

      return (T)action.invoke(
         getInWholeDays-impl(var0),
         getHoursComponent-impl(var0),
         getMinutesComponent-impl(var0),
         getSecondsComponent-impl(var0),
         getNanosecondsComponent-impl(var0)
      );
   }

   @JvmStatic
   public inline fun <T> toComponents(action: (Long, Int, Int, Int) -> T): T {
      contract {
         callsInPlace(action, InvocationKind.EXACTLY_ONCE)
      }

      return (T)action.invoke(getInWholeHours-impl(var0), getMinutesComponent-impl(var0), getSecondsComponent-impl(var0), getNanosecondsComponent-impl(var0));
   }

   @JvmStatic
   public inline fun <T> toComponents(action: (Long, Int, Int) -> T): T {
      contract {
         callsInPlace(action, InvocationKind.EXACTLY_ONCE)
      }

      return (T)action.invoke(getInWholeMinutes-impl(var0), getSecondsComponent-impl(var0), getNanosecondsComponent-impl(var0));
   }

   @JvmStatic
   public inline fun <T> toComponents(action: (Long, Int) -> T): T {
      contract {
         callsInPlace(action, InvocationKind.EXACTLY_ONCE)
      }

      return (T)action.invoke(getInWholeSeconds-impl(var0), getNanosecondsComponent-impl(var0));
   }

   @JvmStatic
   public fun toDouble(unit: DurationUnit): Double {
      return if (var0 == INFINITE)
         java.lang.Double.POSITIVE_INFINITY
         else
         (
            if (var0 == NEG_INFINITE)
               java.lang.Double.NEGATIVE_INFINITY
               else
               DurationUnitKt.convertDurationUnit((double)getValue-impl(var0), getStorageUnit-impl(var0), unit)
         );
   }

   @JvmStatic
   public fun toLong(unit: DurationUnit): Long {
      return if (var0 == INFINITE)
         java.lang.Long.MAX_VALUE
         else
         (if (var0 == NEG_INFINITE) java.lang.Long.MIN_VALUE else DurationUnitKt.convertDurationUnit(getValue-impl(var0), getStorageUnit-impl(var0), unit));
   }

   @JvmStatic
   public fun toInt(unit: DurationUnit): Int {
      return (int)RangesKt.coerceIn(toLong-impl(var0, unit), -2147483648L, 2147483647L);
   }

   @JvmStatic
   public open fun toString(): String {
      val var10000: java.lang.String;
      if (var0 == 0L) {
         var10000 = "0s";
      } else if (var0 == INFINITE) {
         var10000 = "Infinity";
      } else if (var0 == NEG_INFINITE) {
         var10000 = "-Infinity";
      } else {
         val isNegative: Boolean = isNegative-impl(var0);
         val var5: StringBuilder = new StringBuilder();
         if (isNegative) {
            var5.append('-');
         }

         val var8: Long = getAbsoluteValue-UwyO8pc(var0);
         val var23: Long = getInWholeDays-impl(var8);
         val var10001: Int = getHoursComponent-impl(var8);
         val var10002: Int = getMinutesComponent-impl(var8);
         val nanoseconds: Int = getNanosecondsComponent-impl(var8);
         val seconds: Int = getSecondsComponent-impl(var8);
         val hasDays: Boolean = var23 != 0L;
         val hasHours: Boolean = var10001 != 0;
         val hasMinutes: Boolean = var10002 != 0;
         val hasSeconds: Boolean = seconds != 0 || nanoseconds != 0;
         if (hasDays) {
            var5.append(var23).append('d');
            0++;
         }

         if (hasHours || hasDays && (hasMinutes || hasSeconds)) {
            if (0++ > 0) {
               var5.append(' ');
            }

            var5.append(var10001).append('h');
         }

         if (hasMinutes || hasSeconds && (hasHours || hasDays)) {
            if (0++ > 0) {
               var5.append(' ');
            }

            var5.append(var10002).append('m');
         }

         if (hasSeconds) {
            if (0++ > 0) {
               var5.append(' ');
            }

            if (seconds != 0 || hasDays || hasHours || hasMinutes) {
               appendFractional-impl(var0, var5, seconds, nanoseconds, 9, "s", false);
            } else if (nanoseconds >= 1000000) {
               appendFractional-impl(var0, var5, nanoseconds / 1000000, nanoseconds % 1000000, 6, "ms", false);
            } else if (nanoseconds >= 1000) {
               appendFractional-impl(var0, var5, nanoseconds / 1000, nanoseconds % 1000, 3, "us", false);
            } else {
               var5.append(nanoseconds).append("ns");
            }
         }

         if (isNegative && 0 > 1) {
            var5.insert(1, '(').append(')');
         }

         var10000 = var5.toString();
      }

      return var10000;
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.rawValue);
   }

   @JvmStatic
   private fun StringBuilder.appendFractional(whole: Int, fractional: Int, fractionalSize: Int, unit: String, isoZeroes: Boolean) {
      `$this$appendFractional`.append(whole);
      if (fractional != 0) {
         var fracString: java.lang.String;
         var var10000: Int;
         label33: {
            `$this$appendFractional`.append('.');
            fracString = StringsKt.padStart(java.lang.String.valueOf(fractional), fractionalSize, '0');
            val `$this$indexOfLast$iv`: java.lang.CharSequence = fracString;
            var var12: Int = fracString.length() + -1;
            if (0 <= var12) {
               do {
                  val `index$iv`: Int = var12--;
                  if (`$this$indexOfLast$iv`.charAt(`index$iv`) != '0') {
                     var10000 = `index$iv`;
                     break label33;
                  }
               } while (0 <= var12);
            }

            var10000 = -1;
         }

         val nonZeroDigits: Int = var10000 + 1;
         if (!isoZeroes && var10000 + 1 < 3) {
            ;
         }
      }

      `$this$appendFractional`.append(unit);
   }

   @JvmStatic
   public fun toString(unit: DurationUnit, decimals: Int = ...): String {
      if (decimals < 0) {
         throw new IllegalArgumentException(("decimals must be not negative, but was $decimals").toString());
      } else {
         val number: Double = toDouble-impl(var0, unit);
         return if (java.lang.Double.isInfinite(number))
            java.lang.String.valueOf(number)
            else
            "${DurationJvmKt.formatToExactDecimals(number, RangesKt.coerceAtMost(decimals, 12))}${DurationUnitKt.shortName(unit)}";
      }
   }

   @JvmStatic
   public fun toIsoString(): String {
      val var2: StringBuilder = new StringBuilder();
      if (isNegative-impl(var0)) {
         var2.append('-');
      }

      var2.append("PT");
      val var5: Long = getAbsoluteValue-UwyO8pc(var0);
      val var10000: Long = getInWholeHours-impl(var5);
      val var10001: Int = getMinutesComponent-impl(var5);
      val nanoseconds: Int = getNanosecondsComponent-impl(var5);
      val seconds: Int = getSecondsComponent-impl(var5);
      var hours: Long = var10000;
      if (isInfinite-impl(var0)) {
         hours = 9999999999999L;
      }

      val hasHours: Boolean = hours != 0L;
      val hasSeconds: Boolean = seconds != 0 || nanoseconds != 0;
      val hasMinutes: Boolean = var10001 != 0 || (seconds != 0 || nanoseconds != 0) && hasHours;
      if (hasHours) {
         var2.append(hours).append('H');
      }

      if (hasMinutes) {
         var2.append(var10001).append('M');
      }

      if (hasSeconds || !hasHours && !hasMinutes) {
         appendFractional-impl(var0, var2, seconds, nanoseconds, 9, "S", true);
      }

      return var2.toString();
   }

   @JvmStatic
   fun `hashCode-impl`(var0: Long): Int {
      return java.lang.Long.hashCode(var0);
   }

   public override fun hashCode(): Int {
      return hashCode-impl(this.rawValue);
   }

   @JvmStatic
   fun `equals-impl`(var0: Long, other: Any): Boolean {
      if (other !is Duration) {
         return false;
      } else {
         return var0 == (other as Duration).unbox-impl();
      }
   }

   public override operator fun equals(other: Any?): Boolean {
      return equals-impl(this.rawValue, other);
   }

   @JvmStatic
   fun `constructor-impl`(rawValue: Long): Long {
      if (DurationJvmKt.getDurationAssertionsEnabled()) {
         if (isInNanos-impl(rawValue)) {
            val var4: Long = getValue-impl(rawValue);
            if (-4611686018426999999L > var4 || var4 >= 4611686018427000000L) {
               throw new AssertionError("${getValue-impl(rawValue)} ns is out of nanoseconds range");
            }
         } else {
            var var6: Long = getValue-impl(rawValue);
            if (-4611686018427387903L > var6 || var6 >= 4611686018427387904L) {
               throw new AssertionError("${getValue-impl(rawValue)} ms is out of milliseconds range");
            }

            var6 = getValue-impl(rawValue);
            if (-4611686018426L <= var6 && var6 < 4611686018427L) {
               throw new AssertionError("${getValue-impl(rawValue)} ms is denormalized");
            }
         }
      }

      return rawValue;
   }

   @JvmStatic
   fun `equals-impl0`(p1: Long, p2: Long): Boolean {
      return p1 == p2;
   }

   public companion object {
      public final val ZERO: Duration
      public final val INFINITE: Duration
      internal final val NEG_INFINITE: Duration

      @InlineOnly
      public final val nanoseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$nanoseconds`, DurationUnit.NANOSECONDS);
         }


      @InlineOnly
      public final val nanoseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$nanoseconds`, DurationUnit.NANOSECONDS);
         }


      @InlineOnly
      public final val nanoseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$nanoseconds`, DurationUnit.NANOSECONDS);
         }


      @InlineOnly
      public final val microseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$microseconds`, DurationUnit.MICROSECONDS);
         }


      @InlineOnly
      public final val microseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$microseconds`, DurationUnit.MICROSECONDS);
         }


      @InlineOnly
      public final val microseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$microseconds`, DurationUnit.MICROSECONDS);
         }


      @InlineOnly
      public final val milliseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$milliseconds`, DurationUnit.MILLISECONDS);
         }


      @InlineOnly
      public final val milliseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$milliseconds`, DurationUnit.MILLISECONDS);
         }


      @InlineOnly
      public final val milliseconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$milliseconds`, DurationUnit.MILLISECONDS);
         }


      @InlineOnly
      public final val seconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$seconds`, DurationUnit.SECONDS);
         }


      @InlineOnly
      public final val seconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$seconds`, DurationUnit.SECONDS);
         }


      @InlineOnly
      public final val seconds: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$seconds`, DurationUnit.SECONDS);
         }


      @InlineOnly
      public final val minutes: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$minutes`, DurationUnit.MINUTES);
         }


      @InlineOnly
      public final val minutes: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$minutes`, DurationUnit.MINUTES);
         }


      @InlineOnly
      public final val minutes: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$minutes`, DurationUnit.MINUTES);
         }


      @InlineOnly
      public final val hours: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$hours`, DurationUnit.HOURS);
         }


      @InlineOnly
      public final val hours: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$hours`, DurationUnit.HOURS);
         }


      @InlineOnly
      public final val hours: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$hours`, DurationUnit.HOURS);
         }


      @InlineOnly
      public final val days: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$days`, DurationUnit.DAYS);
         }


      @InlineOnly
      public final val days: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$days`, DurationUnit.DAYS);
         }


      @InlineOnly
      public final val days: Duration
         public final inline get() {
            return DurationKt.toDuration(`$this$days`, DurationUnit.DAYS);
         }


      @ExperimentalTime
      public fun convert(value: Double, sourceUnit: DurationUnit, targetUnit: DurationUnit): Double {
         return DurationUnitKt.convertDurationUnit(value, sourceUnit, targetUnit);
      }

      public fun parse(value: String): Duration {
         try {
            return DurationKt.access$parseDuration(value, false);
         } catch (var5: IllegalArgumentException) {
            throw new IllegalArgumentException("Invalid duration string format: '$value'.", var5);
         }
      }

      public fun parseIsoString(value: String): Duration {
         try {
            return DurationKt.access$parseDuration(value, true);
         } catch (var5: IllegalArgumentException) {
            throw new IllegalArgumentException("Invalid ISO duration string format: '$value'.", var5);
         }
      }

      public fun parseOrNull(value: String): Duration? {
         var var2: Duration;
         try {
            var2 = Duration.box-impl(DurationKt.access$parseDuration(value, false));
         } catch (var4: IllegalArgumentException) {
            var2 = null;
         }

         return var2;
      }

      public fun parseIsoStringOrNull(value: String): Duration? {
         var var2: Duration;
         try {
            var2 = Duration.box-impl(DurationKt.access$parseDuration(value, true));
         } catch (var4: IllegalArgumentException) {
            var2 = null;
         }

         return var2;
      }
   }
}
