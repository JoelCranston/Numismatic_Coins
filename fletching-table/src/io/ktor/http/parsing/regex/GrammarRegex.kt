package io.ktor.http.parsing.regex

private class GrammarRegex(regexRaw: String, groupsCountRaw: Int = 0, group: Boolean = false) {
   public final val regex: String
   public final val groupsCount: Int

   init {
      this.regex = if (group) "($regexRaw)" else regexRaw;
      this.groupsCount = if (group) groupsCountRaw + 1 else groupsCountRaw;
   }
}
