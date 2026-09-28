package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.RenderRequestHook.install.1
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.http.content.OutgoingContent
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

internal object RenderRequestHook :
   ClientHook<Function3<? super HttpRequestBuilder, ? super Object, ? super Continuation<? super OutgoingContent>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequestBuilder, Any, Continuation<OutgoingContent?>) -> Any?) {
      client.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getRender(), new 1(handler, null));
   }
}
