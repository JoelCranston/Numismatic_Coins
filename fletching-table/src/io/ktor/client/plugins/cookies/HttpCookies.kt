package io.ktor.client.plugins.cookies

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.cookies.HttpCookies.Companion.install.2
import io.ktor.client.plugins.cookies.HttpCookies.Companion.install.3
import io.ktor.client.plugins.cookies.HttpCookies.initializer.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.HttpResponseKt
import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding
import io.ktor.http.CookieKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessagePropertiesKt
import io.ktor.http.URLBuilderKt
import io.ktor.http.Url
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.KtorDsl
import java.io.Closeable
import java.util.ArrayList
import java.util.Map.Entry
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job

@SourceDebugExtension(["SMAP\nHttpCookies.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpCookies.kt\nio/ktor/client/plugins/cookies/HttpCookies\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 Attributes.kt\nio/ktor/util/AttributesKt\n+ 5 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,166:1\n126#2:167\n153#2,3:168\n1869#3,2:171\n1869#3,2:173\n1869#3,2:175\n21#4:177\n69#5:178\n84#5,8:179\n*S KotlinDebug\n*F\n+ 1 HttpCookies.kt\nio/ktor/client/plugins/cookies/HttpCookies\n*L\n56#1:167\n56#1:168,3\n60#1:171,2\n80#1:173,2\n83#1:175,2\n125#1:177\n125#1:178\n125#1:179,8\n*E\n"])
public class HttpCookies internal constructor(storage: CookiesStorage, defaults: List<(CookiesStorage, Continuation<Unit>) -> Any?>) : Closeable {
   private final val storage: CookiesStorage
   private final val defaults: List<(CookiesStorage, Continuation<Unit>) -> Any?>
   private final val initializer: Job

   init {
      this.storage = storage;
      this.defaults = defaults;
      this.initializer = BuildersKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getUnconfined(), null, new 1(this, null), 2, null);
   }

   public suspend fun get(requestUrl: Url): List<Cookie> {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is io.ktor.client.plugins.cookies.HttpCookies.get.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cookies.HttpCookies.get.1;
            if (((`$completion` as io.ktor.client.plugins.cookies.HttpCookies.get.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cookies.HttpCookies.get.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var7: Job = this.initializer;
            `$continuation`.L$0 = requestUrl;
            `$continuation`.label = 1;
            if (var7.join(`$continuation`) === var5) {
               return var5;
            }
            break;
         case 1:
            requestUrl = `$continuation`.L$0 as Url;
            ResultKt.throwOnFailure(`$result`);
            break;
         case 2:
            requestUrl = `$continuation`.L$0 as Url;
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var8: CookiesStorage = this.storage;
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(requestUrl);
      `$continuation`.label = 2;
      val var10000: Any = var8.get(requestUrl, `$continuation`);
      return if (var10000 === var5) var5 else var10000;
   }

   internal suspend fun captureHeaderCookies(builder: HttpRequestBuilder) {
      var `$continuation`: Continuation;
      label56: {
         if (`$completion` is io.ktor.client.plugins.cookies.HttpCookies.captureHeaderCookies.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cookies.HttpCookies.captureHeaderCookies.1;
            if (((`$completion` as io.ktor.client.plugins.cookies.HttpCookies.captureHeaderCookies.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label56;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cookies.HttpCookies.captureHeaderCookies.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var25: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var `$this$captureHeaderCookies_u24lambda_u240`: HttpRequestBuilder;
      var var4: Int;
      var url: Url;
      var `$this$forEach$iv`: java.lang.Iterable;
      var `$i$f$forEach`: Int;
      var cookieHeader: java.util.Iterator;
      var cookies: java.util.List;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$this$captureHeaderCookies_u24lambda_u240` = builder;
            var4 = 0;
            url = URLBuilderKt.clone(builder.getUrl()).build();
            val var10000: java.lang.String = builder.getHeaders().get(HttpHeaders.INSTANCE.getCookie());
            val var33: java.util.List;
            if (var10000 == null) {
               var33 = null;
            } else {
               HttpCookiesKt.access$getLOGGER$p().trace("Saving cookie $var10000 for ${builder.getUrl()}");
               val var29: java.util.Map = CookieKt.parseClientCookiesHeader$default(var10000, false, 2, null);
               val `destination$iv$iv`: java.util.Collection = new ArrayList(var29.size());

               for (Entry item$iv$iv : $this$map$iv.entrySet()) {
                  `destination$iv$iv`.add(
                     new Cookie(
                        `item$iv$iv`.getKey() as java.lang.String,
                        `item$iv$iv`.getValue() as java.lang.String,
                        CookieEncoding.RAW,
                        null,
                        null,
                        null,
                        null,
                        false,
                        false,
                        null,
                        1016,
                        null
                     )
                  );
               }

               var33 = `destination$iv$iv` as java.util.List;
            }

            cookies = var33;
            if (var33 == null) {
               return Unit.INSTANCE;
            }

            `$this$forEach$iv` = var33;
            `$i$f$forEach` = 0;
            cookieHeader = `$this$forEach$iv`.iterator();
            break;
         case 1:
            val var11: Int = `$continuation`.I$2;
            `$i$f$forEach` = `$continuation`.I$1;
            var4 = `$continuation`.I$0;
            cookies = `$continuation`.L$7 as java.util.List;
            val it: Cookie = `$continuation`.L$6 as Cookie;
            val `element$iv`: Any = `$continuation`.L$5;
            cookieHeader = `$continuation`.L$4 as java.util.Iterator;
            `$this$forEach$iv` = `$continuation`.L$3 as java.lang.Iterable;
            url = `$continuation`.L$2 as Url;
            `$this$captureHeaderCookies_u24lambda_u240` = `$continuation`.L$1 as HttpRequestBuilder;
            builder = `$continuation`.L$0 as HttpRequestBuilder;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while (cookieHeader.hasNext()) {
         val var28: Any = cookieHeader.next();
         val var30: Cookie = var28 as Cookie;
         val var34: CookiesStorage = this.storage;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(builder);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(`$this$captureHeaderCookies_u24lambda_u240`);
         `$continuation`.L$2 = url;
         `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(`$this$forEach$iv`);
         `$continuation`.L$4 = cookieHeader;
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var28);
         `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(var30);
         `$continuation`.L$7 = SpillingKt.nullOutSpilledVariable(cookies);
         `$continuation`.I$0 = var4;
         `$continuation`.I$1 = `$i$f$forEach`;
         `$continuation`.I$2 = 0;
         `$continuation`.label = 1;
         if (var34.addCookie(url, var30, `$continuation`) === var25) {
            return var25;
         }
      }

      return Unit.INSTANCE;
   }

   internal suspend fun sendCookiesWith(builder: HttpRequestBuilder) {
      var `$continuation`: Continuation;
      label30: {
         if (`$completion` is io.ktor.client.plugins.cookies.HttpCookies.sendCookiesWith.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cookies.HttpCookies.sendCookiesWith.1;
            if (((`$completion` as io.ktor.client.plugins.cookies.HttpCookies.sendCookiesWith.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label30;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cookies.HttpCookies.sendCookiesWith.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10001: Url = URLBuilderKt.clone(builder.getUrl()).build();
            `$continuation`.L$0 = builder;
            `$continuation`.label = 1;
            var10000 = this.get(var10001, `$continuation`);
            if (var10000 === var9) {
               return var9;
            }
            break;
         case 1:
            builder = `$continuation`.L$0 as HttpRequestBuilder;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val cookies: java.util.List = var10000 as java.util.List;
      if (!(var10000 as java.util.List).isEmpty()) {
         val cookieHeader: java.lang.String = HttpCookiesKt.access$renderClientCookies(cookies);
         builder.getHeaders().set(HttpHeaders.INSTANCE.getCookie(), cookieHeader);
         HttpCookiesKt.access$getLOGGER$p().trace("Sending cookie $cookieHeader for ${builder.getUrl()}");
      } else {
         builder.getHeaders().remove(HttpHeaders.INSTANCE.getCookie());
      }

      return Unit.INSTANCE;
   }

   internal suspend fun saveCookiesFrom(response: HttpResponse) {
      var `$continuation`: Continuation;
      label49: {
         if (`$completion` is io.ktor.client.plugins.cookies.HttpCookies.saveCookiesFrom.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.cookies.HttpCookies.saveCookiesFrom.1;
            if (((`$completion` as io.ktor.client.plugins.cookies.HttpCookies.saveCookiesFrom.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label49;
            }
         }

         `$continuation` = new io.ktor.client.plugins.cookies.HttpCookies.saveCookiesFrom.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var13: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var url: Url;
      var `$this$forEach$iv`: java.lang.Iterable;
      var `$i$f$forEach`: Int;
      var `$i$f$forEachx`: java.util.Iterator;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            url = HttpResponseKt.getRequest(response).getUrl();
            if (response.getHeaders().getAll(HttpHeaders.INSTANCE.getSetCookie()) != null) {
               for (Object element$iv : var14) {
                  HttpCookiesKt.access$getLOGGER$p()
                     .trace("Received cookie ${var18 as java.lang.String} in response for ${response.getCall().getRequest().getUrl()}");
               }
            }

            `$this$forEach$iv` = HttpMessagePropertiesKt.setCookie(response);
            `$i$f$forEach` = 0;
            `$i$f$forEachx` = `$this$forEach$iv`.iterator();
            break;
         case 1:
            val var9: Int = `$continuation`.I$1;
            `$i$f$forEach` = `$continuation`.I$0;
            val it: Cookie = `$continuation`.L$5 as Cookie;
            val `element$iv`: Any = `$continuation`.L$4;
            `$i$f$forEachx` = `$continuation`.L$3 as java.util.Iterator;
            `$this$forEach$iv` = `$continuation`.L$2 as java.lang.Iterable;
            url = `$continuation`.L$1 as Url;
            response = `$continuation`.L$0 as HttpResponse;
            ResultKt.throwOnFailure(`$result`);
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      while ($i$f$forEachx.hasNext()) {
         val var17: Any = `$i$f$forEachx`.next();
         val var19: Cookie = var17 as Cookie;
         val var22: CookiesStorage = this.storage;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(response);
         `$continuation`.L$1 = url;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(`$this$forEach$iv`);
         `$continuation`.L$3 = `$i$f$forEachx`;
         `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(var17);
         `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var19);
         `$continuation`.I$0 = `$i$f$forEach`;
         `$continuation`.I$1 = 0;
         `$continuation`.label = 1;
         if (var22.addCookie(url, var19, `$continuation`) === var13) {
            return var13;
         }
      }

      return Unit.INSTANCE;
   }

   public override fun close() {
      this.storage.close();
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(HttpCookies.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("HttpCookies", new TypeInfo(HttpCookies::class, var6));
   }

   public companion object : HttpClientPlugin<HttpCookies.Config, HttpCookies> {
      public open val key: AttributeKey<HttpCookies>

      public open fun prepare(block: (io.ktor.client.plugins.cookies.HttpCookies.Config) -> Unit): HttpCookies {
         val var2: HttpCookies.Config = new HttpCookies.Config();
         block.invoke(var2);
         return var2.build$ktor_client_core();
      }

      public open fun install(plugin: HttpCookies, scope: HttpClient) {
         scope.getRequestPipeline()
            .intercept(HttpRequestPipeline.Phases.getState(), new io.ktor.client.plugins.cookies.HttpCookies.Companion.install.1(plugin, null));
         scope.getSendPipeline().intercept(HttpSendPipeline.Phases.getState(), new 2(plugin, null));
         scope.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getState(), new 3(plugin, null));
      }
   }

   @KtorDsl
   public class Config {
      private final val defaults: MutableList<(CookiesStorage, Continuation<Unit>) -> Any?> = (new ArrayList()) as java.util.List
      public final var storage: CookiesStorage = (new AcceptAllCookiesStorage(null, 1, null)) as CookiesStorage

      public fun default(block: (CookiesStorage, Continuation<Unit>) -> Any?) {
         this.defaults.add(block);
      }

      internal fun build(): HttpCookies {
         return new HttpCookies(this.storage, this.defaults);
      }
   }
}
