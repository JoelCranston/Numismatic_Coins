package io.ktor.client.request

import io.ktor.client.engine.HttpClientEngineCapability
import io.ktor.client.engine.HttpClientEngineCapabilityKt
import io.ktor.client.utils.EmptyContent
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.HttpMethod
import io.ktor.http.URLBuilder
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.Attributes
import io.ktor.util.AttributesJvmKt
import io.ktor.util.AttributesKt
import io.ktor.util.StringValuesKt
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.InternalAPI
import java.util.LinkedHashMap
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorKt

public class HttpRequestBuilder : HttpMessageBuilder {
   public final val url: URLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null)
   public final var method: HttpMethod = HttpMethod.Companion.getGet()
   public open val headers: HeadersBuilder = new HeadersBuilder(0, 1, null)
   public final var body: Any = EmptyContent.INSTANCE

   public final var bodyType: TypeInfo?
      public final get() {
         return this.attributes.getOrNull(RequestBodyKt.getBodyTypeAttributeKey());
      }

      public final set(value) {
         if (value != null) {
            this.attributes.put(RequestBodyKt.getBodyTypeAttributeKey(), value);
         } else {
            this.attributes.remove(RequestBodyKt.getBodyTypeAttributeKey());
         }
      }


   public final var executionContext: Job = SupervisorKt.SupervisorJob$default(null, 1, null) as Job
      public final set(value) {
         this.executionContext = var1;
      }


   public final val attributes: Attributes = AttributesJvmKt.Attributes(true)

   public fun url(block: (URLBuilder, URLBuilder) -> Unit) {
      block.invoke(this.url, this.url);
   }

   public fun build(): HttpRequestData {
      val var10000: HttpRequestData = new HttpRequestData;
      val var10002: Url = this.url.build();
      val var10003: HttpMethod = this.method;
      val var10004: Headers = this.getHeaders().build();
      val var1: Any = this.body;
      val var10005: OutgoingContent = this.body as? OutgoingContent;
      if ((this.body as? OutgoingContent) == null) {
         throw new IllegalStateException(("No request transformation found: ${this.body}").toString());
      } else {
         var10000./* $VF: Unable to resugar constructor */<init>(var10002, var10003, var10004, var10005, this.executionContext, this.attributes);
         return var10000;
      }
   }

   public fun setAttributes(block: (Attributes) -> Unit) {
      block.invoke(this.attributes);
   }

   @InternalAPI
   public fun takeFromWithExecutionContext(builder: HttpRequestBuilder): HttpRequestBuilder {
      this.executionContext = builder.executionContext;
      return this.takeFrom(builder);
   }

   public fun takeFrom(builder: HttpRequestBuilder): HttpRequestBuilder {
      this.method = builder.method;
      this.body = builder.body;
      this.setBodyType(builder.getBodyType());
      URLUtilsKt.takeFrom(this.url, builder.url);
      this.url.setEncodedPathSegments(this.url.getEncodedPathSegments());
      StringValuesKt.appendAll(this.getHeaders(), builder.getHeaders());
      AttributesKt.putAll(this.attributes, builder.attributes);
      return this;
   }

   public fun <T : Any> setCapability(key: HttpClientEngineCapability<Any>, capability: Any) {
      this.attributes
         .computeIfAbsent(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY(), HttpRequestBuilder::setCapability$lambda$0)
         .put(key, capability);
   }

   public fun <T : Any> getCapabilityOrNull(key: HttpClientEngineCapability<Any>): Any? {
      val var10000: java.util.Map = this.attributes.getOrNull(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY());
      return (T)(if (var10000 != null) var10000.get(key) else null);
   }

   @JvmStatic
   fun `setCapability$lambda$0`(): java.util.Map {
      return new LinkedHashMap<>();
   }

   public companion object
}
