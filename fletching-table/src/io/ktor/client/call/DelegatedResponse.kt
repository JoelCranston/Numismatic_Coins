package io.ktor.client.call

import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext

internal class DelegatedResponse(call: HttpClientCall, origin: HttpResponse, content: (HttpResponse) -> ByteReadChannel, headers: Headers = origin.getHeaders())
   : HttpResponse {
   public open val call: HttpClientCall
   private final val origin: HttpResponse
   private final val content: (HttpResponse) -> ByteReadChannel
   public open val headers: Headers

   public open val rawContent: ByteReadChannel
      public open get() {
         return this.content.invoke(this.origin);
      }


   public open val coroutineContext: CoroutineContext

   public open val status: HttpStatusCode
      public open get() {
         return this.origin.getStatus();
      }


   public open val version: HttpProtocolVersion
      public open get() {
         return this.origin.getVersion();
      }


   public open val requestTime: GMTDate
      public open get() {
         return this.origin.getRequestTime();
      }


   public open val responseTime: GMTDate
      public open get() {
         return this.origin.getResponseTime();
      }


   init {
      this.call = call;
      this.origin = origin;
      this.content = content;
      this.headers = headers;
      this.coroutineContext = this.origin.getCoroutineContext();
   }
}
