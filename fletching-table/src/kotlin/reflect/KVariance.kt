package kotlin.reflect

import kotlin.enums.EnumEntries

@SinceKotlin(version = "1.1")
public enum class KVariance {
   INVARIANT,
   IN,
   OUT
   @JvmStatic
   fun getEntries(): EnumEntries<KVariance> {
      return $ENTRIES;
   }
}
