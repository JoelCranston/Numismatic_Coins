package io.ktor.http.parsing

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nParserDsl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ParserDsl.kt\nio/ktor/http/parsing/SequenceGrammar\n+ 2 ParserDsl.kt\nio/ktor/http/parsing/ParserDslKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,63:1\n57#2,2:64\n59#2,2:67\n61#2:70\n1869#3:66\n1870#3:69\n*S KotlinDebug\n*F\n+ 1 ParserDsl.kt\nio/ktor/http/parsing/SequenceGrammar\n*L\n29#1:64,2\n29#1:67,2\n29#1:70\n29#1:66\n29#1:69\n*E\n"])
internal class SequenceGrammar(sourceGrammars: List<Grammar>) : Grammar(), ComplexGrammar {
   public open val grammars: List<Grammar>

   init {
      val `result$iv`: java.util.List = new ArrayList();

      val `$this$forEach$iv$iv`: java.lang.Iterable;
      for (Object element$iv$iv : $this$forEach$iv$iv) {
         val `it$iv`: Grammar = `element$iv$iv` as Grammar;
         if (`element$iv$iv` as Grammar is SequenceGrammar) {
            CollectionsKt.addAll(`result$iv`, (`it$iv` as ComplexGrammar).getGrammars());
         } else {
            `result$iv`.add(`it$iv`);
         }
      }

      this.grammars = `result$iv`;
   }
}
