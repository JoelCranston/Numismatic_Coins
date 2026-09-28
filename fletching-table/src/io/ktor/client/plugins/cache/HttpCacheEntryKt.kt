@file:SourceDebugExtension(["SMAP\nHttpCacheEntry.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCacheEntry.kt\nio/ktor/client/plugins/cache/HttpCacheEntryKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,145:1\n1761#2,3:146\n295#2,2:149\n295#2,2:151\n295#2,2:154\n1#3:153\n*S KotlinDebug\n*F\n+ 1 HttpCacheEntry.kt\nio/ktor/client/plugins/cache/HttpCacheEntryKt\n*L\n69#1:146,3\n71#1:149,2\n106#1:151,2\n128#1:154,2\n*E\n"])

package io.ktor.client.plugins.cache

import io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.DateUtilsKt
import io.ktor.http.HeaderValue
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaderValueParserKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.DateKt
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import java.util.LinkedHashMap
import java.util.Locale
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.functions.Function0
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Source
import kotlinx.io.SourcesKt

internal suspend fun HttpCacheEntry(isShared: Boolean, response: HttpResponse): HttpCacheEntry {
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
   val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = response.getRawContent();
         `$continuation`.L$0 = response;
         `$continuation`.Z$0 = isShared;
         `$continuation`.label = 1;
         var10000 = ByteReadChannelOperationsKt.readRemaining((ByteReadChannel)var10000, `$continuation`);
         if (var10000 === var6) {
            return var6;
         }
         break;
      case 1:
         isShared = `$continuation`.Z$0;
         response = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return new HttpCacheEntry(cacheExpires$default(response, isShared, null, 2, null), varyKeys(response), response, SourcesKt.readByteArray(var10000 as Source));
}

internal fun HttpResponse.varyKeys(): Map<String, String> {
   if (HttpMessagePropertiesKt.vary(`$this$varyKeys`) == null) {
      return MapsKt.emptyMap();
   } else {
      val result: java.util.Map = new LinkedHashMap();
      val requestHeaders: Headers = `$this$varyKeys`.getCall().getRequest().getHeaders();

      val validationKeys: java.util.List;
      for (java.lang.String key : validationKeys) {
         var var9: java.lang.String;
         label20: {
            var9 = key.toLowerCase(Locale.ROOT);
            val var10: java.util.List = requestHeaders.getAll(key);
            if (var10 != null) {
               var9 = CollectionsKt.joinToString$default(var10, ",", null, null, 0, null, null, 62, null);
               if (var9 != null) {
                  break label20;
               }
            }

            var9 = "";
         }

         result.put(var9, var9);
      }

      return result;
   }
}

internal fun HttpResponse.cacheExpires(isShared: Boolean, fallback: () -> GMTDate = HttpCacheEntryKt::cacheExpires$lambda$0): GMTDate {
   var cacheControl: java.util.List;
   var var28: java.lang.String;
   label77: {
      cacheControl = HttpMessagePropertiesKt.cacheControl(`$this$cacheExpires`);
      if (isShared) {
         val maxAge: java.lang.Iterable = cacheControl;
         var var10000: Boolean;
         if (cacheControl is java.util.Collection && (cacheControl as java.util.Collection).isEmpty()) {
            var10000 = false;
         } else {
            val `$this$firstOrNull$iv`: java.util.Iterator = maxAge.iterator();

            while (true) {
               if (!`$this$firstOrNull$iv`.hasNext()) {
                  var10000 = false;
                  break;
               }

               if (StringsKt.startsWith$default((`$this$firstOrNull$iv`.next() as HeaderValue).getValue(), "s-maxage", false, 2, null)) {
                  var10000 = true;
                  break;
               }
            }
         }

         if (var10000) {
            var28 = "s-maxage";
            break label77;
         }
      }

      var28 = "max-age";
   }

   val maxAgeKey: java.lang.String = var28;
   val var21: java.util.Iterator = cacheControl.iterator();

   while (true) {
      if (var21.hasNext()) {
         val var25: Any = var21.next();
         if (!StringsKt.startsWith$default((var25 as HeaderValue).getValue(), maxAgeKey, false, 2, null)) {
            continue;
         }

         var28 = (java.lang.String)var25;
         break;
      }

      var28 = null;
      break;
   }

   label54: {
      val var15: HeaderValue = var28 as HeaderValue;
      if (var28 as HeaderValue != null) {
         val var18: java.lang.String = var15.getValue();
         if (var18 != null) {
            val var20: java.util.List = StringsKt.split$default(var18, new java.lang.String[]{"="}, false, 0, 6, null);
            if (var20 != null) {
               val var23: java.lang.String = CollectionsKt.getOrNull(var20, 1);
               if (var23 != null) {
                  var31 = StringsKt.toLongOrNull(var23);
                  break label54;
               }
            }
         }
      }

      var31 = null;
   }

   if (var31 != null) {
      return DateKt.plus(`$this$cacheExpires`.getRequestTime(), var31 * 1000L);
   } else {
      val var16: java.lang.String = `$this$cacheExpires`.getHeaders().get(HttpHeaders.INSTANCE.getExpires());
      if (var16 != null) {
         val var24: java.lang.String = var16;
         if (!(var16 == "0") && !StringsKt.isBlank(var16)) {
            var var27: GMTDate;
            try {
               var27 = DateUtilsKt.fromHttpToGmtDate(var24);
            } catch (var13: java.lang.Throwable) {
               var27 = fallback.invoke() as GMTDate;
            }

            return var27;
         } else {
            return fallback.invoke() as GMTDate;
         }
      } else {
         return fallback.invoke() as GMTDate;
      }
   }
}

@JvmSynthetic
fun `cacheExpires$default`(var0: HttpResponse, var1: Boolean, var2: Function0, var3: Int, var4: Any): GMTDate {
   if ((var3 and 2) != 0) {
      var2 = HttpCacheEntryKt::cacheExpires$lambda$0;
   }

   return cacheExpires(var0, var1, var2);
}

internal fun shouldValidate(cacheExpires: GMTDate, responseHeaders: Headers, request: HttpRequestBuilder): ValidateStatus {
   val requestHeaders: HeadersBuilder = request.getHeaders();
   var var10000: java.util.List = responseHeaders.getAll(HttpHeaders.INSTANCE.getCacheControl());
   val responseCacheControl: java.util.List = HttpHeaderValueParserKt.parseHeaderValue(
      if (var10000 != null) CollectionsKt.joinToString$default(var10000, ",", null, null, 0, null, null, 62, null) else null
   );
   var10000 = requestHeaders.getAll(HttpHeaders.INSTANCE.getCacheControl());
   val requestCacheControl: java.util.List = HttpHeaderValueParserKt.parseHeaderValue(
      if (var10000 != null) CollectionsKt.joinToString$default(var10000, ",", null, null, 0, null, null, 62, null) else null
   );
   if (requestCacheControl.contains(CacheControl.INSTANCE.getNO_CACHE$ktor_client_core())) {
      HttpCacheKt.getLOGGER().trace("\"no-cache\" is set for ${request.getUrl()}, should validate cached response");
      return ValidateStatus.ShouldValidate;
   } else {
      val maxStaleMillis: java.util.Iterator = requestCacheControl.iterator();

      while (true) {
         if (maxStaleMillis.hasNext()) {
            val `$this$firstOrNull$iv`: Any = maxStaleMillis.next();
            if (!StringsKt.startsWith$default((`$this$firstOrNull$iv` as HeaderValue).getValue(), "max-age=", false, 2, null)) {
               continue;
            }

            var10000 = (java.util.List)`$this$firstOrNull$iv`;
            break;
         }

         var10000 = null;
         break;
      }

      label89: {
         val validMillis: HeaderValue = var10000 as HeaderValue;
         if (var10000 as HeaderValue != null) {
            val var18: java.lang.String = validMillis.getValue();
            if (var18 != null) {
               val var19: java.util.List = StringsKt.split$default(var18, new java.lang.String[]{"="}, false, 0, 6, null);
               if (var19 != null) {
                  val var22: java.lang.String = var19.get(1) as java.lang.String;
                  if (var22 != null) {
                     val var36: Int = StringsKt.toIntOrNull(var22);
                     var35 = Integer.valueOf((int)(var36 ?: 0));
                     break label89;
                  }
               }
            }
         }

         var35 = null;
      }

      if (var35 != null) {
         if (var35 == 0) {
            HttpCacheKt.getLOGGER().trace("\"max-age\" is not set for ${request.getUrl()}, should validate cached response");
            return ValidateStatus.ShouldValidate;
         }
      }

      if (responseCacheControl.contains(CacheControl.INSTANCE.getNO_CACHE$ktor_client_core())) {
         HttpCacheKt.getLOGGER().trace("\"no-cache\" is set for ${request.getUrl()}, should validate cached response");
         return ValidateStatus.ShouldValidate;
      } else {
         val var17: Long = cacheExpires.getTimestamp() - DateJvmKt.getTimeMillis();
         if (var17 > 0L) {
            HttpCacheKt.getLOGGER().trace("Cached response is valid for ${request.getUrl()}, should not validate");
            return ValidateStatus.ShouldNotValidate;
         } else if (responseCacheControl.contains(CacheControl.INSTANCE.getMUST_REVALIDATE$ktor_client_core())) {
            HttpCacheKt.getLOGGER().trace("\"must-revalidate\" is set for ${request.getUrl()}, should validate cached response");
            return ValidateStatus.ShouldValidate;
         } else {
            val var30: java.util.Iterator = requestCacheControl.iterator();

            while (true) {
               if (var30.hasNext()) {
                  val `element$iv`: Any = var30.next();
                  if (!StringsKt.startsWith$default((`element$iv` as HeaderValue).getValue(), "max-stale=", false, 2, null)) {
                     continue;
                  }

                  var10000 = (java.util.List)`element$iv`;
                  break;
               }

               var10000 = null;
               break;
            }

            label74: {
               val var23: HeaderValue = var10000 as HeaderValue;
               if (var10000 as HeaderValue != null) {
                  val var26: java.lang.String = var23.getValue();
                  if (var26 != null) {
                     val var38: java.lang.String = var26.substring(10);
                     if (var38 != null) {
                        val var31: Int = StringsKt.toIntOrNull(var38);
                        if (var31 != null) {
                           var39 = var31;
                           break label74;
                        }
                     }
                  }
               }

               var39 = 0;
            }

            if (var17 + var39 * 1000L > 0L) {
               HttpCacheKt.getLOGGER().trace("Cached response is stale for ${request.getUrl()} but less than max-stale, should warn");
               return ValidateStatus.ShouldWarn;
            } else {
               HttpCacheKt.getLOGGER().trace("Cached response is stale for ${request.getUrl()}, should validate cached response");
               return ValidateStatus.ShouldValidate;
            }
         }
      }
   }
}

fun `cacheExpires$lambda$0`(): GMTDate {
   return DateJvmKt.GMTDate$default(null, 1, null);
}
