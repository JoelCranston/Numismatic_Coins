package kotlinx.coroutines.channels

import kotlin.enums.EnumEntries
import kotlinx.coroutines.ObsoleteCoroutinesApi

@ObsoleteCoroutinesApi
public enum class TickerMode {
   FIXED_PERIOD,
   FIXED_DELAY
   @JvmStatic
   fun getEntries(): EnumEntries<TickerMode> {
      return $ENTRIES;
   }
}
