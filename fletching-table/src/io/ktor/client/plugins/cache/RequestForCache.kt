package io.ktor.client.plugins.cache

import io.ktor.client.call.HttpClientCall
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestData
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes

private class RequestForCache(data: HttpRequestData) : HttpRequest {
   public open val call: HttpClientCall
      public open get() {
         throw new IllegalStateException("This request has no call");
      }


   public open val method: HttpMethod
   public open val url: Url
   public open val attributes: Attributes
   public open val content: OutgoingContent
   public open val headers: Headers

   init {
      this.method = data.getMethod();
      this.url = data.getUrl();
      this.attributes = data.getAttributes();
      this.content = data.getBody();
      this.headers = data.getHeaders();
   }
}
