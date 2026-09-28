package kotlin.internal

import kotlin.enums.EnumEntries

@SinceKotlin(version = "1.2")
internal enum class RequireKotlinVersionKind {
   LANGUAGE_VERSION,
   COMPILER_VERSION,
   API_VERSION
   @JvmStatic
   fun getEntries(): EnumEntries<RequireKotlinVersionKind> {
      return $ENTRIES;
   }
}
