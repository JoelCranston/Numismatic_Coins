package it.krzeminski.snakeyaml.engine.kmp.scanner

import it.krzeminski.snakeyaml.engine.kmp.exceptions.Mark

private class BreakIntentHolder(breaks: String, maxIndent: Int, endMark: Mark?) {
   public final val breaks: String
   public final val maxIndent: Int
   public final val endMark: Mark?

   init {
      this.breaks = breaks;
      this.maxIndent = maxIndent;
      this.endMark = endMark;
   }
}
