package io.ktor.http

import io.ktor.util.Base64Kt
import io.ktor.util.TextKt
import io.ktor.util.date.GMTDate
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.NoSuchElementException
import java.util.Map.Entry
import kotlin.jvm.internal.Intrinsics

private final val loweredPartNames: Set<String> = SetsKt.setOf(new java.lang.String[]{"max-age", "expires", "domain", "path", "secure", "httponly", "$x-enc"})
private final val clientCookieHeaderPattern: Regex = new Regex("(^|;)\\s*([^;=\\{\\}\\s]+)\\s*(=\\s*(\"[^\"]*\"|[^;]*))?")
private final val cookieCharsShouldBeEscaped: Set<Char> = SetsKt.setOf(new Character[]{';', ',', '"'})

public fun parseServerSetCookieHeader(cookiesHeader: String): Cookie {
   val asMap: java.util.Map = parseClientCookiesHeader(cookiesHeader, false);

   val encoding: java.lang.Iterable;
   for (Object element$iv : encoding) {
      if (!StringsKt.startsWith$default((`$i$f$filterKeys` as Entry).getKey() as java.lang.String, "$", false, 2, null)) {
         var first: Entry;
         var var54: CookieEncoding;
         label60: {
            first = `$i$f$filterKeys` as Entry;
            val var10000: java.lang.String = asMap.get("$x-enc") as java.lang.String;
            if (var10000 != null) {
               var54 = CookieEncoding.valueOf(var10000);
               if (var54 != null) {
                  break label60;
               }
            }

            var54 = CookieEncoding.RAW;
         }

         val var48: java.util.Map = new LinkedHashMap(MapsKt.mapCapacity(asMap.size()));
         val it: java.lang.Iterable = asMap.entrySet();
         val var11: java.util.Map = var48;

         for (Object element$iv$iv$iv : it) {
            var11.put(
               TextKt.toLowerCasePreservingASCIIRules((`element$iv$iv$iv` as Entry).getKey() as java.lang.String), (`element$iv$iv$iv` as Entry).getValue()
            );
         }

         val var55: java.lang.String = first.getKey() as java.lang.String;
         val var56: java.lang.String = decodeCookieValue(first.getValue() as java.lang.String, var54);
         val var10003: java.lang.String = var11.get("max-age") as java.lang.String;
         val var57: Int = if (var10003 != null) toIntClamping(var10003) else null;
         val var10004: java.lang.String = var11.get("expires") as java.lang.String;
         val var58: GMTDate = if (var10004 != null) DateUtilsKt.fromCookieToGmtDate(var10004) else null;
         val var10005: java.lang.String = var11.get("domain") as java.lang.String;
         val var10006: java.lang.String = var11.get("path") as java.lang.String;
         val var10007: Boolean = var11.containsKey("secure");
         val var30: Boolean = var11.containsKey("httponly");
         val var46: LinkedHashMap = new LinkedHashMap();

         for (Entry entry$iv : asMap.entrySet()) {
            val var51: java.lang.String = var50.getKey() as java.lang.String;
            if (!loweredPartNames.contains(TextKt.toLowerCasePreservingASCIIRules(var51)) && !(var51 == first.getKey())) {
               var46.put(var50.getKey(), var50.getValue());
            }
         }

         return new Cookie(var55, var56, var54, var57, var58, var10005, var10006, var10007, var30, var46);
      }
   }

   throw new NoSuchElementException("Collection contains no element matching the predicate.");
}

public fun parseClientCookiesHeader(cookiesHeader: String, skipEscaped: Boolean = true): Map<String, String> {
   return MapsKt.toMap(
      SequencesKt.map(
         SequencesKt.filter(
            SequencesKt.map(Regex.findAll$default(clientCookieHeaderPattern, cookiesHeader, 0, 2, null), CookieKt::parseClientCookiesHeader$lambda$0),
            CookieKt::parseClientCookiesHeader$lambda$1
         ),
         CookieKt::parseClientCookiesHeader$lambda$2
      )
   );
}

@JvmSynthetic
fun `parseClientCookiesHeader$default`(var0: java.lang.String, var1: Boolean, var2: Int, var3: Any): java.util.Map {
   if ((var2 and 2) != 0) {
      var1 = true;
   }

   return parseClientCookiesHeader(var0, var1);
}

public fun renderSetCookieHeader(cookie: Cookie): String {
   return renderSetCookieHeader$default(
      cookie.getName(),
      cookie.getValue(),
      cookie.getEncoding(),
      cookie.getMaxAgeInt(),
      cookie.getExpires(),
      cookie.getDomain(),
      cookie.getPath(),
      cookie.getSecure(),
      cookie.getHttpOnly(),
      cookie.getExtensions(),
      false,
      1024,
      null
   );
}

public fun renderCookieHeader(cookie: Cookie): String {
   return "${cookie.getName()}=${encodeCookieValue(cookie.getValue(), cookie.getEncoding())}";
}

public fun renderSetCookieHeader(
   name: String,
   value: String,
   encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
   maxAge: Int? = null,
   expires: GMTDate? = null,
   domain: String? = null,
   path: String? = null,
   secure: Boolean = false,
   httpOnly: Boolean = false,
   extensions: Map<String, String?> = MapsKt.emptyMap(),
   includeEncoding: Boolean = true
): String {
   val `$this$filter$iv`: Array<java.lang.String> = new java.lang.String[7];
   `$this$filter$iv`[0] = "${assertCookieName(name)}=${encodeCookieValue(value.toString(), encoding)}";
   `$this$filter$iv`[1] = if (maxAge != null) "Max-Age=$maxAge" else "";
   val `$this$filterTo$iv$iv`: Any = if (expires != null) DateUtilsKt.toHttpDate(expires) else null;
   `$this$filter$iv`[2] = if (`$this$filterTo$iv$iv` != null) "Expires=$`$this$filterTo$iv$iv`" else "";
   var var42: CookieEncoding = CookieEncoding.RAW;
   `$this$filter$iv`[3] = if (domain != null) "Domain=${encodeCookieValue(domain.toString(), var42)}" else "";
   var42 = CookieEncoding.RAW;
   `$this$filter$iv`[4] = if (path != null) "Path=${encodeCookieValue(path.toString(), var42)}" else "";
   `$this$filter$iv`[5] = if (secure) "Secure" else "";
   `$this$filter$iv`[6] = if (httpOnly) "HttpOnly" else "";
   val var27: java.util.Collection = CollectionsKt.listOf(`$this$filter$iv`);
   val var46: java.util.Collection = new ArrayList(extensions.size());

   for (Entry item$iv$iv : extensions.entrySet()) {
      val `name$iv`: java.lang.String = assertCookieName(`element$iv$iv`.getKey() as java.lang.String);
      val `value$ivx`: java.lang.String = `element$iv$iv`.getValue() as java.lang.String;
      val var10000: java.lang.String;
      if (`value$ivx` == null) {
         var10000 = `name$iv`;
      } else {
         val var59: CookieEncoding = CookieEncoding.RAW;
         var10000 = "$`name$iv`=${encodeCookieValue(`value$ivx`.toString(), var59)}";
      }

      var46.add(var10000);
   }

   val var60: java.util.Collection = CollectionsKt.plus(var27, var46 as java.util.List);
   val var10001: java.lang.String;
   if (includeEncoding) {
      val `value$ivx`: java.lang.String = encoding.name();
      if (`value$ivx` == null) {
         var10001 = "$x-enc";
      } else {
         val var54: CookieEncoding = CookieEncoding.RAW;
         var10001 = "\$x-enc=${encodeCookieValue(`value$ivx`.toString(), var54)}";
      }
   } else {
      var10001 = "";
   }

   val var30: java.lang.Iterable = CollectionsKt.plus(var60, var10001);
   val `destination$iv$ivx`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : var30) {
      if ((var57 as java.lang.String).length() > 0) {
         `destination$iv$ivx`.add(var57);
      }
   }

   return CollectionsKt.joinToString$default(`destination$iv$ivx` as java.util.List, "; ", null, null, 0, null, null, 62, null);
}

@JvmSynthetic
fun `renderSetCookieHeader$default`(
   var0: java.lang.String,
   var1: java.lang.String,
   var2: CookieEncoding,
   var3: Int,
   var4: GMTDate,
   var5: java.lang.String,
   var6: java.lang.String,
   var7: Boolean,
   var8: Boolean,
   var9: java.util.Map,
   var10: Boolean,
   var11: Int,
   var12: Any
): java.lang.String {
   if ((var11 and 4) != 0) {
      var2 = CookieEncoding.URI_ENCODING;
   }

   if ((var11 and 8) != 0) {
      var3 = null;
   }

   if ((var11 and 16) != 0) {
      var4 = null;
   }

   if ((var11 and 32) != 0) {
      var5 = null;
   }

   if ((var11 and 64) != 0) {
      var6 = null;
   }

   if ((var11 and 128) != 0) {
      var7 = false;
   }

   if ((var11 and 256) != 0) {
      var8 = false;
   }

   if ((var11 and 512) != 0) {
      var9 = MapsKt.emptyMap();
   }

   if ((var11 and 1024) != 0) {
      var10 = true;
   }

   return renderSetCookieHeader(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10);
}

public fun encodeCookieValue(value: String, encoding: CookieEncoding): String {
   var var10000: java.lang.String;
   switch (CookieKt.WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()]) {
      case 1:
         var10000 = value;
         break;
      case 2:
         if (StringsKt.contains$default(value, '"', false, 2, null)) {
            throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in DQUOTES format. Consider URL_ENCODING mode");
         }

         val `$this$any$iv`: java.lang.CharSequence = value;
         var var4: Int = 0;

         while (true) {
            if (var4 >= `$this$any$iv`.length()) {
               var8 = false;
               break;
            }

            if (shouldEscapeInCookies(`$this$any$iv`.charAt(var4))) {
               var8 = true;
               break;
            }

            var4++;
         }

         var10000 = if (var8) ""$value"" else value;
         break;
      case 3:
         var10000 = Base64Kt.encodeBase64(value);
         break;
      case 4:
         var10000 = CodecsKt.encodeURLParameter(value, true);
         break;
      default:
         throw new NoWhenBranchMatchedException();
   }

   return var10000;
}

public fun decodeCookieValue(encodedValue: String, encoding: CookieEncoding): String {
   var var10000: java.lang.String;
   switch (CookieKt.WhenMappings.$EnumSwitchMapping$0[encoding.ordinal()]) {
      case 1:
      case 2:
         var10000 = if (StringsKt.startsWith$default(StringsKt.trimStart(encodedValue).toString(), "\"", false, 2, null)
               && StringsKt.endsWith$default(StringsKt.trimEnd(encodedValue).toString(), "\"", false, 2, null))
            StringsKt.removeSurrounding(StringsKt.trim(encodedValue).toString(), "\"")
            else
            encodedValue;
         break;
      case 3:
         var10000 = Base64Kt.decodeBase64String(encodedValue);
         break;
      case 4:
         var10000 = CodecsKt.decodeURLQueryComponent$default(encodedValue, 0, 0, true, null, 11, null);
         break;
      default:
         throw new NoWhenBranchMatchedException();
   }

   return var10000;
}

private fun String.assertCookieName(): String {
   val `$this$any$iv`: java.lang.CharSequence = `$this$assertCookieName`;
   var var3: Int = 0;

   var var10000: Boolean;
   while (true) {
      if (var3 >= `$this$any$iv`.length()) {
         var10000 = false;
         break;
      }

      if (shouldEscapeInCookies(`$this$any$iv`.charAt(var3))) {
         var10000 = true;
         break;
      }

      var3++;
   }

   if (var10000) {
      throw new IllegalArgumentException("Cookie name is not valid: $`$this$assertCookieName`");
   } else {
      return `$this$assertCookieName`;
   }
}

private fun Char.shouldEscapeInCookies(): Boolean {
   return CharsKt.isWhitespace(`$this$shouldEscapeInCookies`)
      || Intrinsics.compare(`$this$shouldEscapeInCookies`, 32) < 0
      || cookieCharsShouldBeEscaped.contains(`$this$shouldEscapeInCookies`);
}

private inline fun cookiePart(name: String, value: Any?, encoding: CookieEncoding): String {
   return if (value != null) "$name=${encodeCookieValue(value.toString(), encoding)}" else "";
}

private inline fun cookiePartUnencoded(name: String, value: Any?): String {
   return if (value != null) "$name=$value" else "";
}

private inline fun cookiePartFlag(name: String, value: Boolean): String {
   return if (value) name else "";
}

private inline fun cookiePartExt(name: String, value: String?): String {
   val var10000: java.lang.String;
   if (value == null) {
      var10000 = name;
   } else {
      val var7: CookieEncoding = CookieEncoding.RAW;
      var10000 = "$name=${encodeCookieValue(value.toString(), var7)}";
   }

   return var10000;
}

private fun String.toIntClamping(): Int {
   return (int)kotlin.ranges.RangesKt.coerceIn(java.lang.Long.parseLong(`$this$toIntClamping`), 0L, 2147483647L);
}

fun `parseClientCookiesHeader$lambda$0`(it: MatchResult): Pair {
   var var1: java.lang.String;
   label19: {
      val var10000: MatchGroup = it.getGroups().get(2);
      if (var10000 != null) {
         var1 = var10000.getValue();
         if (var1 != null) {
            break label19;
         }
      }

      var1 = "";
   }

   val var10001: MatchGroup = it.getGroups().get(4);
   if (var10001 != null) {
      val var2: java.lang.String = var10001.getValue();
      if (var2 != null) {
         return TuplesKt.to(var1, var2);
      }
   }

   return TuplesKt.to(var1, "");
}

fun `parseClientCookiesHeader$lambda$1`(`$skipEscaped`: Boolean, it: Pair): Boolean {
   return !`$skipEscaped` || !StringsKt.startsWith$default(it.getFirst() as java.lang.String, "$", false, 2, null);
}

fun `parseClientCookiesHeader$lambda$2`(cookie: Pair): Pair {
   return if (StringsKt.startsWith$default(cookie.getSecond() as java.lang.String, "\"", false, 2, null)
         && StringsKt.endsWith$default(cookie.getSecond() as java.lang.String, "\"", false, 2, null))
      Pair.copy$default(cookie, null, StringsKt.removeSurrounding(cookie.getSecond() as java.lang.String, "\""), 1, null)
      else
      cookie;
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[CookieEncoding.values().length];

      try {
         var0[CookieEncoding.RAW.ordinal()] = 1;
      } catch (var5: NoSuchFieldError) {
      }

      try {
         var0[CookieEncoding.DQUOTES.ordinal()] = 2;
      } catch (var4: NoSuchFieldError) {
      }

      try {
         var0[CookieEncoding.BASE64_ENCODING.ordinal()] = 3;
      } catch (var3: NoSuchFieldError) {
      }

      try {
         var0[CookieEncoding.URI_ENCODING.ordinal()] = 4;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
