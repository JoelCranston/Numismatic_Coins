package dev.kikugie.commons_samples

import dev.kikugie.commons.text.StringsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.test.AssertionsKt
import org.junit.jupiter.api.Test

@SourceDebugExtension(["SMAP\nText.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Text.kt\ndev/kikugie/commons_samples/Strings\n+ 2 Strings.kt\ndev/kikugie/commons/text/StringsKt\n*L\n1#1,56:1\n48#2:57\n65#2,5:58\n48#2:63\n65#2,5:64\n65#2,5:69\n*S KotlinDebug\n*F\n+ 1 Text.kt\ndev/kikugie/commons_samples/Strings\n*L\n31#1:57\n31#1:58,5\n32#1:63\n32#1:64,5\n33#1:69,5\n*E\n"])
private class Strings {
   @Test
   public fun getOrDefault() {
      AssertionsKt.assertEquals$default('b', StringsKt.getOrDefault$default("abc", 1, '\u0000', 2, null), null, 4, null);
      AssertionsKt.assertEquals$default(' ', StringsKt.getOrDefault$default("abc", 4, '\u0000', 2, null), null, 4, null);
      AssertionsKt.assertEquals$default('?', StringsKt.getOrDefault("abc", 4, '?'), null, 4, null);
   }

   @Test
   public fun countMatching() {
      AssertionsKt.assertEquals$default(0, StringsKt.countMatchingFull("...oops", 'o'), null, 4, null);
      AssertionsKt.assertEquals$default(3, StringsKt.countMatchingFull("...oops", '.'), null, 4, null);
      AssertionsKt.assertEquals$default(5, StringsKt.countMatchingFull("...oops", '.', 'o'), null, 4, null);
      AssertionsKt.assertEquals$default(3, StringsKt.countMatching("...oops", 3, 6, 'o', 'p', 's'), null, 4, null);
   }

   @Test
   public fun countWhile() {
      var var10000: Int = 0;
      var `$this$countWhile$iv`: java.lang.CharSequence = "\n hello!";
      var `end$iv`: java.lang.CharSequence = "\n hello!";
      var `count$iv`: Int = "\n hello!".length();
      var `count$iv$iv`: Int = 0;
      var p0: Int = RangesKt.coerceAtLeast(0, 0);

      for (int var9 = RangesKt.coerceAtMost(end$iv$iv, $this$countWhile$iv.length()); i$iv$iv < var9; i$iv$iv++) {
         if (!Character.isLetter(`end$iv`.charAt(p0))) {
            break;
         }

         `count$iv$iv`++;
      }

      AssertionsKt.assertEquals$default(var10000, `count$iv$iv`, null, 4, null);
      var10000 = 2;
      `$this$countWhile$iv` = "\n hello!";
      `end$iv` = "\n hello!";
      `count$iv` = "\n hello!".length();
      `count$iv$iv` = 0;
      p0 = RangesKt.coerceAtLeast(0, 0);

      for (int var30 = RangesKt.coerceAtMost(end$iv$iv, $this$countWhile$iv.length()); i$iv$iv < var30; i$iv$iv++) {
         if (!CharsKt.isWhitespace(`end$iv`.charAt(p0))) {
            break;
         }

         `count$iv$iv`++;
      }

      AssertionsKt.assertEquals$default(var10000, `count$iv$iv`, null, 4, null);
      var10000 = 5;
      `$this$countWhile$iv` = "\n hello!";
      `count$iv` = 0;
      var var25: Int = RangesKt.coerceAtLeast(2, 0);

      for (int var27 = RangesKt.coerceAtMost(7, $this$countWhile$iv.length()); i$iv < var27; i$iv++) {
         if (!Character.isLetter(`$this$countWhile$iv`.charAt(var25))) {
            break;
         }

         `count$iv`++;
      }

      AssertionsKt.assertEquals$default(var10000, `count$iv`, null, 4, null);
   }

   @Test
   public fun reverseView() {
      AssertionsKt.assertEquals$default('c', StringsKt.reverseView("abc").charAt(0), null, 4, null);
      AssertionsKt.assertEquals$default("cba", StringsKt.reverseView("abc").toString(), null, 4, null);
      AssertionsKt.assertEquals$default("row", StringsKt.reverseView("hello world").subSequence(2, 5), null, 4, null);
   }
}
