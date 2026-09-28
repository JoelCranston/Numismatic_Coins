@file:SourceDebugExtension(["SMAP\nHttpCacheLegacy.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCacheLegacy.kt\nio/ktor/client/plugins/cache/HttpCacheLegacyKt\n+ 2 Headers.kt\nio/ktor/http/Headers$Companion\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,172:1\n30#2:173\n1068#3:174\n295#3:175\n296#3:179\n168#4,3:176\n168#4,3:180\n*S KotlinDebug\n*F\n+ 1 HttpCacheLegacy.kt\nio/ktor/client/plugins/cache/HttpCacheLegacyKt\n*L\n95#1:173\n151#1:174\n152#1:175\n152#1:179\n153#1:176,3\n165#1:180,3\n*E\n"])

package io.ktor.client.plugins.cache

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.cache.HttpCacheLegacyKt.findResponse.requestHeaders.2
import io.ktor.client.plugins.cache.HttpCacheLegacyKt.interceptReceiveLegacy.1
import io.ktor.client.plugins.cache.storage.HttpCacheStorage
import io.ktor.client.plugins.cache.storage.HttpCacheStorageKt
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.UtilsKt
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaderValueParserKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessageBuilder
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.HttpStatusCode
import io.ktor.http.HttpStatusCodeKt
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.http.content.OutgoingContent
import io.ktor.util.date.GMTDate
import io.ktor.util.pipeline.PipelineContext
import java.util.Map.Entry
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

internal suspend fun PipelineContext<Any, HttpRequestBuilder>.interceptSendLegacy(plugin: HttpCache, content: OutgoingContent, scope: HttpClient) {
   val cache: HttpCacheEntry = findResponse(plugin, `$this$interceptSendLegacy`.getContext() as HttpRequestBuilder, content);
   if (cache == null) {
      if (HttpHeaderValueParserKt.parseHeaderValue(
            (`$this$interceptSendLegacy`.getContext() as HttpRequestBuilder).getHeaders().get(HttpHeaders.INSTANCE.getCacheControl())
         )
         .contains(CacheControl.INSTANCE.getONLY_IF_CACHED$ktor_client_core())) {
         val var16: Any = HttpCache.Companion.proceedWithMissingCache$ktor_client_core(`$this$interceptSendLegacy`, scope, `$completion`);
         if (var16 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return var16;
         }
      }

      return Unit.INSTANCE;
   } else {
      val cachedCall: HttpClientCall = cache.produceResponse$ktor_client_core().getCall();
      val validateStatus: ValidateStatus = HttpCacheEntryKt.shouldValidate(
         cache.getExpires(), cache.getResponse().getHeaders(), `$this$interceptSendLegacy`.getContext() as HttpRequestBuilder
      );
      if (validateStatus === ValidateStatus.ShouldNotValidate) {
         val var15: Any = HttpCache.Companion.proceedWithCache$ktor_client_core(`$this$interceptSendLegacy`, scope, cachedCall, `$completion`);
         return if (var15 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var15 else Unit.INSTANCE;
      } else if (validateStatus === ValidateStatus.ShouldWarn) {
         val var14: Any = proceedWithWarning(`$this$interceptSendLegacy`, cachedCall, scope, `$completion`);
         return if (var14 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var14 else Unit.INSTANCE;
      } else {
         var var10000: java.lang.String = cache.getResponseHeaders$ktor_client_core().get(HttpHeaders.INSTANCE.getETag());
         if (var10000 != null) {
            UtilsKt.header(`$this$interceptSendLegacy`.getContext() as HttpMessageBuilder, HttpHeaders.INSTANCE.getIfNoneMatch(), var10000);
         }

         var10000 = cache.getResponseHeaders$ktor_client_core().get(HttpHeaders.INSTANCE.getLastModified());
         if (var10000 != null) {
            UtilsKt.header(`$this$interceptSendLegacy`.getContext() as HttpMessageBuilder, HttpHeaders.INSTANCE.getIfModifiedSince(), var10000);
         }

         return Unit.INSTANCE;
      }
   }
}

internal suspend fun PipelineContext<HttpResponse, Unit>.interceptReceiveLegacy(response: HttpResponse, plugin: HttpCache, scope: HttpClient) {
   var `$continuation`: Continuation;
   label49: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label49;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (!HttpStatusCodeKt.isSuccess(response.getStatus())) {
            if (!(response.getStatus() == HttpStatusCode.Companion.getNotModified())) {
               return Unit.INSTANCE;
            }

            var10000 = findAndRefresh(plugin, response.getCall().getRequest(), response);
            if (var10000 == null) {
               throw new InvalidCacheStateException(response.getCall().getRequest().getUrl());
            }

            if (HttpCacheEntryKt.varyKeys((HttpResponse)var10000).size() != HttpCacheEntryKt.varyKeys(response).size()) {
               HttpCacheKt.getLOGGER()
                  .warn(
                     "Vary header mismatch on cached response for ${response.getCall().getRequest().getUrl()}. Received 304 Not Modified with Vary: ${HttpCacheEntryKt.varyKeys(
                        response
                     )} but cached response has Vary: ${HttpCacheEntryKt.varyKeys((HttpResponse)var10000)}. According to RFC 7232 §4.1 and RFC 9111 §4.1, the server must include the full Vary header in 304 responses. Proceeding with cached response despite mismatch. Consider reporting this issue to the server maintainers."
                  );
            }

            scope.getMonitor().raise(HttpCache.Companion.getHttpResponseFromCache(), (HttpResponse)var10000);
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$interceptReceiveLegacy`);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(response);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(plugin);
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(scope);
            `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var10000);
            `$continuation`.label = 3;
            if (`$this$interceptReceiveLegacy`.proceedWith(var10000, `$continuation`) === var8) {
               return var8;
            }

            return Unit.INSTANCE;
         }

         `$continuation`.L$0 = `$this$interceptReceiveLegacy`;
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(response);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(plugin);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(scope);
         `$continuation`.label = 1;
         var10000 = cacheResponse(plugin, response, `$continuation`);
         if (var10000 === var8) {
            return var8;
         }
         break;
      case 1:
         scope = `$continuation`.L$3 as HttpClient;
         plugin = `$continuation`.L$2 as HttpCache;
         response = `$continuation`.L$1 as HttpResponse;
         `$this$interceptReceiveLegacy` = `$continuation`.L$0 as PipelineContext;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      case 2:
         val var17: HttpResponse = `$continuation`.L$4 as HttpResponse;
         scope = `$continuation`.L$3 as HttpClient;
         plugin = `$continuation`.L$2 as HttpCache;
         response = `$continuation`.L$1 as HttpResponse;
         `$this$interceptReceiveLegacy` = `$continuation`.L$0 as PipelineContext;
         ResultKt.throwOnFailure(`$result`);
         return Unit.INSTANCE;
      case 3:
         val responseFromCache: HttpResponse = `$continuation`.L$4 as HttpResponse;
         scope = `$continuation`.L$3 as HttpClient;
         plugin = `$continuation`.L$2 as HttpCache;
         response = `$continuation`.L$1 as HttpResponse;
         `$this$interceptReceiveLegacy` = `$continuation`.L$0 as PipelineContext;
         ResultKt.throwOnFailure(`$result`);
         return Unit.INSTANCE;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val var18: HttpResponse = var10000 as HttpResponse;
   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$interceptReceiveLegacy`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(response);
   `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(plugin);
   `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(scope);
   `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var18);
   `$continuation`.label = 2;
   return if (`$this$interceptReceiveLegacy`.proceedWith(var18, `$continuation`) === var8) var8 else Unit.INSTANCE;
}

private suspend fun PipelineContext<Any, HttpRequestBuilder>.proceedWithWarning(cachedCall: HttpClientCall, scope: HttpClient) {
   val request: HttpRequestData = (`$this$proceedWithWarning`.getContext() as HttpRequestBuilder).build();
   var var10000: HttpStatusCode = cachedCall.getResponse().getStatus();
   val var10001: GMTDate = cachedCall.getResponse().getRequestTime();
   val call: Headers.Companion = Headers.Companion;
   val var8: HeadersBuilder = new HeadersBuilder(0, 1, null);
   var8.appendAll(cachedCall.getResponse().getHeaders());
   var8.append(HttpHeaders.INSTANCE.getWarning(), "110");
   val var20: HttpClientCall = new HttpClientCall(
      scope,
      request,
      new HttpResponseData(
         var10000,
         var10001,
         var8.build(),
         cachedCall.getResponse().getVersion(),
         cachedCall.getResponse().getRawContent(),
         cachedCall.getResponse().getCoroutineContext()
      )
   );
   `$this$proceedWithWarning`.finish();
   scope.getMonitor().raise(HttpCache.Companion.getHttpResponseFromCache(), var20.getResponse());
   var10000 = (HttpStatusCode)`$this$proceedWithWarning`.proceedWith(var20, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

private suspend fun HttpCache.cacheResponse(response: HttpResponse): HttpResponse {
   var `$continuation`: Continuation;
   label35: {
      if (`$completion` is io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse.1;
         if (((`$completion` as io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label35;
         }
      }

      `$continuation` = new io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var10: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0: {
         ResultKt.throwOnFailure(`$result`);
         val var13: HttpRequest = response.getCall().getRequest();
         val var14: java.util.List = HttpMessagePropertiesKt.cacheControl(response);
         val var15: java.util.List = HttpMessagePropertiesKt.cacheControl(var13);
         val var16: HttpCacheStorage = if (var14.contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core()))
            `$this$cacheResponse`.getPrivateStorage$ktor_client_core()
            else
            `$this$cacheResponse`.getPublicStorage$ktor_client_core();
         if (var14.contains(CacheControl.INSTANCE.getNO_STORE$ktor_client_core()) || var15.contains(CacheControl.INSTANCE.getNO_STORE$ktor_client_core())) {
            return response;
         }

         val var10001: Url = var13.getUrl();
         val var10003: Boolean = `$this$cacheResponse`.isSharedClient$ktor_client_core();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$cacheResponse`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(response);
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var13);
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var14);
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var15);
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var16);
         `$continuation`.label = 1;
         var10000 = HttpCacheStorageKt.store(var16, var10001, response, var10003, `$continuation`);
         if (var10000 === var10) {
            return var10;
         }
         break;
      }
      case 1: {
         val storage: HttpCacheStorage = `$continuation`.L$5 as HttpCacheStorage;
         val requestCacheControl: java.util.List = `$continuation`.L$4 as java.util.List;
         val responseCacheControl: java.util.List = `$continuation`.L$3 as java.util.List;
         val request: HttpRequest = `$continuation`.L$2 as HttpRequest;
         response = `$continuation`.L$1 as HttpResponse;
         `$this$cacheResponse` = `$continuation`.L$0 as HttpCache;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return (var10000 as HttpCacheEntry).produceResponse$ktor_client_core();
}

private fun HttpCache.findAndRefresh(request: HttpRequest, response: HttpResponse): HttpResponse? {
   val url: Url = response.getCall().getRequest().getUrl();
   val storage: HttpCacheStorage = if (HttpMessagePropertiesKt.cacheControl(response).contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core()))
      `$this$findAndRefresh`.getPrivateStorage$ktor_client_core()
      else
      `$this$findAndRefresh`.getPublicStorage$ktor_client_core();
   val var10000: HttpCacheEntry = findResponse(`$this$findAndRefresh`, storage, HttpCacheEntryKt.varyKeys(response), url, request);
   if (var10000 == null) {
      return null;
   } else {
      storage.store(
         url,
         new HttpCacheEntry(
            HttpCacheEntryKt.cacheExpires$default(response, `$this$findAndRefresh`.isSharedClient$ktor_client_core(), null, 2, null),
            var10000.getVaryKeys(),
            var10000.getResponse(),
            var10000.getBody()
         )
      );
      return var10000.produceResponse$ktor_client_core();
   }
}

private fun HttpCache.findResponse(storage: HttpCacheStorage, varyKeys: Map<String, String>, url: Url, request: HttpRequest): HttpCacheEntry? {
   val var10000: HttpCacheEntry;
   if (!varyKeys.isEmpty()) {
      var10000 = storage.find(url, varyKeys);
   } else {
      val requestHeaders: Function1 = HttpCacheKt.mergedHeadersLookup(
         request.getContent(),
         new io.ktor.client.plugins.cache.HttpCacheLegacyKt.findResponse.requestHeaders.1(request.getHeaders()),
         new 2(request.getHeaders())
      );
      val var8: java.util.Iterator = CollectionsKt.sortedWith(
            storage.findByUrl(url), new io.ktor.client.plugins.cache.HttpCacheLegacyKt.findResponse..inlined.sortedByDescending.1<>()
         )
         .iterator();

      while (true) {
         if (!var8.hasNext()) {
            var23 = null;
            break;
         }

         val `element$iv`: Any = var8.next();
         val `$this$all$iv`: java.util.Map = (`element$iv` as HttpCacheEntry).getVaryKeys();
         var var22: Boolean;
         if (`$this$all$iv`.isEmpty()) {
            var22 = true;
         } else {
            val var14: java.util.Iterator = `$this$all$iv`.entrySet().iterator();

            while (true) {
               if (!var14.hasNext()) {
                  var22 = true;
                  break;
               }

               val `element$ivx`: Entry = var14.next() as Entry;
               if (!(requestHeaders.invoke(`element$ivx`.getKey() as java.lang.String) == `element$ivx`.getValue() as java.lang.String)) {
                  var22 = false;
                  break;
               }
            }
         }

         if (var22) {
            var23 = `element$iv`;
            break;
         }
      }

      var10000 = var23 as HttpCacheEntry;
   }

   return var10000;
}

private fun HttpCache.findResponse(context: HttpRequestBuilder, content: OutgoingContent): HttpCacheEntry? {
   val url: Url = URLUtilsKt.Url(context.getUrl());
   val lookup: Function1 = HttpCacheKt.mergedHeadersLookup(
      content,
      new io.ktor.client.plugins.cache.HttpCacheLegacyKt.findResponse.lookup.1(context.getHeaders()),
      new io.ktor.client.plugins.cache.HttpCacheLegacyKt.findResponse.lookup.2(context.getHeaders())
   );
   val var6: java.util.Iterator = SetsKt.plus(
         `$this$findResponse`.getPrivateStorage$ktor_client_core().findByUrl(url), `$this$findResponse`.getPublicStorage$ktor_client_core().findByUrl(url)
      )
      .iterator();

   val item: HttpCacheEntry;
   var var10000: Boolean;
   label31:
   do {
      if (!var6.hasNext()) {
         return null;
      }

      item = var6.next() as HttpCacheEntry;
      val varyKeys: java.util.Map = item.getVaryKeys();
      if (varyKeys.isEmpty()) {
         break;
      }

      if (varyKeys.isEmpty()) {
         var10000 = true;
      } else {
         for (Entry element$iv : varyKeys.entrySet()) {
            if (!(lookup.invoke(`element$iv`.getKey() as java.lang.String) == `element$iv`.getValue() as java.lang.String)) {
               var10000 = false;
               continue label31;
            }
         }

         var10000 = true;
      }
   } while (!var10000);

   return item;
}

@JvmSynthetic
fun `access$proceedWithWarning`(`$receiver`: PipelineContext, cachedCall: HttpClientCall, scope: HttpClient, `$completion`: Continuation): Any {
   return proceedWithWarning(`$receiver`, cachedCall, scope, `$completion`);
}

@JvmSynthetic
fun `access$cacheResponse`(`$receiver`: HttpCache, response: HttpResponse, `$completion`: Continuation): Any {
   return cacheResponse(`$receiver`, response, `$completion`);
}
