@file:SourceDebugExtension(["SMAP\nHttpClientJvm.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientJvm.kt\nio/ktor/client/HttpClientJvmKt\n+ 2 ServiceLoader.kt\nio/ktor/util/reflect/ServiceLoaderKt\n*L\n1#1,47:1\n47#2:48\n23#2,2:49\n22#2,4:51\n*S KotlinDebug\n*F\n+ 1 HttpClientJvm.kt\nio/ktor/client/HttpClientJvmKt\n*L\n43#1:48\n43#1:49,2\n43#1:51,4\n*E\n"])

package io.ktor.client

import io.ktor.client.engine.HttpClientEngineFactory
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

private final val FACTORY: HttpClientEngineFactory<*>

public fun HttpClient(block: (HttpClientConfig<*>) -> Unit = HttpClientJvmKt::HttpClient$lambda$0): HttpClient {
   return HttpClientKt.HttpClient(FACTORY, block);
}

@JvmSynthetic
fun `HttpClient$default`(var0: Function1, var1: Int, var2: Any): HttpClient {
   if ((var1 and 1) != 0) {
      var0 = HttpClientJvmKt::HttpClient$lambda$0;
   }

   return HttpClient(var0);
}

fun `HttpClient$lambda$0`(var0: HttpClientConfig): Unit {
   return Unit.INSTANCE;
}
