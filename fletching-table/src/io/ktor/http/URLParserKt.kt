@file:SourceDebugExtension(["SMAP\nURLParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 URLParser.kt\nio/ktor/http/URLParserKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,289:1\n158#2,6:290\n170#2,6:296\n1#3:302\n*S KotlinDebug\n*F\n+ 1 URLParser.kt\nio/ktor/http/URLParserKt\n*L\n38#1:290,6\n39#1:296,6\n*E\n"])

package io.ktor.http

import io.ktor.util.CharsetKt
import kotlin.jvm.internal.SourceDebugExtension

internal final val ROOT_PATH: List<String> = CollectionsKt.listOf("")

public fun URLBuilder.takeFrom(urlString: String): URLBuilder {
   if (StringsKt.isBlank(urlString)) {
      return `$this$takeFrom`;
   } else {
      try {
         return takeFromUnsafe(`$this$takeFrom`, urlString);
      } catch (var4: java.lang.Throwable) {
         throw new URLParserException(urlString, var4);
      }
   }
}

internal fun URLBuilder.takeFromUnsafe(urlString: String): URLBuilder {
   val endIndex: java.lang.CharSequence = urlString;
   var slashCount: Int = 0;
   var pathEnd: Int = endIndex.length();

   var var10000: Int;
   while (true) {
      if (slashCount >= pathEnd) {
         var10000 = -1;
         break;
      }

      if (!CharsKt.isWhitespace(endIndex.charAt(slashCount))) {
         var10000 = slashCount;
         break;
      }

      slashCount++;
   }

   var startIndex: Int;
   label182: {
      startIndex = var10000;
      val var13: java.lang.CharSequence = urlString;
      pathEnd = urlString.length() + -1;
      if (0 <= pathEnd) {
         do {
            val `index$ivx`: Int = pathEnd--;
            if (!CharsKt.isWhitespace(var13.charAt(`index$ivx`))) {
               var10000 = `index$ivx`;
               break label182;
            }
         } while (0 <= delimiter);
      }

      var10000 = -1;
   }

   val var12: Int = var10000 + 1;
   val var14: Int = findScheme(urlString, var10000, var10000 + 1);
   if (var14 > 0) {
      val var38: java.lang.String = urlString.substring(var10000, var10000 + var14);
      `$this$takeFromUnsafe`.setProtocol(URLProtocol.Companion.createOrDefault(var38));
      startIndex = var10000 + var14 + 1;
   }

   if (`$this$takeFromUnsafe`.getProtocol().getName() == "data") {
      val var46: java.lang.String = urlString.substring(startIndex, var12);
      `$this$takeFromUnsafe`.setHost(var46);
      return `$this$takeFromUnsafe`;
   } else {
      slashCount = count(urlString, startIndex, var12, '/');
      startIndex = startIndex + slashCount;
      if (`$this$takeFromUnsafe`.getProtocol().getName() == "file") {
         parseFile(`$this$takeFromUnsafe`, urlString, startIndex, var12, slashCount);
         return `$this$takeFromUnsafe`;
      } else if (`$this$takeFromUnsafe`.getProtocol().getName() == "mailto") {
         if (slashCount != 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
         } else {
            parseMailto(`$this$takeFromUnsafe`, urlString, startIndex, var12);
            return `$this$takeFromUnsafe`;
         }
      } else if (`$this$takeFromUnsafe`.getProtocol().getName() == "about") {
         if (slashCount != 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
         } else {
            val var45: java.lang.String = urlString.substring(startIndex, var12);
            `$this$takeFromUnsafe`.setHost(var45);
            return `$this$takeFromUnsafe`;
         }
      } else if (`$this$takeFromUnsafe`.getProtocol().getName() == "tel") {
         if (slashCount != 0) {
            throw new IllegalArgumentException("Failed requirement.".toString());
         } else {
            val var44: java.lang.String = urlString.substring(startIndex, var12);
            `$this$takeFromUnsafe`.setHost(var44);
            return `$this$takeFromUnsafe`;
         }
      } else {
         if (slashCount >= 2) {
            while (true) {
               val var28: Int = StringsKt.indexOfAny$default(urlString, CharsetKt.toCharArray("@/\\?#"), startIndex, false, 4, null);
               val var31: Int = var28.intValue();
               pathEnd = if ((if (var31 > 0) var28 else null) != null) if (var31 > 0) var28 else null else var12;
               if (pathEnd >= var12 || urlString.charAt(pathEnd) != '@') {
                  fillHost(`$this$takeFromUnsafe`, urlString, startIndex, pathEnd);
                  startIndex = pathEnd;
                  break;
               }

               val var22: Int = indexOfColonInHostPort(urlString, startIndex, pathEnd);
               if (var22 != -1) {
                  var var10001: java.lang.String = urlString.substring(startIndex, var22);
                  `$this$takeFromUnsafe`.setEncodedUser(var10001);
                  var10001 = urlString.substring(var22 + 1, pathEnd);
                  `$this$takeFromUnsafe`.setEncodedPassword(var10001);
               } else {
                  val var43: java.lang.String = urlString.substring(startIndex, pathEnd);
                  `$this$takeFromUnsafe`.setEncodedUser(var43);
               }

               startIndex = pathEnd + 1;
            }
         }

         if (startIndex >= var12) {
            `$this$takeFromUnsafe`.setEncodedPathSegments(if (urlString.charAt(var12 - 1) == '/') ROOT_PATH else CollectionsKt.emptyList());
            return `$this$takeFromUnsafe`;
         } else {
            `$this$takeFromUnsafe`.setEncodedPathSegments(
               if (slashCount == 0) CollectionsKt.dropLast(`$this$takeFromUnsafe`.getEncodedPathSegments(), 1) else CollectionsKt.emptyList()
            );
            val var29: Int = StringsKt.indexOfAny$default(urlString, CharsetKt.toCharArray("?#"), startIndex, false, 4, null);
            val itx: Int = var29.intValue();
            pathEnd = if ((if (itx > 0) var29 else null) != null) if (itx > 0) var29 else null else var12;
            if (pathEnd > startIndex) {
               val var39: java.lang.String = urlString.substring(startIndex, pathEnd);
               `$this$takeFromUnsafe`.setEncodedPathSegments(
                  CollectionsKt.plus(
                     if (`$this$takeFromUnsafe`.getEncodedPathSegments().size() == 1
                           && CollectionsKt.first(`$this$takeFromUnsafe`.getEncodedPathSegments()).length() == 0)
                        CollectionsKt.emptyList()
                        else
                        `$this$takeFromUnsafe`.getEncodedPathSegments(),
                     CollectionsKt.plus(
                        if (slashCount == 1) ROOT_PATH else CollectionsKt.emptyList(),
                        if (var39 == "/") ROOT_PATH else StringsKt.split$default(var39, new char[]{'/'}, false, 0, 6, null)
                     )
                  )
               );
               startIndex = pathEnd;
            }

            if (startIndex < var12 && urlString.charAt(startIndex) == '?') {
               startIndex = parseQuery(`$this$takeFromUnsafe`, urlString, startIndex, var12);
            }

            parseFragment(`$this$takeFromUnsafe`, urlString, startIndex, var12);
            return `$this$takeFromUnsafe`;
         }
      }
   }
}

private fun URLBuilder.parseFile(urlString: String, startIndex: Int, endIndex: Int, slashCount: Int) {
   switch (slashCount) {
      case 1:
         `$this$parseFile`.setHost("");
         val var9: java.lang.String = urlString.substring(startIndex, endIndex);
         URLBuilderKt.setEncodedPath(`$this$parseFile`, var9);
         break;
      case 2:
         val nextSlash: Int = StringsKt.indexOf$default(urlString, '/', startIndex, false, 4, null);
         if (nextSlash == -1 || nextSlash == endIndex) {
            val var8: java.lang.String = urlString.substring(startIndex, endIndex);
            `$this$parseFile`.setHost(var8);
            return;
         }

         var var6: java.lang.String = urlString.substring(startIndex, nextSlash);
         `$this$parseFile`.setHost(var6);
         var6 = urlString.substring(nextSlash, endIndex);
         URLBuilderKt.setEncodedPath(`$this$parseFile`, var6);
         break;
      case 3:
         `$this$parseFile`.setHost("");
         val var10001: StringBuilder = new StringBuilder().append('/');
         val var10002: java.lang.String = urlString.substring(startIndex, endIndex);
         URLBuilderKt.setEncodedPath(`$this$parseFile`, var10001.append(var10002).toString());
         break;
      default:
         throw new IllegalArgumentException("Invalid file url: $urlString");
   }
}

private fun URLBuilder.parseMailto(urlString: String, startIndex: Int, endIndex: Int) {
   val delimiter: Int = StringsKt.indexOf$default(urlString, "@", startIndex, false, 4, null);
   if (delimiter == -1) {
      throw new IllegalArgumentException("Invalid mailto url: $urlString, it should contain '@'.");
   } else {
      var var10001: java.lang.String = urlString.substring(startIndex, delimiter);
      `$this$parseMailto`.setUser(CodecsKt.decodeURLPart$default(var10001, 0, 0, null, 7, null));
      var10001 = urlString.substring(delimiter + 1, endIndex);
      `$this$parseMailto`.setHost(var10001);
   }
}

private fun URLBuilder.parseQuery(urlString: String, startIndex: Int, endIndex: Int): Int {
   if (startIndex + 1 == endIndex) {
      `$this$parseQuery`.setTrailingQuery(true);
      return endIndex;
   } else {
      val var6: Int = StringsKt.indexOf$default(urlString, '#', startIndex + 1, false, 4, null);
      val it: Int = var6.intValue();
      val fragmentStart: Int = if ((if (it > 0) var6 else null) != null) if (it > 0) var6 else null else endIndex;
      val var10000: java.lang.String = urlString.substring(startIndex + 1, fragmentStart);
      QueryKt.parseQueryString$default(var10000, 0, 0, false, 6, null).forEach(URLParserKt::parseQuery$lambda$1);
      return fragmentStart;
   }
}

private fun URLBuilder.parseFragment(urlString: String, startIndex: Int, endIndex: Int) {
   if (startIndex < endIndex && urlString.charAt(startIndex) == '#') {
      val var10001: java.lang.String = urlString.substring(startIndex + 1, endIndex);
      `$this$parseFragment`.setEncodedFragment(var10001);
   }
}

private fun URLBuilder.fillHost(urlString: String, startIndex: Int, endIndex: Int) {
   val var5: Int = indexOfColonInHostPort(urlString, startIndex, endIndex);
   val it: Int = var5.intValue();
   val colonIndex: Int = if ((if (it > 0) var5 else null) != null) if (it > 0) var5 else null else endIndex;
   var var10001: java.lang.String = urlString.substring(startIndex, colonIndex);
   `$this$fillHost`.setHost(var10001);
   val var9: Int;
   if (colonIndex + 1 < endIndex) {
      var10001 = urlString.substring(colonIndex + 1, endIndex);
      var9 = Integer.parseInt(var10001);
   } else {
      var9 = 0;
   }

   `$this$fillHost`.setPort(var9);
}

private fun findScheme(urlString: String, startIndex: Int, endIndex: Int): Int {
   var current: Int = startIndex;
   var incorrectSchemePosition: Int = -1;
   val firstChar: Char = urlString.charAt(startIndex);
   if (('a' > firstChar || firstChar >= '{') && ('A' > firstChar || firstChar >= '[')) {
      incorrectSchemePosition = startIndex;
   }

   for (; current < endIndex; current++) {
      val var6: Char = urlString.charAt(current);
      if (var6 == ':') {
         if (incorrectSchemePosition != -1) {
            throw new IllegalArgumentException("Illegal character in scheme at position $incorrectSchemePosition");
         }

         return current - startIndex;
      }

      switch (char) {
         case '#':
         case '/':
         case '?':
            return -1;
         default:
      }

      if (incorrectSchemePosition == -1
         && ('a' > var6 || var6 >= '{')
         && ('A' > var6 || var6 >= '[')
         && ('0' > var6 || var6 >= ':')
         && var6 != '.'
         && var6 != '+'
         && var6 != '-') {
         incorrectSchemePosition = current;
      }
   }

   return -1;
}

private fun count(urlString: String, startIndex: Int, endIndex: Int, char: Char): Int {
   var result: Int = 0;

   while (startIndex + result < endIndex && urlString.charAt(startIndex + result) == char) {
      result++;
   }

   return result;
}

private fun String.indexOfColonInHostPort(startIndex: Int, endIndex: Int): Int {
   var skip: Boolean = false;

   for (int index = startIndex; index < endIndex; index++) {
      switch ($this$indexOfColonInHostPort.charAt(index)) {
         case ':':
            if (!skip) {
               return index;
            }
            break;
         case '[':
            skip = true;
            break;
         case ']':
            skip = false;
         default:
      }
   }

   return -1;
}

private fun Char.isLetter(): Boolean {
   val var1: Char = Character.toLowerCase(`$this$isLetter`);
   return 'a' <= var1 && var1 < '{';
}

fun `parseQuery$lambda$1`(`$this_parseQuery`: URLBuilder, key: java.lang.String, values: java.util.List): Unit {
   `$this_parseQuery`.getEncodedParameters().appendAll(key, values);
   return Unit.INSTANCE;
}
