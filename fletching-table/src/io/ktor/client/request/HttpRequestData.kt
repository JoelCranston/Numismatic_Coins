package io.ktor.client.request

import io.ktor.client.engine.HttpClientEngineCapability
import io.ktor.client.engine.HttpClientEngineCapabilityKt
import io.ktor.http.Headers
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.Job

public class HttpRequestData @InternalAPI  public constructor(url: Url,
   method: HttpMethod,
   headers: Headers,
   body: OutgoingContent,
   executionContext: Job,
   attributes: Attributes
) {
   public final val url: Url
   public final val method: HttpMethod
   public final val headers: Headers
   public final val body: OutgoingContent
   public final val executionContext: Job
   public final val attributes: Attributes
   internal final val requiredCapabilities: Set<HttpClientEngineCapability<*>>

   init {
      var var7: java.util.Set;
      label11: {
         super();
         this.url = url;
         this.method = method;
         this.headers = headers;
         this.body = body;
         this.executionContext = executionContext;
         this.attributes = attributes;
         val var10001: java.util.Map = this.attributes.getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
         if (var10001 != null) {
            var7 = var10001.keySet();
            if (var7 != null) {
               break label11;
            }
         }

         var7 = SetsKt.emptySet();
      }

      this.requiredCapabilities = var7;
   }

   public fun <T> getCapabilityOrNull(key: HttpClientEngineCapability<Any>): Any? {
      val var10000: java.util.Map = this.attributes.getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
      return (T)(if (var10000 != null) var10000.get(key) else null);
   }

   public override fun toString(): String {
      return "HttpRequestData(url=${this.url}, method=${this.method})";
   }
}
