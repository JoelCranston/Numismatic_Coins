@file:SourceDebugExtension(["SMAP\nHttpHeaderValueParser.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpHeaderValueParser.kt\nio/ktor/http/HttpHeaderValueParserKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,243:1\n1068#2:244\n1563#2:245\n1634#2,3:246\n*S KotlinDebug\n*F\n+ 1 HttpHeaderValueParser.kt\nio/ktor/http/HttpHeaderValueParserKt\n*L\n59#1:244\n115#1:245\n115#1:246,3\n*E\n"])

package io.ktor.http

import io.ktor.http.HttpHeaderValueParserKt.parseAndSortHeader..inlined.sortedByDescending.1
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

public fun parseAndSortHeader(header: String?): List<HeaderValue> {
   return CollectionsKt.sortedWith(parseHeaderValue(header), new 1<>());
}

public fun parseAndSortContentTypeHeader(header: String?): List<HeaderValue> {
   return CollectionsKt.sortedWith(
      parseHeaderValue(header),
      new io.ktor.http.HttpHeaderValueParserKt.parseAndSortContentTypeHeader..inlined.thenByDescending.1<>(
         new io.ktor.http.HttpHeaderValueParserKt.parseAndSortContentTypeHeader..inlined.thenBy.1(
            new io.ktor.http.HttpHeaderValueParserKt.parseAndSortContentTypeHeader..inlined.compareByDescending.1()
         )
      )
   );
}

public fun parseHeaderValue(text: String?): List<HeaderValue> {
   return parseHeaderValue(text, false);
}

public fun parseHeaderValue(text: String?, parametersOnly: Boolean): List<HeaderValue> {
   if (text == null) {
      return CollectionsKt.emptyList();
   } else {
      var position: Int = 0;
      val items: Lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, HttpHeaderValueParserKt::parseHeaderValue$lambda$0);

      while (position <= StringsKt.getLastIndex(text)) {
         position = parseHeaderValueItem(text, position, items, parametersOnly);
      }

      return valueOrEmpty(items);
   }
}

public fun Iterable<Pair<String, String>>.toHeaderParamsList(): List<HeaderValueParam> {
   val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$toHeaderParamsList`, 10));

   for (Object item$iv$iv : $this$toHeaderParamsList) {
      `destination$iv$iv`.add(
         new HeaderValueParam((`item$iv$iv` as Pair).getFirst() as java.lang.String, (`item$iv$iv` as Pair).getSecond() as java.lang.String)
      );
   }

   return `destination$iv$iv` as MutableList<HeaderValueParam>;
}

private fun <T> Lazy<List<Any>>.valueOrEmpty(): List<Any> {
   return if (`$this$valueOrEmpty`.isInitialized()) `$this$valueOrEmpty`.getValue() as java.util.List else CollectionsKt.emptyList();
}

private fun String.subtrim(start: Int, end: Int): String {
   val var10000: java.lang.String = `$this$subtrim`.substring(start, end);
   return StringsKt.trim(var10000).toString();
}

private fun parseHeaderValueItem(text: String, start: Int, items: Lazy<ArrayList<HeaderValue>>, parametersOnly: Boolean): Int {
   var position: Int = start;
   val parameters: Lazy = LazyKt.lazy(LazyThreadSafetyMode.NONE, HttpHeaderValueParserKt::parseHeaderValueItem$lambda$0);
   var valueEnd: Int = if (parametersOnly) start else null;

   while (position <= StringsKt.getLastIndex(text)) {
      switch (text.charAt(position)) {
         case ',':
            (items.getValue() as ArrayList).add(new HeaderValue(subtrim(text, start, valueEnd ?: position), valueOrEmpty(parameters)));
            return position + 1;
         case ';':
            if (valueEnd == null) {
               valueEnd = position;
            }

            position = parseHeaderValueParameter(text, position + 1, parameters);
            break;
         default:
            position = if (parametersOnly) parseHeaderValueParameter(text, position, parameters) else position + 1;
      }
   }

   (items.getValue() as ArrayList).add(new HeaderValue(subtrim(text, start, valueEnd ?: position), valueOrEmpty(parameters)));
   return position;
}

private fun parseHeaderValueParameter(text: String, start: Int, parameters: Lazy<ArrayList<HeaderValueParam>>): Int {
   var position: Int;
   for (position = start; position <= StringsKt.getLastIndex(text); position++) {
      switch (text.charAt(position)) {
         case ',':
         case ';':
            parseHeaderValueParameter$addParam(parameters, text, start, position, "");
            return position;
         case '=':
            val var4: Pair = parseHeaderValueParameterValue(text, position + 1);
            val paramEnd: Int = (var4.component1() as java.lang.Number).intValue();
            parseHeaderValueParameter$addParam(parameters, text, start, position, var4.component2() as java.lang.String);
            return paramEnd;
         default:
      }
   }

   parseHeaderValueParameter$addParam(parameters, text, start, position, "");
   return position;
}

private fun parseHeaderValueParameterValue(value: String, start: Int): Pair<Int, String> {
   if (value.length() == start) {
      return TuplesKt.to(start, "");
   } else {
      var position: Int = start;
      if (value.charAt(start) == '"') {
         return parseHeaderValueParameterValueQuoted(value, start + 1);
      } else {
         while (position <= StringsKt.getLastIndex(value)) {
            switch (value.charAt(position)) {
               case ',':
               case ';':
                  return TuplesKt.to(position, subtrim(value, start, position));
               default:
                  position++;
            }
         }

         return TuplesKt.to(position, subtrim(value, start, position));
      }
   }
}

private fun parseHeaderValueParameterValueQuoted(value: String, start: Int): Pair<Int, String> {
   var position: Int = start;
   val builder: StringBuilder = new StringBuilder();

   while (position <= StringsKt.getLastIndex(value)) {
      val currentChar: Char = value.charAt(position);
      if (currentChar == '"' && nextIsDelimiterOrEnd(value, position)) {
         return TuplesKt.to(position + 1, builder.toString());
      }

      if (currentChar == '\\' && position < StringsKt.getLastIndex(value) - 2) {
         builder.append(value.charAt(position + 1));
         position += 2;
      } else {
         builder.append(currentChar);
         position++;
      }
   }

   val var10000: Int = position;
   val var10001: java.lang.String = builder.toString();
   return TuplesKt.to(var10000, "${34}$var10001");
}

private fun String.nextIsDelimiterOrEnd(start: Int): Boolean {
   var position: Int = start + 1;

   while (position < $this$nextIsDelimiterOrEnd.length() && $this$nextIsDelimiterOrEnd.charAt(position) == ' ') {
      position++;
   }

   return position == `$this$nextIsDelimiterOrEnd`.length()
      || `$this$nextIsDelimiterOrEnd`.charAt(position) == ';'
      || `$this$nextIsDelimiterOrEnd`.charAt(position) == ',';
}

fun `parseHeaderValue$lambda$0`(): ArrayList {
   return new ArrayList();
}

fun `parseHeaderValueItem$lambda$0`(): ArrayList {
   return new ArrayList();
}

fun `parseHeaderValueParameter$addParam`(
   `$parameters`: Lazy<? extends ArrayList<HeaderValueParam>>, text: java.lang.String, start: Int, end: Int, value: java.lang.String
) {
   val name: java.lang.String = subtrim(text, start, end);
   if (name.length() != 0) {
      (`$parameters`.getValue() as ArrayList).add(new HeaderValueParam(name, value));
   }
}
