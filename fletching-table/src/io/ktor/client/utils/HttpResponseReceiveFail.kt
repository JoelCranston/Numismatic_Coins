package io.ktor.client.utils

import io.ktor.client.statement.HttpResponse

public class HttpResponseReceiveFail(response: HttpResponse, cause: Throwable) {
   public final val response: HttpResponse
   public final val cause: Throwable

   init {
      this.response = response;
      this.cause = cause;
   }
}
