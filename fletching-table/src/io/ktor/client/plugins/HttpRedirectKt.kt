package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpRedirectKt.HttpRedirect.1
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.ClientPluginBuilder
import io.ktor.client.plugins.api.CreatePluginUtilsKt
import io.ktor.client.plugins.api.Send
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.events.EventDefinition
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLBuilderKt
import io.ktor.http.URLParserKt
import io.ktor.http.URLProtocol
import io.ktor.http.URLProtocolKt
import io.ktor.http.UrlKt
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Ref
import org.slf4j.Logger

private final val ALLOWED_FOR_REDIRECT: Set<HttpMethod> = SetsKt.setOf(new HttpMethod[]{HttpMethod.Companion.getGet(), HttpMethod.Companion.getHead()})
private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpRedirect")
public final val HttpResponseRedirectEvent: EventDefinition<HttpResponse> = new EventDefinition()
public final val HttpRedirect: ClientPlugin<HttpRedirectConfig> =
   CreatePluginUtilsKt.createClientPlugin("HttpRedirect", 1.INSTANCE, HttpRedirectKt::HttpRedirect$lambda$0)

private fun HttpStatusCode.isRedirect(): Boolean {
   val var1: Int = `$this$isRedirect`.getValue();
   return var1 == HttpStatusCode.Companion.getMovedPermanently().getValue()
      || var1 == HttpStatusCode.Companion.getFound().getValue()
      || var1 == HttpStatusCode.Companion.getTemporaryRedirect().getValue()
      || var1 == HttpStatusCode.Companion.getPermanentRedirect().getValue()
      || var1 == HttpStatusCode.Companion.getSeeOther().getValue();
}

fun ClientPluginBuilder.`HttpRedirect$lambda$0`(): Unit {
   `$this$createClientPlugin`.on(
      Send.INSTANCE,
      new io.ktor.client.plugins.HttpRedirectKt.HttpRedirect.2.1(
         (`$this$createClientPlugin`.getPluginConfig() as HttpRedirectConfig).getCheckHttpMethod(),
         (`$this$createClientPlugin`.getPluginConfig() as HttpRedirectConfig).getAllowHttpsDowngrade(),
         `$this$createClientPlugin`,
         null
      )
   );
   return Unit.INSTANCE;
}

// $VF: Irreducible bytecode was duplicated to produce valid code
fun Send.Sender.`HttpRedirect$lambda$0$handleCall`(
   context: HttpRequestBuilder, origin: HttpClientCall, allowHttpsDowngrade: Boolean, client: HttpClient, `$completion`: Continuation<? super HttpClientCall>
): Any {
   var `$continuation`: Continuation;
   label49: {
      if (`$completion` is io.ktor.client.plugins.HttpRedirectKt.HttpRedirect.2.handleCall.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.HttpRedirectKt.HttpRedirect.2.handleCall.1;
         if (((`$completion` as io.ktor.client.plugins.HttpRedirectKt.HttpRedirect.2.handleCall.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label49;
         }
      }

      `$continuation` = new io.ktor.client.plugins.HttpRedirectKt.HttpRedirect.2.handleCall.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var17: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var call: Ref.ObjectRef;
   var requestBuilder: Ref.ObjectRef;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         call = new Ref.ObjectRef();
         call.element = (T)origin;
         requestBuilder = new Ref.ObjectRef();
         requestBuilder.element = (T)context;
         break;
      case 1:
         allowHttpsDowngrade = `$continuation`.Z$0;
         val var14: Ref.ObjectRef = `$continuation`.L$9 as Ref.ObjectRef;
         val location: java.lang.String = `$continuation`.L$8 as java.lang.String;
         val previousAuthority: java.lang.String = `$continuation`.L$7 as java.lang.String;
         val previousProtocol: URLProtocol = `$continuation`.L$6 as URLProtocol;
         requestBuilder = `$continuation`.L$5 as Ref.ObjectRef;
         call = `$continuation`.L$4 as Ref.ObjectRef;
         client = `$continuation`.L$3 as HttpClient;
         origin = `$continuation`.L$2 as HttpClientCall;
         context = `$continuation`.L$1 as HttpRequestBuilder;
         `$this$HttpRedirect_u24lambda_u240_u24handleCall` = `$continuation`.L$0 as Send.Sender;
         ResultKt.throwOnFailure(`$result`);
         var14.element = (T)`$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   while (true) {
      val var18: URLProtocol = (call.element as HttpClientCall).getRequest().getUrl().getProtocol();
      val var19: java.lang.String = UrlKt.getAuthority((call.element as HttpClientCall).getRequest().getUrl());
      if (!isRedirect((call.element as HttpClientCall).getResponse().getStatus())) {
         return call.element;
      }

      val var20: java.lang.String = (call.element as HttpClientCall).getResponse().getHeaders().get(HttpHeaders.INSTANCE.getLocation());
      if (var20 == null) {
         LOGGER.warn("Location header missing from redirect response ${(call.element as HttpClientCall).getRequest().getUrl()}; returning response as is");
         return call.element;
      }

      client.getMonitor().raise(HttpResponseRedirectEvent, (call.element as HttpClientCall).getResponse());
      LOGGER.trace("Received redirect response to $var20 for request ${(call.element as HttpClientCall).getRequest().getUrl()}");
      val var11: HttpRequestBuilder = new HttpRequestBuilder();
      var11.takeFromWithExecutionContext(requestBuilder.element as HttpRequestBuilder);
      var11.getUrl().getParameters().clear();
      URLParserKt.takeFrom(var11.getUrl(), var20);
      if (!allowHttpsDowngrade && URLProtocolKt.isSecure(var18) && !URLProtocolKt.isSecure(var11.getUrl().getProtocol())) {
         LOGGER.trace("Blocked redirect from ${(call.element as HttpClientCall).getRequest().getUrl()} to $var20 due to HTTPS downgrade");
         return call.element;
      }

      if (!(var19 == URLBuilderKt.getAuthority(var11.getUrl()))) {
         var11.getHeaders().remove(HttpHeaders.INSTANCE.getAuthorization());
         LOGGER.trace("Removing Authorization header for cross-authority redirect: $var19 -> ${var11.getUrl().buildString()}");
      }

      requestBuilder.element = (T)var11;
      val var10001: HttpRequestBuilder = requestBuilder.element as HttpRequestBuilder;
      `$continuation`.L$0 = `$this$HttpRedirect_u24lambda_u240_u24handleCall`;
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(context);
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(origin);
      `$continuation`.L$3 = client;
      `$continuation`.L$4 = call;
      `$continuation`.L$5 = requestBuilder;
      `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(var18);
      `$continuation`.L$7 = SpillingKt.nullOutSpilledVariable(var19);
      `$continuation`.L$8 = SpillingKt.nullOutSpilledVariable(var20);
      `$continuation`.L$9 = call;
      `$continuation`.Z$0 = allowHttpsDowngrade;
      `$continuation`.label = 1;
      val var10000: Any = `$this$HttpRedirect_u24lambda_u240_u24handleCall`.proceed(var10001, `$continuation`);
      if (var10000 === var17) {
         return var17;
      }

      call.element = (T)var10000;
   }
}

@JvmSynthetic
fun `access$getALLOWED_FOR_REDIRECT$p`(): java.util.Set {
   return ALLOWED_FOR_REDIRECT;
}

@JvmSynthetic
fun `access$HttpRedirect$lambda$0$handleCall`(
   `$receiver`: Send.Sender, context: HttpRequestBuilder, origin: HttpClientCall, allowHttpsDowngrade: Boolean, client: HttpClient, `$completion`: Continuation
): Any {
   return HttpRedirect$lambda$0$handleCall(`$receiver`, context, origin, allowHttpsDowngrade, client, `$completion`);
}
