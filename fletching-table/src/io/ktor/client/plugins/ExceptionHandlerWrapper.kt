package io.ktor.client.plugins

import kotlin.coroutines.Continuation

internal class ExceptionHandlerWrapper(handler: (Throwable, Continuation<Unit>) -> Any?) : HandlerWrapper {
   public final val handler: (Throwable, Continuation<Unit>) -> Any?

   init {
      this.handler = handler;
   }
}
