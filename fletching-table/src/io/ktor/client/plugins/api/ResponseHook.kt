package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.ResponseHook.install.1
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

internal object ResponseHook : ClientHook<Function3<? super OnResponseContext, ? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (OnResponseContext, HttpResponse, Continuation<Unit>) -> Any?) {
      client.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getState(), new 1(handler, null));
   }
}
