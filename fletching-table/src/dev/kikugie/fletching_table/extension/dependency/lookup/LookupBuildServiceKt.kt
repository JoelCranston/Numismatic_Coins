package dev.kikugie.fletching_table.extension.dependency.lookup

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.HttpClientKt
import io.ktor.client.engine.java.Java
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpRequestRetryConfig
import io.ktor.client.plugins.HttpRequestRetryKt
import io.ktor.client.plugins.HttpTimeoutConfig
import io.ktor.client.plugins.HttpTimeoutKt
import io.ktor.client.plugins.UserAgentConfig
import io.ktor.client.plugins.UserAgentKt
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.contentnegotiation.ContentNegotiationConfig
import io.ktor.client.plugins.contentnegotiation.ContentNegotiationKt
import io.ktor.serialization.kotlinx.json.JsonSupportKt
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonKt

private fun newClient(): HttpClient {
   return HttpClientKt.HttpClient(Java.INSTANCE, LookupBuildServiceKt::newClient$lambda$0);
}

fun HttpClientConfig.`newClient$lambda$0`(): Unit {
   `$this$HttpClient`.install(
      UserAgentKt.getUserAgent() as HttpClientPlugin<? extends UserAgentConfig, ClientPluginInstance<UserAgentConfig>>,
      LookupBuildServiceKt::newClient$lambda$0$0
   );
   `$this$HttpClient`.install(
      HttpTimeoutKt.getHttpTimeout() as HttpClientPlugin<? extends HttpTimeoutConfig, ClientPluginInstance<HttpTimeoutConfig>>,
      LookupBuildServiceKt::newClient$lambda$0$1
   );
   `$this$HttpClient`.install(
      HttpRequestRetryKt.getHttpRequestRetry() as HttpClientPlugin<? extends HttpRequestRetryConfig, ClientPluginInstance<HttpRequestRetryConfig>>,
      LookupBuildServiceKt::newClient$lambda$0$2
   );
   `$this$HttpClient`.install(
      ContentNegotiationKt.getContentNegotiation() as HttpClientPlugin<? extends ContentNegotiationConfig, ClientPluginInstance<ContentNegotiationConfig>>,
      LookupBuildServiceKt::newClient$lambda$0$3
   );
   return Unit.INSTANCE;
}

fun UserAgentConfig.`newClient$lambda$0$0`(): Unit {
   `$this$install`.setAgent("kikugie/fletching-table");
   return Unit.INSTANCE;
}

fun HttpTimeoutConfig.`newClient$lambda$0$1`(): Unit {
   `$this$install`.setConnectTimeoutMillis(10000L);
   `$this$install`.setRequestTimeoutMillis(10000L);
   return Unit.INSTANCE;
}

fun HttpRequestRetryConfig.`newClient$lambda$0$2`(): Unit {
   `$this$install`.setMaxRetries(3);
   return Unit.INSTANCE;
}

fun ContentNegotiationConfig.`newClient$lambda$0$3`(): Unit {
   JsonSupportKt.json$default(`$this$install`, JsonKt.Json$default(null, LookupBuildServiceKt::newClient$lambda$0$3$0, 1, null), null, 2, null);
   return Unit.INSTANCE;
}

fun JsonBuilder.`newClient$lambda$0$3$0`(): Unit {
   `$this$Json`.setIgnoreUnknownKeys(true);
   `$this$Json`.setLenient(true);
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$newClient`(): HttpClient {
   return newClient();
}
