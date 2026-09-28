package io.ktor.client.engine

import io.ktor.utils.io.KtorDsl
import java.net.Proxy
import kotlinx.coroutines.CoroutineDispatcher

@KtorDsl
public open class HttpClientEngineConfig {
   @Deprecated(
      message = "The [threadsCount] property is deprecated. Consider setting [dispatcher] instead.",
      level = DeprecationLevel.ERROR
   )
   public final var threadsCount: Int = 4

   public final var dispatcher: CoroutineDispatcher?
   public final var pipelining: Boolean
   public final var proxy: Proxy?
}
