package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.SetupRequest.install.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function2

public object SetupRequest : ClientHook<Function2<? super HttpRequestBuilder, ? super Continuation<? super Unit>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequestBuilder, Continuation<Unit>) -> Any?) {
      client.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getBefore(), new 1(handler, null));
   }
}
