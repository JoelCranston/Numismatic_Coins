package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.SendingRequest.install.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpSendPipeline
import io.ktor.http.content.OutgoingContent
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

public object SendingRequest : ClientHook<Function3<? super HttpRequestBuilder, ? super OutgoingContent, ? super Continuation<? super Unit>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequestBuilder, OutgoingContent, Continuation<Unit>) -> Any?) {
      client.getSendPipeline().intercept(HttpSendPipeline.Phases.getState(), new 1(handler, null));
   }
}
