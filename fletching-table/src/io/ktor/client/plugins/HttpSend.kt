package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpSend.DefaultSender.execute.1
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.HttpSendPipeline
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.KtorDsl
import java.util.ArrayList
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlinx.coroutines.CoroutineScopeKt

@SourceDebugExtension(["SMAP\nHttpSend.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpSend.kt\nio/ktor/client/plugins/HttpSend\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,159:1\n21#2:160\n69#3:161\n84#3,8:162\n*S KotlinDebug\n*F\n+ 1 HttpSend.kt\nio/ktor/client/plugins/HttpSend\n*L\n73#1:160\n73#1:161\n73#1:162,8\n*E\n"])
public class HttpSend private constructor(maxSendCount: Int = 20) {
   private final val maxSendCount: Int
   private final val interceptors: MutableList<(Sender, HttpRequestBuilder, Continuation<HttpClientCall>) -> Any?>

   init {
      this.maxSendCount = maxSendCount;
      this.interceptors = new ArrayList<>();
   }

   public fun intercept(block: (Sender, HttpRequestBuilder, Continuation<HttpClientCall>) -> Any?) {
      this.interceptors.add(block);
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(HttpSend.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("HttpSend", new TypeInfo(HttpSend::class, var6));
   }

   @KtorDsl
   public class Config {
      public final var maxSendCount: Int = 20
   }

   private class DefaultSender(maxSendCount: Int, client: HttpClient) : Sender {
      private final val maxSendCount: Int
      private final val client: HttpClient
      private final var sentCount: Int
      private final var currentCall: HttpClientCall?

      init {
         this.maxSendCount = maxSendCount;
         this.client = client;
      }

      public override suspend fun execute(requestBuilder: HttpRequestBuilder): HttpClientCall {
         var `$continuation`: Continuation;
         label38: {
            if (`$completion` is 1) {
               `$continuation` = `$completion` as 1;
               if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
                  `$continuation`.label -= Integer.MIN_VALUE;
                  break label38;
               }
            }

            `$continuation` = new 1(this, `$completion`);
         }

         val `$result`: Any = `$continuation`.result;
         val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var var10000: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               if (this.currentCall != null) {
                  CoroutineScopeKt.cancel$default(this.currentCall, null, 1, null);
               }

               if (this.sentCount >= this.maxSendCount) {
                  throw new SendCountExceedException(
                     "Max send count ${this.maxSendCount} exceeded. Consider increasing the property maxSendCount if more is required."
                  );
               }

               val sendResult: Int = this.sentCount++;
               var10000 = this.client.getSendPipeline();
               val var10002: Any = requestBuilder.getBody();
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(requestBuilder);
               `$continuation`.label = 1;
               var10000 = (HttpSendPipeline)var10000.execute(requestBuilder, var10002, `$continuation`);
               if (var10000 === var7) {
                  return var7;
               }
               break;
            case 1:
               requestBuilder = `$continuation`.L$0 as HttpRequestBuilder;
               ResultKt.throwOnFailure(`$result`);
               var10000 = (HttpSendPipeline)`$result`;
               break;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         val var11: HttpClientCall = var10000 as? HttpClientCall;
         if ((var10000 as? HttpClientCall) == null) {
            throw new IllegalStateException(("Failed to execute send pipeline. Expected [HttpClientCall], but received $var10000").toString());
         } else {
            this.currentCall = var11;
            return var11;
         }
      }
   }

   private class InterceptedSender(interceptor: (Sender, HttpRequestBuilder, Continuation<HttpClientCall>) -> Any?, nextSender: Sender) : Sender {
      private final val interceptor: (Sender, HttpRequestBuilder, Continuation<HttpClientCall>) -> Any?
      private final val nextSender: Sender

      init {
         this.interceptor = interceptor;
         this.nextSender = nextSender;
      }

      public override suspend fun execute(requestBuilder: HttpRequestBuilder): HttpClientCall {
         return this.interceptor.invoke(this.nextSender, requestBuilder, `$completion`);
      }
   }

   public companion object Plugin : HttpClientPlugin<HttpSend.Config, HttpSend> {
      public open val key: AttributeKey<HttpSend>

      public open fun prepare(block: (io.ktor.client.plugins.HttpSend.Config) -> Unit): HttpSend {
         val var3: HttpSend.Config = new HttpSend.Config();
         block.invoke(var3);
         return new HttpSend(var3.getMaxSendCount(), null);
      }

      public open fun install(plugin: HttpSend, scope: HttpClient) {
         scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getSend(), new io.ktor.client.plugins.HttpSend.Plugin.install.1(plugin, scope, null));
      }
   }
}
