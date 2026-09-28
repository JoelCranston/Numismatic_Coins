package io.ktor.client.plugins

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

public class HttpRetryEventData internal constructor(request: HttpRequestBuilder, retryCount: Int, response: HttpResponse?, cause: Throwable?) {
   public final val request: HttpRequestBuilder
   public final val retryCount: Int
   public final val response: HttpResponse?
   public final val cause: Throwable?

   init {
      this.request = request;
      this.retryCount = retryCount;
      this.response = response;
      this.cause = cause;
   }
}
