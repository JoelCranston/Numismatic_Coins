package io.ktor.client.plugins

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse

public class HttpRetryModifyRequestContext internal constructor(request: HttpRequestBuilder, response: HttpResponse?, cause: Throwable?, retryCount: Int) {
   public final val request: HttpRequestBuilder
   public final val response: HttpResponse?
   public final val cause: Throwable?
   public final val retryCount: Int

   init {
      this.request = request;
      this.response = response;
      this.cause = cause;
      this.retryCount = retryCount;
   }
}
