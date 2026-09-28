package io.ktor.client.plugins

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

public class HttpRetryDelayContext internal constructor(request: HttpRequestBuilder, response: HttpResponse?, cause: Throwable?) {
   public final val request: HttpRequestBuilder
   public final val response: HttpResponse?
   public final val cause: Throwable?

   init {
      this.request = request;
      this.response = response;
      this.cause = cause;
   }
}
