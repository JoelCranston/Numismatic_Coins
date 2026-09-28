package io.ktor.http.parsing.regex

import io.ktor.http.parsing.ParseResult
import io.ktor.http.parsing.Parser
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nRegexParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RegexParser.kt\nio/ktor/http/parsing/regex/RegexParser\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,33:1\n216#2:34\n217#2:38\n1869#3:35\n1870#3:37\n1#4:36\n*S KotlinDebug\n*F\n+ 1 RegexParser.kt\nio/ktor/http/parsing/regex/RegexParser\n*L\n20#1:34\n20#1:38\n21#1:35\n21#1:37\n*E\n"])
internal class RegexParser(expression: Regex, indexes: Map<String, List<Int>>) : Parser {
   private final val expression: Regex
   private final val indexes: Map<String, List<Int>>

   init {
      this.expression = expression;
      this.indexes = indexes;
   }

   public override fun parse(input: String): ParseResult? {
      val match: MatchResult = this.expression.matchEntire(input);
      if (match != null && match.getValue().length() == input.length()) {
         val mapping: java.util.Map = new LinkedHashMap();

         for (Entry element$iv : this.indexes.entrySet()) {
            val key: java.lang.String = `element$iv`.getKey() as java.lang.String;

            val `$this$forEach$iv`: java.lang.Iterable;
            for (Object element$ivx : $this$forEach$iv) {
               val index: Int = (`element$ivx` as java.lang.Number).intValue();
               val result: java.util.List = new ArrayList();
               val var10000: MatchGroup = match.getGroups().get(index);
               if (var10000 != null) {
                  result.add(var10000.getValue());
               }

               if (!result.isEmpty()) {
                  mapping.put(key, result);
               }
            }
         }

         return new ParseResult(mapping);
      } else {
         return null;
      }
   }

   public override fun match(input: String): Boolean {
      return this.expression.matches(input);
   }
}
