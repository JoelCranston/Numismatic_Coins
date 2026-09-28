package io.ktor.client.plugins

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestData
import java.io.IOException
import kotlinx.coroutines.CopyableThrowable

public class HttpRequestTimeoutException(url: String, timeoutMillis: Long?, cause: Throwable? = null)
   : IOException,
   CopyableThrowable<HttpRequestTimeoutException> {
   private final val url: String
   private final val timeoutMillis: Long?

   init {
      val var10001: StringBuilder = new StringBuilder().append("Request timeout has expired [url=").append(url).append(", request_timeout=");
      var var10002: Any = timeoutMillis;
      if (timeoutMillis == null) {
         var10002 = "unknown";
      }

      super(var10001.append(var10002).append(" ms]").toString(), cause);
      this.url = url;
      this.timeoutMillis = timeoutMillis;
   }

   public constructor(request: HttpRequestBuilder)  {
      val var10001: java.lang.String = request.getUrl().buildString();
      val var10002: HttpTimeoutConfig = request.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
      this(var10001, if (var10002 != null) var10002.getRequestTimeoutMillis() else null, null, 4, null);
   }

   public constructor(request: HttpRequestData)  {
      val var10001: java.lang.String = request.getUrl().toString();
      val var10002: HttpTimeoutConfig = request.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
      this(var10001, if (var10002 != null) var10002.getRequestTimeoutMillis() else null, null, 4, null);
   }

   public open fun createCopy(): HttpRequestTimeoutException {
      return new HttpRequestTimeoutException(this.url, this.timeoutMillis, this.getCause());
   }
}
