package io.ktor.http.parsing

import java.util.ArrayList

internal class GrammarBuilder {
   private final val grammars: MutableList<Grammar> = (new ArrayList()) as java.util.List

   public infix fun then(grammar: Grammar): GrammarBuilder {
      this.grammars.add(grammar);
      return this;
   }

   public infix fun then(value: String): GrammarBuilder {
      this.grammars.add(new StringGrammar(value));
      return this;
   }

   public operator fun (() -> Grammar).unaryPlus() {
      this.grammars.add((Grammar)`$this$unaryPlus`.invoke());
   }

   public operator fun Grammar.unaryPlus() {
      this.grammars.add(`$this$unaryPlus`);
   }

   public operator fun String.unaryPlus() {
      this.grammars.add(new StringGrammar(`$this$unaryPlus`));
   }

   public fun build(): Grammar {
      return if (this.grammars.size() == 1) CollectionsKt.first(this.grammars) else new SequenceGrammar(this.grammars);
   }
}
