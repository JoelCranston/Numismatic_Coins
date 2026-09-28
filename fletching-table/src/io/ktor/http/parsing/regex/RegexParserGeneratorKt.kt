@file:SourceDebugExtension(["SMAP\nRegexParserGenerator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RegexParserGenerator.kt\nio/ktor/http/parsing/regex/RegexParserGeneratorKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,72:1\n1878#2,3:73\n*S KotlinDebug\n*F\n+ 1 RegexParserGenerator.kt\nio/ktor/http/parsing/regex/RegexParserGeneratorKt\n*L\n41#1:73,3\n*E\n"])

package io.ktor.http.parsing.regex

import io.ktor.http.parsing.AnyOfGrammar
import io.ktor.http.parsing.AtLeastOne
import io.ktor.http.parsing.ComplexGrammar
import io.ktor.http.parsing.Grammar
import io.ktor.http.parsing.ManyGrammar
import io.ktor.http.parsing.MaybeGrammar
import io.ktor.http.parsing.NamedGrammar
import io.ktor.http.parsing.OrGrammar
import io.ktor.http.parsing.Parser
import io.ktor.http.parsing.RangeGrammar
import io.ktor.http.parsing.RawGrammar
import io.ktor.http.parsing.SimpleGrammar
import io.ktor.http.parsing.StringGrammar
import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

internal fun Grammar.buildRegexParser(): Parser {
   val groups: java.util.Map = new LinkedHashMap();
   return new RegexParser(new Regex(toRegex$default(`$this$buildRegexParser`, groups, 0, false, 6, null).getRegex()), groups);
}

private fun Grammar.toRegex(groups: MutableMap<String, MutableList<Int>>, offset: Int = 1, shouldGroup: Boolean = false): GrammarRegex {
   val var10000: GrammarRegex;
   if (`$this$toRegex` is StringGrammar) {
      var10000 = new GrammarRegex(Regex.Companion.escape((`$this$toRegex` as StringGrammar).getValue()), 0, false, 6, null);
   } else if (`$this$toRegex` is RawGrammar) {
      var10000 = new GrammarRegex((`$this$toRegex` as RawGrammar).getValue(), 0, false, 6, null);
   } else if (`$this$toRegex` is NamedGrammar) {
      val operator: GrammarRegex = toRegex$default((`$this$toRegex` as NamedGrammar).getGrammar(), groups, offset + 1, false, 4, null);
      add(groups, (`$this$toRegex` as NamedGrammar).getName(), offset);
      var10000 = new GrammarRegex(operator.getRegex(), operator.getGroupsCount(), true);
   } else if (`$this$toRegex` is ComplexGrammar) {
      val var17: StringBuilder = new StringBuilder();
      var var19: Int = if (shouldGroup) offset + 1 else offset;
      val groupsCount: java.lang.Iterable = (`$this$toRegex` as ComplexGrammar).getGrammars();
      var `index$iv`: Int = 0;

      for (Object item$iv : $this$forEachIndexed$iv) {
         val var12: Int = `index$iv`++;
         if (var12 < 0) {
            CollectionsKt.throwIndexOverflow();
         }

         val current: GrammarRegex = toRegex(`item$iv` as Grammar, groups, var19, true);
         if (var12 != 0 && `$this$toRegex` is OrGrammar) {
            var17.append("|");
         }

         var17.append(current.getRegex());
         var19 += current.getGroupsCount();
      }

      val var21: Int = if (shouldGroup) var19 - offset - 1 else var19 - offset;
      val var10002: java.lang.String = var17.toString();
      var10000 = new GrammarRegex(var10002, var21, shouldGroup);
   } else if (`$this$toRegex` is SimpleGrammar) {
      val var23: Byte;
      if (`$this$toRegex` is MaybeGrammar) {
         var23 = 63;
      } else if (`$this$toRegex` is ManyGrammar) {
         var23 = 42;
      } else {
         if (`$this$toRegex` !is AtLeastOne) {
            throw new IllegalStateException(("Unsupported simple grammar element: $`$this$toRegex`").toString());
         }

         var23 = 43;
      }

      val var20: GrammarRegex = toRegex((`$this$toRegex` as SimpleGrammar).getGrammar(), groups, offset, true);
      var10000 = new GrammarRegex("${var20.getRegex()}$var23", var20.getGroupsCount(), false, 4, null);
   } else if (`$this$toRegex` is AnyOfGrammar) {
      var10000 = new GrammarRegex("[${Regex.Companion.escape((`$this$toRegex` as AnyOfGrammar).getValue())}]", 0, false, 6, null);
   } else {
      if (`$this$toRegex` !is RangeGrammar) {
         throw new IllegalStateException(("Unsupported grammar element: $`$this$toRegex`").toString());
      }

      var10000 = new GrammarRegex("[${(`$this$toRegex` as RangeGrammar).getFrom()}-${(`$this$toRegex` as RangeGrammar).getTo()}]", 0, false, 6, null);
   }

   return var10000;
}

@JvmSynthetic
fun `toRegex$default`(var0: Grammar, var1: java.util.Map, var2: Int, var3: Boolean, var4: Int, var5: Any): GrammarRegex {
   if ((var4 and 2) != 0) {
      var2 = 1;
   }

   if ((var4 and 4) != 0) {
      var3 = false;
   }

   return toRegex(var0, var1, var2, var3);
}

private fun MutableMap<String, MutableList<Int>>.add(key: String, value: Int) {
   if (!`$this$add`.containsKey(key)) {
      `$this$add`.put(key, new ArrayList());
   }

   val var10000: Any = `$this$add`.get(key);
   (var10000 as java.util.Collection).add(value);
}
