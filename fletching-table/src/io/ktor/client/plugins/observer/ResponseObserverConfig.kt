package io.ktor.client.plugins.observer

import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.observer.ResponseObserverConfig.responseHandler.1
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.KtorDsl
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function2

@KtorDsl
public class ResponseObserverConfig {
   internal final var responseHandler: (HttpResponse, Continuation<Unit>) -> Any? = (new 1(null)) as Function2
   internal final var filter: ((HttpClientCall) -> Boolean)?

   public fun onResponse(block: (HttpResponse, Continuation<Unit>) -> Any?) {
      this.responseHandler = block;
   }

   public fun filter(block: (HttpClientCall) -> Boolean) {
      this.filter = block;
   }
}
