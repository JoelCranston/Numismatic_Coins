@file:SourceDebugExtension(["SMAP\nParserDsl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ParserDsl.kt\nio/ktor/http/parsing/ParserDslKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,63:1\n1869#2,2:64\n*S KotlinDebug\n*F\n+ 1 ParserDsl.kt\nio/ktor/http/parsing/ParserDslKt\n*L\n58#1:64,2\n*E\n"])

package io.ktor.http.parsing

import java.util.ArrayList
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

internal fun maybe(grammar: Grammar): Grammar {
   return new MaybeGrammar(grammar);
}

internal fun maybe(value: String): Grammar {
   return new MaybeGrammar(new StringGrammar(value));
}

internal fun maybe(block: (GrammarBuilder) -> Unit): () -> Grammar {
   return ParserDslKt::maybe$lambda$0;
}

internal infix fun String.then(grammar: Grammar): Grammar {
   return then(new StringGrammar(`$this$then`), grammar);
}

internal infix fun Grammar.then(grammar: Grammar): Grammar {
   return new SequenceGrammar(CollectionsKt.listOf(new Grammar[]{`$this$then`, grammar}));
}

internal infix fun Grammar.then(value: String): Grammar {
   return then(`$this$then`, new StringGrammar(value));
}

internal infix fun Grammar.or(grammar: Grammar): Grammar {
   return new OrGrammar(CollectionsKt.listOf(new Grammar[]{`$this$or`, grammar}));
}

internal infix fun Grammar.or(value: String): Grammar {
   return or(`$this$or`, new StringGrammar(value));
}

internal infix fun String.or(grammar: Grammar): Grammar {
   return or(new StringGrammar(`$this$or`), grammar);
}

internal fun many(grammar: Grammar): Grammar {
   return new ManyGrammar(grammar);
}

internal fun atLeastOne(grammar: Grammar): Grammar {
   return new AtLeastOne(grammar);
}

internal fun Grammar.named(name: String): Grammar {
   return new NamedGrammar(name, `$this$named`);
}

internal fun anyOf(value: String): Grammar {
   return new AnyOfGrammar(value);
}

internal infix fun Char.to(other: Char): Grammar {
   return new RangeGrammar(`$this$to`, other);
}

@JvmSynthetic
internal inline fun <reified T : ComplexGrammar> List<Grammar>.flatten(): List<Grammar> {
   val result: java.util.List = new ArrayList();

   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      val it: Grammar = `element$iv` as Grammar;
      Intrinsics.reifiedOperationMarker(3, "T");
      if (it is ComplexGrammar) {
         CollectionsKt.addAll(result, (it as ComplexGrammar).getGrammars());
      } else {
         result.add(it);
      }
   }

   return result;
}

fun `maybe$lambda$0`(`$block`: Function1): Grammar {
   val var1: GrammarBuilder = new GrammarBuilder();
   `$block`.invoke(var1);
   return maybe(var1.build());
}
