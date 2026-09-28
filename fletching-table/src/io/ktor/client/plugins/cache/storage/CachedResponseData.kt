package io.ktor.client.plugins.cache.storage

import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.Url
import io.ktor.util.date.GMTDate

public class CachedResponseData(url: Url,
   statusCode: HttpStatusCode,
   requestTime: GMTDate,
   responseTime: GMTDate,
   version: HttpProtocolVersion,
   expires: GMTDate,
   headers: Headers,
   varyKeys: Map<String, String>,
   body: ByteArray
) {
   public final val url: Url
   public final val statusCode: HttpStatusCode
   public final val requestTime: GMTDate
   public final val responseTime: GMTDate
   public final val version: HttpProtocolVersion
   public final val expires: GMTDate
   public final val headers: Headers
   public final val varyKeys: Map<String, String>
   public final val body: ByteArray

   init {
      this.url = url;
      this.statusCode = statusCode;
      this.requestTime = requestTime;
      this.responseTime = responseTime;
      this.version = version;
      this.expires = expires;
      this.headers = headers;
      this.varyKeys = varyKeys;
      this.body = body;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is CachedResponseData) {
         return false;
      } else if (!(this.url == (other as CachedResponseData).url)) {
         return false;
      } else {
         return this.varyKeys == (other as CachedResponseData).varyKeys;
      }
   }

   public override fun hashCode(): Int {
      return 31 * this.url.hashCode() + this.varyKeys.hashCode();
   }

   internal fun copy(varyKeys: Map<String, String>, expires: GMTDate): CachedResponseData {
      return new CachedResponseData(this.url, this.statusCode, this.requestTime, this.responseTime, this.version, expires, this.headers, varyKeys, this.body);
   }
}
