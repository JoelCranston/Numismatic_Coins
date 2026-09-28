@file:SourceDebugExtension(["SMAP\nDuration.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1062:1\n1015#1,6:1064\n1018#1,3:1070\n1015#1,6:1073\n1015#1,6:1079\n1018#1,3:1085\n1#2:1063\n*S KotlinDebug\n*F\n+ 1 Duration.kt\nkotlin/time/DurationKt\n*L\n930#1:1064,6\n964#1:1070,3\n967#1:1073,6\n970#1:1079,6\n1015#1:1085,3\n*E\n"])

package kotlin.time

import kotlin.internal.InlineOnly
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.math.MathKt

internal const val NANOS_IN_MILLIS: Int = 1000000
internal const val MAX_NANOS: Long = 4611686018426999999L
internal const val MAX_MILLIS: Long = 4611686018427387903L
private const val MAX_NANOS_IN_MILLIS: Long = 4611686018426L

@SinceKotlin(version = "1.6")
public fun Int.toDuration(unit: DurationUnit): Duration {
   return if (unit.compareTo(DurationUnit.SECONDS) <= 0)
      durationOfNanos(DurationUnitKt.convertDurationUnitOverflow((long)`$this$toDuration`, unit, DurationUnit.NANOSECONDS))
      else
      toDuration((long)`$this$toDuration`, unit);
}

@SinceKotlin(version = "1.6")
public fun Long.toDuration(unit: DurationUnit): Duration {
   val maxNsInUnit: Long = DurationUnitKt.convertDurationUnitOverflow(4611686018426999999L, DurationUnit.NANOSECONDS, unit);
   return if (-maxNsInUnit <= `$this$toDuration` && `$this$toDuration` <= maxNsInUnit)
      durationOfNanos(DurationUnitKt.convertDurationUnitOverflow(`$this$toDuration`, unit, DurationUnit.NANOSECONDS))
      else
      durationOfMillis(
         RangesKt.coerceIn(DurationUnitKt.convertDurationUnit(`$this$toDuration`, unit, DurationUnit.MILLISECONDS), -4611686018427387903L, 4611686018427387903L)
      );
}

@SinceKotlin(version = "1.6")
public fun Double.toDuration(unit: DurationUnit): Duration {
   val valueInNs: Double = DurationUnitKt.convertDurationUnit(`$this$toDuration`, unit, DurationUnit.NANOSECONDS);
   if (java.lang.Double.isNaN(valueInNs)) {
      throw new IllegalArgumentException("Duration value cannot be NaN.".toString());
   } else {
      val nanos: Long = MathKt.roundToLong(valueInNs);
      return if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L)
         durationOfNanos(nanos)
         else
         durationOfMillisNormalized(MathKt.roundToLong(DurationUnitKt.convertDurationUnit(`$this$toDuration`, unit, DurationUnit.MILLISECONDS)));
   }
}

@SinceKotlin(version = "1.6")
@InlineOnly
public inline operator fun Int.times(duration: Duration): Duration {
   return Duration.times-UwyO8pc(var1, `$this$times_u2dmvk6XK0`);
}

@SinceKotlin(version = "1.6")
@InlineOnly
public inline operator fun Double.times(duration: Duration): Duration {
   return Duration.times-UwyO8pc(var2, `$this$times_u2dkIfJnKk`);
}

private fun parseDuration(value: String, strictIso: Boolean): Duration {
   val length: Int = value.length();
   if (length == 0) {
      throw new IllegalArgumentException("The string is empty");
   } else {
      var var25: Int = 0;
      var result: Long = Duration.Companion.getZERO-UwyO8pc();
      switch (value.charAt(0)) {
         case '+':
         case '-':
            0++;
         case ',':
         default:
            val hasSign: Boolean = 0 > 0;
            val isNegative: Boolean = 0 > 0 && StringsKt.startsWith$default(value, '-', false, 2, null);
            if (length <= 0) {
               throw new IllegalArgumentException("No components");
            } else {
               if (value.charAt(0) != 'P') {
                  if (strictIso) {
                     throw new IllegalArgumentException();
                  }

                  if (StringsKt.regionMatches(value, 0, "Infinity", 0, Math.max(length - 0, "Infinity".length()), true)) {
                     result = Duration.Companion.getINFINITE-UwyO8pc();
                  } else {
                     var var30: DurationUnit = null;
                     var var32: Boolean = false;
                     var var33: Boolean = !hasSign;
                     if (hasSign && value.charAt(0) == '(' && StringsKt.last(value) == ')') {
                        var33 = true;
                        0++;
                        if (0 == --length) {
                           throw new IllegalArgumentException("No components");
                        }
                     }

                     while (index < length) {
                        if (var32 && var33) {
                           val var34: java.lang.String = value;

                           var var41: Int;
                           for (i$iv = index; i$iv < var34.length(); i$iv++) {
                              if (var34.charAt(var41) != ' ') {
                                 break;
                              }
                           }

                           var25 = var41;
                        }

                        var32 = true;
                        val var49: java.lang.String = value;

                        var var53: Int;
                        for (i$iv$iv = index; i$iv$iv < var49.length(); i$iv$iv++) {
                           val var55: Char = var49.charAt(var53);
                           if (('0' > var55 || var55 >= ':') && var55 != '.') {
                              break;
                           }
                        }

                        var var60: java.lang.String = value.substring(var25, var53);
                        if (var60.length() == 0) {
                           throw new IllegalArgumentException();
                        }

                        var25 = var25 + var60.length();
                        val var50: java.lang.String = value;

                        var `i$iv$ivx`: Int;
                        for (i$iv$ivx = index; i$iv$ivx < var50.length(); i$iv$ivx++) {
                           val var58: Char = var50.charAt(`i$iv$ivx`);
                           if ('a' > var58 || var58 >= '{') {
                              break;
                           }
                        }

                        var60 = value.substring(var25, `i$iv$ivx`);
                        var25 = var25 + var60.length();
                        val var39: DurationUnit = DurationUnitKt.durationUnitByShortName(var60);
                        if (var30 != null && var30.compareTo(var39) <= 0) {
                           throw new IllegalArgumentException("Unexpected order of duration components");
                        }

                        var30 = var39;
                        val var43: Int = StringsKt.indexOf$default(var60, '.', 0, false, 6, null);
                        if (var43 > 0) {
                           val var62: java.lang.String = var60.substring(0, var43);
                           result = Duration.plus-LRDsOJo(result, toDuration(java.lang.Long.parseLong(var62), var39));
                           val var63: java.lang.String = var60.substring(var43);
                           result = Duration.plus-LRDsOJo(result, toDuration(java.lang.Double.parseDouble(var63), var39));
                           if (var25 < length) {
                              throw new IllegalArgumentException("Fractional component must be last");
                           }
                        } else {
                           result = Duration.plus-LRDsOJo(result, toDuration(java.lang.Long.parseLong(var60), var39));
                        }
                     }
                  }
               } else {
                  if (++0 == length) {
                     throw new IllegalArgumentException();
                  }

                  val prevUnit: java.lang.String = "+-.";
                  var afterFirst: Boolean = false;
                  var allowSpaces: DurationUnit = null;

                  while (index < length) {
                     if (value.charAt(var25) == 'T') {
                        if (afterFirst || ++var25 == length) {
                           throw new IllegalArgumentException();
                        }

                        afterFirst = true;
                     } else {
                        val `$this$skipWhile$iv$iv`: java.lang.String = value;

                        var `i$iv$ivxx`: Int;
                        for (i$iv$ivxx = index; i$iv$ivxx < $this$skipWhile$iv$iv.length(); i$iv$ivxx++) {
                           val `i$iv$iv`: Char = `$this$skipWhile$iv$iv`.charAt(`i$iv$ivxx`);
                           if (('0' > `i$iv$iv` || `i$iv$iv` >= ':') && !StringsKt.contains$default(prevUnit, `i$iv$iv`, false, 2, null)) {
                              break;
                           }
                        }

                        val var10000: java.lang.String = value.substring(var25, `i$iv$ivxx`);
                        if (var10000.length() == 0) {
                           throw new IllegalArgumentException();
                        }

                        var25 += var10000.length();
                        val unit: java.lang.CharSequence = value;
                        if (0 > var25 || var25 >= value.length()) {
                           throw new IllegalArgumentException("Missing unit for value $var10000");
                        }

                        val unitName: Char = unit.charAt(var25);
                        var25++;
                        val var37: DurationUnit = DurationUnitKt.durationUnitByIsoChar(unitName, afterFirst);
                        if (allowSpaces != null && allowSpaces.compareTo(var37) <= 0) {
                           throw new IllegalArgumentException("Unexpected order of duration components");
                        }

                        allowSpaces = var37;
                        val var40: Int = StringsKt.indexOf$default(var10000, '.', 0, false, 6, null);
                        if (var37 === DurationUnit.SECONDS && var40 > 0) {
                           val var59: java.lang.String = var10000.substring(0, var40);
                           result = Duration.plus-LRDsOJo(result, toDuration(parseOverLongIsoComponent(var59), var37));
                           val var10001: java.lang.String = var10000.substring(var40);
                           result = Duration.plus-LRDsOJo(result, toDuration(java.lang.Double.parseDouble(var10001), var37));
                        } else {
                           result = Duration.plus-LRDsOJo(result, toDuration(parseOverLongIsoComponent(var10000), var37));
                        }
                     }
                  }
               }

               return if (isNegative) Duration.unaryMinus-UwyO8pc(result) else result;
            }
      }
   }
}

private fun parseOverLongIsoComponent(value: String): Long {
   val length: Int = value.length();
   if (length > 0 && StringsKt.contains$default("+-", value.charAt(0), false, 2, null)) {
      0++;
   }

   if (length - 0 > 16) {
      var firstNonZero: Int = 0;
      var index: Int = 0;

      while (true) {
         if (index >= length) {
            if (length - firstNonZero > 16) {
               return if (value.charAt(0) == '-') java.lang.Long.MIN_VALUE else java.lang.Long.MAX_VALUE;
            }
            break;
         }

         val var6: Char = value.charAt(index);
         if (var6 == '0') {
            if (firstNonZero == index) {
               firstNonZero++;
            }
         } else if ('1' > var6 || var6 >= ':') {
            break;
         }

         index++;
      }
   }

   if (StringsKt.startsWith$default(value, "+", false, 2, null) && length > 1) {
      val var7: Char = value.charAt(1);
      if ('0' <= var7 && var7 < ':') {
         return java.lang.Long.parseLong(StringsKt.drop(value, 1));
      }
   }

   return java.lang.Long.parseLong(value);
}

private inline fun String.substringWhile(startIndex: Int, predicate: (Char) -> Boolean): String {
   val `$this$skipWhile$iv`: java.lang.String = `$this$substringWhile`;
   var `i$iv`: Int = startIndex;

   while (i$iv < $this$skipWhile$iv.length() && predicate.invoke($this$skipWhile$iv.charAt(i$iv))) {
      `i$iv`++;
   }

   val var10000: java.lang.String = `$this$substringWhile`.substring(startIndex, `i$iv`);
   return var10000;
}

private inline fun String.skipWhile(startIndex: Int, predicate: (Char) -> Boolean): Int {
   var i: Int = startIndex;

   while (i < $this$skipWhile.length() && predicate.invoke($this$skipWhile.charAt(i))) {
      i++;
   }

   return i;
}

private fun nanosToMillis(nanos: Long): Long {
   return nanos / 1000000;
}

private fun millisToNanos(millis: Long): Long {
   return millis * 1000000;
}

private fun durationOfNanos(normalNanos: Long): Duration {
   return Duration.constructor-impl(normalNanos shl 1);
}

private fun durationOfMillis(normalMillis: Long): Duration {
   return Duration.constructor-impl((normalMillis shl 1) + 1L);
}

private fun durationOf(normalValue: Long, unitDiscriminator: Int): Duration {
   return Duration.constructor-impl((normalValue shl 1) + (long)unitDiscriminator);
}

private fun durationOfNanosNormalized(nanos: Long): Duration {
   return if (-4611686018426999999L <= nanos && nanos < 4611686018427000000L) durationOfNanos(nanos) else durationOfMillis(nanosToMillis(nanos));
}

private fun durationOfMillisNormalized(millis: Long): Duration {
   return if (-4611686018426L <= millis && millis < 4611686018427L)
      durationOfNanos(millisToNanos(millis))
      else
      durationOfMillis(RangesKt.coerceIn(millis, -4611686018427387903L, 4611686018427387903L));
}

@JvmSynthetic
fun `access$parseDuration`(value: java.lang.String, strictIso: Boolean): Long {
   return parseDuration(value, strictIso);
}

@JvmSynthetic
fun `access$durationOf`(normalValue: Long, unitDiscriminator: Int): Long {
   return durationOf(normalValue, unitDiscriminator);
}

@JvmSynthetic
fun `access$durationOfNanosNormalized`(nanos: Long): Long {
   return durationOfNanosNormalized(nanos);
}

@JvmSynthetic
fun `access$durationOfMillisNormalized`(millis: Long): Long {
   return durationOfMillisNormalized(millis);
}

@JvmSynthetic
fun `access$nanosToMillis`(nanos: Long): Long {
   return nanosToMillis(nanos);
}

@JvmSynthetic
fun `access$millisToNanos`(millis: Long): Long {
   return millisToNanos(millis);
}

@JvmSynthetic
fun `access$durationOfNanos`(normalNanos: Long): Long {
   return durationOfNanos(normalNanos);
}

@JvmSynthetic
fun `access$durationOfMillis`(normalMillis: Long): Long {
   return durationOfMillis(normalMillis);
}
