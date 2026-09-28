package io.ktor.client.plugins.cache.storage

import io.ktor.client.HttpClient
import io.ktor.client.call.SavedHttpCall
import io.ktor.client.plugins.cache.HttpCacheEntry
import io.ktor.client.plugins.cache.HttpCacheEntryKt
import io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store.1
import io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store.3
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.http.Url
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.core.StringsKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlinx.io.Source

internal suspend fun HttpCacheStorage.store(url: Url, value: HttpResponse, isShared: Boolean): HttpCacheEntry {
   var `$continuation`: Continuation;
   label25: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label25;
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
         val var11: Boolean = isShared;
         `$continuation`.L$0 = `$this$store`;
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(value);
         `$continuation`.Z$0 = isShared;
         `$continuation`.label = 1;
         var10000 = HttpCacheEntryKt.HttpCacheEntry(var11, value, `$continuation`);
         if (var10000 === var8) {
            return var8;
         }
         break;
      case 1:
         isShared = `$continuation`.Z$0;
         value = `$continuation`.L$2 as HttpResponse;
         url = `$continuation`.L$1 as Url;
         `$this$store` = `$continuation`.L$0 as HttpCacheStorage;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val result: HttpCacheEntry = var10000 as HttpCacheEntry;
   `$this$store`.store(url, var10000 as HttpCacheEntry);
   return result;
}

@Deprecated(message = "Please use method with `response.varyKeys()` and `isShared` arguments", replaceWith = @ReplaceWith(expression = "store(response, response.varyKeys(), isShared)", imports = []), level = DeprecationLevel.ERROR)
public suspend fun CacheStorage.store(response: HttpResponse): CachedResponseData {
   return store$default(`$this$store`, response, HttpCacheEntryKt.varyKeys(response), false, `$completion`, 4, null);
}

public suspend fun CacheStorage.store(response: HttpResponse, varyKeys: Map<String, String>, isShared: Boolean = ...): CachedResponseData {
   var `$continuation`: Continuation;
   label32: {
      if (`$completion` is 3) {
         `$continuation` = `$completion` as 3;
         if (((`$completion` as 3).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label32;
         }
      }

      `$continuation` = new 3(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var17: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var22: Url;
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var22 = response.getCall().getRequest().getUrl();
         var10000 = response.getRawContent();
         `$continuation`.L$0 = `$this$store`;
         `$continuation`.L$1 = response;
         `$continuation`.L$2 = varyKeys;
         `$continuation`.L$3 = var22;
         `$continuation`.Z$0 = isShared;
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readRemaining((ByteReadChannel)var10000, `$continuation`);
         if (var10000 === var17) {
            return var17;
         }
         break;
      case 1:
         isShared = `$continuation`.Z$0;
         var22 = `$continuation`.L$3 as Url;
         varyKeys = `$continuation`.L$2 as java.util.Map;
         response = `$continuation`.L$1 as HttpResponse;
         `$this$store` = `$continuation`.L$0 as CacheStorage;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      case 2:
         isShared = `$continuation`.Z$0;
         val data: CachedResponseData = `$continuation`.L$5 as CachedResponseData;
         val body: ByteArray = `$continuation`.L$4 as ByteArray;
         var22 = `$continuation`.L$3 as Url;
         varyKeys = `$continuation`.L$2 as java.util.Map;
         response = `$continuation`.L$1 as HttpResponse;
         `$this$store` = `$continuation`.L$0 as CacheStorage;
         ResultKt.throwOnFailure(`$result`);
         return data;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val var23: ByteArray = StringsKt.readBytes(var10000 as Source);
   val data: CachedResponseData = new CachedResponseData(
      response.getCall().getRequest().getUrl(),
      response.getStatus(),
      response.getRequestTime(),
      response.getResponseTime(),
      response.getVersion(),
      HttpCacheEntryKt.cacheExpires$default(response, isShared, null, 2, null),
      response.getHeaders(),
      varyKeys,
      var23
   );
   `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$store`);
   `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(response);
   `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(varyKeys);
   `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(var22);
   `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var23);
   `$continuation`.L$5 = data;
   `$continuation`.Z$0 = isShared;
   `$continuation`.label = 2;
   return if (`$this$store`.store(var22, data, `$continuation`) === var17) var17 else data;
}

@JvmSynthetic
fun `store$default`(var0: CacheStorage, var1: HttpResponse, var2: java.util.Map, var3: Boolean, var4: Continuation, var5: Int, var6: Any): Any {
   if ((var5 and 4) != 0) {
      var3 = false;
   }

   return store(var0, var1, var2, var3, var4);
}

internal fun CachedResponseData.createResponse(client: HttpClient, request: HttpRequest, responseContext: CoroutineContext): HttpResponse {
   return new SavedHttpCall(
         client,
         request,
         new io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.createResponse.response.1(`$this$createResponse`, responseContext),
         `$this$createResponse`.getBody()
      )
      .getResponse();
}
