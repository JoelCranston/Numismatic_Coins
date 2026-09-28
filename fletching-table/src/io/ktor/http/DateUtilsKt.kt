package io.ktor.http

import io.ktor.util.date.GMTDate
import io.ktor.util.date.GMTDateParser
import io.ktor.util.date.InvalidDateStringException

private final val HTTP_DATE_FORMATS: List<String> =
   CollectionsKt.listOf(
      new java.lang.String[]{
         "***, dd MMM YYYY hh:mm:ss zzz",
         "****, dd-MMM-YYYY hh:mm:ss zzz",
         "*** MMM d hh:mm:ss YYYY",
         "***, dd-MMM-YYYY hh:mm:ss zzz",
         "***, dd-MMM-YYYY hh-mm-ss zzz",
         "***, dd MMM YYYY hh:mm:ss zzz",
         "*** dd-MMM-YYYY hh:mm:ss zzz",
         "*** dd MMM YYYY hh:mm:ss zzz",
         "*** dd-MMM-YYYY hh-mm-ss zzz",
         "***,dd-MMM-YYYY hh:mm:ss zzz",
         "*** MMM d YYYY hh:mm:ss zzz"
      }
   )

public fun String.fromHttpToGmtDate(): GMTDate {
   for (java.lang.String format : HTTP_DATE_FORMATS) {
      try {
         return new GMTDateParser(format).parse(`$this$fromHttpToGmtDate`);
      } catch (var6: InvalidDateStringException) {
      }
   }

   throw new IllegalStateException(("Failed to parse date: ${StringsKt.trim(`$this$fromHttpToGmtDate`).toString()}").toString());
}

public fun String.fromCookieToGmtDate(): GMTDate {
   val `$this$fromCookieToGmtDate_u24lambda_u240`: java.lang.String = StringsKt.trim(`$this$fromCookieToGmtDate`).toString();

   try {
      return new CookieDateParser().parse(`$this$fromCookieToGmtDate_u24lambda_u240`);
   } catch (var4: InvalidCookieDateException) {
      return fromHttpToGmtDate(`$this$fromCookieToGmtDate_u24lambda_u240`);
   }
}

public fun GMTDate.toHttpDate(): String {
   val var1: StringBuilder = new StringBuilder();
   var1.append("${`$this$toHttpDate`.getDayOfWeek().getValue()}, ");
   var1.append("${padZero(`$this$toHttpDate`.getDayOfMonth(), 2)} ");
   var1.append("${`$this$toHttpDate`.getMonth().getValue()} ");
   var1.append(padZero(`$this$toHttpDate`.getYear(), 4));
   var1.append(" ${padZero(`$this$toHttpDate`.getHours(), 2)}:${padZero(`$this$toHttpDate`.getMinutes(), 2)}:${padZero(`$this$toHttpDate`.getSeconds(), 2)} ");
   var1.append("GMT");
   return var1.toString();
}

private fun Int.padZero(length: Int): String {
   return StringsKt.padStart(java.lang.String.valueOf(`$this$padZero`), length, '0');
}
