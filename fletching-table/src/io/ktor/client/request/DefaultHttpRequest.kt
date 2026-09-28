package io.ktor.client.request

import io.ktor.client.call.HttpClientCall
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext

@InternalAPI
public open class DefaultHttpRequest(call: HttpClientCall, data: HttpRequestData) : HttpRequest {
   public open val call: HttpClientCall

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.getCall().getCoroutineContext();
      }


   public open val method: HttpMethod
   public open val url: Url
   public open val content: OutgoingContent
   public open val headers: Headers
   public open val attributes: Attributes

   init {
      this.call = call;
      this.method = data.getMethod();
      this.url = data.getUrl();
      this.content = data.getBody();
      this.headers = data.getHeaders();
      this.attributes = data.getAttributes();
   }
}
