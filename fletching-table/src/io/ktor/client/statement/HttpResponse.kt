package io.ktor.client.statement

import io.ktor.client.call.HttpClientCall
import io.ktor.http.HttpMessage
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.CoroutineScope

public abstract class HttpResponse : HttpMessage, CoroutineScope {
   public abstract val call: HttpClientCall
   public abstract val status: HttpStatusCode
   public abstract val version: HttpProtocolVersion
   public abstract val requestTime: GMTDate
   public abstract val responseTime: GMTDate

   @InternalAPI
   public abstract val rawContent: ByteReadChannel

   public override fun toString(): String {
      return "HttpResponse[${HttpResponseKt.getRequest(this).getUrl()}, ${this.getStatus()}]";
   }
}
