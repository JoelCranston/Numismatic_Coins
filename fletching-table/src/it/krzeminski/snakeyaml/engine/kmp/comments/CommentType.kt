package it.krzeminski.snakeyaml.engine.kmp.comments

import kotlin.enums.EnumEntries

public enum class CommentType {
   BLANK_LINE,
   BLOCK,
   IN_LINE
   @JvmStatic
   fun getEntries(): EnumEntries<CommentType> {
      return $ENTRIES;
   }
}
