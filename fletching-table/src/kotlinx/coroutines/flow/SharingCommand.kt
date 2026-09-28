package kotlinx.coroutines.flow

import kotlin.enums.EnumEntries

public enum class SharingCommand {
   START,
   STOP,
   STOP_AND_RESET_REPLAY_CACHE
   @JvmStatic
   fun getEntries(): EnumEntries<SharingCommand> {
      return $ENTRIES;
   }
}
