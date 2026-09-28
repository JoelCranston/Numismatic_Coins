package io.ktor.client.plugins.observer

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.plugins.observer.ResponseObserverKt.ResponseObserver.1
import io.ktor.client.statement.HttpResponse
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function2

public final val ResponseObserver: ClientPlugin<ResponseObserverConfig> =
   CreatePluginUtilsKt.createClientPlugin("ResponseObserver", 1.INSTANCE, ResponseObserverKt::ResponseObserver$lambda$0)

public fun HttpClientConfig<*>.ResponseObserver(block: (HttpResponse, Continuation<Unit>) -> Any?) {
   `$this$ResponseObserver`.install(
      ResponseObserver as HttpClientPlugin<? extends ResponseObserverConfig, ClientPluginInstance<ResponseObserverConfig>>,
      ResponseObserverKt::ResponseObserver$lambda$1
   );
}

fun ClientPluginBuilder.`ResponseObserver$lambda$0`(): Unit {
   `$this$createClientPlugin`.on(
      AfterReceiveHook.INSTANCE,
      new io.ktor.client.plugins.observer.ResponseObserverKt.ResponseObserver.2.1(
         (`$this$createClientPlugin`.getPluginConfig() as ResponseObserverConfig).getFilter$ktor_client_core(),
         `$this$createClientPlugin`,
         (`$this$createClientPlugin`.getPluginConfig() as ResponseObserverConfig).getResponseHandler$ktor_client_core(),
         null
      )
   );
   return Unit.INSTANCE;
}

fun `ResponseObserver$lambda$1`(`$block`: Function2, `$this$install`: ResponseObserverConfig): Unit {
   `$this$install`.setResponseHandler$ktor_client_core(`$block`);
   return Unit.INSTANCE;
}
