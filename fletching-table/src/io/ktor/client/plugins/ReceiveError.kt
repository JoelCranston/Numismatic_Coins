package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.ReceiveError.install.1
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.util.pipeline.PipelinePhase
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

internal object ReceiveError :
   ClientHook<Function3<? super HttpRequest, ? super java.lang.Throwable, ? super Continuation<? super java.lang.Throwable>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequest, Throwable, Continuation<Throwable?>) -> Any?) {
      val BeforeReceive: PipelinePhase = new PipelinePhase("BeforeReceive");
      client.getResponsePipeline().insertPhaseBefore(HttpResponsePipeline.Phases.getReceive(), BeforeReceive);
      client.getResponsePipeline().intercept(BeforeReceive, new 1(handler, null));
   }
}
