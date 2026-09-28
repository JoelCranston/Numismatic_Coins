package com.charleskorn.kaml

import kotlin.enums.EnumEntries

public enum class SequenceStyle {
   Block,
   Flow
   @JvmStatic
   fun getEntries(): EnumEntries<SequenceStyle> {
      return $ENTRIES;
   }
}
