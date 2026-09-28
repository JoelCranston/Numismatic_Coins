@file:SourceDebugExtension(["SMAP\nDebug.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Debug.kt\nio/ktor/http/parsing/DebugKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,41:1\n1869#2,2:42\n1869#2,2:44\n*S KotlinDebug\n*F\n+ 1 Debug.kt\nio/ktor/http/parsing/DebugKt\n*L\n16#1:42,2\n20#1:44,2\n*E\n"])

package io.ktor.http.parsing

import kotlin.jvm.internal.SourceDebugExtension

internal fun Grammar.printDebug(offset: Int = 0) {
   if (`$this$printDebug` is StringGrammar) {
      printlnWithOffset(offset, "STRING[${Regex.Companion.escape((`$this$printDebug` as StringGrammar).getValue())}]");
   } else if (`$this$printDebug` is RawGrammar) {
      printlnWithOffset(offset, "STRING[${(`$this$printDebug` as RawGrammar).getValue()}]");
   } else if (`$this$printDebug` is NamedGrammar) {
      printlnWithOffset(offset, "NAMED[${(`$this$printDebug` as NamedGrammar).getName()}]");
      printDebug((`$this$printDebug` as NamedGrammar).getGrammar(), offset + 2);
   } else if (`$this$printDebug` is SequenceGrammar) {
      printlnWithOffset(offset, "SEQUENCE");

      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         printDebug(`element$iv` as Grammar, offset + 2);
      }
   } else if (`$this$printDebug` is OrGrammar) {
      printlnWithOffset(offset, "OR");

      val var9: java.lang.Iterable;
      for (Object element$iv : var9) {
         printDebug(var12 as Grammar, offset + 2);
      }
   } else if (`$this$printDebug` is MaybeGrammar) {
      printlnWithOffset(offset, "MAYBE");
      printDebug((`$this$printDebug` as MaybeGrammar).getGrammar(), offset + 2);
   } else if (`$this$printDebug` is ManyGrammar) {
      printlnWithOffset(offset, "MANY");
      printDebug((`$this$printDebug` as ManyGrammar).getGrammar(), offset + 2);
   } else if (`$this$printDebug` is AtLeastOne) {
      printlnWithOffset(offset, "MANY_NOT_EMPTY");
      printDebug((`$this$printDebug` as AtLeastOne).getGrammar(), offset + 2);
   } else if (`$this$printDebug` is AnyOfGrammar) {
      printlnWithOffset(offset, "ANY_OF[${Regex.Companion.escape((`$this$printDebug` as AnyOfGrammar).getValue())}]");
   } else {
      if (`$this$printDebug` !is RangeGrammar) {
         throw new NoWhenBranchMatchedException();
      }

      printlnWithOffset(offset, "RANGE[${(`$this$printDebug` as RangeGrammar).getFrom()}-${(`$this$printDebug` as RangeGrammar).getTo()}]");
   }
}

@JvmSynthetic
fun `printDebug$default`(var0: Grammar, var1: Int, var2: Int, var3: Any) {
   if ((var2 and 1) != 0) {
      var1 = 0;
   }

   printDebug(var0, var1);
}

private fun printlnWithOffset(offset: Int, node: Any) {
   System.out.println("${StringsKt.repeat(" ", offset)}${offset / 2}: $node");
}
