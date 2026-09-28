@file:SourceDebugExtension(["SMAP\nSSE.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SSE.kt\nio/ktor/client/plugins/sse/SSEKt\n+ 2 Logger.kt\nio/ktor/util/logging/LoggerKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,219:1\n38#2,2:220\n21#3:222\n21#3:232\n69#4:223\n84#4,8:224\n69#4:233\n84#4,8:234\n*S KotlinDebug\n*F\n+ 1 SSE.kt\nio/ktor/client/plugins/sse/SSEKt\n*L\n196#1:220,2\n188#1:222\n189#1:232\n188#1:223\n188#1:224,8\n189#1:233\n189#1:234,8\n*E\n"])

package io.ktor.client.plugins.sse

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.call.SavedCallKt
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.plugins.sse.SSEKt.SSE.1
import io.ktor.client.plugins.sse.SSEKt.SSE.2.2
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseKt
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.ContentType
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.HttpStatusCode
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import io.ktor.util.logging.LoggerJvmKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import org.slf4j.Logger

internal final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.sse.SSE")
public final val SSE: ClientPlugin<SSEConfig> = CreatePluginUtilsKt.createClientPlugin("SSE", 1.INSTANCE, SSEKt::SSE$lambda$0)
internal final val SSEClientForReconnectionAttr: AttributeKey<HttpClient>
internal final val SSEReconnectionRequestAttr: AttributeKey<Boolean>

private fun <T : Any> getAttributeValue(request: HttpRequestBuilder, attributeKey: AttributeKey<Any>): Any? {
   return (T)request.getAttributes().getOrNull(attributeKey);
}

internal suspend fun checkResponse(response: HttpResponse) {
   var `$continuation`: Continuation;
   label48: {
      if (`$completion` is io.ktor.client.plugins.sse.SSEKt.checkResponse.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.sse.SSEKt.checkResponse.1;
         if (((`$completion` as io.ktor.client.plugins.sse.SSEKt.checkResponse.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label48;
         }
      }

      `$continuation` = new io.ktor.client.plugins.sse.SSEKt.checkResponse.1(`$completion`);
   }

   var var23: HttpStatusCode;
   var var25: Any;
   label43: {
      val `$result`: Any = `$continuation`.result;
      val var20: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var contentType: ContentType;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            var23 = response.getStatus();
            contentType = HttpMessagePropertiesKt.contentType(response);
            if (var23 == HttpStatusCode.Companion.getNoContent()) {
               val `$this$trace$iv`: Logger = LOGGER;
               if (LoggerJvmKt.isTraceEnabled(LOGGER)) {
                  `$this$trace$iv`.trace("Receive status code NoContent for SSE request to ${HttpResponseKt.getRequest(response).getUrl()}");
               }

               return Unit.INSTANCE;
            }

            if (!(var23 == HttpStatusCode.Companion.getOK())) {
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(response);
               `$continuation`.L$1 = var23;
               `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(contentType);
               `$continuation`.label = 1;
               var25 = saved(response, `$continuation`);
               if (var25 === var20) {
                  return var20;
               }
               break label43;
            }

            if ((if (contentType != null) contentType.withoutParameters() else null) == ContentType.Text.INSTANCE.getEventStream()) {
               return Unit.INSTANCE;
            }

            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(response);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var23);
            `$continuation`.L$2 = contentType;
            `$continuation`.label = 2;
            var25 = saved(response, `$continuation`);
            if (var25 === var20) {
               return var20;
            }
            break;
         case 1:
            contentType = `$continuation`.L$2 as ContentType;
            var23 = `$continuation`.L$1 as HttpStatusCode;
            response = `$continuation`.L$0 as HttpResponse;
            ResultKt.throwOnFailure(`$result`);
            var25 = `$result`;
            break label43;
         case 2:
            contentType = `$continuation`.L$2 as ContentType;
            var23 = `$continuation`.L$1 as HttpStatusCode;
            response = `$continuation`.L$0 as HttpResponse;
            ResultKt.throwOnFailure(`$result`);
            var25 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      throw new SSEClientException(
         var25 as HttpResponse, null, "Expected Content-Type ${ContentType.Text.INSTANCE.getEventStream()} but was $contentType", 2, null
      );
   }

   throw new SSEClientException(
      var25 as HttpResponse, null, "Expected status code ${HttpStatusCode.Companion.getOK().getValue()} but was ${var23.getValue()}", 2, null
   );
}

internal suspend fun HttpResponse.saved(): HttpResponse {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.client.plugins.sse.SSEKt.saved.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.sse.SSEKt.saved.1;
         if (((`$completion` as io.ktor.client.plugins.sse.SSEKt.saved.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.client.plugins.sse.SSEKt.saved.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$this$saved`.getCall();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$saved`);
         `$continuation`.label = 1;
         var10000 = SavedCallKt.save((HttpClientCall)var10000, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         `$this$saved` = `$continuation`.L$0 as HttpResponse;
         ResultKt.throwOnFailure(`$result`);
         var10000 = `$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   val savedCall: HttpClientCall = var10000 as HttpClientCall;
   (var10000 as HttpClientCall).getRequest().getAttributes().remove(BuildersKt.getSseRequestAttr());
   return savedCall.getResponse();
}

fun ClientPluginBuilder.`SSE$lambda$0`(): Unit {
   `$this$createClientPlugin`.on(
      AfterRender.INSTANCE,
      new io.ktor.client.plugins.sse.SSEKt.SSE.2.1(
         `$this$createClientPlugin`,
         (`$this$createClientPlugin`.getPluginConfig() as SSEConfig).getReconnectionTime-UwyO8pc(),
         (`$this$createClientPlugin`.getPluginConfig() as SSEConfig).getShowCommentEvents$ktor_client_core(),
         (`$this$createClientPlugin`.getPluginConfig() as SSEConfig).getShowRetryEvents$ktor_client_core(),
         (`$this$createClientPlugin`.getPluginConfig() as SSEConfig).getMaxReconnectionAttempts(),
         (`$this$createClientPlugin`.getPluginConfig() as SSEConfig).getBufferPolicy(),
         null
      )
   );
   `$this$createClientPlugin`.getClient().getResponsePipeline().intercept(HttpResponsePipeline.Phases.getTransform(), new 2(null));
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$getAttributeValue`(request: HttpRequestBuilder, attributeKey: AttributeKey): Any {
   return getAttributeValue(request, attributeKey);
}
