package io.ktor.client.engine.java

import io.ktor.client.engine.java.JavaHttpWebSocketKt.executeWebSocketRequest.1
import io.ktor.client.plugins.HttpTimeoutKt
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.Headers
import java.net.http.HttpClient
import java.net.http.HttpConnectTimeoutException
import java.net.http.HttpTimeoutException
import java.util.TreeSet
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt

private final val ILLEGAL_HEADERS: TreeSet<String>

internal suspend fun HttpClient.executeWebSocketRequest(coroutineContext: CoroutineContext, requestData: HttpRequestData): HttpResponseData {
   var `$continuation`: Continuation;
   label40: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label40;
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
         val webSocket: JavaHttpWebSocket = new JavaHttpWebSocket(coroutineContext, `$this$executeWebSocketRequest`, requestData, null, 8, null);

         try {
            `$continuation`.L$0 = requestData;
            `$continuation`.label = 1;
            var10000 = webSocket.getResponse(`$continuation`);
         } catch (var13: HttpConnectTimeoutException) {
            throw HttpTimeoutKt.ConnectTimeoutException(requestData, var13);
         } catch (var14: HttpTimeoutException) {
            throw HttpTimeoutKt.SocketTimeoutException(requestData, var14);
         }

         if (var10000 === var8) {
            return var8;
         }
         break;
      case 1:
         requestData = `$continuation`.L$0 as HttpRequestData;

         try {
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         } catch (var11: HttpConnectTimeoutException) {
            throw HttpTimeoutKt.ConnectTimeoutException(`$continuation`.L$0 as HttpRequestData, var11);
         } catch (var12: HttpTimeoutException) {
            throw HttpTimeoutKt.SocketTimeoutException(`$continuation`.L$0 as HttpRequestData, var12);
         }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   try {
      return var10000;
   } catch (var9: HttpConnectTimeoutException) {
      throw HttpTimeoutKt.ConnectTimeoutException(requestData, var9);
   } catch (var10: HttpTimeoutException) {
      throw HttpTimeoutKt.SocketTimeoutException(requestData, var10);
   }
}

private fun headersOf(map: Map<String, List<String>>): Headers {
   return new io.ktor.client.engine.java.JavaHttpWebSocketKt.headersOf.1(map);
}

@JvmSynthetic
fun `access$headersOf`(map: java.util.Map): Headers {
   return headersOf(map);
}

@JvmSynthetic
fun `access$getILLEGAL_HEADERS$p`(): TreeSet {
   return ILLEGAL_HEADERS;
}
