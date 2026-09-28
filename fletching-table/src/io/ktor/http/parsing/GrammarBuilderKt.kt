package io.ktor.http.parsing

internal fun grammar(block: (GrammarBuilder) -> Unit): Grammar {
   val var1: GrammarBuilder = new GrammarBuilder();
   block.invoke(var1);
   return var1.build();
}
