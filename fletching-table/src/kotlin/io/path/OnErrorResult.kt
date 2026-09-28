package kotlin.io.path

import kotlin.enums.EnumEntries

@ExperimentalPathApi
@SinceKotlin(version = "1.8")
public enum class OnErrorResult {
   SKIP_SUBTREE,
   TERMINATE
   @JvmStatic
   fun getEntries(): EnumEntries<OnErrorResult> {
      return $ENTRIES;
   }
}
