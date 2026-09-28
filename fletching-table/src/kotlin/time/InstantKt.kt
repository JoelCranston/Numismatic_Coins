@file:SourceDebugExtension(["SMAP\nInstant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Instant.kt\nkotlin/time/InstantKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Instant.kt\nkotlin/time/UnboundLocalDateTime\n*L\n1#1,864:1\n1#2:865\n479#3,28:866\n*S KotlinDebug\n*F\n+ 1 Instant.kt\nkotlin/time/InstantKt\n*L\n689#1:866,28\n*E\n"])

package kotlin.time

import kotlin.internal.InlineOnly
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SinceKotlin(
   version = "2.1"
)
@ExperimentalTime
@InlineOnly
public final val isDistantPast: Boolean
   public final inline get() {
      return `$this$isDistantPast`.compareTo(Instant.Companion.getDISTANT_PAST()) <= 0;
   }


@SinceKotlin(
   version = "2.1"
)
@ExperimentalTime
@InlineOnly
public final val isDistantFuture: Boolean
   public final inline get() {
      return `$this$isDistantFuture`.compareTo(Instant.Companion.getDISTANT_FUTURE()) >= 0;
   }


private const val DISTANT_PAST_SECONDS: Long = -3217862419201L
private const val DISTANT_FUTURE_SECONDS: Long = 3093527980800L
private const val MIN_SECOND: Long = -31557014167219200L
private const val MAX_SECOND: Long = 31556889864403199L
private const val DAYS_PER_CYCLE: Int = 146097
private const val DAYS_0000_TO_1970: Int = 719528
private const val SECONDS_PER_HOUR: Int = 3600
private const val SECONDS_PER_MINUTE: Int = 60
private const val HOURS_PER_DAY: Int = 24
private const val SECONDS_PER_DAY: Int = 86400
internal const val NANOS_PER_SECOND: Int = 1000000000
private const val NANOS_PER_MILLI: Int = 1000000
private const val MILLIS_PER_SECOND: Int = 1000
private final val POWERS_OF_TEN: IntArray = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000, 1000000000}
private final val asciiDigitPositionsInIsoStringAfterYear: IntArray = new int[]{1, 2, 4, 5, 7, 8, 10, 11, 13, 14}
private final val colonsInIsoOffsetString: IntArray = new int[]{3, 6}
private final val asciiDigitsInIsoOffsetString: IntArray = new int[]{1, 2, 4, 5, 7, 8}

@ExperimentalTime
private fun parseIso(isoString: CharSequence): InstantParseResult {
   val s: java.lang.CharSequence = isoString;
   var i: Int = 0;
   if (isoString.length() == 0) {
      return new InstantParseResult.Failure("An empty string is not a valid Instant", isoString);
   } else {
      val yearStart: Char = isoString.charAt(0);
      var var10000: Char;
      switch (c) {
         case '+':
         case '-':
            0++;
            var10000 = yearStart;
            break;
         case ',':
         default:
            var10000 = 32;
      }

      var absYear: Int;
      for (absYear = 0; i < s.length(); i++) {
         val yearStrLength: Char = s.charAt(i);
         if ('0' > yearStrLength || yearStrLength >= ':') {
            break;
         }

         absYear = absYear * 10 + (s.charAt(i) - '0');
      }

      val var37: Int = i - 0;
      if (i - 0 > 10) {
         return parseIso$parseFailure(isoString, "Expected at most 10 digits for the year number, got $var37 digits");
      } else if (var37 == 10 && Intrinsics.compare(s.charAt(0), 50) >= 0) {
         return parseIso$parseFailure(isoString, "Expected at most 9 digits for the year number or year 1000000000, got $var37 digits");
      } else if (var37 < 4) {
         return parseIso$parseFailure(isoString, "The year number must be padded to 4 digits, got $var37 digits");
      } else if (var10000 == 43 && var37 == 4) {
         return parseIso$parseFailure(isoString, "The '+' sign at the start is only valid for year numbers longer than 4 digits");
      } else if (var10000 == 32 && var37 != 4) {
         return parseIso$parseFailure(isoString, "A '+' or '-' sign is required for year numbers longer than 4 digits");
      } else {
         val year: Int = if (var10000 == 45) -absYear else absYear;
         if (s.length() < i + 16) {
            return parseIso$parseFailure(isoString, "The input string is too short");
         } else {
            var month: InstantParseResult.Failure = parseIso$expect(isoString, "'-'", i, InstantKt::parseIso$lambda$0);
            if (month != null) {
               return month;
            } else {
               month = parseIso$expect(isoString, "'-'", i + 3, InstantKt::parseIso$lambda$2);
               if (month != null) {
                  return month;
               } else {
                  month = parseIso$expect(isoString, "'T' or 't'", i + 6, InstantKt::parseIso$lambda$4);
                  if (month != null) {
                     return month;
                  } else {
                     month = parseIso$expect(isoString, "':'", i + 9, InstantKt::parseIso$lambda$6);
                     if (month != null) {
                        return month;
                     } else {
                        month = parseIso$expect(isoString, "':'", i + 12, InstantKt::parseIso$lambda$8);
                        if (month != null) {
                           return month;
                        } else {
                           for (int j : asciiDigitPositionsInIsoStringAfterYear) {
                              val second: InstantParseResult.Failure = parseIso$expect(isoString, "an ASCII digit", i + minute, InstantKt::parseIso$lambda$10);
                              if (second != null) {
                                 return second;
                              }
                           }

                           val var43: Int = parseIso$twoDigitNumber(s, i + 1);
                           val var44: Int = parseIso$twoDigitNumber(s, i + 4);
                           val var45: Int = parseIso$twoDigitNumber(s, i + 7);
                           val var46: Int = parseIso$twoDigitNumber(s, i + 10);
                           val var52: Int = parseIso$twoDigitNumber(s, i + 13);
                           if (s.charAt(i + 15) == '.') {
                              val offsetSeconds: Int = i + 16;
                              i = i + 16;

                              var var54: Int;
                              for (fraction = 0; i < s.length(); i++) {
                                 val `offsetSeconds$iv`: Char = s.charAt(i);
                                 if ('0' > `offsetSeconds$iv` || `offsetSeconds$iv` >= ':') {
                                    break;
                                 }

                                 var54 = var54 * 10 + (s.charAt(i) - '0');
                              }

                              val var57: Int = i - offsetSeconds;
                              if (1 > i - offsetSeconds || i - offsetSeconds >= 10) {
                                 return parseIso$parseFailure(isoString, "1..9 digits are supported for the fraction of the second, got $var57 digits");
                              }

                              var10000 = var54 * POWERS_OF_TEN[9 - var57];
                           } else {
                              i = i + 15;
                              var10000 = 0;
                           }

                           if (i >= s.length()) {
                              return parseIso$parseFailure(isoString, "The UTC offset at the end of the string is missing");
                           } else {
                              val var55: Char = s.charAt(i);
                              label250:
                              switch (sign) {
                                 case '+':
                                 case '-':
                                    val var58: Int = s.length() - i;
                                    if (var58 > 9) {
                                       return parseIso$parseFailure(
                                          isoString,
                                          "The UTC offset string \"${truncateForErrorMessage(s.subSequence(i, s.length()).toString(), 16)}\" is too long"
                                       );
                                    }

                                    if (var58 % 3 != 0) {
                                       return parseIso$parseFailure(isoString, "Invalid UTC offset string \"${s.subSequence(i, s.length()).toString()}"");
                                    }

                                    for (int jx : colonsInIsoOffsetString) {
                                       if (i + jx >= s.length()) {
                                          break;
                                       }

                                       if (s.charAt(i + jx) != ':') {
                                          return parseIso$parseFailure(isoString, "Expected ':' at index ${i + jx}, got '${s.charAt(i + jx)}'");
                                       }
                                    }

                                    val var59: IntArray = asciiDigitsInIsoOffsetString;
                                    var var62: Int = 0;
                                    var var66: Int = asciiDigitsInIsoOffsetString.length;

                                    while (true) {
                                       if (var62 < var66) {
                                          val jx: Int = var59[var62];
                                          if (i + var59[var62] < s.length()) {
                                             val var71: Char = s.charAt(i + jx);
                                             if ('0' > var71 || var71 >= ':') {
                                                return parseIso$parseFailure(isoString, "Expected an ASCII digit at index ${i + jx}, got '${s.charAt(i + jx)}'");
                                             }

                                             var62++;
                                             continue;
                                          }
                                       }

                                       val var60: Int = parseIso$twoDigitNumber(s, i + 1);
                                       var62 = if (var58 > 3) parseIso$twoDigitNumber(s, i + 4) else 0;
                                       var66 = if (var58 > 6) parseIso$twoDigitNumber(s, i + 7) else 0;
                                       if (var62 > 59) {
                                          return parseIso$parseFailure(isoString, "Expected offset-minute-of-hour in 0..59, got $var62");
                                       }

                                       if (var66 > 59) {
                                          return parseIso$parseFailure(isoString, "Expected offset-second-of-minute in 0..59, got $var66");
                                       }

                                       if (var60 > 17 && (var60 != 18 || var62 != 0 || var66 != 0)) {
                                          return parseIso$parseFailure(
                                             isoString, "Expected an offset in -18:00..+18:00, got ${s.subSequence(i, s.length()).toString()}"
                                          );
                                       }

                                       var10000 = (var60 * 3600 + var62 * 60 + var66) * (if (var55 == '-') -1 else 1);
                                       break label250;
                                    }
                                 case 'Z':
                                 case 'z':
                                    if (s.length() != i + 1) {
                                       return parseIso$parseFailure(isoString, "Extra text after the instant at position ${i + 1}");
                                    }

                                    var10000 = 0;
                                    break;
                                 default:
                                    return parseIso$parseFailure(isoString, "Expected the UTC offset at position $i, got '$var55'");
                              }

                              if (1 > var43 || var43 >= 13) {
                                 return parseIso$parseFailure(isoString, "Expected a month number in 1..12, got $var43");
                              } else if (1 > var44 || var44 > monthLength(var43, isLeapYear(year))) {
                                 return parseIso$parseFailure(isoString, "Expected a valid day-of-month for month $var43 of year $year, got $var44");
                              } else if (var45 > 23) {
                                 return parseIso$parseFailure(isoString, "Expected hour in 0..23, got $var45");
                              } else if (var46 > 59) {
                                 return parseIso$parseFailure(isoString, "Expected minute-of-hour in 0..59, got $var46");
                              } else if (var52 > 59) {
                                 return parseIso$parseFailure(isoString, "Expected second-of-minute in 0..59, got $var52");
                              } else {
                                 val var56: UnboundLocalDateTime = new UnboundLocalDateTime(year, var43, var44, var45, var46, var52, var10000);
                                 val `y$iv`: Long = var56.getYear();
                                 var `total$iv`: Long = 365 * `y$iv`;
                                 if (`y$iv` >= 0L) {
                                    `total$iv` = `total$iv` + (`y$iv` + 3) / 4 - (`y$iv` + 99) / 100 + (`y$iv` + 399) / 400;
                                 } else {
                                    `total$iv` = `total$iv` - (`y$iv` / -4 - `y$iv` / -100 + `y$iv` / -400);
                                 }

                                 `total$iv` = `total$iv` + (367 * var56.getMonth() - 362) / 12 + (var56.getDay() - 1);
                                 if (var56.getMonth() > 2) {
                                    `total$iv` += -1L;
                                    if (!isLeapYear(var56.getYear())) {
                                       `total$iv` += -1L;
                                    }
                                 }

                                 return new InstantParseResult.Success(
                                    (`total$iv` - 719528) * 86400 + (var56.getHour() * 3600 + var56.getMinute() * 60 + var56.getSecond()) - var10000,
                                    var56.getNanosecond()
                                 );
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}

@ExperimentalTime
private fun formatIso(instant: Instant): String {
   val var1: StringBuilder = new StringBuilder();
   val ldt: UnboundLocalDateTime = UnboundLocalDateTime.Companion.fromInstant(instant);
   val number: Int = ldt.getYear();
   if (Math.abs(number) < 1000) {
      val innerBuilder: StringBuilder = new StringBuilder();
      if (number >= 0) {
         ;
      }

      var1.append(innerBuilder);
   } else {
      if (number >= 10000) {
         var1.append('+');
      }

      var1.append(number);
   }

   var1.append('-');
   formatIso$lambda$0$appendTwoDigits(var1, var1, ldt.getMonth());
   var1.append('-');
   formatIso$lambda$0$appendTwoDigits(var1, var1, ldt.getDay());
   var1.append('T');
   formatIso$lambda$0$appendTwoDigits(var1, var1, ldt.getHour());
   var1.append(':');
   formatIso$lambda$0$appendTwoDigits(var1, var1, ldt.getMinute());
   var1.append(':');
   formatIso$lambda$0$appendTwoDigits(var1, var1, ldt.getSecond());
   if (ldt.getNanosecond() != 0) {
      var1.append('.');
      var zerosToStrip: Int = 0;

      while (ldt.getNanosecond() % POWERS_OF_TEN[zerosToStrip + 1] == 0) {
         zerosToStrip++;
      }

      val var10: java.lang.String = java.lang.String.valueOf(
         ldt.getNanosecond() / POWERS_OF_TEN[zerosToStrip - zerosToStrip % 3] + POWERS_OF_TEN[9 - (zerosToStrip - zerosToStrip % 3)]
      );
      val var10001: java.lang.String = var10.substring(1);
      var1.append(var10001);
   }

   var1.append('Z');
   return var1.toString();
}

private inline fun safeAddOrElse(a: Long, b: Long, action: () -> Nothing): Long {
   val sum: Long = a + b;
   if ((a xor a + b) < 0L && (a xor b) >= 0L) {
      action.invoke();
      throw new KotlinNothingValueException();
   } else {
      return sum;
   }
}

private inline fun safeMultiplyOrElse(a: Long, b: Long, action: () -> Nothing): Long {
   if (b == 1L) {
      return a;
   } else if (a == 1L) {
      return b;
   } else if (a != 0L && b != 0L) {
      val total: Long = a * b;
      if (a * b / b == a && (a != java.lang.Long.MIN_VALUE || b != -1L) && (b != java.lang.Long.MIN_VALUE || a != -1L)) {
         return total;
      } else {
         action.invoke();
         throw new KotlinNothingValueException();
      }
   } else {
      return 0L;
   }
}

internal fun isLeapYear(year: Int): Boolean {
   return (year and 3) == 0 && (year % 100 != 0 || year % 400 == 0);
}

private fun Int.monthLength(isLeapYear: Boolean): Int {
   var var10000: Int;
   switch ($this$monthLength) {
      case 2:
         var10000 = if (isLeapYear) 29 else 28;
         break;
      case 3:
      case 5:
      case 7:
      case 8:
      case 10:
      default:
         var10000 = 31;
         break;
      case 4:
      case 6:
      case 9:
      case 11:
         var10000 = 30;
   }

   return var10000;
}

private fun CharSequence.truncateForErrorMessage(maxLength: Int): String {
   return if (`$this$truncateForErrorMessage`.length() <= maxLength)
      `$this$truncateForErrorMessage`.toString()
      else
      "${`$this$truncateForErrorMessage`.subSequence(0, maxLength).toString()}...";
}

fun `parseIso$parseFailure`(`$isoString`: java.lang.CharSequence, error: java.lang.String): InstantParseResult.Failure {
   return new InstantParseResult.Failure("$error when parsing an Instant from \"${truncateForErrorMessage(`$isoString`, 64)}"", `$isoString`);
}

fun `parseIso$expect`(`$isoString`: java.lang.CharSequence, what: java.lang.String, where: Int, predicate: (Character?) -> java.lang.Boolean): InstantParseResult.Failure {
   val c: Char = `$isoString`.charAt(where);
   return if (predicate.invoke(c)) null else parseIso$parseFailure(`$isoString`, "Expected $what, but got '$c' at position $where");
}

fun `parseIso$lambda$0`(it: Char): Boolean {
   return it == '-';
}

fun `parseIso$lambda$2`(it: Char): Boolean {
   return it == '-';
}

fun `parseIso$lambda$4`(it: Char): Boolean {
   return it == 'T' || it == 't';
}

fun `parseIso$lambda$6`(it: Char): Boolean {
   return it == ':';
}

fun `parseIso$lambda$8`(it: Char): Boolean {
   return it == ':';
}

fun `parseIso$lambda$10`(it: Char): Boolean {
   return '0' <= it && it < ':';
}

fun `parseIso$twoDigitNumber`(s: java.lang.CharSequence, index: Int): Int {
   return (s.charAt(index) - 48) * 10 + (s.charAt(index + 1) - 48);
}

fun Appendable.`formatIso$lambda$0$appendTwoDigits`(`$this_buildString`: StringBuilder, number: Int) {
   if (number < 10) {
      `$this$formatIso_u24lambda_u240_u24appendTwoDigits`.append('0');
   }

   `$this_buildString`.append(number);
}

@JvmSynthetic
fun `access$formatIso`(instant: Instant): java.lang.String {
   return formatIso(instant);
}

@JvmSynthetic
fun `access$parseIso`(isoString: java.lang.CharSequence): InstantParseResult {
   return parseIso(isoString);
}

@JvmSynthetic
fun `access$truncateForErrorMessage`(`$receiver`: java.lang.CharSequence, maxLength: Int): java.lang.String {
   return truncateForErrorMessage(`$receiver`, maxLength);
}
