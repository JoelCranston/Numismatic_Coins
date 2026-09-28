package kotlin.io

import kotlin.enums.EnumEntries

public enum class OnErrorAction {
   SKIP,
   TERMINATE
   @JvmStatic
   fun getEntries(): EnumEntries<OnErrorAction> {
      return $ENTRIES;
   }
}
