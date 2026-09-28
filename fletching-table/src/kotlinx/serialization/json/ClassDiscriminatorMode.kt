package kotlinx.serialization.json

import kotlin.enums.EnumEntries

public enum class ClassDiscriminatorMode {
   NONE,
   ALL_JSON_OBJECTS,
   POLYMORPHIC
   @JvmStatic
   fun getEntries(): EnumEntries<ClassDiscriminatorMode> {
      return $ENTRIES;
   }
}
