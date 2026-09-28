package io.ktor.client.call

import io.ktor.client.request.HttpRequest
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes
import kotlin.coroutines.CoroutineContext

internal class DelegatedRequest(call: HttpClientCall, origin: HttpRequest) : HttpRequest {
   public open val call: HttpClientCall
   public open val attributes: Attributes
   public open val content: OutgoingContent

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.$$delegate_0.getCoroutineContext();
      }


   public open val headers: Headers
   public open val method: HttpMethod
   public open val url: Url

   init {
      this.$$delegate_0 = origin;
      this.call = call;
   }
}
