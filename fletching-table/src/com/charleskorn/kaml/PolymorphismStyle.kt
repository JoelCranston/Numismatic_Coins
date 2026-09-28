package com.charleskorn.kaml

import kotlin.enums.EnumEntries

public enum class PolymorphismStyle {
   Tag,
   Property,
   None
   @JvmStatic
   fun getEntries(): EnumEntries<PolymorphismStyle> {
      return $ENTRIES;
   }
}
