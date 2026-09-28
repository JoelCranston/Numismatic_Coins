package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.api.Send.install.1
import io.ktor.client.request.HttpRequestBuilder
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.functions.Function3
import kotlinx.coroutines.CoroutineScope

public object Send : ClientHook<Function3<? super Send.Sender, ? super HttpRequestBuilder, ? super Continuation<? super HttpClientCall>, ? extends Object>> {
   public open fun install(client: HttpClient, handler: (io.ktor.client.plugins.api.Send.Sender, HttpRequestBuilder, Continuation<HttpClientCall>) -> Any?) {
      HttpClientPluginKt.plugin(client, HttpSend.Plugin).intercept(new 1(handler, client, null));
   }

   public class Sender internal constructor(httpSendSender: io.ktor.client.plugins.Sender, coroutineContext: CoroutineContext) : CoroutineScope {
      private final val httpSendSender: io.ktor.client.plugins.Sender
      public open val coroutineContext: CoroutineContext

      init {
         this.httpSendSender = httpSendSender;
         this.coroutineContext = coroutineContext;
      }

      public suspend fun proceed(requestBuilder: HttpRequestBuilder): HttpClientCall {
         return this.httpSendSender.execute(requestBuilder, `$completion`);
      }
   }
}
