package it.krzeminski.snakeyaml.engine.kmp.common

import kotlin.enums.EnumEntries

public enum class NonPrintableStyle {
   BINARY,
   ESCAPE
   @JvmStatic
   fun getEntries(): EnumEntries<NonPrintableStyle> {
      return $ENTRIES;
   }
}
