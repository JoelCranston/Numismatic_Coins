package kotlin

import kotlin.enums.EnumEntries

public enum class LazyThreadSafetyMode {
   SYNCHRONIZED,
   PUBLICATION,
   NONE
   @JvmStatic
   fun getEntries(): EnumEntries<LazyThreadSafetyMode> {
      return $ENTRIES;
   }
}
