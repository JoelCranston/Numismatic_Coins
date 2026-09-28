package io.ktor.http.parsing

internal class NamedGrammar(name: String, grammar: Grammar) : Grammar() {
   public final val name: String
   public final val grammar: Grammar

   init {
      this.name = name;
      this.grammar = grammar;
   }
}
