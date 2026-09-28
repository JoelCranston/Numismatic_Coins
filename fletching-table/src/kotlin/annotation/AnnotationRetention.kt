package kotlin.annotation

import kotlin.enums.EnumEntries

public enum class AnnotationRetention {
   SOURCE,
   BINARY,
   RUNTIME
   @JvmStatic
   fun getEntries(): EnumEntries<AnnotationRetention> {
      return $ENTRIES;
   }
}
