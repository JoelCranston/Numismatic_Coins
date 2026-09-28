package kotlin.io.path

import kotlin.enums.EnumEntries

@WasExperimental(markerClass = [ExperimentalPathApi::class])
@SinceKotlin(version = "2.1")
public enum class PathWalkOption {
   INCLUDE_DIRECTORIES,
   BREADTH_FIRST,
   FOLLOW_LINKS
   @JvmStatic
   fun getEntries(): EnumEntries<PathWalkOption> {
      return $ENTRIES;
   }
}
