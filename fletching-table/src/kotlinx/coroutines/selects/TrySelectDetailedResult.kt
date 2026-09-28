package kotlinx.coroutines.selects

import kotlin.enums.EnumEntries

internal enum class TrySelectDetailedResult {
   SUCCESSFUL,
   REREGISTER,
   CANCELLED,
   ALREADY_SELECTED
   @JvmStatic
   fun getEntries(): EnumEntries<TrySelectDetailedResult> {
      return $ENTRIES;
   }
}
