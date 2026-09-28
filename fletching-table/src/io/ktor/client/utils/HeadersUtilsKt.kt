@file:SourceDebugExtension(["SMAP\nHeadersUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HeadersUtils.kt\nio/ktor/client/utils/HeadersUtilsKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,33:1\n21#2:34\n69#3:35\n84#3,8:36\n*S KotlinDebug\n*F\n+ 1 HeadersUtils.kt\nio/ktor/client/utils/HeadersUtilsKt\n*L\n11#1:34\n11#1:35\n11#1:36,8\n*E\n"])

package io.ktor.client.utils

import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.utils.io.InternalAPI
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

private final val DecompressionListAttribute: AttributeKey<MutableList<String>>

@InternalAPI
public fun HeadersBuilder.dropCompressionHeaders(method: HttpMethod, attributes: Attributes, alwaysRemove: Boolean = false) {
   if (!(method == HttpMethod.Companion.getHead()) && !(method == HttpMethod.Companion.getOptions())) {
      val header: java.lang.String = `$this$dropCompressionHeaders`.get(HttpHeaders.INSTANCE.getContentEncoding());
      if (header == null) {
         if (!alwaysRemove) {
            return;
         }
      } else {
         attributes.computeIfAbsent(DecompressionListAttribute, HeadersUtilsKt::dropCompressionHeaders$lambda$0).add(header);
      }

      `$this$dropCompressionHeaders`.remove(HttpHeaders.INSTANCE.getContentEncoding());
      `$this$dropCompressionHeaders`.remove(HttpHeaders.INSTANCE.getContentLength());
   }
}

@JvmSynthetic
fun `dropCompressionHeaders$default`(var0: HeadersBuilder, var1: HttpMethod, var2: Attributes, var3: Boolean, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = false;
   }

   dropCompressionHeaders(var0, var1, var2, var3);
}

fun `dropCompressionHeaders$lambda$0`(): java.util.List {
   return new ArrayList<>();
}
