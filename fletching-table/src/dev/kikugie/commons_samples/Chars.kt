package dev.kikugie.commons_samples

import dev.kikugie.commons.text.CharsKt
import kotlin.test.AssertionsKt
import org.junit.jupiter.api.Test

private class Chars {
   @Test
   public fun isEnglishLetter() {
      AssertionsKt.assertTrue$default(CharsKt.isEnglishLetter('a'), null, 2, null);
      AssertionsKt.assertFalse$default(CharsKt.isEnglishLetter('1'), null, 2, null);
      AssertionsKt.assertFalse$default(CharsKt.isEnglishLetter('ッ'), null, 2, null);
   }

   public fun isEnglishLetterOrDigit() {
      AssertionsKt.assertTrue$default(CharsKt.isEnglishLetterOrDigit('1'), null, 2, null);
      AssertionsKt.assertFalse$default(CharsKt.isEnglishLetterOrDigit(' '), null, 2, null);
   }
}
