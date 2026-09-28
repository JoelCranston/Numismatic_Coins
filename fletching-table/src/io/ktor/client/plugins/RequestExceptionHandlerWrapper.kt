package io.ktor.client.plugins

import io.ktor.client.request.HttpRequest
import kotlin.coroutines.Continuation

internal class RequestExceptionHandlerWrapper(handler: (Throwable, HttpRequest, Continuation<Unit>) -> Any?) : HandlerWrapper {
   public final val handler: (Throwable, HttpRequest, Continuation<Unit>) -> Any?

   init {
      this.handler = handler;
   }
}
