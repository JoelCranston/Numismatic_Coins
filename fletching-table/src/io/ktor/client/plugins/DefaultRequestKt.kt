package io.ktor.client.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.DefaultRequest.DefaultRequestBuilder
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.jvm.functions.Function1
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.DefaultRequest")

public fun HttpClientConfig<*>.defaultRequest(block: (DefaultRequestBuilder) -> Unit) {
   `$this$defaultRequest`.install(DefaultRequest.Plugin, DefaultRequestKt::defaultRequest$lambda$0);
}

fun `defaultRequest$lambda$0`(`$block`: Function1, `$this$install`: DefaultRequest.DefaultRequestBuilder): Unit {
   `$block`.invoke(`$this$install`);
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}
