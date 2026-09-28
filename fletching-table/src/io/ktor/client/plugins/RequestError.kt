package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.RequestError.install.1
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestPipeline
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

internal object RequestError :
   ClientHook<Function3<? super HttpRequest, ? super java.lang.Throwable, ? super Continuation<? super java.lang.Throwable>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequest, Throwable, Continuation<Throwable?>) -> Any?) {
      client.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getBefore(), new 1(handler, null));
   }
}
