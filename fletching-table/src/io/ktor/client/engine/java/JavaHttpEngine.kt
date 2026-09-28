package io.ktor.client.engine.java

import io.ktor.client.engine.HttpClientEngineBase
import io.ktor.client.engine.HttpClientEngineCapability
import io.ktor.client.engine.UtilsKt
import io.ktor.client.engine.java.JavaHttpEngine.execute.1
import io.ktor.client.plugins.HttpTimeoutCapability
import io.ktor.client.plugins.HttpTimeoutConfig
import io.ktor.client.plugins.sse.SSECapability
import io.ktor.client.plugins.websocket.WebSocketCapability
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpRequestKt
import io.ktor.client.request.HttpResponseData
import java.net.InetSocketAddress
import java.net.Proxy
import java.net.ProxySelector
import java.net.SocketAddress
import java.net.Proxy.Type
import java.net.http.HttpClient
import java.net.http.HttpClient.Builder
import java.net.http.HttpClient.Version
import java.time.Duration
import java.util.concurrent.CancellationException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlinx.coroutines.ExceptionsKt
import kotlinx.coroutines.ExecutorsKt
import kotlinx.coroutines.JobKt

public class JavaHttpEngine(config: JavaHttpConfig) : HttpClientEngineBase("ktor-java") {
   public open val config: JavaHttpConfig
   private final val protocolVersion: Version
   public open val supportedCapabilities: Set<HttpClientEngineCapability<*>>
   private final var httpClient: HttpClient?

   init {
      this.config = config;
      this.protocolVersion = this.getConfig().getProtocolVersion();
      this.supportedCapabilities = SetsKt.setOf(
         new HttpClientEngineCapability[]{HttpTimeoutCapability.INSTANCE, WebSocketCapability.INSTANCE, SSECapability.INSTANCE}
      );
      JobKt.getJob(this.getCoroutineContext()).invokeOnCompletion(JavaHttpEngine::_init_$lambda$0);
   }

   public override suspend fun execute(data: HttpRequestData): HttpResponseData {
      var `$continuation`: Continuation;
      label81: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label81;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      var callContext: CoroutineContext;
      var var19: HttpResponseData;
      label89: {
         label73: {
            label85: {
               val `$result`: Any = `$continuation`.result;
               val var9: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
               var engine: HttpClient;
               switch ($continuation.label) {
                  case 0:
                     ResultKt.throwOnFailure(`$result`);
                     engine = this.getJavaHttpClient(data);
                     `$continuation`.L$0 = data;
                     `$continuation`.L$1 = engine;
                     `$continuation`.label = 1;
                     var19 = (HttpResponseData)UtilsKt.callContext(`$continuation`);
                     if (var19 === var9) {
                        return var9;
                     }
                     break;
                  case 1:
                     engine = `$continuation`.L$1 as HttpClient;
                     data = `$continuation`.L$0 as HttpRequestData;
                     ResultKt.throwOnFailure(`$result`);
                     var19 = (HttpResponseData)`$result`;
                     break;
                  case 2:
                     callContext = `$continuation`.L$0 as CoroutineContext;

                     try {
                        ResultKt.throwOnFailure(`$result`);
                        var19 = (HttpResponseData)`$result`;
                        break label85;
                     } catch (var15: java.lang.Throwable) {
                        JobKt.cancel(`$continuation`.L$0 as CoroutineContext, ExceptionsKt.CancellationException("Failed to execute request", var15));
                        throw var15;
                     }
                  case 3:
                     callContext = `$continuation`.L$0 as CoroutineContext;

                     try {
                        ResultKt.throwOnFailure(`$result`);
                        var19 = (HttpResponseData)`$result`;
                        break label73;
                     } catch (var12: java.lang.Throwable) {
                        JobKt.cancel(`$continuation`.L$0 as CoroutineContext, ExceptionsKt.CancellationException("Failed to execute request", var12));
                        throw var12;
                     }
                  default:
                     throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
               }

               callContext = var19 as CoroutineContext;

               label64: {
                  try {
                     if (!HttpRequestKt.isUpgradeRequest(data)) {
                        break label64;
                     }

                     `$continuation`.L$0 = callContext;
                     `$continuation`.L$1 = null;
                     `$continuation`.label = 2;
                     var19 = (HttpResponseData)JavaHttpWebSocketKt.executeWebSocketRequest(engine, callContext, data, `$continuation`);
                  } catch (var16: java.lang.Throwable) {
                     JobKt.cancel(var19 as CoroutineContext, ExceptionsKt.CancellationException("Failed to execute request", var16));
                     throw var16;
                  }

                  if (var19 === var9) {
                     return var9;
                  }
                  break label85;
               }

               try {
                  `$continuation`.L$0 = callContext;
                  `$continuation`.L$1 = null;
                  `$continuation`.label = 3;
                  var19 = (HttpResponseData)JavaHttpResponseKt.executeHttpRequest(engine, callContext, data, `$continuation`);
               } catch (var13: java.lang.Throwable) {
                  JobKt.cancel(var19 as CoroutineContext, ExceptionsKt.CancellationException("Failed to execute request", var13));
                  throw var13;
               }

               if (var19 === var9) {
                  return var9;
               }
               break label73;
            }

            try {
               var19 = var19;
               break label89;
            } catch (var14: java.lang.Throwable) {
               JobKt.cancel(callContext, ExceptionsKt.CancellationException("Failed to execute request", var14));
               throw var14;
            }
         }

         try {
            var19 = var19;
            if (var19 == null) {
               throw new CancellationException("Request was cancelled");
            }
         } catch (var11: java.lang.Throwable) {
            JobKt.cancel(callContext, ExceptionsKt.CancellationException("Failed to execute request", var11));
            throw var11;
         }
      }

      try {
         return var19;
      } catch (var10: java.lang.Throwable) {
         JobKt.cancel(callContext, ExceptionsKt.CancellationException("Failed to execute request", var10));
         throw var10;
      }
   }

   private fun getJavaHttpClient(data: HttpRequestData): HttpClient {
      var var10000: HttpClient = this.httpClient;
      if (this.httpClient == null) {
         synchronized (this) {
            var10000 = this.httpClient;
            if (this.httpClient == null) {
               val var4: Builder = HttpClient.newBuilder();
               var4.version(this.protocolVersion);
               var4.executor(ExecutorsKt.asExecutor(this.getDispatcher()));
               this.getConfig().getConfig$ktor_client_java().invoke(var4);
               this.setupProxy(var4);
               val var18: HttpTimeoutConfig = data.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
               if (var18 != null) {
                  val var19: java.lang.Long = var18.getConnectTimeoutMillis();
                  if (var19 != null) {
                     val it: Long = var19.longValue();
                     if (!JavaHttpEngineKt.isTimeoutInfinite$default(it, null, 2, null)) {
                        var4.connectTimeout(Duration.ofMillis(it));
                     }
                  }
               }

               val var15: HttpClient = var4.build();
               this.httpClient = var15;
               var10000 = var15;
            }

            var10000 = var10000;
         }
      }

      return var10000;
   }

   private fun Builder.setupProxy() {
      val var10000: Proxy = this.getConfig().getProxy();
      if (var10000 != null) {
         val type: Type = var10000.type();
         switch (type == null ? -1 : JavaHttpEngine.WhenMappings.$EnumSwitchMapping$0[type.ordinal()]) {
            case 1:
            case 2:
               val address: SocketAddress = var10000.address();
               if (address !is InetSocketAddress) {
                  throw new IllegalStateException("Only http proxy is supported for Java HTTP engine.".toString());
               }

               `$this$setupProxy`.proxy(ProxySelector.of(address as InetSocketAddress));
               break;
            case 3:
               `$this$setupProxy`.proxy(Builder.NO_PROXY);
               break;
            default:
               throw new IllegalStateException(("Java HTTP engine does not currently support $type proxies.").toString());
         }
      }
   }

   @JvmStatic
   fun `_init_$lambda$0`(`this$0`: JavaHttpEngine, it: java.lang.Throwable): Unit {
      `this$0`.httpClient = null;
      return Unit.INSTANCE;
   }
}
