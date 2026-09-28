package io.ktor.http.parsing

internal class AtLeastOne(grammar: Grammar) : Grammar(), SimpleGrammar {
   public open val grammar: Grammar

   init {
      this.grammar = grammar;
   }
}
