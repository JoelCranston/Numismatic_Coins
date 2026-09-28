package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.SetupRequestContext.install.1
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function1
import kotlin.jvm.functions.Function3

public object SetupRequestContext :
   ClientHook<Function3<? super HttpRequestBuilder, ? super Function1<? super Continuation<? super Unit>, ? extends Object>, ? super Continuation<? super Unit>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequestBuilder, (Continuation<Unit>) -> Any?, Continuation<Unit>) -> Any?) {
      client.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getBefore(), new 1(handler, null));
   }
}
