package kotlinx.coroutines.channels

import kotlin.enums.EnumEntries

public enum class BufferOverflow {
   SUSPEND,
   DROP_OLDEST,
   DROP_LATEST
   @JvmStatic
   fun getEntries(): EnumEntries<BufferOverflow> {
      return $ENTRIES;
   }
}
