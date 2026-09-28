package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.RequestHook.install.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function4

internal object RequestHook :
   ClientHook<Function4<? super OnRequestContext, ? super HttpRequestBuilder, ? super Object, ? super Continuation<? super Unit>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (OnRequestContext, HttpRequestBuilder, Any, Continuation<Unit>) -> Any?) {
      client.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getState(), new 1(handler, null));
   }
}
