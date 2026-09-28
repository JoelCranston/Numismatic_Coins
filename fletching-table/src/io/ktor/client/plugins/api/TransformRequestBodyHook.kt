package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.TransformRequestBodyHook.install.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.http.content.OutgoingContent
import io.ktor.util.reflect.TypeInfo
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function5

internal object TransformRequestBodyHook :
   ClientHook<Function5<? super TransformRequestBodyContext, ? super HttpRequestBuilder, ? super Object, ? super TypeInfo, ? super Continuation<? super OutgoingContent>, ? extends Object>> {
   public open fun install(
      client: HttpClient,
      handler: (TransformRequestBodyContext, HttpRequestBuilder, Any, TypeInfo?, Continuation<OutgoingContent?>) -> Any?
   ) {
      client.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getTransform(), new 1(handler, null));
   }
}
