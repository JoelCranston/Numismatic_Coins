package io.ktor.http.parsing

internal class RangeGrammar(from: Char, to: Char) : Grammar() {
   public final val from: Char
   public final val to: Char

   init {
      this.from = from;
      this.to = to;
   }
}
