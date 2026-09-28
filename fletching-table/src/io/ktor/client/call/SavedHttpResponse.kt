package io.ktor.client.call

import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteChannelCtorKt
import io.ktor.utils.io.ByteReadChannel
import kotlin.coroutines.CoroutineContext

internal class SavedHttpResponse(call: SavedHttpCall, body: ByteArray, origin: HttpResponse) : HttpResponse {
   public open val call: SavedHttpCall
   private final val body: ByteArray
   public open val status: HttpStatusCode
   public open val version: HttpProtocolVersion
   public open val requestTime: GMTDate
   public open val responseTime: GMTDate
   public open val headers: Headers
   public open val coroutineContext: CoroutineContext

   public open val rawContent: ByteReadChannel
      public open get() {
         return ByteChannelCtorKt.ByteReadChannel$default(this.body, 0, 0, 6, null);
      }


   init {
      this.call = call;
      this.body = body;
      this.status = origin.getStatus();
      this.version = origin.getVersion();
      this.requestTime = origin.getRequestTime();
      this.responseTime = origin.getResponseTime();
      this.headers = origin.getHeaders();
      this.coroutineContext = origin.getCoroutineContext();
   }
}
