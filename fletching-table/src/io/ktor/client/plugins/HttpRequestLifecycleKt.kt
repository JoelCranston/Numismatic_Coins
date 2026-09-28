package io.ktor.client.plugins

import io.ktor.client.plugins.HttpRequestLifecycleKt.HttpRequestLifecycle.1.1
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpRequestLifecycle")
public final val HttpRequestLifecycle: ClientPlugin<Unit> =
   CreatePluginUtilsKt.createClientPlugin("RequestLifecycle", HttpRequestLifecycleKt::HttpRequestLifecycle$lambda$0)

private fun attachToClientEngineJob(requestJob: CompletableJob, clientEngineJob: Job) {
   requestJob.invokeOnCompletion(HttpRequestLifecycleKt::attachToClientEngineJob$lambda$1);
}

fun ClientPluginBuilder.`HttpRequestLifecycle$lambda$0`(): Unit {
   `$this$createClientPlugin`.on(SetupRequestContext.INSTANCE, new 1(`$this$createClientPlugin`, null));
   return Unit.INSTANCE;
}

fun `attachToClientEngineJob$lambda$0`(`$requestJob`: CompletableJob, cause: java.lang.Throwable): Unit {
   if (cause != null) {
      LOGGER.trace("Cancelling request because engine Job failed with error: $cause");
      JobKt.cancel(`$requestJob`, "Engine failed", cause);
   } else {
      LOGGER.trace("Cancelling request because engine Job completed");
      `$requestJob`.complete();
   }

   return Unit.INSTANCE;
}

fun `attachToClientEngineJob$lambda$1`(`$handler`: DisposableHandle, it: java.lang.Throwable): Unit {
   `$handler`.dispose();
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$attachToClientEngineJob`(requestJob: CompletableJob, clientEngineJob: Job) {
   attachToClientEngineJob(requestJob, clientEngineJob);
}
