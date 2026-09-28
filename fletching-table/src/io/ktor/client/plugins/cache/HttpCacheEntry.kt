package io.ktor.client.plugins.cache

import io.ktor.client.call.SavedHttpCall
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.util.date.GMTDate
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nHttpCacheEntry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCacheEntry.kt\nio/ktor/client/plugins/cache/HttpCacheEntry\n+ 2 Headers.kt\nio/ktor/http/Headers$Companion\n*L\n1#1,145:1\n30#2:146\n*S KotlinDebug\n*F\n+ 1 HttpCacheEntry.kt\nio/ktor/client/plugins/cache/HttpCacheEntry\n*L\n32#1:146\n*E\n"])
public class HttpCacheEntry internal constructor(expires: GMTDate, varyKeys: Map<String, String>, response: HttpResponse, body: ByteArray) {
   public final val expires: GMTDate
   public final val varyKeys: Map<String, String>
   public final val response: HttpResponse
   public final val body: ByteArray
   internal final val responseHeaders: Headers

   init {
      this.expires = expires;
      this.varyKeys = varyKeys;
      this.response = response;
      this.body = body;
      val `this_$iv`: Headers.Companion = Headers.Companion;
      val var7: HeadersBuilder = new HeadersBuilder(0, 1, null);
      var7.appendAll(this.response.getHeaders());
      this.responseHeaders = var7.build();
   }

   internal fun produceResponse(): HttpResponse {
      return new SavedHttpCall(this.response.getCall().getClient(), this.response.getCall().getRequest(), this.response, this.body).getResponse();
   }

   public override operator fun equals(other: Any?): Boolean {
      if (other == null || other !is HttpCacheEntry) {
         return false;
      } else {
         return other === this || this.varyKeys == (other as HttpCacheEntry).varyKeys;
      }
   }

   public override fun hashCode(): Int {
      return this.varyKeys.hashCode();
   }
}
