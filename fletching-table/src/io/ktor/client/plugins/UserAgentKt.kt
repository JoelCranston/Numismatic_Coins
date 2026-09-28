package io.ktor.client.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.UserAgentKt.UserAgent.2
import io.ktor.client.plugins.UserAgentKt.UserAgent.3.1
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.UserAgent")
public final val UserAgent: ClientPlugin<UserAgentConfig> = CreatePluginUtilsKt.createClientPlugin("UserAgent", 2.INSTANCE, UserAgentKt::UserAgent$lambda$1)

public fun HttpClientConfig<*>.BrowserUserAgent() {
   `$this$BrowserUserAgent`.install(
      UserAgent as HttpClientPlugin<? extends UserAgentConfig, ClientPluginInstance<UserAgentConfig>>, UserAgentKt::BrowserUserAgent$lambda$0
   );
}

public fun HttpClientConfig<*>.CurlUserAgent() {
   `$this$CurlUserAgent`.install(
      UserAgent as HttpClientPlugin<? extends UserAgentConfig, ClientPluginInstance<UserAgentConfig>>, UserAgentKt::CurlUserAgent$lambda$0
   );
}

fun ClientPluginBuilder.`UserAgent$lambda$1`(): Unit {
   `$this$createClientPlugin`.onRequest(new 1((`$this$createClientPlugin`.getPluginConfig() as UserAgentConfig).getAgent(), null));
   return Unit.INSTANCE;
}

fun UserAgentConfig.`BrowserUserAgent$lambda$0`(): Unit {
   `$this$install`.setAgent(
      "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Ubuntu Chromium/70.0.3538.77 Chrome/70.0.3538.77 Safari/537.36"
   );
   return Unit.INSTANCE;
}

fun UserAgentConfig.`CurlUserAgent$lambda$0`(): Unit {
   `$this$install`.setAgent("curl/7.61.0");
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}
