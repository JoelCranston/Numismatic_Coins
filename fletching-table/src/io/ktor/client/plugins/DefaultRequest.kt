package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineCapability
import io.ktor.client.engine.HttpClientEngineCapabilityKt
import io.ktor.client.plugins.DefaultRequest.Plugin.install.1
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.UnixSocketCapability
import io.ktor.client.request.UnixSocketSettings
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.ParametersKt
import io.ktor.http.URLBuilder
import io.ktor.http.URLBuilderKt
import io.ktor.http.URLParserKt
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.util.AttributesJvmKt
import io.ktor.util.StringValuesKt
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.KtorDsl
import java.util.LinkedHashMap
import java.util.Map.Entry
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@SourceDebugExtension(["SMAP\nDefaultRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultRequest.kt\nio/ktor/client/plugins/DefaultRequest\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,265:1\n21#2:266\n69#3:267\n84#3,8:268\n*S KotlinDebug\n*F\n+ 1 DefaultRequest.kt\nio/ktor/client/plugins/DefaultRequest\n*L\n67#1:266\n67#1:267\n67#1:268,8\n*E\n"])
public class DefaultRequest private constructor(block: (io.ktor.client.plugins.DefaultRequest.DefaultRequestBuilder) -> Unit) {
   private final val block: (io.ktor.client.plugins.DefaultRequest.DefaultRequestBuilder) -> Unit

   init {
      this.block = block;
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(DefaultRequest.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("DefaultRequest", new TypeInfo(DefaultRequest::class, var6));
   }

   @KtorDsl
   public class DefaultRequestBuilder internal constructor() : HttpMessageBuilder {
      public open val headers: HeadersBuilder = new HeadersBuilder(0, 1, null)
      public final val url: URLBuilder = new URLBuilder(null, null, 0, null, null, null, null, null, false, 511, null)
      public final val attributes: Attributes = AttributesJvmKt.Attributes(true)

      public final var host: String
         public final get() {
            return this.url.getHost();
         }

         public final set(value) {
            if (StringsKt.contains$default(value, "/", false, 2, null)
               || StringsKt.contains$default(value, "?", false, 2, null)
               || StringsKt.contains$default(value, "#", false, 2, null)) {
               DefaultRequestKt.access$getLOGGER$p()
                  .warn(
                     "DefaultRequest.host was set to '$value', which is not a valid host. Host must not contain scheme, path, query or fragment. Use `url(...)` or `url{ ... }` instead."
                  );
            }

            this.url.setHost(value);
         }


      public final var port: Int
         public final get() {
            return this.url.getPort();
         }

         public final set(value) {
            this.url.setPort(value);
         }


      public fun url(block: (URLBuilder) -> Unit) {
         block.invoke(this.url);
      }

      public fun url(
         scheme: String? = null,
         host: String? = null,
         port: Int? = null,
         path: String? = null,
         block: (URLBuilder) -> Unit = DefaultRequest.DefaultRequestBuilder::url$lambda$0
      ) {
         URLBuilderKt.set(this.url, scheme, host, port, path, block);
      }

      public fun url(urlString: String) {
         URLParserKt.takeFrom(this.url, urlString);
      }

      public fun setAttributes(block: (Attributes) -> Unit) {
         block.invoke(this.attributes);
      }

      public fun <T : Any> setCapability(key: HttpClientEngineCapability<Any>, capability: Any) {
         this.attributes
            .computeIfAbsent(HttpClientEngineCapabilityKt.getENGINE_CAPABILITIES_KEY(), DefaultRequest.DefaultRequestBuilder::setCapability$lambda$0)
            .put(key, capability);
      }

      public fun unixSocket(path: String) {
         this.setCapability(UnixSocketCapability.INSTANCE, new UnixSocketSettings(path));
      }

      @JvmStatic
      fun `url$lambda$0`(var0: URLBuilder): Unit {
         return Unit.INSTANCE;
      }

      @JvmStatic
      fun `setCapability$lambda$0`(): java.util.Map {
         return new LinkedHashMap<>();
      }
   }

   @SourceDebugExtension(["SMAP\nDefaultRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DefaultRequest.kt\nio/ktor/client/plugins/DefaultRequest$Plugin\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,265:1\n1869#2,2:266\n*S KotlinDebug\n*F\n+ 1 DefaultRequest.kt\nio/ktor/client/plugins/DefaultRequest$Plugin\n*L\n135#1:266,2\n*E\n"])
   public companion object Plugin : HttpClientPlugin<DefaultRequest.DefaultRequestBuilder, DefaultRequest> {
      public open val key: AttributeKey<DefaultRequest>

      public open fun prepare(block: (io.ktor.client.plugins.DefaultRequest.DefaultRequestBuilder) -> Unit): DefaultRequest {
         return new DefaultRequest(block, null);
      }

      public open fun install(plugin: DefaultRequest, scope: HttpClient) {
         scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getBefore(), new 1(plugin, null));
      }

      private fun mergeUrls(baseUrl: Url, requestUrl: URLBuilder) {
         if (requestUrl.getProtocolOrNull() == null) {
            requestUrl.setProtocolOrNull(baseUrl.getProtocolOrNull());
         }

         if (requestUrl.getHost().length() <= 0) {
            val resultUrl: URLBuilder = URLUtilsKt.URLBuilder(baseUrl);
            resultUrl.setProtocolOrNull(requestUrl.getProtocolOrNull());
            if (requestUrl.getPort() != 0) {
               resultUrl.setPort(requestUrl.getPort());
            }

            resultUrl.setEncodedPathSegments(DefaultRequest.Plugin.concatenatePath(resultUrl.getEncodedPathSegments(), requestUrl.getEncodedPathSegments()));
            if (requestUrl.getEncodedFragment().length() > 0) {
               resultUrl.setEncodedFragment(requestUrl.getEncodedFragment());
            }

            StringValuesKt.appendAll(ParametersKt.ParametersBuilder$default(0, 1, null), resultUrl.getEncodedParameters());
            resultUrl.setEncodedParameters(requestUrl.getEncodedParameters());

            val var15: java.lang.Iterable;
            for (Object element$iv : var15) {
               val key: java.lang.String = (`element$iv` as Entry).getKey() as java.lang.String;
               val values: java.util.List = (`element$iv` as Entry).getValue() as java.util.List;
               if (!resultUrl.getEncodedParameters().contains(key)) {
                  resultUrl.getEncodedParameters().appendAll(key, values);
               }
            }

            URLUtilsKt.takeFrom(requestUrl, resultUrl);
         }
      }

      private fun concatenatePath(parent: List<String>, child: List<String>): List<String> {
         if (child.isEmpty()) {
            return parent;
         } else if (parent.isEmpty()) {
            return child;
         } else if (CollectionsKt.<java.lang.CharSequence>first(child).length() == 0) {
            return child;
         } else {
            val var3: java.util.List = CollectionsKt.createListBuilder(parent.size() + child.size() - 1);
            val `$this$concatenatePath_u24lambda_u240`: java.util.List = var3;
            var index: Int = 0;

            for (int var7 = parent.size() - 1; index < var7; index++) {
               `$this$concatenatePath_u24lambda_u240`.add(parent.get(index));
            }

            `$this$concatenatePath_u24lambda_u240`.addAll(child);
            return CollectionsKt.build(var3);
         }
      }
   }
}
