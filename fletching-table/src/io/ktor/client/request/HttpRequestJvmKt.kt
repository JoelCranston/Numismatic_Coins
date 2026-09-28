@file:SourceDebugExtension(["SMAP\nHttpRequestJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpRequestJvm.kt\nio/ktor/client/request/HttpRequestJvmKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,23:1\n1#2:24\n*E\n"])

package io.ktor.client.request

import io.ktor.client.request.HttpRequestBuilder.Companion
import io.ktor.http.URLBuilder
import io.ktor.http.URLUtilsJvmKt
import java.net.URL
import kotlin.jvm.internal.SourceDebugExtension

public fun HttpRequestBuilder.url(url: URL): URLBuilder {
   return URLUtilsJvmKt.takeFrom(`$this$url`.getUrl(), url);
}

public operator fun Companion.invoke(url: URL): HttpRequestBuilder {
   val var2: HttpRequestBuilder = new HttpRequestBuilder();
   url(var2, url);
   return var2;
}
