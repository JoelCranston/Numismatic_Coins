@file:SourceDebugExtension(["SMAP\nHttpMessagePropertiesJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpMessagePropertiesJvm.kt\nio/ktor/http/HttpMessagePropertiesJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,61:1\n1#2:62\n*E\n"])

package io.ktor.http

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import kotlin.jvm.internal.SourceDebugExtension

private final val HTTP_DATE_FORMAT: SimpleDateFormat
   private final get() {
      val var0: SimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
      var0.setTimeZone(TimeZone.getTimeZone("GMT"));
      return var0;
   }


private fun parseHttpDate(date: String): Date {
   val var10000: Date = getHTTP_DATE_FORMAT().parse(date);
   return var10000;
}

private fun formatHttpDate(date: Date): String {
   val var10000: java.lang.String = getHTTP_DATE_FORMAT().format(date);
   return var10000;
}

public fun HttpMessageBuilder.ifModifiedSince(date: Date) {
   `$this$ifModifiedSince`.getHeaders().set(HttpHeaders.INSTANCE.getIfModifiedSince(), formatHttpDate(date));
}

public fun HttpMessageBuilder.lastModified(): Date? {
   val var10000: java.lang.String = `$this$lastModified`.getHeaders().get(HttpHeaders.INSTANCE.getLastModified());
   return if (var10000 != null) parseHttpDate(var10000) else null;
}

public fun HttpMessageBuilder.expires(): Date? {
   val var10000: java.lang.String = `$this$expires`.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
   return if (var10000 != null) parseHttpDate(var10000) else null;
}

public fun HttpMessage.lastModified(): Date? {
   val var10000: java.lang.String = `$this$lastModified`.getHeaders().get(HttpHeaders.INSTANCE.getLastModified());
   return if (var10000 != null) parseHttpDate(var10000) else null;
}

public fun HttpMessage.expires(): Date? {
   val var10000: java.lang.String = `$this$expires`.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
   return if (var10000 != null) parseHttpDate(var10000) else null;
}

public fun HttpMessage.date(): Date? {
   val var10000: java.lang.String = `$this$date`.getHeaders().get(HttpHeaders.INSTANCE.getDate());
   return if (var10000 != null) parseHttpDate(var10000) else null;
}
