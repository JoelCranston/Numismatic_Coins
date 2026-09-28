package io.ktor.client.plugins.cache

import io.ktor.client.engine.UtilsKt
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.content.OutgoingContent
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.jvm.functions.Function1
import org.slf4j.Logger

internal final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCache")

internal fun mergedHeadersLookup(content: OutgoingContent, headerExtractor: (String) -> String?, allHeadersExtractor: (String) -> List<String>?): (String) -> String {
   return HttpCacheKt::mergedHeadersLookup$lambda$0;
}

private fun URLProtocol.canStore(): Boolean {
   return `$this$canStore`.getName() == "http" || `$this$canStore`.getName() == "https";
}

fun `mergedHeadersLookup$lambda$0`(`$content`: OutgoingContent, `$headerExtractor`: Function1, `$allHeadersExtractor`: Function1, header: java.lang.String): java.lang.String {
   var var9: java.lang.String;
   if (header == HttpHeaders.INSTANCE.getContentLength()) {
      val var10000: java.lang.Long = `$content`.getContentLength();
      if (var10000 != null) {
         var9 = java.lang.String.valueOf(var10000.longValue());
         if (var9 != null) {
            return var9;
         }
      }

      var9 = "";
   } else if (header == HttpHeaders.INSTANCE.getContentType()) {
      val var7: ContentType = `$content`.getContentType();
      if (var7 != null) {
         var9 = var7.toString();
         if (var9 != null) {
            return var9;
         }
      }

      var9 = "";
   } else if (header == HttpHeaders.INSTANCE.getUserAgent()) {
      var9 = `$content`.getHeaders().get(HttpHeaders.INSTANCE.getUserAgent());
      if (var9 == null) {
         var9 = `$headerExtractor`.invoke(HttpHeaders.INSTANCE.getUserAgent()) as java.lang.String;
         if (var9 == null) {
            var9 = UtilsKt.getKTOR_DEFAULT_USER_AGENT();
         }
      }
   } else {
      var var8: java.util.List = `$content`.getHeaders().getAll(header);
      if (var8 == null) {
         var8 = `$allHeadersExtractor`.invoke(header) as java.util.List;
         if (var8 == null) {
            var8 = CollectionsKt.emptyList();
         }
      }

      var9 = CollectionsKt.joinToString$default(var8, ";", null, null, 0, null, null, 62, null);
   }

   return var9;
}

@JvmSynthetic
fun `access$canStore`(`$receiver`: URLProtocol): Boolean {
   return canStore(`$receiver`);
}
