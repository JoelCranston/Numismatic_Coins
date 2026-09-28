package io.ktor.http.parsing

internal class MaybeGrammar(grammar: Grammar) : Grammar(), SimpleGrammar {
   public open val grammar: Grammar

   init {
      this.grammar = grammar;
   }
}
