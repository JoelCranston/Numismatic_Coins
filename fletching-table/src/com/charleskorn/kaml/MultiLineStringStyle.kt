package com.charleskorn.kaml

import kotlin.enums.EnumEntries

public enum class MultiLineStringStyle {
   Literal,
   Folded,
   DoubleQuoted,
   SingleQuoted,
   Plain
   @JvmStatic
   fun getEntries(): EnumEntries<MultiLineStringStyle> {
      return $ENTRIES;
   }
}
