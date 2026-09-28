package io.ktor.client.plugins.observer

import io.ktor.client.HttpClient
import io.ktor.client.plugins.api.ClientHook
import io.ktor.client.plugins.observer.AfterReceiveHook.install.1
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.util.pipeline.PipelineContext
import kotlin.coroutines.Continuation
import kotlin.jvm.functions.Function3

private object AfterReceiveHook :
   ClientHook<Function3<? super AfterReceiveHook.Context, ? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (io.ktor.client.plugins.observer.AfterReceiveHook.Context, HttpResponse, Continuation<Unit>) -> Any?) {
      client.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getAfter(), new 1(handler, null));
   }

   public class Context(context: PipelineContext<HttpResponse, Unit>) {
      private final val context: PipelineContext<HttpResponse, Unit>

      init {
         this.context = context;
      }

      public suspend fun proceedWith(response: HttpResponse): HttpResponse {
         return this.context.proceedWith(response, `$completion`);
      }
   }
}
