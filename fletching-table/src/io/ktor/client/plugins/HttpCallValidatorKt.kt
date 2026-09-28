@file:SourceDebugExtension(["SMAP\nHttpCallValidator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCallValidator.kt\nio/ktor/client/plugins/HttpCallValidatorKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Attributes.kt\nio/ktor/util/AttributesKt\n+ 4 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,211:1\n1869#2,2:212\n1869#2,2:214\n21#3:216\n69#4:217\n84#4,8:218\n*S KotlinDebug\n*F\n+ 1 HttpCallValidator.kt\nio/ktor/client/plugins/HttpCallValidatorKt\n*L\n110#1:212,2\n115#1:214,2\n204#1:216\n204#1:217\n204#1:218,8\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.1
import io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.2
import io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.3
import io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.4
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.plugins.api.Send
import io.ktor.client.plugins.api.SetupRequest
import io.ktor.client.request.HttpRequest
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function2
import kotlin.jvm.functions.Function3
import kotlin.jvm.internal.SourceDebugExtension
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCallValidator")
public final val HttpCallValidator: ClientPlugin<HttpCallValidatorConfig> =
   CreatePluginUtilsKt.createClientPlugin("HttpResponseValidator", 1.INSTANCE, HttpCallValidatorKt::HttpCallValidator$lambda$0)

public final var expectSuccess: Boolean
   public final get() {
      val var10000: java.lang.Boolean = `$this$expectSuccess`.getAttributes().getOrNull(ExpectSuccessAttributeKey);
      return var10000 == null || var10000;
   }

   public final set(value) {
      `$this$expectSuccess`.getAttributes().put(ExpectSuccessAttributeKey, value);
   }


internal final val ExpectSuccessAttributeKey: AttributeKey<Boolean>

private fun HttpRequest(builder: HttpRequestBuilder): HttpRequest {
   return new io.ktor.client.plugins.HttpCallValidatorKt.HttpRequest.1(builder);
}

public fun HttpClientConfig<*>.HttpResponseValidator(block: (HttpCallValidatorConfig) -> Unit) {
   `$this$HttpResponseValidator`.install(
      HttpCallValidator as HttpClientPlugin<? extends HttpCallValidatorConfig, ClientPluginInstance<HttpCallValidatorConfig>>, block
   );
}

fun ClientPluginBuilder.`HttpCallValidator$lambda$0`(): Unit {
   val responseValidators: java.util.List = CollectionsKt.reversed(
      (`$this$createClientPlugin`.getPluginConfig() as HttpCallValidatorConfig).getResponseValidators$ktor_client_core()
   );
   val callExceptionHandlers: java.util.List = CollectionsKt.reversed(
      (`$this$createClientPlugin`.getPluginConfig() as HttpCallValidatorConfig).getResponseExceptionHandlers$ktor_client_core()
   );
   `$this$createClientPlugin`.on(
      SetupRequest.INSTANCE,
      new io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.1(
         (`$this$createClientPlugin`.getPluginConfig() as HttpCallValidatorConfig).getExpectSuccess$ktor_client_core(), null
      )
   );
   `$this$createClientPlugin`.on(Send.INSTANCE, new 2(responseValidators, null));
   `$this$createClientPlugin`.on(RequestError.INSTANCE, new 3(callExceptionHandlers, null));
   `$this$createClientPlugin`.on(ReceiveError.INSTANCE, new 4(callExceptionHandlers, null));
   return Unit.INSTANCE;
}

// $VF: Irreducible bytecode was duplicated to produce valid code
fun `HttpCallValidator$lambda$0$validateResponse`(
   responseValidators: MutableList<(HttpResponse?, Continuation<? super Unit>?) -> Any>, response: HttpResponse, `$completion`: Continuation<? super Unit>
): Any {
   var `$continuation`: Continuation;
   label33: {
      if (`$completion` is io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.validateResponse.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.validateResponse.1;
         if (((`$completion` as io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.validateResponse.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label33;
         }
      }

      `$continuation` = new io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.validateResponse.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `$this$forEach$iv`: java.lang.Iterable;
   var `$i$f$forEach`: Int;
   var var5: java.util.Iterator;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         LOGGER.trace("Validating response for request ${response.getCall().getRequest().getUrl()}");
         `$this$forEach$iv` = responseValidators;
         `$i$f$forEach` = 0;
         var5 = `$this$forEach$iv`.iterator();
         break;
      case 1:
         val var8: Int = `$continuation`.I$1;
         `$i$f$forEach` = `$continuation`.I$0;
         val it: Function2 = `$continuation`.L$5 as Function2;
         val `element$iv`: Any = `$continuation`.L$4;
         var5 = `$continuation`.L$3 as java.util.Iterator;
         `$this$forEach$iv` = `$continuation`.L$2 as java.lang.Iterable;
         response = `$continuation`.L$1 as HttpResponse;
         responseValidators = `$continuation`.L$0 as java.util.List;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (var5.hasNext()) {
      val var12: Any = var5.next();
      val var13: Function2 = var12 as Function2;
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(responseValidators);
      `$continuation`.L$1 = response;
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(`$this$forEach$iv`);
      `$continuation`.L$3 = var5;
      `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var12);
      `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var13);
      `$continuation`.I$0 = `$i$f$forEach`;
      `$continuation`.I$1 = 0;
      `$continuation`.label = 1;
      if (var13.invoke(response, `$continuation`) === var11) {
         return var11;
      }
   }

   return Unit.INSTANCE;
}

// $VF: Irreducible bytecode was duplicated to produce valid code
fun `HttpCallValidator$lambda$0$processException`(
   callExceptionHandlers: MutableList<HandlerWrapper>, cause: java.lang.Throwable, request: HttpRequest, `$completion`: Continuation<? super Unit>
): Any {
   var `$continuation`: Continuation;
   label53: {
      if (`$completion` is io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.processException.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.processException.1;
         if (((`$completion` as io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.processException.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label53;
         }
      }

      `$continuation` = new io.ktor.client.plugins.HttpCallValidatorKt.HttpCallValidator.2.processException.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var13: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var `$this$forEach$iv`: java.lang.Iterable;
   var `$i$f$forEach`: Int;
   var var6: java.util.Iterator;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         LOGGER.trace("Processing exception $cause for request ${request.getUrl()}");
         `$this$forEach$iv` = callExceptionHandlers;
         `$i$f$forEach` = 0;
         var6 = `$this$forEach$iv`.iterator();
         break;
      case 1: {
         val var18: Int = `$continuation`.I$1;
         `$i$f$forEach` = `$continuation`.I$0;
         val var16: HandlerWrapper = `$continuation`.L$6 as HandlerWrapper;
         val var14: Any = `$continuation`.L$5;
         var6 = `$continuation`.L$4 as java.util.Iterator;
         `$this$forEach$iv` = `$continuation`.L$3 as java.lang.Iterable;
         request = `$continuation`.L$2 as HttpRequest;
         cause = `$continuation`.L$1 as java.lang.Throwable;
         callExceptionHandlers = `$continuation`.L$0 as java.util.List;
         ResultKt.throwOnFailure(`$result`);
         break;
      }
      case 2: {
         val var9: Int = `$continuation`.I$1;
         `$i$f$forEach` = `$continuation`.I$0;
         val it: HandlerWrapper = `$continuation`.L$6 as HandlerWrapper;
         val `element$iv`: Any = `$continuation`.L$5;
         var6 = `$continuation`.L$4 as java.util.Iterator;
         `$this$forEach$iv` = `$continuation`.L$3 as java.lang.Iterable;
         request = `$continuation`.L$2 as HttpRequest;
         cause = `$continuation`.L$1 as java.lang.Throwable;
         callExceptionHandlers = `$continuation`.L$0 as java.util.List;
         ResultKt.throwOnFailure(`$result`);
         break;
      }
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (var6.hasNext()) {
      val var15: Any = var6.next();
      val var17: HandlerWrapper = var15 as HandlerWrapper;
      if (var15 as HandlerWrapper is ExceptionHandlerWrapper) {
         val var10000: Function2 = (var17 as ExceptionHandlerWrapper).getHandler();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(callExceptionHandlers);
         `$continuation`.L$1 = cause;
         `$continuation`.L$2 = request;
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(`$this$forEach$iv`);
         `$continuation`.L$4 = var6;
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var15);
         `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.I$0 = `$i$f$forEach`;
         `$continuation`.I$1 = 0;
         `$continuation`.label = 1;
         if (var10000.invoke(cause, `$continuation`) === var13) {
            return var13;
         }
      } else {
         if (var17 !is RequestExceptionHandlerWrapper) {
            throw new NoWhenBranchMatchedException();
         }

         val var20: Function3 = (var17 as RequestExceptionHandlerWrapper).getHandler();
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(callExceptionHandlers);
         `$continuation`.L$1 = cause;
         `$continuation`.L$2 = request;
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(`$this$forEach$iv`);
         `$continuation`.L$4 = var6;
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var15);
         `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.I$0 = `$i$f$forEach`;
         `$continuation`.I$1 = 0;
         `$continuation`.label = 2;
         if (var20.invoke(cause, request, `$continuation`) === var13) {
            return var13;
         }
      }
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$HttpRequest`(builder: HttpRequestBuilder): HttpRequest {
   return HttpRequest(builder);
}

@JvmSynthetic
fun `access$HttpCallValidator$lambda$0$validateResponse`(responseValidators: java.util.List, response: HttpResponse, `$completion`: Continuation): Any {
   return HttpCallValidator$lambda$0$validateResponse(responseValidators, response, `$completion`);
}

@JvmSynthetic
fun `access$HttpCallValidator$lambda$0$processException`(
   callExceptionHandlers: java.util.List, cause: java.lang.Throwable, request: HttpRequest, `$completion`: Continuation
): Any {
   return HttpCallValidator$lambda$0$processException(callExceptionHandlers, cause, request, `$completion`);
}
