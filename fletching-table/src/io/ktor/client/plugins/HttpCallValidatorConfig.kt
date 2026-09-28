package io.ktor.client.plugins

import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.utils.io.KtorDsl
import java.util.ArrayList
import kotlin.coroutines.Continuation

@KtorDsl
public class HttpCallValidatorConfig {
   internal final val responseValidators: MutableList<(HttpResponse, Continuation<Unit>) -> Any?> = (new ArrayList()) as java.util.List
   internal final val responseExceptionHandlers: MutableList<HandlerWrapper> = (new ArrayList()) as java.util.List
   internal final var expectSuccess: Boolean = true

   public fun handleResponseException(block: (Throwable, HttpRequest, Continuation<Unit>) -> Any?) {
      this.responseExceptionHandlers.add(new RequestExceptionHandlerWrapper(block));
   }

   public fun handleResponseExceptionWithRequest(block: (Throwable, HttpRequest, Continuation<Unit>) -> Any?) {
      this.responseExceptionHandlers.add(new RequestExceptionHandlerWrapper(block));
   }

   public fun validateResponse(block: (HttpResponse, Continuation<Unit>) -> Any?) {
      this.responseValidators.add(block);
   }
}
