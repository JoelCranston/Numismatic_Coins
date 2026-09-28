package io.ktor.client.engine

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine.executeWithinCallContext.2
import io.ktor.client.engine.HttpClientEngine.install.1
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.HttpSendPipeline
import io.ktor.utils.io.InternalAPI
import java.io.Closeable
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

@SourceDebugExtension(["SMAP\nHttpClientEngine.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientEngine.kt\nio/ktor/client/engine/HttpClientEngine\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,239:1\n1#2:240\n*E\n"])
public interface HttpClientEngine : CoroutineScope, Closeable {
   public val dispatcher: CoroutineDispatcher
   public val config: HttpClientEngineConfig

   public open val supportedCapabilities: Set<HttpClientEngineCapability<*>>
      public open get() {
         return SetsKt.emptySet();
      }


   private final val closed: Boolean
      private final get() {
         val var10000: Job = this.getCoroutineContext().get(Job.Key);
         return var10000 == null || !var10000.isActive();
      }


   @InternalAPI
   public abstract suspend fun execute(data: HttpRequestData): HttpResponseData {
   }

   @InternalAPI
   public open fun install(client: HttpClient) {
      client.getSendPipeline().intercept(HttpSendPipeline.Phases.getEngine(), new 1(client, this, null));
   }

   private suspend fun executeWithinCallContext(requestData: HttpRequestData): HttpResponseData {
      var `$continuation`: Continuation;
      label27: {
         if (`$completion` is io.ktor.client.engine.HttpClientEngine.executeWithinCallContext.1) {
            `$continuation` = `$completion` as io.ktor.client.engine.HttpClientEngine.executeWithinCallContext.1;
            if (((`$completion` as io.ktor.client.engine.HttpClientEngine.executeWithinCallContext.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label27;
            }
         }

         `$continuation` = new io.ktor.client.engine.HttpClientEngine.executeWithinCallContext.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var7: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var11: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            val var10001: Job = requestData.getExecutionContext();
            `$continuation`.L$0 = requestData;
            `$continuation`.label = 1;
            var11 = (Deferred)HttpClientEngineKt.createCallContext(this, var10001, `$continuation`);
            if (var11 === var7) {
               return var7;
            }
            break;
         case 1:
            requestData = `$continuation`.L$0 as HttpRequestData;
            ResultKt.throwOnFailure(`$result`);
            var11 = (Deferred)`$result`;
            break;
         case 2:
            val context: CoroutineContext = `$continuation`.L$2 as CoroutineContext;
            val callContext: CoroutineContext = `$continuation`.L$1 as CoroutineContext;
            requestData = `$continuation`.L$0 as HttpRequestData;
            ResultKt.throwOnFailure(`$result`);
            return `$result`;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      val var9: CoroutineContext = var11;
      val var10: CoroutineContext = var11.plus(new KtorCallContextElement(var11));
      var11 = BuildersKt.async$default(this, var10, null, new 2(this, requestData, null), 2, null);
      `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(requestData);
      `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(var9);
      `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(var10);
      `$continuation`.label = 2;
      var11 = (Deferred)var11.await(`$continuation`);
      return if (var11 === var7) var7 else var11;
   }

   private fun checkExtensions(requestData: HttpRequestData) {
      for (HttpClientEngineCapability requestedExtension : requestData.getRequiredCapabilities$ktor_client_core()) {
         if (!this.getSupportedCapabilities().contains(requestedExtension)) {
            throw new IllegalArgumentException(("Engine doesn't support $requestedExtension").toString());
         }
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun getSupportedCapabilities(`$this`: HttpClientEngine): MutableSet<HttpClientEngineCapability<?>> {
         return HttpClientEngine.access$getSupportedCapabilities$jd(`$this`);
      }

      @Deprecated
      @InternalAPI
      @JvmStatic
      fun install(`$this`: HttpClientEngine, client: HttpClient) {
         HttpClientEngine.access$install$jd(`$this`, client);
      }
   }
}
