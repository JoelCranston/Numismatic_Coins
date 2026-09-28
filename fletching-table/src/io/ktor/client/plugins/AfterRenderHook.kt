package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.plugins.AfterRenderHook.install.1
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.http.content.OutgoingContent
import io.ktor.util.pipeline.PipelinePhase
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

internal object AfterRenderHook :
   ClientHook<Function3<? super HttpRequestBuilder, ? super OutgoingContent, ? super Continuation<? super OutgoingContent>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (HttpRequestBuilder, OutgoingContent, Continuation<OutgoingContent?>) -> Any?) {
      val observableContentPhase: PipelinePhase = new PipelinePhase("ObservableContent");
      client.getRequestPipeline().insertPhaseAfter(HttpRequestPipeline.Phases.getRender(), observableContentPhase);
      client.getRequestPipeline().intercept(observableContentPhase, new 1(handler, null));
   }
}
