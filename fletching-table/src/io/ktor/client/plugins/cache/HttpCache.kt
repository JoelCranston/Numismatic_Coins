package io.ktor.client.plugins.cache

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.cache.HttpCache.findAndRefresh.1
import io.ktor.client.plugins.cache.HttpCache.findResponse.4
import io.ktor.client.plugins.cache.HttpCache.findResponse.requestHeaders.2
import io.ktor.client.plugins.cache.storage.CacheStorage
import io.ktor.client.plugins.cache.storage.CachedResponseData
import io.ktor.client.plugins.cache.storage.HttpCacheStorage
import io.ktor.client.plugins.cache.storage.HttpCacheStorageKt
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.events.EventDefinition
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.AttributeKey
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.GMTDate
import io.ktor.util.pipeline.PipelineContext
import io.ktor.util.pipeline.PipelinePhase
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteChannelCtorKt
import io.ktor.utils.io.KtorDsl
import java.util.Map.Entry
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@SourceDebugExtension(["SMAP\nHttpCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCache.kt\nio/ktor/client/plugins/cache/HttpCache\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 4 Attributes.kt\nio/ktor/util/AttributesKt\n+ 5 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,417:1\n1068#2:418\n295#2:419\n296#2:423\n168#3,3:420\n168#3,3:424\n21#4:427\n69#5:428\n84#5,8:429\n*S KotlinDebug\n*F\n+ 1 HttpCache.kt\nio/ktor/client/plugins/cache/HttpCache\n*L\n360#1:418\n360#1:419\n360#1:423\n361#1:420,3\n373#1:424,3\n142#1:427\n142#1:428\n142#1:429,8\n*E\n"])
public class HttpCache private constructor(publicStorage: HttpCacheStorage,
   privateStorage: HttpCacheStorage,
   publicStorageNew: CacheStorage,
   privateStorageNew: CacheStorage,
   useOldStorage: Boolean,
   isSharedClient: Boolean
) {
   @Deprecated(
      message = "This will become internal",
      level = DeprecationLevel.ERROR
   )
   internal final val publicStorage: HttpCacheStorage

   @Deprecated(
      message = "This will become internal",
      level = DeprecationLevel.ERROR
   )
   internal final val privateStorage: HttpCacheStorage

   private final val publicStorageNew: CacheStorage
   private final val privateStorageNew: CacheStorage
   private final val useOldStorage: Boolean
   internal final val isSharedClient: Boolean

   init {
      this.publicStorage = publicStorage;
      this.privateStorage = privateStorage;
      this.publicStorageNew = publicStorageNew;
      this.privateStorageNew = privateStorageNew;
      this.useOldStorage = useOldStorage;
      this.isSharedClient = isSharedClient;
   }

   private suspend fun cacheResponse(response: HttpResponse): CachedResponseData? {
      val request: HttpRequest = response.getCall().getRequest();
      val responseCacheControl: java.util.List = HttpMessagePropertiesKt.cacheControl(response);
      val requestCacheControl: java.util.List = HttpMessagePropertiesKt.cacheControl(request);
      val isPrivate: Boolean = responseCacheControl.contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core());
      if (isPrivate && this.isSharedClient) {
         return null;
      } else {
         val storage: CacheStorage = if (isPrivate) this.privateStorageNew else this.publicStorageNew;
         return if (!responseCacheControl.contains(CacheControl.INSTANCE.getNO_STORE$ktor_client_core())
               && !requestCacheControl.contains(CacheControl.INSTANCE.getNO_STORE$ktor_client_core()))
            HttpCacheStorageKt.store(storage, response, HttpCacheEntryKt.varyKeys(response), this.isSharedClient, `$completion`)
            else
            null;
      }
   }

   private suspend fun findAndRefresh(request: HttpRequest, response: HttpResponse): HttpResponse? {
      var `$continuation`: Continuation;
      label42: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label42;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var12: Url;
      var var13: java.util.List;
      var var14: Boolean;
      var var15: CacheStorage;
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var12 = response.getCall().getRequest().getUrl();
            var13 = HttpMessagePropertiesKt.cacheControl(response);
            var14 = var13.contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core());
            if (var14 && this.isSharedClient) {
               return null;
            }

            var15 = if (var14) this.privateStorageNew else this.publicStorageNew;
            val var10002: java.util.Map = HttpCacheEntryKt.varyKeys(response);
            `$continuation`.L$0 = request;
            `$continuation`.L$1 = response;
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var12);
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var13);
            `$continuation`.L$4 = var15;
            `$continuation`.Z$0 = var14;
            `$continuation`.label = 1;
            var10000 = (CachedResponseData)this.findResponse(var15, var10002, var12, request, `$continuation`);
            if (var10000 === var11) {
               return var11;
            }
            break;
         case 1:
            var14 = `$continuation`.Z$0;
            var15 = `$continuation`.L$4 as CacheStorage;
            var13 = `$continuation`.L$3 as java.util.List;
            var12 = `$continuation`.L$2 as Url;
            response = `$continuation`.L$1 as HttpResponse;
            request = `$continuation`.L$0 as HttpRequest;
            ResultKt.throwOnFailure(`$result`);
            var10000 = (CachedResponseData)`$result`;
            break;
         case 2:
            var14 = `$continuation`.Z$0;
            val cache: CachedResponseData = `$continuation`.L$5 as CachedResponseData;
            var15 = `$continuation`.L$4 as CacheStorage;
            var13 = `$continuation`.L$3 as java.util.List;
            var12 = `$continuation`.L$2 as Url;
            response = `$continuation`.L$1 as HttpResponse;
            request = `$continuation`.L$0 as HttpRequest;
            ResultKt.throwOnFailure(`$result`);
            return HttpCacheStorageKt.createResponse(cache, request.getCall().getClient(), request, response.getCoroutineContext());
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var10000 = var10000;
      if (var10000 == null) {
         return null;
      } else {
         val var10001: Url = request.getUrl();
         val var17: CachedResponseData = var10000.copy$ktor_client_core(
            var10000.getVaryKeys(), HttpCacheEntryKt.cacheExpires$default(response, this.isSharedClient, null, 2, null)
         );
         `$continuation`.L$0 = request;
         `$continuation`.L$1 = response;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var12);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var13);
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var15);
         `$continuation`.L$5 = var10000;
         `$continuation`.Z$0 = var14;
         `$continuation`.label = 2;
         return if (var15.store(var10001, var17, `$continuation`) === var11)
            var11
            else
            HttpCacheStorageKt.createResponse(var10000, request.getCall().getClient(), request, response.getCoroutineContext());
      }
   }

   private suspend fun findResponse(storage: CacheStorage, varyKeys: Map<String, String>, url: Url, request: HttpRequest): CachedResponseData? {
      var `$continuation`: Continuation;
      label64: {
         if (`$completion` is io.ktor.client.plugins.cache.HttpCache.findResponse.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cache.HttpCache.findResponse.1;
            if (((`$completion` as io.ktor.client.plugins.cache.HttpCache.findResponse.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label64;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cache.HttpCache.findResponse.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var23: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var requestHeaders: Function1;
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            if (!varyKeys.isEmpty()) {
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(storage);
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(varyKeys);
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(url);
               `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(request);
               `$continuation`.label = 1;
               var10000 = storage.find(url, varyKeys, `$continuation`);
               if (var10000 === var23) {
                  return var23;
               }

               return var10000;
            }

            requestHeaders = HttpCacheKt.mergedHeadersLookup(
               request.getContent(),
               new io.ktor.client.plugins.cache.HttpCache.findResponse.requestHeaders.1(request.getHeaders()),
               new 2(request.getHeaders())
            );
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(storage);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(varyKeys);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(url);
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(request);
            `$continuation`.L$4 = requestHeaders;
            `$continuation`.label = 2;
            var10000 = storage.findAll(url, `$continuation`);
            if (var10000 === var23) {
               return var23;
            }
            break;
         case 1:
            request = `$continuation`.L$3 as HttpRequest;
            url = `$continuation`.L$2 as Url;
            varyKeys = `$continuation`.L$1 as java.util.Map;
            storage = `$continuation`.L$0 as CacheStorage;
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         case 2:
            requestHeaders = `$continuation`.L$4 as Function1;
            request = `$continuation`.L$3 as HttpRequest;
            url = `$continuation`.L$2 as Url;
            varyKeys = `$continuation`.L$1 as java.util.Map;
            storage = `$continuation`.L$0 as CacheStorage;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var9: java.util.Iterator = CollectionsKt.sortedWith(
            var10000 as java.lang.Iterable, new io.ktor.client.plugins.cache.HttpCache.findResponse..inlined.sortedByDescending.1()
         )
         .iterator();

      while (true) {
         if (!var9.hasNext()) {
            var10000 = null;
            break;
         }

         val `element$iv`: Any = var9.next();
         val `$this$all$iv`: java.util.Map = (`element$iv` as CachedResponseData).getVaryKeys();
         var var35: Boolean;
         if (`$this$all$iv`.isEmpty()) {
            var35 = true;
         } else {
            val var15: java.util.Iterator = `$this$all$iv`.entrySet().iterator();

            while (true) {
               if (!var15.hasNext()) {
                  var35 = true;
                  break;
               }

               val `element$ivx`: Entry = var15.next() as Entry;
               if (!(requestHeaders.invoke(`element$ivx`.getKey() as java.lang.String) == `element$ivx`.getValue() as java.lang.String)) {
                  var35 = false;
                  break;
               }
            }
         }

         if (var35) {
            var10000 = `element$iv`;
            break;
         }
      }

      return var10000 as CachedResponseData;
   }

   private suspend fun findResponse(context: HttpRequestBuilder, content: OutgoingContent): CachedResponseData? {
      var `$continuation`: Continuation;
      label59: {
         if (`$completion` is 4) {
            `$continuation` = `$completion` as 4;
            if (((`$completion` as 4).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label59;
            }
         }

         `$continuation` = new 4(this, `$completion`);
      }

      var lookup: Function1;
      var var18: java.util.Set;
      var var10000: CacheStorage;
      label53: {
         val `$result`: Any = `$continuation`.result;
         val var21: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var24: Url;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               var24 = URLUtilsKt.Url(context.getUrl());
               lookup = HttpCacheKt.mergedHeadersLookup(
                  content,
                  new io.ktor.client.plugins.cache.HttpCache.findResponse.lookup.1(context.getHeaders()),
                  new io.ktor.client.plugins.cache.HttpCache.findResponse.lookup.2(context.getHeaders())
               );
               var10000 = this.privateStorageNew;
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(context);
               `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(content);
               `$continuation`.L$2 = var24;
               `$continuation`.L$3 = lookup;
               `$continuation`.label = 1;
               var10000 = var10000.findAll(var24, `$continuation`);
               if (var10000 === var21) {
                  return var21;
               }
               break;
            case 1:
               lookup = `$continuation`.L$3 as Function1;
               var24 = `$continuation`.L$2 as Url;
               content = `$continuation`.L$1 as OutgoingContent;
               context = `$continuation`.L$0 as HttpRequestBuilder;
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            case 2:
               var18 = `$continuation`.L$4 as java.util.Set;
               lookup = `$continuation`.L$3 as Function1;
               var24 = `$continuation`.L$2 as Url;
               content = `$continuation`.L$1 as OutgoingContent;
               context = `$continuation`.L$0 as HttpRequestBuilder;
               ResultKt.throwOnFailure(`$result`);
               var10000 = (CacheStorage)`$result`;
               break label53;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         var18 = var10000 as java.util.Set;
         var10000 = this.publicStorageNew;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(context);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(content);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var24);
         `$continuation`.L$3 = lookup;
         `$continuation`.L$4 = var18;
         `$continuation`.label = 2;
         var10000 = (CacheStorage)var10000.findAll(var24, `$continuation`);
         if (var10000 === var21) {
            return var21;
         }
      }

      val var7: java.util.Iterator = SetsKt.plus(var18, var10000 as java.lang.Iterable).iterator();

      val item: CachedResponseData;
      label45:
      do {
         if (!var7.hasNext()) {
            return null;
         }

         item = var7.next() as CachedResponseData;
         val varyKeys: java.util.Map = item.getVaryKeys();
         if (varyKeys.isEmpty()) {
            break;
         }

         if (varyKeys.isEmpty()) {
            var28 = true;
         } else {
            for (Entry element$iv : varyKeys.entrySet()) {
               if (!(lookup.invoke(`element$iv`.getKey() as java.lang.String) == `element$iv`.getValue() as java.lang.String)) {
                  var28 = false;
                  continue label45;
               }
            }

            var28 = true;
         }
      } while (!var28);

      return item;
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(HttpCache.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("HttpCache", new TypeInfo(HttpCache::class, var6));
      HttpResponseFromCache = new EventDefinition<>();
   }

   @SourceDebugExtension(["SMAP\nHttpCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCache.kt\nio/ktor/client/plugins/cache/HttpCache$Companion\n+ 2 Headers.kt\nio/ktor/http/Headers$Companion\n*L\n1#1,417:1\n30#2:418\n*S KotlinDebug\n*F\n+ 1 HttpCache.kt\nio/ktor/client/plugins/cache/HttpCache$Companion\n*L\n280#1:418\n*E\n"])
   public companion object : HttpClientPlugin<HttpCache.Config, HttpCache> {
      public open val key: AttributeKey<HttpCache>
      public final val HttpResponseFromCache: EventDefinition<HttpResponse>

      public open fun prepare(block: (io.ktor.client.plugins.cache.HttpCache.Config) -> Unit): HttpCache {
         val `$this$prepare_u24lambda_u240`: HttpCache.Config = new HttpCache.Config();
         block.invoke(`$this$prepare_u24lambda_u240`);
         return new HttpCache(
            `$this$prepare_u24lambda_u240`.getPublicStorage(),
            `$this$prepare_u24lambda_u240`.getPrivateStorage(),
            `$this$prepare_u24lambda_u240`.getPublicStorageNew$ktor_client_core(),
            `$this$prepare_u24lambda_u240`.getPrivateStorageNew$ktor_client_core(),
            `$this$prepare_u24lambda_u240`.getUseOldStorage$ktor_client_core(),
            `$this$prepare_u24lambda_u240`.isShared(),
            null
         );
      }

      public open fun install(plugin: HttpCache, scope: HttpClient) {
         val cacheRequestPhase: PipelinePhase = new PipelinePhase("Cache");
         scope.getSendPipeline().insertPhaseAfter(HttpSendPipeline.Phases.getState(), cacheRequestPhase);
         scope.getSendPipeline().intercept(cacheRequestPhase, new io.ktor.client.plugins.cache.HttpCache.Companion.install.1(plugin, scope, null));
         val cacheResponsePhase: PipelinePhase = new PipelinePhase("Cache");
         scope.getReceivePipeline().insertPhaseAfter(HttpReceivePipeline.Phases.getState(), cacheResponsePhase);
         scope.getReceivePipeline().intercept(cacheResponsePhase, new io.ktor.client.plugins.cache.HttpCache.Companion.install.2(plugin, scope, null));
      }

      internal suspend fun PipelineContext<Any, HttpRequestBuilder>.proceedWithCache(scope: HttpClient, cachedCall: HttpClientCall) {
         `$this$proceedWithCache`.finish();
         scope.getMonitor().raise(this.getHttpResponseFromCache(), cachedCall.getResponse());
         val var10000: Any = `$this$proceedWithCache`.proceedWith(cachedCall, `$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }

      private suspend fun PipelineContext<Any, HttpRequestBuilder>.proceedWithWarning(
         cachedResponse: CachedResponseData,
         scope: HttpClient,
         callContext: CoroutineContext
      ) {
         val request: HttpRequestData = (`$this$proceedWithWarning`.getContext() as HttpRequestBuilder).build();
         var var10000: HttpStatusCode = cachedResponse.getStatusCode();
         val var10001: GMTDate = cachedResponse.getRequestTime();
         val call: Headers.Companion = Headers.Companion;
         val var10: HeadersBuilder = new HeadersBuilder(0, 1, null);
         var10.appendAll(cachedResponse.getHeaders());
         var10.append(HttpHeaders.INSTANCE.getWarning(), "110");
         val var22: HttpClientCall = new HttpClientCall(
            scope,
            request,
            new HttpResponseData(
               var10000,
               var10001,
               var10.build(),
               cachedResponse.getVersion(),
               ByteChannelCtorKt.ByteReadChannel$default(cachedResponse.getBody(), 0, 0, 6, null),
               callContext
            )
         );
         `$this$proceedWithWarning`.finish();
         scope.getMonitor().raise(this.getHttpResponseFromCache(), var22.getResponse());
         var10000 = (HttpStatusCode)`$this$proceedWithWarning`.proceedWith(var22, `$completion`);
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }

      internal suspend fun PipelineContext<Any, HttpRequestBuilder>.proceedWithMissingCache(scope: HttpClient) {
         `$this$proceedWithMissingCache`.finish();
         val request: HttpRequestData = (`$this$proceedWithMissingCache`.getContext() as HttpRequestBuilder).build();
         val var10000: Any = `$this$proceedWithMissingCache`.proceedWith(
            new HttpClientCall(
               scope,
               request,
               new HttpResponseData(
                  HttpStatusCode.Companion.getGatewayTimeout(),
                  DateJvmKt.GMTDate$default(null, 1, null),
                  Headers.Companion.getEmpty(),
                  HttpProtocolVersion.Companion.getHTTP_1_1(),
                  ByteChannelCtorKt.ByteReadChannel$default(new byte[0], 0, 0, 6, null),
                  request.getExecutionContext()
               )
            ),
            `$completion`
         );
         return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
      }
   }

   @KtorDsl
   public class Config {
      internal final var publicStorageNew: CacheStorage = CacheStorage.Companion.getUnlimited().invoke() as CacheStorage
      internal final var privateStorageNew: CacheStorage = CacheStorage.Companion.getUnlimited().invoke() as CacheStorage
      internal final var useOldStorage: Boolean
      public final var isShared: Boolean

      @Deprecated(
         message = "This will become internal. Use setter method instead with new storage interface",
         level = DeprecationLevel.ERROR
      )
      public final var publicStorage: HttpCacheStorage = HttpCacheStorage.Companion.getUnlimited().invoke() as HttpCacheStorage
         public final set(value) {
            this.useOldStorage = true;
            this.publicStorage = value;
         }


      @Deprecated(
         message = "This will become internal. Use setter method instead with new storage interface",
         level = DeprecationLevel.ERROR
      )
      public final var privateStorage: HttpCacheStorage = HttpCacheStorage.Companion.getUnlimited().invoke() as HttpCacheStorage
         public final set(value) {
            this.useOldStorage = true;
            this.privateStorage = value;
         }


      public fun publicStorage(storage: CacheStorage) {
         this.publicStorageNew = storage;
      }

      public fun privateStorage(storage: CacheStorage) {
         this.privateStorageNew = storage;
      }
   }
}
