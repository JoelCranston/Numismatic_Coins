package io.ktor.client

import io.ktor.client.HttpClient.2
import io.ktor.client.HttpClient.4
import io.ktor.client.HttpClient.execute.1
import io.ktor.client.call.HttpClientCall
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.HttpClientEngineCapability
import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.BodyProgressKt
import io.ktor.client.plugins.DefaultResponseValidationKt
import io.ktor.client.plugins.DefaultTransformKt
import io.ktor.client.plugins.DoubleReceivePluginKt
import io.ktor.client.plugins.HttpCallValidatorKt
import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.client.plugins.HttpPlainTextKt
import io.ktor.client.plugins.HttpRedirectKt
import io.ktor.client.plugins.HttpRequestLifecycleKt
import io.ktor.client.plugins.HttpSend
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.HttpSendPipeline
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.client.utils.ClientEventsKt
import io.ktor.events.Events
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.util.AttributesJvmKt
import java.io.Closeable
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineScopeKt
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt

@SourceDebugExtension(["SMAP\nHttpClient.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClient.kt\nio/ktor/client/HttpClient\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1493:1\n1869#2,2:1494\n*S KotlinDebug\n*F\n+ 1 HttpClient.kt\nio/ktor/client/HttpClient\n*L\n1476#1:1494,2\n*E\n"])
public class HttpClient(engine: HttpClientEngine, userConfig: HttpClientConfig<out HttpClientEngineConfig> = new HttpClientConfig()) :
   CoroutineScope,
   Closeable {
   public final val engine: HttpClientEngine
   private final val userConfig: HttpClientConfig<out HttpClientEngineConfig>
   private final var manageEngine: Boolean
   private final val clientJob: CompletableJob
   public open val coroutineContext: CoroutineContext
   public final val requestPipeline: HttpRequestPipeline
   public final val responsePipeline: HttpResponsePipeline
   public final val sendPipeline: HttpSendPipeline
   public final val receivePipeline: HttpReceivePipeline
   public final val attributes: Attributes
   public final val engineConfig: HttpClientEngineConfig
   public final val monitor: Events
   internal final val config: HttpClientConfig<HttpClientEngineConfig>

   init {
      this.engine = engine;
      this.userConfig = userConfig;
      this.closed = 0;
      this.clientJob = JobKt.Job(this.engine.getCoroutineContext().get(Job.Key));
      this.coroutineContext = this.engine.getCoroutineContext().plus(this.clientJob);
      this.requestPipeline = new HttpRequestPipeline(false, 1, null);
      this.responsePipeline = new HttpResponsePipeline(false, 1, null);
      this.sendPipeline = new HttpSendPipeline(false, 1, null);
      this.receivePipeline = new HttpReceivePipeline(false, 1, null);
      this.attributes = AttributesJvmKt.Attributes(true);
      this.engineConfig = this.engine.getConfig();
      this.monitor = new Events();
      this.config = new HttpClientConfig<>();
      if (this.manageEngine) {
         this.clientJob.invokeOnCompletion(HttpClient::_init_$lambda$0);
      }

      this.engine.install(this);
      this.sendPipeline.intercept(HttpSendPipeline.Phases.getReceive(), new 2(this, null));
      val `$this$_init__u24lambda_u241`: HttpClientConfig = this.userConfig;
      HttpClientConfig.install$default(this.config, HttpRequestLifecycleKt.getHttpRequestLifecycle(), null, 2, null);
      HttpClientConfig.install$default(this.config, BodyProgressKt.getBodyProgress(), null, 2, null);
      HttpClientConfig.install$default(this.config, DoubleReceivePluginKt.getSaveBody(), null, 2, null);
      if (`$this$_init__u24lambda_u241`.getUseDefaultTransformers()) {
         this.config.install("DefaultTransformers", HttpClient::lambda$1$0);
      }

      HttpClientConfig.install$default(this.config, HttpSend.Plugin, null, 2, null);
      HttpClientConfig.install$default(this.config, HttpCallValidatorKt.getHttpCallValidator(), null, 2, null);
      if (`$this$_init__u24lambda_u241`.getFollowRedirects()) {
         HttpClientConfig.install$default(this.config, HttpRedirectKt.getHttpRedirect(), null, 2, null);
      }

      this.config.plusAssign(`$this$_init__u24lambda_u241`);
      if (`$this$_init__u24lambda_u241`.getUseDefaultTransformers()) {
         HttpClientConfig.install$default(this.config, HttpPlainTextKt.getHttpPlainText(), null, 2, null);
      }

      DefaultResponseValidationKt.addDefaultResponseValidation(this.config);
      this.config.install(this);
      this.responsePipeline.intercept(HttpResponsePipeline.Phases.getReceive(), new 4(this, null));
   }

   internal constructor(engine: HttpClientEngine, userConfig: HttpClientConfig<out HttpClientEngineConfig>, manageEngine: Boolean) : this(engine, userConfig) {
      this.manageEngine = manageEngine;
   }

   internal suspend fun execute(builder: HttpRequestBuilder): HttpClientCall {
      var `$continuation`: Continuation;
      label20: {
         if (`$completion` is 1) {
            `$continuation` = `$completion` as 1;
            if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label20;
            }
         }

         `$continuation` = new 1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var5: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            this.monitor.raise(ClientEventsKt.getHttpRequestCreated(), builder);
            var10000 = this.requestPipeline;
            val var10002: Any = builder.getBody();
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(builder);
            `$continuation`.label = 1;
            var10000 = (HttpRequestPipeline)var10000.execute(builder, var10002, `$continuation`);
            if (var10000 === var5) {
               return var5;
            }
            break;
         case 1:
            builder = `$continuation`.L$0 as HttpRequestBuilder;
            ResultKt.throwOnFailure(`$result`);
            var10000 = (HttpRequestPipeline)`$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      return var10000 as HttpClientCall;
   }

   public fun isSupported(capability: HttpClientEngineCapability<*>): Boolean {
      return this.engine.getSupportedCapabilities().contains(capability);
   }

   public fun config(block: (HttpClientConfig<*>) -> Unit): HttpClient {
      val var10000: HttpClientEngine = this.engine;
      val var2: HttpClientConfig = new HttpClientConfig();
      var2.plusAssign(this.userConfig);
      block.invoke(var2);
      return new HttpClient(var10000, var2, this.manageEngine);
   }

   public override fun close() {
      if (closed$FU.compareAndSet(this, 0, 1)) {
         val installedFeatures: Attributes = this.attributes.get(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST());

         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            val key: AttributeKey = `element$iv` as AttributeKey;
            val plugin: Any = installedFeatures.get(key);
            if (plugin is AutoCloseable) {
               (plugin as AutoCloseable).close();
            }
         }

         this.clientJob.complete();
         if (this.manageEngine) {
            this.engine.close();
         }
      }
   }

   public override fun toString(): String {
      return "HttpClient[${this.engine}]";
   }

   @JvmStatic
   fun `_init_$lambda$0`(`this$0`: HttpClient, it: java.lang.Throwable): Unit {
      if (it != null) {
         CoroutineScopeKt.cancel$default(`this$0`.engine, null, 1, null);
      }

      return Unit.INSTANCE;
   }

   @JvmStatic
   fun HttpClient.`lambda$1$0`(): Unit {
      DefaultTransformKt.defaultTransformers(`$this$install`);
      return Unit.INSTANCE;
   }
}
