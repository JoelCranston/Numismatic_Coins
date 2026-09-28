@file:SourceDebugExtension(["SMAP\nHttpRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpRequest.kt\nio/ktor/client/request/HttpRequestKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,440:1\n1#2:441\n21#3:442\n69#4:443\n84#4,8:444\n*S KotlinDebug\n*F\n+ 1 HttpRequest.kt\nio/ktor/client/request/HttpRequestKt\n*L\n400#1:442\n400#1:443\n400#1:444,8\n*E\n"])

package io.ktor.client.request

import io.ktor.client.plugins.sse.SSEClientContent
import io.ktor.client.plugins.sse.SSEKt
import io.ktor.client.request.HttpRequestBuilder.Companion
import io.ktor.client.request.HttpRequestKt.forEachHeader.1
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.HttpMethodKt
import io.ktor.http.URLBuilder
import io.ktor.http.URLBuilderKt
import io.ktor.http.URLParserKt
import io.ktor.http.URLUtilsKt
import io.ktor.http.content.OutgoingContentKt
import io.ktor.util.AttributeKey
import io.ktor.util.AttributesKt
import io.ktor.utils.io.InternalAPI
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

@InternalAPI
public final val ResponseAdapterAttributeKey: AttributeKey<ResponseAdapter>

@InternalAPI
public inline fun HttpRequestData.forEachHeader(crossinline block: (String, String) -> Unit) {
   io.ktor.client.engine.UtilsKt.mergeHeaders(
      `$this$forEachHeader`.getHeaders(),
      `$this$forEachHeader`.getBody(),
      new 1(!HttpMethodKt.getSupportsRequestBody(`$this$forEachHeader`.getMethod()) && OutgoingContentKt.isEmpty(`$this$forEachHeader`.getBody()), block)
   );
}

public fun HttpMessageBuilder.headers(block: (HeadersBuilder) -> Unit): HeadersBuilder {
   val var2: HeadersBuilder = `$this$headers`.getHeaders();
   block.invoke(var2);
   return var2;
}

public fun HttpRequestBuilder.takeFrom(request: HttpRequest): HttpRequestBuilder {
   `$this$takeFrom`.setMethod(request.getMethod());
   `$this$takeFrom`.setBody(request.getContent());
   `$this$takeFrom`.setBodyType(`$this$takeFrom`.getAttributes().getOrNull(RequestBodyKt.getBodyTypeAttributeKey()));
   URLUtilsKt.takeFrom(`$this$takeFrom`.getUrl(), request.getUrl());
   `$this$takeFrom`.getHeaders().appendAll(request.getHeaders());
   AttributesKt.putAll(`$this$takeFrom`.getAttributes(), request.getAttributes());
   return `$this$takeFrom`;
}

public fun HttpRequestBuilder.url(block: (URLBuilder) -> Unit) {
   block.invoke(`$this$url`.getUrl());
}

public fun HttpRequestBuilder.takeFrom(request: HttpRequestData): HttpRequestBuilder {
   `$this$takeFrom`.setMethod(request.getMethod());
   `$this$takeFrom`.setBody(request.getBody());
   `$this$takeFrom`.setBodyType(`$this$takeFrom`.getAttributes().getOrNull(RequestBodyKt.getBodyTypeAttributeKey()));
   URLUtilsKt.takeFrom(`$this$takeFrom`.getUrl(), request.getUrl());
   `$this$takeFrom`.getHeaders().appendAll(request.getHeaders());
   AttributesKt.putAll(`$this$takeFrom`.getAttributes(), request.getAttributes());
   return `$this$takeFrom`;
}

public operator fun Companion.invoke(block: (URLBuilder) -> Unit): HttpRequestBuilder {
   val var2: HttpRequestBuilder = new HttpRequestBuilder();
   url(var2, block);
   return var2;
}

public fun HttpRequestBuilder.url(
   scheme: String? = null,
   host: String? = null,
   port: Int? = null,
   path: String? = null,
   block: (URLBuilder) -> Unit = HttpRequestKt::url$lambda$0
) {
   URLBuilderKt.set(`$this$url`.getUrl(), scheme, host, port, path, block);
}

@JvmSynthetic
fun `url$default`(
   var0: HttpRequestBuilder, var1: java.lang.String, var2: java.lang.String, var3: Int, var4: java.lang.String, var5: Function1, var6: Int, var7: Any
) {
   if ((var6 and 1) != 0) {
      var1 = null;
   }

   if ((var6 and 2) != 0) {
      var2 = null;
   }

   if ((var6 and 4) != 0) {
      var3 = null;
   }

   if ((var6 and 8) != 0) {
      var4 = null;
   }

   if ((var6 and 16) != 0) {
      var5 = HttpRequestKt::url$lambda$0;
   }

   url(var0, var1, var2, var3, var4, var5);
}

public operator fun Companion.invoke(
   scheme: String? = null,
   host: String? = null,
   port: Int? = null,
   path: String? = null,
   block: (URLBuilder) -> Unit = HttpRequestKt::invoke$lambda$1
): HttpRequestBuilder {
   val var6: HttpRequestBuilder = new HttpRequestBuilder();
   url(var6, scheme, host, port, path, block);
   return var6;
}

@JvmSynthetic
fun `invoke$default`(
   var0: HttpRequestBuilder.Companion, var1: java.lang.String, var2: java.lang.String, var3: Int, var4: java.lang.String, var5: Function1, var6: Int, var7: Any
): HttpRequestBuilder {
   if ((var6 and 1) != 0) {
      var1 = null;
   }

   if ((var6 and 2) != 0) {
      var2 = null;
   }

   if ((var6 and 4) != 0) {
      var3 = null;
   }

   if ((var6 and 8) != 0) {
      var4 = null;
   }

   if ((var6 and 16) != 0) {
      var5 = HttpRequestKt::invoke$lambda$1;
   }

   return invoke(var0, var1, var2, var3, var4, var5);
}

public fun HttpRequestBuilder.url(urlString: String) {
   URLParserKt.takeFrom(`$this$url`.getUrl(), urlString);
}

@InternalAPI
public fun HttpRequestData.isUpgradeRequest(): Boolean {
   return `$this$isUpgradeRequest`.getBody() is ClientUpgradeContent;
}

@InternalAPI
public fun HttpRequestData.isSseRequest(): Boolean {
   return `$this$isSseRequest`.getBody() is SSEClientContent;
}

@InternalAPI
public fun HttpRequestData.isSseReconnectionRequest(): Boolean {
   return `$this$isSseReconnectionRequest`.getAttributes().getOrNull(SSEKt.getSSEReconnectionRequestAttr()) == true;
}

fun `url$lambda$0`(var0: URLBuilder): Unit {
   return Unit.INSTANCE;
}

fun `invoke$lambda$1`(var0: URLBuilder): Unit {
   return Unit.INSTANCE;
}
