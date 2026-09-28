package io.ktor.http.auth

import io.ktor.http.CookieUtilsKt
import io.ktor.http.parsing.ParseException
import io.ktor.utils.io.InternalAPI
import java.util.ArrayList
import java.util.LinkedHashMap

private final val TOKEN_EXTRA: Set<Char> = SetsKt.setOf(new Character[]{'!', '#', '$', '%', '&', '\'', '*', '+', '-', '.', '^', '_', '`', '|', '~'})
private final val TOKEN68_EXTRA: Set<Char> = SetsKt.setOf(new Character[]{'-', '.', '_', '~', '+', '/'})
private final val token68Pattern: Regex = new Regex("[a-zA-Z0-9\\-._~+/]+=*")
private final val escapeRegex: Regex = new Regex("\\\\.")

public fun parseAuthorizationHeader(headerValue: String): HttpAuthHeader? {
   var var8: Int = skipSpaces(headerValue, 0);
   val tokenStartIndex: Int = var8;

   while (var8 < headerValue.length() && isToken(headerValue.charAt(var8))) {
      var8++;
   }

   val authScheme: java.lang.String = StringsKt.substring(headerValue, RangesKt.until(tokenStartIndex, var8));
   var8 = skipSpaces(headerValue, var8);
   if (StringsKt.isBlank(authScheme)) {
      return null;
   } else if (headerValue.length() == var8) {
      return new HttpAuthHeader.Parameterized(authScheme, CollectionsKt.emptyList(), null, 4, null);
   } else {
      val token68EndIndex: Int = matchToken68(headerValue, var8);
      val token68: java.lang.String = StringsKt.trim(StringsKt.substring(headerValue, RangesKt.until(var8, token68EndIndex))).toString();
      if (token68.length() > 0 && token68EndIndex == headerValue.length()) {
         return new HttpAuthHeader.Single(authScheme, token68);
      } else {
         val parameters: java.util.Map = new LinkedHashMap();
         if (matchParameters(headerValue, var8, parameters) == -1) {
            return new HttpAuthHeader.Parameterized(authScheme, parameters, null, 4, null);
         } else {
            throw new ParseException("Function parseAuthorizationHeader can parse only one header", null, 2, null);
         }
      }
   }
}

@InternalAPI
public fun parseAuthorizationHeaders(headerValue: String): List<HttpAuthHeader> {
   var index: Int = 0;
   val headers: java.util.List = new ArrayList();

   while (index != -1) {
      index = parseAuthorizationHeader(headerValue, index, headers);
   }

   return headers;
}

private fun parseAuthorizationHeader(headerValue: String, startIndex: Int, headers: MutableList<HttpAuthHeader>): Int {
   var index: Int = skipSpaces(headerValue, startIndex);
   val schemeStartIndex: Int = index;

   while (index < headerValue.length() && isToken(headerValue.charAt(index))) {
      index++;
   }

   val authScheme: java.lang.String = StringsKt.substring(headerValue, RangesKt.until(schemeStartIndex, index));
   if (StringsKt.isBlank(authScheme)) {
      throw new ParseException("Invalid authScheme value: it should be token, can't be blank", null, 2, null);
   } else {
      index = skipSpaces(headerValue, index);
      val token68EndIndex: Int = nextChallengeIndex(
         headers, new HttpAuthHeader.Parameterized(authScheme, CollectionsKt.emptyList(), null, 4, null), index, headerValue
      );
      if (token68EndIndex != null) {
         return token68EndIndex.intValue();
      } else {
         val var13: Int = matchToken68(headerValue, index);
         val token68: java.lang.String = StringsKt.trim(StringsKt.substring(headerValue, RangesKt.until(index, var13))).toString();
         if (token68.length() > 0) {
            val parameters: Int = nextChallengeIndex(headers, new HttpAuthHeader.Single(authScheme, token68), var13, headerValue);
            if (parameters != null) {
               return parameters.intValue();
            }
         }

         val var14: java.util.Map = new LinkedHashMap();
         val nextIndexChallenge: Int = matchParameters(headerValue, index, var14);
         headers.add(new HttpAuthHeader.Parameterized(authScheme, var14, null, 4, null));
         return nextIndexChallenge;
      }
   }
}

private fun nextChallengeIndex(headers: MutableList<HttpAuthHeader>, header: HttpAuthHeader, index: Int, headerValue: String): Int? {
   if (index != headerValue.length() && headerValue.charAt(index) != ',') {
      return null;
   } else {
      headers.add(header);
      val var10000: Int;
      if (index == headerValue.length()) {
         var10000 = -1;
      } else {
         if (headerValue.charAt(index) != ',') {
            throw new IllegalStateException("".toString());
         }

         var10000 = index + 1;
      }

      return var10000;
   }
}

private fun matchParameters(headerValue: String, startIndex: Int, parameters: MutableMap<String, String>): Int {
   var index: Int = startIndex;

   while (index > 0 && index < headerValue.length()) {
      val nextIndex: Int = matchParameter(headerValue, index, parameters);
      if (nextIndex == index) {
         return index;
      }

      index = skipDelimiter(headerValue, nextIndex, ',');
   }

   return index;
}

private fun matchParameter(headerValue: String, startIndex: Int, parameters: MutableMap<String, String>): Int {
   val keyStart: Int = skipSpaces(headerValue, startIndex);
   var index: Int = keyStart;

   while (index < headerValue.length() && isToken(headerValue.charAt(index))) {
      index++;
   }

   val key: java.lang.String = StringsKt.substring(headerValue, RangesKt.until(keyStart, index));
   index = skipSpaces(headerValue, index);
   if (index != headerValue.length() && headerValue.charAt(index) == '=') {
      index = skipSpaces(headerValue, ++index);
      var quoted: Boolean = false;
      var valueStart: Int = index;
      if (headerValue.charAt(index) == '"') {
         quoted = true;
         valueStart = ++index;

         for (boolean escaped = false; index < headerValue.length() && (headerValue.charAt(index) != '"' || escaped); index++) {
            value = !value && headerValue.charAt(index) == '\\';
         }

         if (index == headerValue.length()) {
            throw new ParseException("Expected closing quote'\"' in parameter", null, 2, null);
         }
      } else {
         while (index < headerValue.length() && headerValue.charAt(index) != ' ' && headerValue.charAt(index) != ',') {
            index++;
         }
      }

      val var12: java.lang.String = StringsKt.substring(headerValue, RangesKt.until(valueStart, index));
      parameters.put(key, if (quoted) unescaped(var12) else var12);
      if (quoted) {
         index++;
      }

      return index;
   } else {
      return startIndex;
   }
}

private fun matchToken68(headerValue: String, startIndex: Int): Int {
   var index: Int = skipSpaces(headerValue, startIndex);

   while (index < headerValue.length() && isToken68(headerValue.charAt(index))) {
      index++;
   }

   while (index < headerValue.length() && headerValue.charAt(index) == '=') {
      index++;
   }

   return skipSpaces(headerValue, index);
}

private fun String.unescaped(): String {
   return escapeRegex.replace(`$this$unescaped`, HttpAuthHeaderKt::unescaped$lambda$0);
}

private fun String.skipDelimiter(startIndex: Int, delimiter: Char): Int {
   var index: Int = skipSpaces(`$this$skipDelimiter`, startIndex);
   if (index == `$this$skipDelimiter`.length()) {
      return -1;
   } else if (`$this$skipDelimiter`.charAt(index) != delimiter) {
      throw new ParseException("Expected delimiter $delimiter at position $index", null, 2, null);
   } else {
      return skipSpaces(`$this$skipDelimiter`, ++index);
   }
}

private fun String.skipSpaces(startIndex: Int): Int {
   var index: Int = startIndex;

   while (index < $this$skipSpaces.length() && $this$skipSpaces.charAt(index) == ' ') {
      index++;
   }

   return index;
}

private fun Char.isToken68(): Boolean {
   return 'a' <= `$this$isToken68` && `$this$isToken68` < '{'
      || 'A' <= `$this$isToken68` && `$this$isToken68` < '['
      || CookieUtilsKt.isDigit(`$this$isToken68`)
      || TOKEN68_EXTRA.contains(`$this$isToken68`);
}

private fun Char.isToken(): Boolean {
   return 'a' <= `$this$isToken` && `$this$isToken` < '{'
      || 'A' <= `$this$isToken` && `$this$isToken` < '['
      || CookieUtilsKt.isDigit(`$this$isToken`)
      || TOKEN_EXTRA.contains(`$this$isToken`);
}

fun `unescaped$lambda$0`(it: MatchResult): java.lang.CharSequence {
   return StringsKt.takeLast(it.getValue(), 1);
}

@JvmSynthetic
fun `access$getToken68Pattern$p`(): Regex {
   return token68Pattern;
}
