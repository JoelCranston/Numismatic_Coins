package dev.kikugie.commons.text

public fun Char.isEnglishLetter(): Boolean {
   return 'a' <= `$this$isEnglishLetter` && `$this$isEnglishLetter` < '{' || 'A' <= `$this$isEnglishLetter` && `$this$isEnglishLetter` < '[';
}

public fun Char.isEnglishLetterOrDigit(): Boolean {
   return isEnglishLetter(`$this$isEnglishLetterOrDigit`) || Character.isDigit(`$this$isEnglishLetterOrDigit`);
}
