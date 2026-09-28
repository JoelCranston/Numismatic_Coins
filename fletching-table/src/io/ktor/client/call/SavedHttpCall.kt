package io.ktor.client.call

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpMessagePropertiesKt

internal class SavedHttpCall(client: HttpClient, request: HttpRequest, response: HttpResponse, responseBody: ByteArray) : HttpClientCall(client) {
   private final val responseBody: ByteArray
   protected open val allowDoubleReceive: Boolean

   init {
      this.responseBody = responseBody;
      this.setRequest(new SavedHttpRequest(this, request));
      this.setResponse(new SavedHttpResponse(this, this.responseBody, response));
      UtilsKt.checkContentLength(HttpMessagePropertiesKt.contentLength(response), (long)this.responseBody.length, request.getMethod());
      this.allowDoubleReceive = true;
   }
}
