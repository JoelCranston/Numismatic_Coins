@file:SourceDebugExtension(["SMAP\nHttpPlainText.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpPlainText.kt\nio/ktor/client/plugins/HttpPlainTextKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,179:1\n1068#2:180\n774#2:181\n865#2,2:182\n1056#2:184\n1869#2,2:185\n1869#2,2:187\n*S KotlinDebug\n*F\n+ 1 HttpPlainText.kt\nio/ktor/client/plugins/HttpPlainTextKt\n*L\n78#1:180\n81#1:181\n81#1:182,2\n82#1:184\n85#1:185,2\n90#1:187,2\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpPlainTextKt.HttpPlainText.1
import io.ktor.client.plugins.HttpPlainTextKt.HttpPlainText.2.2
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.content.OutgoingContent
import io.ktor.http.content.TextContent
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import io.ktor.utils.io.charsets.CharsetJVMKt
import io.ktor.utils.io.core.StringsKt
import java.nio.charset.Charset
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.math.MathKt
import kotlinx.io.Source
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpPlainText")
public final val HttpPlainText: ClientPlugin<HttpPlainTextConfig> =
   CreatePluginUtilsKt.createClientPlugin("HttpPlainText", 1.INSTANCE, HttpPlainTextKt::HttpPlainText$lambda$0)

public fun HttpClientConfig<*>.Charsets(block: (HttpPlainTextConfig) -> Unit) {
   `$this$Charsets`.install(HttpPlainText as HttpClientPlugin<? extends HttpPlainTextConfig, ClientPluginInstance<HttpPlainTextConfig>>, block);
}

fun ClientPluginBuilder.`HttpPlainText$lambda$0`(): Unit {
   val withQuality: java.util.List = CollectionsKt.sortedWith(
      MapsKt.toList((`$this$createClientPlugin`.getPluginConfig() as HttpPlainTextConfig).getCharsetQuality$ktor_client_core()),
      new io.ktor.client.plugins.HttpPlainTextKt.HttpPlainText.lambda.0..inlined.sortedByDescending.1<>()
   );
   val var18: Charset = (`$this$createClientPlugin`.getPluginConfig() as HttpPlainTextConfig).getResponseCharsetFallback();
   val acceptCharsetHeader: java.lang.Iterable = (`$this$createClientPlugin`.getPluginConfig() as HttpPlainTextConfig).getCharsets$ktor_client_core();
   val var7: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : acceptCharsetHeader) {
      if (!(`$this$createClientPlugin`.getPluginConfig() as HttpPlainTextConfig).getCharsetQuality$ktor_client_core().containsKey(`element$iv$iv` as Charset)) {
         var7.add(`element$iv$iv`);
      }
   }

   val var19: java.util.List = CollectionsKt.sortedWith(
      var7 as java.util.List, new io.ktor.client.plugins.HttpPlainTextKt.HttpPlainText.lambda.0..inlined.sortedBy.1()
   );
   val var23: StringBuilder = new StringBuilder();
   val `$this$HttpPlainText_u24lambda_u240_u243`: StringBuilder = var23;

   val var26: java.lang.Iterable;
   for (Object element$iv : var26) {
      val var34: Charset = var32 as Charset;
      if (`$this$HttpPlainText_u24lambda_u240_u243`.length() > 0) {
         `$this$HttpPlainText_u24lambda_u240_u243`.append(",");
      }

      `$this$HttpPlainText_u24lambda_u240_u243`.append(CharsetJVMKt.getName(var34));
   }

   for (Object element$iv : var26) {
      val charset: Charset = (var33 as Pair).component1() as Charset;
      val quality: Float = ((var33 as Pair).component2() as java.lang.Number).floatValue();
      if (`$this$HttpPlainText_u24lambda_u240_u243`.length() > 0) {
         `$this$HttpPlainText_u24lambda_u240_u243`.append(",");
      }

      if (!(0.0 <= quality) || !(quality <= 1.0)) {
         throw new IllegalStateException("Check failed.");
      }

      `$this$HttpPlainText_u24lambda_u240_u243`.append("${CharsetJVMKt.getName(charset)};q=${(double)MathKt.roundToInt((float)100 * quality) / 100.0}");
   }

   if (`$this$HttpPlainText_u24lambda_u240_u243`.length() == 0) {
      `$this$HttpPlainText_u24lambda_u240_u243`.append(CharsetJVMKt.getName(var18));
   }

   val var21: java.lang.String = var23.toString();
   var var10000: Charset = (`$this$createClientPlugin`.getPluginConfig() as HttpPlainTextConfig).getSendCharset();
   if (var10000 == null) {
      var10000 = CollectionsKt.firstOrNull(var19);
      if (var10000 == null) {
         val var38: Pair = CollectionsKt.firstOrNull(withQuality);
         var10000 = if (var38 != null) var38.getFirst() as Charset else null;
         if (var10000 == null) {
            var10000 = Charsets.UTF_8;
         }
      }
   }

   `$this$createClientPlugin`.on(RenderRequestHook.INSTANCE, new io.ktor.client.plugins.HttpPlainTextKt.HttpPlainText.2.1(var21, var10000, null));
   `$this$createClientPlugin`.transformResponseBody(new 2(var18, null));
   return Unit.INSTANCE;
}

fun `HttpPlainText$lambda$0$wrapContent`(requestCharset: Charset, request: HttpRequestBuilder, content: java.lang.String, requestContentType: ContentType): OutgoingContent {
   var var10000: ContentType = requestContentType;
   if (requestContentType == null) {
      var10000 = ContentType.Text.INSTANCE.getPlain();
   }

   label14: {
      if (requestContentType != null) {
         var6 = ContentTypesKt.charset(requestContentType);
         if (var6 != null) {
            break label14;
         }
      }

      var6 = requestCharset;
   }

   LOGGER.trace("Sending request body to ${request.getUrl()} as text/plain with charset $var6");
   return new TextContent(content, ContentTypesKt.withCharset(var10000, var6), null, 4, null);
}

fun `HttpPlainText$lambda$0$read`(responseCharsetFallback: Charset, call: HttpClientCall, body: Source): java.lang.String {
   var var10000: Charset = HttpMessagePropertiesKt.charset(call.getResponse());
   if (var10000 == null) {
      var10000 = responseCharsetFallback;
   }

   LOGGER.trace("Reading response body for ${call.getRequest().getUrl()} as String with charset $var10000");
   return StringsKt.readText$default(body, var10000, 0, 2, null);
}

fun `HttpPlainText$lambda$0$addCharsetHeaders`(acceptCharsetHeader: java.lang.String, context: HttpRequestBuilder) {
   if (context.getHeaders().get(HttpHeaders.INSTANCE.getAcceptCharset()) == null) {
      LOGGER.trace("Adding Accept-Charset=$acceptCharsetHeader to ${context.getUrl()}");
      context.getHeaders().set(HttpHeaders.INSTANCE.getAcceptCharset(), acceptCharsetHeader);
   }
}

@JvmSynthetic
fun `access$HttpPlainText$lambda$0$addCharsetHeaders`(acceptCharsetHeader: java.lang.String, context: HttpRequestBuilder) {
   HttpPlainText$lambda$0$addCharsetHeaders(acceptCharsetHeader, context);
}

@JvmSynthetic
fun `access$HttpPlainText$lambda$0$wrapContent`(
   requestCharset: Charset, request: HttpRequestBuilder, content: java.lang.String, requestContentType: ContentType
): OutgoingContent {
   return HttpPlainText$lambda$0$wrapContent(requestCharset, request, content, requestContentType);
}

@JvmSynthetic
fun `access$HttpPlainText$lambda$0$read`(responseCharsetFallback: Charset, call: HttpClientCall, body: Source): java.lang.String {
   return HttpPlainText$lambda$0$read(responseCharsetFallback, call, body);
}
