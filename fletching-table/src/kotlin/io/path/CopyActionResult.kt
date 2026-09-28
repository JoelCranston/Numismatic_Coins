package kotlin.io.path

import kotlin.enums.EnumEntries

@ExperimentalPathApi
@SinceKotlin(version = "1.8")
public enum class CopyActionResult {
   CONTINUE,
   SKIP_SUBTREE,
   TERMINATE
   @JvmStatic
   fun getEntries(): EnumEntries<CopyActionResult> {
      return $ENTRIES;
   }
}
