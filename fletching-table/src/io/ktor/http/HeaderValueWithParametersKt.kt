@file:SourceDebugExtension(["SMAP\nHeaderValueWithParameters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeaderValueWithParameters.kt\nio/ktor/http/HeaderValueWithParametersKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,165:1\n1#2:166\n*E\n"])

package io.ktor.http

import io.ktor.util.StringValuesBuilder
import kotlin.jvm.internal.SourceDebugExtension

private final val HeaderFieldValueSeparators: Set<Char> =
   SetsKt.setOf(new Character[]{'(', ')', '<', '>', '@', ',', ';', ':', '\\', '"', '/', '[', ']', '?', '=', '{', '}', ' ', '\t', '\n', '\r'})

public fun StringValuesBuilder.append(name: String, value: HeaderValueWithParameters) {
   `$this$append`.append(name, value.toString());
}

public fun String.escapeIfNeeded(): String {
   return if (needQuotes(`$this$escapeIfNeeded`)) quote(`$this$escapeIfNeeded`) else `$this$escapeIfNeeded`;
}

private inline fun String.escapeIfNeededTo(out: StringBuilder) {
   if (access$needQuotes(`$this$escapeIfNeededTo`)) {
      out.append(quote(`$this$escapeIfNeededTo`));
   } else {
      out.append(`$this$escapeIfNeededTo`);
   }
}

private fun String.needQuotes(): Boolean {
   if (`$this$needQuotes`.length() == 0) {
      return true;
   } else if (isQuoted(`$this$needQuotes`)) {
      return false;
   } else {
      var var1: Int = 0;

      for (int var2 = $this$needQuotes.length(); var1 < var2; var1++) {
         if (HeaderFieldValueSeparators.contains(`$this$needQuotes`.charAt(var1))) {
            return true;
         }
      }

      return false;
   }
}

private fun String.isQuoted(): Boolean {
   if (`$this$isQuoted`.length() < 2) {
      return false;
   } else if (StringsKt.first(`$this$isQuoted`) == '"' && StringsKt.last(`$this$isQuoted`) == '"') {
      var startIndex: Int = 1;

      val index: Int;
      do {
         index = StringsKt.indexOf$default(`$this$isQuoted`, '"', startIndex, false, 4, null);
         if (index == StringsKt.getLastIndex(`$this$isQuoted`)) {
            break;
         }

         var slashesCount: Int = 0;

         for (int slashIndex = index - 1; $this$isQuoted.charAt(slashIndex) == '\\'; slashIndex--) {
            slashesCount++;
         }

         if (slashesCount % 2 == 0) {
            return false;
         }

         startIndex = index + 1;
      } while (index + 1 < $this$isQuoted.length());

      return true;
   } else {
      return false;
   }
}

public fun String.quote(): String {
   val var1: StringBuilder = new StringBuilder();
   quoteTo(`$this$quote`, var1);
   return var1.toString();
}

private fun String.quoteTo(out: StringBuilder) {
   out.append("\"");
   var var2: Int = 0;

   for (int var3 = $this$quoteTo.length(); var2 < var3; var2++) {
      val element: Char = `$this$quoteTo`.charAt(var2);
      switch (element) {
         case '\t':
            out.append("\\t");
            break;
         case '\n':
            out.append("\\n");
            break;
         case '\r':
            out.append("\\r");
            break;
         case '"':
            out.append("\\\"");
            break;
         case '\\':
            out.append("\\\\");
            break;
         default:
            out.append(element);
      }
   }

   out.append("\"");
}

@JvmSynthetic
fun `access$needQuotes`(`$receiver`: java.lang.String): Boolean {
   return needQuotes(`$receiver`);
}
