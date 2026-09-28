package io.ktor.client.statement

import io.ktor.client.call.HttpClientCall
import io.ktor.client.request.HttpResponseData
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext

@InternalAPI
public class DefaultHttpResponse(call: HttpClientCall, responseData: HttpResponseData) : HttpResponse {
   public open val call: HttpClientCall
   public open val coroutineContext: CoroutineContext
   public open val status: HttpStatusCode
   public open val version: HttpProtocolVersion
   public open val requestTime: GMTDate
   public open val responseTime: GMTDate
   public open val rawContent: ByteReadChannel
   public open val headers: Headers

   init {
      this.call = call;
      this.coroutineContext = responseData.getCallContext();
      this.status = responseData.getStatusCode();
      this.version = responseData.getVersion();
      this.requestTime = responseData.getRequestTime();
      this.responseTime = responseData.getResponseTime();
      val var3: Any = responseData.getBody();
      var var10001: ByteReadChannel = var3 as? ByteReadChannel;
      if ((var3 as? ByteReadChannel) == null) {
         var10001 = ByteReadChannel.Companion.getEmpty();
      }

      this.rawContent = var10001;
      this.headers = responseData.getHeaders();
   }
}
