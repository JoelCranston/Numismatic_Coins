package io.ktor.client.plugins.sse

import io.ktor.client.statement.HttpResponse

public class SSEClientException(response: HttpResponse? = null, cause: Throwable? = null, message: String? = null) : IllegalStateException {
   public final val response: HttpResponse?
   public open val cause: Throwable?
   public open val message: String?

   init {
      this.response = response;
      this.cause = cause;
      this.message = message;
   }

   fun SSEClientException() {
      this(null, null, null, 7, null);
   }
}
