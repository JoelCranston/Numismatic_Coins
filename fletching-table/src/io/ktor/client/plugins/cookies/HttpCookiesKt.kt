@file:SourceDebugExtension(["SMAP\nHttpCookies.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCookies.kt\nio/ktor/client/plugins/cookies/HttpCookiesKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,166:1\n1#2:167\n*E\n"])

package io.ktor.client.plugins.cookies

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.client.plugins.cookies.HttpCookiesKt.cookies.2
import io.ktor.client.plugins.cookies.HttpCookiesKt.renderClientCookies.1
import io.ktor.http.Cookie
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.util.logging.KtorSimpleLoggerJvmKt
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import org.slf4j.Logger

private final val LOGGER: Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpCookies")

private fun renderClientCookies(cookies: List<Cookie>): String {
   return CollectionsKt.joinToString$default(cookies, "; ", null, null, 0, null, 1.INSTANCE, 30, null);
}

public suspend fun HttpClient.cookies(url: Url): List<Cookie> {
   var `$continuation`: Continuation;
   label34: {
      if (`$completion` is io.ktor.client.plugins.cookies.HttpCookiesKt.cookies.1) {
         `$continuation` = `$completion` as io.ktor.client.plugins.cookies.HttpCookiesKt.cookies.1;
         if (((`$completion` as io.ktor.client.plugins.cookies.HttpCookiesKt.cookies.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label34;
         }
      }

      `$continuation` = new io.ktor.client.plugins.cookies.HttpCookiesKt.cookies.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = HttpClientPluginKt.pluginOrNull(`$this$cookies`, HttpCookies.Companion);
         if (var10000 == null) {
            return CollectionsKt.emptyList();
         }

         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$cookies`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(url);
         `$continuation`.label = 1;
         var10000 = (HttpCookies)var10000.get(url, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         url = `$continuation`.L$1 as Url;
         `$this$cookies` = `$continuation`.L$0 as HttpClient;
         ResultKt.throwOnFailure(`$result`);
         var10000 = (HttpCookies)`$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return if (var10000 as java.util.List != null) var10000 as java.util.List else CollectionsKt.emptyList();
}

public suspend fun HttpClient.cookies(urlString: String): List<Cookie> {
   var `$continuation`: Continuation;
   label34: {
      if (`$completion` is 2) {
         `$continuation` = `$completion` as 2;
         if (((`$completion` as 2).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label34;
         }
      }

      `$continuation` = new 2(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = HttpClientPluginKt.pluginOrNull(`$this$cookies`, HttpCookies.Companion);
         if (var10000 == null) {
            return CollectionsKt.emptyList();
         }

         val var10001: Url = URLUtilsKt.Url(urlString);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$cookies`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(urlString);
         `$continuation`.label = 1;
         var10000 = (HttpCookies)var10000.get(var10001, `$continuation`);
         if (var10000 === var5) {
            return var5;
         }
         break;
      case 1:
         urlString = `$continuation`.L$1 as java.lang.String;
         `$this$cookies` = `$continuation`.L$0 as HttpClient;
         ResultKt.throwOnFailure(`$result`);
         var10000 = (HttpCookies)`$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return if (var10000 as java.util.List != null) var10000 as java.util.List else CollectionsKt.emptyList();
}

public operator fun List<Cookie>.get(name: String): Cookie? {
   val var3: java.util.Iterator = `$this$get`.iterator();

   var var10000: Any;
   while (true) {
      if (var3.hasNext()) {
         val var4: Any = var3.next();
         if (!((var4 as Cookie).getName() == name)) {
            continue;
         }

         var10000 = var4;
         break;
      }

      var10000 = null;
      break;
   }

   return var10000 as Cookie;
}

@JvmSynthetic
fun `access$getLOGGER$p`(): Logger {
   return LOGGER;
}

@JvmSynthetic
fun `access$renderClientCookies`(cookies: java.util.List): java.lang.String {
   return renderClientCookies(cookies);
}
