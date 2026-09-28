package kotlin.io

import kotlin.enums.EnumEntries

public enum class FileWalkDirection {
   TOP_DOWN,
   BOTTOM_UP
   @JvmStatic
   fun getEntries(): EnumEntries<FileWalkDirection> {
      return $ENTRIES;
   }
}
