package io.ktor.client.engine.java

import io.ktor.client.engine.java.JavaHttpResponseKt.executeHttpRequest.1
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.HttpTimeoutKt
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import java.net.http.HttpClient
import java.net.http.HttpConnectTimeoutException
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.net.http.HttpTimeoutException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.future.FutureKt

internal suspend fun HttpClient.executeHttpRequest(callContext: CoroutineContext, requestData: HttpRequestData): HttpResponseData? {
   var `$continuation`: Continuation;
   label63: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label63;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   var var23: HttpResponseData;
   label57: {
      label56: {
         val `$result`: Any = `$continuation`.result;
         val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val httpRequest: HttpRequest = JavaHttpRequestKt.convertToHttpRequest(requestData, callContext);

               try {
                  val var20: CompletableFuture = `$this$executeHttpRequest`.sendAsync(
                     httpRequest, new JavaHttpResponseBodyHandler(callContext, requestData, null, 4, null)
                  );
                  if (var20 == null) {
                     break label56;
                  }

                  val var21: CompletionStage = var20;
                  `$continuation`.L$0 = requestData;
                  `$continuation`.label = 1;
                  var23 = (HttpResponseData)FutureKt.await(var21, `$continuation`);
               } catch (var18: HttpConnectTimeoutException) {
                  throw HttpTimeoutKt.ConnectTimeoutException(requestData, var18);
               } catch (var19: HttpTimeoutException) {
                  throw new HttpRequestTimeoutException(requestData);
               }

               if (var23 === var9) {
                  return var9;
               }
               break;
            case 1:
               requestData = `$continuation`.L$0 as HttpRequestData;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  var23 = (HttpResponseData)`$result`;
                  break;
               } catch (var14: HttpConnectTimeoutException) {
                  throw HttpTimeoutKt.ConnectTimeoutException(`$continuation`.L$0 as HttpRequestData, var14);
               } catch (var15: HttpTimeoutException) {
                  throw new HttpRequestTimeoutException(`$continuation`.L$0 as HttpRequestData);
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            val var22: HttpResponse = var23 as HttpResponse;
            if (var23 as HttpResponse != null) {
               var23 = var22.body() as HttpResponseData;
               break label57;
            }
         } catch (var16: HttpConnectTimeoutException) {
            throw HttpTimeoutKt.ConnectTimeoutException(requestData, var16);
         } catch (var17: HttpTimeoutException) {
            throw new HttpRequestTimeoutException(requestData);
         }
      }

      try {
         var23 = null;
      } catch (var12: HttpConnectTimeoutException) {
         throw HttpTimeoutKt.ConnectTimeoutException(requestData, var12);
      } catch (var13: HttpTimeoutException) {
         throw new HttpRequestTimeoutException(requestData);
      }
   }

   try {
      return var23;
   } catch (var10: HttpConnectTimeoutException) {
      throw HttpTimeoutKt.ConnectTimeoutException(requestData, var10);
   } catch (var11: HttpTimeoutException) {
      throw new HttpRequestTimeoutException(requestData);
   }
}
