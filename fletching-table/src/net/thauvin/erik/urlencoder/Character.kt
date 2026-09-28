package net.thauvin.erik.urlencoder

internal object Character {
   private const val HIGH_SURROGATE_ENCODE_OFFSET: Char = 'ퟀ'
   private const val MAX_CODE_POINT: Int = 1114111
   private const val MIN_SUPPLEMENTARY_CODE_POINT: Int = 65536
   private const val SURROGATE_DECODE_OFFSET: Int = -56613888

   internal fun isSupplementaryCodePoint(codePoint: Int): Boolean {
      return 65536 <= codePoint && codePoint < 1114112;
   }

   internal fun toCodePoint(highSurrogate: Char, lowSurrogate: Char): Int {
      return (highSurrogate shl 10) + lowSurrogate + -56613888;
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
