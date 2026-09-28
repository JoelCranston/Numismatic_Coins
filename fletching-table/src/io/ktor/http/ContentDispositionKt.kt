@file:SourceDebugExtension(["SMAP\nContentDisposition.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContentDisposition.kt\nio/ktor/http/ContentDispositionKt\n+ 2 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,125:1\n1069#2,2:126\n*S KotlinDebug\n*F\n+ 1 ContentDisposition.kt\nio/ktor/http/ContentDispositionKt\n*L\n120#1:126,2\n*E\n"])

package io.ktor.http

import kotlin.jvm.internal.SourceDebugExtension

private fun encodeContentDispositionAttribute(key: String, value: String): String {
   if (!(key == "filename*")) {
      return value;
   } else if (StringsKt.startsWith(value, "utf-8''", true)) {
      return value;
   } else {
      label33: {
         val encodedValue: java.lang.CharSequence = value;
         var var4: Int = 0;

         var var10000: Boolean;
         while (true) {
            if (var4 >= encodedValue.length()) {
               var10000 = true;
               break;
            }

            if (!CodecsKt.getATTRIBUTE_CHARACTERS().contains(encodedValue.charAt(var4))) {
               var10000 = false;
               break;
            }

            var4++;
         }

         return if (var10000) value else "utf-8''${CodecsKt.percentEncode(value, CodecsKt.getATTRIBUTE_CHARACTERS())}";
      }
   }
}

@JvmSynthetic
fun `access$encodeContentDispositionAttribute`(key: java.lang.String, value: java.lang.String): java.lang.String {
   return encodeContentDispositionAttribute(key, value);
}
