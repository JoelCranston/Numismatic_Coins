package kotlin

import kotlin.enums.EnumEntries

public enum class DeprecationLevel {
   WARNING,
   ERROR,
   HIDDEN
   @JvmStatic
   fun getEntries(): EnumEntries<DeprecationLevel> {
      return $ENTRIES;
   }
}
