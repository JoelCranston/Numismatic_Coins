package it.krzeminski.snakeyaml.engine.kmp.common

import kotlin.enums.EnumEntries

public enum class FlowStyle {
   FLOW,
   BLOCK,
   AUTO
   @JvmStatic
   fun getEntries(): EnumEntries<FlowStyle> {
      return $ENTRIES;
   }
}
