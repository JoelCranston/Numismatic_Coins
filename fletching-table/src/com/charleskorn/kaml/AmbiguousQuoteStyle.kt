package com.charleskorn.kaml

import kotlin.enums.EnumEntries

public enum class AmbiguousQuoteStyle {
   DoubleQuoted,
   SingleQuoted
   @JvmStatic
   fun getEntries(): EnumEntries<AmbiguousQuoteStyle> {
      return $ENTRIES;
   }
}
