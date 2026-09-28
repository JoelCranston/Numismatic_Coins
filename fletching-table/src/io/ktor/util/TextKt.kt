@file:SourceDebugExtension(["SMAP\nText.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Text.kt\nio/ktor/util/TextKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,130:1\n158#2,6:131\n158#2,6:137\n*S KotlinDebug\n*F\n+ 1 Text.kt\nio/ktor/util/TextKt\n*L\n54#1:131,6\n79#1:137,6\n*E\n"])

package io.ktor.util

import kotlin.jvm.internal.SourceDebugExtension

public fun String.escapeHTML(): String {
   val text: java.lang.String = `$this$escapeHTML`;
   if (`$this$escapeHTML`.length() == 0) {
      return `$this$escapeHTML`;
   } else {
      val var3: StringBuilder = new StringBuilder(`$this$escapeHTML`.length());
      val `$this$escapeHTML_u24lambda_u240`: StringBuilder = var3;
      var var6: Int = 0;

      for (int var7 = $this$escapeHTML.length(); var6 < var7; var6++) {
         val element: Char = text.charAt(var6);
         switch (element) {
            case '"':
               `$this$escapeHTML_u24lambda_u240`.append("&quot;");
               break;
            case '&':
               `$this$escapeHTML_u24lambda_u240`.append("&amp;");
               break;
            case '\'':
               `$this$escapeHTML_u24lambda_u240`.append("&#x27;");
               break;
            case '<':
               `$this$escapeHTML_u24lambda_u240`.append("&lt;");
               break;
            case '>':
               `$this$escapeHTML_u24lambda_u240`.append("&gt;");
               break;
            default:
               `$this$escapeHTML_u24lambda_u240`.append(element);
         }
      }

      return var3.toString();
   }
}

public inline fun String.chomp(separator: String, onMissingDelimiter: () -> Pair<String, String>): Pair<String, String> {
   val idx: Int = StringsKt.indexOf$default(`$this$chomp`, separator, 0, false, 6, null);
   val var10000: Pair;
   if (idx == -1) {
      var10000 = onMissingDelimiter.invoke() as Pair;
   } else {
      val var5: java.lang.String = `$this$chomp`.substring(0, idx);
      val var10001: java.lang.String = `$this$chomp`.substring(idx + separator.length());
      var10000 = TuplesKt.to(var5, var10001);
   }

   return var10000;
}

public fun String.toLowerCasePreservingASCIIRules(): String {
   val original: java.lang.CharSequence = `$this$toLowerCasePreservingASCIIRules`;
   var `index$iv`: Int = 0;
   val `$this$toLowerCasePreservingASCIIRules_u24lambda_u241`: Int = original.length();

   var var10000: Int;
   while (true) {
      if (`index$iv` >= `$this$toLowerCasePreservingASCIIRules_u24lambda_u241`) {
         var10000 = -1;
         break;
      }

      val var6: Char = original.charAt(`index$iv`);
      if (toLowerCasePreservingASCII(var6) != var6) {
         var10000 = `index$iv`;
         break;
      }

      `index$iv`++;
   }

   if (var10000 == -1) {
      return `$this$toLowerCasePreservingASCIIRules`;
   } else {
      val var9: java.lang.String = `$this$toLowerCasePreservingASCIIRules`;
      val var11: StringBuilder = new StringBuilder(`$this$toLowerCasePreservingASCIIRules`.length());
      val var12: StringBuilder = var11;
      var11.append(`$this$toLowerCasePreservingASCIIRules`, 0, var10000);
      var var14: Int = var10000;
      val var8: Int = StringsKt.getLastIndex(`$this$toLowerCasePreservingASCIIRules`);
      if (var10000 <= var8) {
         while (true) {
            var12.append(toLowerCasePreservingASCII(var9.charAt(var14)));
            if (var14 == var8) {
               break;
            }

            var14++;
         }
      }

      return var11.toString();
   }
}

public fun String.toUpperCasePreservingASCIIRules(): String {
   val original: java.lang.CharSequence = `$this$toUpperCasePreservingASCIIRules`;
   var `index$iv`: Int = 0;
   val `$this$toUpperCasePreservingASCIIRules_u24lambda_u241`: Int = original.length();

   var var10000: Int;
   while (true) {
      if (`index$iv` >= `$this$toUpperCasePreservingASCIIRules_u24lambda_u241`) {
         var10000 = -1;
         break;
      }

      val var6: Char = original.charAt(`index$iv`);
      if (toUpperCasePreservingASCII(var6) != var6) {
         var10000 = `index$iv`;
         break;
      }

      `index$iv`++;
   }

   if (var10000 == -1) {
      return `$this$toUpperCasePreservingASCIIRules`;
   } else {
      val var9: java.lang.String = `$this$toUpperCasePreservingASCIIRules`;
      val var11: StringBuilder = new StringBuilder(`$this$toUpperCasePreservingASCIIRules`.length());
      val var12: StringBuilder = var11;
      var11.append(`$this$toUpperCasePreservingASCIIRules`, 0, var10000);
      var var14: Int = var10000;
      val var8: Int = StringsKt.getLastIndex(`$this$toUpperCasePreservingASCIIRules`);
      if (var10000 <= var8) {
         while (true) {
            var12.append(toUpperCasePreservingASCII(var9.charAt(var14)));
            if (var14 == var8) {
               break;
            }

            var14++;
         }
      }

      return var11.toString();
   }
}

private fun toLowerCasePreservingASCII(ch: Char): Char {
   return if ('A' <= ch && ch < '[') (char)(ch + ' ') else (if (0 <= ch && ch < 128) ch else Character.toLowerCase(ch));
}

private fun toUpperCasePreservingASCII(ch: Char): Char {
   return if ('a' <= ch && ch < '{') (char)(ch - ' ') else (if (0 <= ch && ch < 128) ch else Character.toLowerCase(ch));
}

internal fun String.caseInsensitive(): CaseInsensitiveString {
   return new CaseInsensitiveString(`$this$caseInsensitive`);
}
