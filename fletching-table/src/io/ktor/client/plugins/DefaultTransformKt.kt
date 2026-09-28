package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultTransformKt.defaultTransformers.1
import io.ktor.client.plugins.DefaultTransformKt.defaultTransformers.2
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.defaultTransformers")

public fun HttpClient.defaultTransformers() {
   `$this$defaultTransformers`.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getRender(), new 1(null));
   `$this$defaultTransformers`.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getParse(), new 2(`$this$defaultTransformers`, null));
   DefaultTransformersJvmKt.platformResponseDefaultTransformers(`$this$defaultTransformers`);
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}
