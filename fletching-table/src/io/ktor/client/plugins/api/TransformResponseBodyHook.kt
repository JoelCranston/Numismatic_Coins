package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.TransformResponseBodyHook.install.1
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function5

internal object TransformResponseBodyHook :
   ClientHook<Function5<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super Continuation<? super Object>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, Continuation<Any?>) -> Any?) {
      client.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getTransform(), new 1(handler, null));
   }
}
