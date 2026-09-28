package io.ktor.client.request

import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.GMTDate
import kotlin.coroutines.CoroutineContext

public class HttpResponseData(statusCode: HttpStatusCode,
   requestTime: GMTDate,
   headers: Headers,
   version: HttpProtocolVersion,
   body: Any,
   callContext: CoroutineContext
) {
   public final val statusCode: HttpStatusCode
   public final val requestTime: GMTDate
   public final val headers: Headers
   public final val version: HttpProtocolVersion
   public final val body: Any
   public final val callContext: CoroutineContext
   public final val responseTime: GMTDate

   init {
      this.statusCode = statusCode;
      this.requestTime = requestTime;
      this.headers = headers;
      this.version = version;
      this.body = body;
      this.callContext = callContext;
      this.responseTime = DateJvmKt.GMTDate$default(null, 1, null);
   }

   public override fun toString(): String {
      return "HttpResponseData=(statusCode=${this.statusCode})";
   }
}
