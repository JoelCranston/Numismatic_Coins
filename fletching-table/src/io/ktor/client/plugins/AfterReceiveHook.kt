package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.AfterReceiveHook.install.1
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function2

internal object AfterReceiveHook : ClientHook<Function2<? super HttpResponse, ? super Continuation<? super HttpResponse>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpResponse, Continuation<HttpResponse?>) -> Any?) {
      client.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getAfter(), new 1(handler, null));
   }
}
