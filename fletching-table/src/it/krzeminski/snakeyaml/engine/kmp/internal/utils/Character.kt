package it.krzeminski.snakeyaml.engine.kmp.internal.utils

internal object Character {
   private const val MIN_CODE_POINT: Int = 0
   private const val MAX_CODE_POINT: Int = 1114111
   private const val MIN_SUPPLEMENTARY_CODE_POINT: Int = 65536
   private const val SURROGATE_DECODE_OFFSET: Int = -56613888
   private const val HIGH_SURROGATE_ENCODE_OFFSET: Char = 'ퟀ'

   internal fun isSupplementaryCodePoint(codePoint: Int): Boolean {
      return 65536 <= codePoint && codePoint < 1114112;
   }

   internal fun charCount(codePoint: Int): Int {
      return if (codePoint <= 65536) 1 else 2;
   }

   internal fun isValidCodePoint(codePoint: Int): Boolean {
      return 0 <= codePoint && codePoint < 1114112;
   }

   internal fun isSurrogatePair(highSurrogate: Char, lowSurrogate: Char): Boolean {
      return java.lang.Character.isHighSurrogate(highSurrogate) && java.lang.Character.isLowSurrogate(lowSurrogate);
   }

   internal fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int {
      return (highSurrogate shl 10) + lowSurrogate + -56613888;
   }

   internal fun toChars(codePoint: Int): CharArray {
      val var10000: CharArray;
      if (this.isBmpCodePoint$snakeyaml_engine_kmp(codePoint)) {
         var10000 = new char[]{(char)codePoint};
      } else {
         val var6: Char = this.highSurrogateOf$snakeyaml_engine_kmp(codePoint);
         val lo: Char = this.lowSurrogateOf$snakeyaml_engine_kmp(codePoint);
         if (!this.isSurrogatePair$snakeyaml_engine_kmp(var6, lo)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
         }

         var10000 = new char[]{var6, lo};
      }

      return var10000;
   }

   internal fun isBmpCodePoint(codePoint: Int): Boolean {
      return codePoint ushr 16 == 0;
   }

   internal fun highSurrogateOf(codePoint: Int): Char {
      return (char)((codePoint ushr '\n') + 55232);
   }

   internal fun lowSurrogateOf(codePoint: Int): Char {
      return (char)((codePoint and 1023) + 56320);
   }
}
