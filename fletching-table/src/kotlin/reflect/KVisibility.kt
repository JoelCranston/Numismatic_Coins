package kotlin.reflect

import kotlin.enums.EnumEntries

@SinceKotlin(version = "1.1")
public enum class KVisibility {
   PUBLIC,
   PROTECTED,
   INTERNAL,
   PRIVATE
   @JvmStatic
   fun getEntries(): EnumEntries<KVisibility> {
      return $ENTRIES;
   }
}
