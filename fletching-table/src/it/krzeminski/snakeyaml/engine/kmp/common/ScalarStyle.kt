package it.krzeminski.snakeyaml.engine.kmp.common

import kotlin.enums.EnumEntries

public enum class ScalarStyle(styleOpt: Char?) {
   DOUBLE_QUOTED('"'),
   SINGLE_QUOTED('\''),
   LITERAL('|'),
   FOLDED('>'),
   JSON_SCALAR_STYLE('J'),
   PLAIN(null)
   private final val styleOpt: Char?

   init {
      this.styleOpt = styleOpt;
   }

   public override fun toString(): String {
      return java.lang.String.valueOf(if (this.styleOpt != null) this.styleOpt else ':');
   }

   @JvmStatic
   fun getEntries(): EnumEntries<ScalarStyle> {
      return $ENTRIES;
   }
}
