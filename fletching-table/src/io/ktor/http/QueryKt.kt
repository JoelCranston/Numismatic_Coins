@file:SourceDebugExtension(["SMAP\nQuery.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Query.kt\nio/ktor/http/QueryKt\n+ 2 Parameters.kt\nio/ktor/http/Parameters$Companion\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,124:1\n31#2:125\n2746#3,3:126\n*S KotlinDebug\n*F\n+ 1 Query.kt\nio/ktor/http/QueryKt\n*L\n18#1:125\n106#1:126,3\n*E\n"])

package io.ktor.http

import io.ktor.http.QueryKt.withEmptyStringForValuelessKeys.2.1
import io.ktor.utils.io.InternalAPI
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension

public fun parseQueryString(query: String, startIndex: Int = 0, limit: Int = 1000, decode: Boolean = true): Parameters {
   val var10000: Parameters;
   if (startIndex > StringsKt.getLastIndex(query)) {
      var10000 = Parameters.Companion.getEmpty();
   } else {
      val `this_$iv`: Parameters.Companion = Parameters.Companion;
      val var6: ParametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, null);
      parse(var6, query, startIndex, limit, decode);
      var10000 = var6.build();
   }

   return var10000;
}

@JvmSynthetic
fun `parseQueryString$default`(var0: java.lang.String, var1: Int, var2: Int, var3: Boolean, var4: Int, var5: Any): Parameters {
   if ((var4 and 2) != 0) {
      var1 = 0;
   }

   if ((var4 and 4) != 0) {
      var2 = 1000;
   }

   if ((var4 and 8) != 0) {
      var3 = true;
   }

   return parseQueryString(var0, var1, var2, var3);
}

private fun ParametersBuilder.parse(query: String, startIndex: Int, limit: Int, decode: Boolean) {
   var count: Int = 0;
   var nameIndex: Int = startIndex;
   var equalIndex: Int = -1;
   var index: Int = startIndex;
   val var9: Int = StringsKt.getLastIndex(query);
   if (startIndex <= var9) {
      while (true) {
         if (count == limit) {
            return;
         }

         switch (query.charAt(index)) {
            case '&':
               appendParam(`$this$parse`, query, nameIndex, equalIndex, index, decode);
               nameIndex = index + 1;
               equalIndex = -1;
               count++;
               break;
            case '=':
               if (equalIndex == -1) {
                  equalIndex = index;
               }
            default:
         }

         if (index == var9) {
            break;
         }

         index++;
      }
   }

   if (count != limit) {
      appendParam(`$this$parse`, query, nameIndex, equalIndex, query.length(), decode);
   }
}

private fun ParametersBuilder.appendParam(query: String, nameIndex: Int, equalIndex: Int, endIndex: Int, decode: Boolean) {
   if (equalIndex == -1) {
      val var12: Int = trimStart(nameIndex, endIndex, query);
      val var13: Int = trimEnd(var12, endIndex, query);
      if (var13 > var12) {
         val var16: java.lang.String;
         if (decode) {
            var16 = CodecsKt.decodeURLQueryComponent$default(query, var12, var13, false, null, 12, null);
         } else {
            var16 = query.substring(var12, var13);
         }

         `$this$appendParam`.appendAll(var16, CollectionsKt.emptyList());
      }
   } else {
      val spaceNameIndex: Int = trimStart(nameIndex, equalIndex, query);
      val spaceEqualIndex: Int = trimEnd(spaceNameIndex, equalIndex, query);
      if (spaceEqualIndex > spaceNameIndex) {
         var var10000: java.lang.String;
         if (decode) {
            var10000 = CodecsKt.decodeURLQueryComponent$default(query, spaceNameIndex, spaceEqualIndex, false, null, 12, null);
         } else {
            var10000 = query.substring(spaceNameIndex, spaceEqualIndex);
         }

         val spaceValueIndex: Int = trimStart(equalIndex + 1, endIndex, query);
         val spaceEndIndex: Int = trimEnd(spaceValueIndex, endIndex, query);
         if (decode) {
            var10000 = CodecsKt.decodeURLQueryComponent$default(query, spaceValueIndex, spaceEndIndex, true, null, 8, null);
         } else {
            var10000 = query.substring(spaceValueIndex, spaceEndIndex);
         }

         `$this$appendParam`.append(var10000, var10000);
      }
   }
}

private fun trimEnd(start: Int, end: Int, text: CharSequence): Int {
   var spaceIndex: Int = end;

   while (spaceIndex > start && CharsKt.isWhitespace(text.charAt(spaceIndex - 1))) {
      spaceIndex--;
   }

   return spaceIndex;
}

private fun trimStart(start: Int, end: Int, query: CharSequence): Int {
   var spaceIndex: Int = start;

   while (spaceIndex < end && CharsKt.isWhitespace(query.charAt(spaceIndex))) {
      spaceIndex++;
   }

   return spaceIndex;
}

@InternalAPI
public fun Parameters.withEmptyStringForValuelessKeys(): Parameters {
   val `$this$none$iv`: java.lang.Iterable = `$this$withEmptyStringForValuelessKeys`.entries();
   var var10000: Boolean;
   if (`$this$none$iv` is java.util.Collection && (`$this$none$iv` as java.util.Collection).isEmpty()) {
      var10000 = true;
   } else {
      val var3: java.util.Iterator = `$this$none$iv`.iterator();

      while (true) {
         if (!var3.hasNext()) {
            var10000 = true;
            break;
         }

         if (((var3.next() as Entry).getValue() as java.util.List).isEmpty()) {
            var10000 = false;
            break;
         }
      }
   }

   return if (var10000) `$this$withEmptyStringForValuelessKeys` else new 1(`$this$withEmptyStringForValuelessKeys`);
}
