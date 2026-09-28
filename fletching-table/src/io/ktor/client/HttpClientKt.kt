package io.ktor.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function1
import kotlinx.coroutines.Job

public fun <T : HttpClientEngineConfig> HttpClient(
   engineFactory: HttpClientEngineFactory<Any>,
   block: (HttpClientConfig<Any>) -> Unit = HttpClientKt::HttpClient$lambda$0
): HttpClient {
   val engine: HttpClientConfig = new HttpClientConfig();
   block.invoke(engine);
   val var5: HttpClientEngine = engineFactory.create(engine.getEngineConfig$ktor_client_core());
   val client: HttpClient = new HttpClient(var5, engine, true);
   val var10000: CoroutineContext.Element = client.getCoroutineContext().get(Job.Key);
   (var10000 as Job).invokeOnCompletion(HttpClientKt::HttpClient$lambda$1);
   return client;
}

@JvmSynthetic
fun `HttpClient$default`(var0: HttpClientEngineFactory, var1: Function1, var2: Int, var3: Any): HttpClient {
   if ((var2 and 2) != 0) {
      var1 = HttpClientKt::HttpClient$lambda$0;
   }

   return HttpClient(var0, var1);
}

public fun HttpClient(engine: HttpClientEngine, block: (HttpClientConfig<*>) -> Unit): HttpClient {
   val var2: HttpClientConfig = new HttpClientConfig();
   block.invoke(var2);
   return new HttpClient(engine, var2, false);
}

fun `HttpClient$lambda$0`(var0: HttpClientConfig): Unit {
   return Unit.INSTANCE;
}

fun `HttpClient$lambda$1`(`$engine`: HttpClientEngine, it: java.lang.Throwable): Unit {
   `$engine`.close();
   return Unit.INSTANCE;
}
