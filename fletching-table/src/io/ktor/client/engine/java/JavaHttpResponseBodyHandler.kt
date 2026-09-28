package io.ktor.client.engine.java

import io.ktor.client.engine.java.JavaHttpResponseBodyHandler.JavaHttpResponseBodySubscriber.1
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpRequestKt
import io.ktor.client.request.HttpResponseData
import io.ktor.client.request.ResponseAdapter
import io.ktor.http.Headers
import io.ktor.http.HeadersImpl
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteChannelUtilsKt
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import java.io.IOException
import java.net.http.HttpClient.Version
import java.net.http.HttpResponse.BodyHandler
import java.net.http.HttpResponse.BodySubscriber
import java.net.http.HttpResponse.ResponseInfo
import java.nio.ByteBuffer
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.concurrent.Flow.Subscription
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ChannelResult
import kotlinx.coroutines.channels.SendChannel

internal class JavaHttpResponseBodyHandler(coroutineContext: CoroutineContext,
      requestData: HttpRequestData,
      requestTime: GMTDate = DateJvmKt.GMTDate$default(null, 1, null)
   ) :
   BodyHandler<HttpResponseData> {
   private final val coroutineContext: CoroutineContext
   private final val requestData: HttpRequestData
   private final val requestTime: GMTDate

   init {
      this.coroutineContext = coroutineContext;
      this.requestData = requestData;
      this.requestTime = requestTime;
   }

   public override fun apply(responseInfo: ResponseInfo): BodySubscriber<HttpResponseData> {
      return new JavaHttpResponseBodyHandler.JavaHttpResponseBodySubscriber(this.coroutineContext, this.requestData, responseInfo, this.requestTime);
   }

   @SourceDebugExtension(["SMAP\nJavaHttpResponseBodyHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaHttpResponseBodyHandler.kt\nio/ktor/client/engine/java/JavaHttpResponseBodyHandler$JavaHttpResponseBodySubscriber\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,156:1\n1869#2,2:157\n*S KotlinDebug\n*F\n+ 1 JavaHttpResponseBodyHandler.kt\nio/ktor/client/engine/java/JavaHttpResponseBodyHandler$JavaHttpResponseBodySubscriber\n*L\n121#1:157,2\n*E\n"])
   private class JavaHttpResponseBodySubscriber(callContext: CoroutineContext, requestData: HttpRequestData, response: ResponseInfo, requestTime: GMTDate) :
      BodySubscriber<HttpResponseData>,
      CoroutineScope {
      private final val consumerJob: CompletableJob
      public open val coroutineContext: CoroutineContext
      private final val responseChannel: ByteChannel
      public final val status: HttpStatusCode
      public final val headers: HeadersImpl
      public final val body: Any
      private final val httpResponse: HttpResponseData
      private final val queue: Channel<ByteBuffer>

      init {
         var var10001: Any;
         label23: {
            super();
            this.consumerJob = JobKt.Job(callContext.get(Job.Key));
            this.coroutineContext = callContext.plus(this.consumerJob);
            val version: ByteChannel = new ByteChannel(false, 1, null);
            ByteChannelUtilsKt.attachJob(version, this.consumerJob);
            this.responseChannel = version;
            this.status = HttpStatusCode.Companion.fromValue(response.statusCode());
            val var10003: java.util.Map = response.headers().map();
            this.headers = new HeadersImpl(var10003);
            val var9: ResponseAdapter = requestData.getAttributes().getOrNull(HttpRequestKt.getResponseAdapterAttributeKey());
            if (var9 != null) {
               val `$this$_init__u24lambda_u242`: Any = var9.adapt(
                  requestData, this.status, this.headers, this.responseChannel, requestData.getBody(), callContext
               );
               if (`$this$_init__u24lambda_u242` != null) {
                  var10001 = (HttpResponseData)`$this$_init__u24lambda_u242`;
                  break label23;
               }
            }

            var10001 = this.responseChannel;
         }

         this.body = var10001;
         var10001 = new HttpResponseData;
         val var14: HttpStatusCode = this.status;
         val var10005: Headers = this.headers;
         val var10: Version = response.version();
         var var10006: HttpProtocolVersion;
         switch (version == null ? -1 : JavaHttpResponseBodyHandler.JavaHttpResponseBodySubscriber.WhenMappings.$EnumSwitchMapping$0[version.ordinal()]) {
            case 1:
               var10006 = HttpProtocolVersion.Companion.getHTTP_1_1();
               break;
            case 2:
               var10006 = HttpProtocolVersion.Companion.getHTTP_2_0();
               break;
            default:
               throw new IllegalStateException("Unknown HTTP protocol version ${var10.name()}");
         }

         var10001./* $VF: Unable to resugar constructor */<init>(var14, requestTime, var10005, var10006, this.body, callContext);
         this.httpResponse = var10001;
         this.closed = 0;
         this.subscription = null;
         this.queue = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
         BuildersKt.launch$default(this, null, null, new 1(this, null), 3, null)
            .invokeOnCompletion(JavaHttpResponseBodyHandler.JavaHttpResponseBodySubscriber::lambda$2$lambda$1);
      }

      public override fun onSubscribe(s: Subscription) {
         label38: {
            try {
               if (!subscription$FU.compareAndSet(this, null, s)) {
                  s.cancel();
                  return;
               }

               if (this.closed != 0) {
                  s.cancel();
               } else {
                  s.request(1L);
               }
            } catch (var6: java.lang.Throwable) {
               val cause: java.lang.Throwable = var6;

               label55: {
                  try {
                     try {
                        this.close(cause);
                        break label55;
                     } catch (var4: IOException) {
                     }
                  } catch (var5: java.lang.Throwable) {
                     this.onError(var6);
                  }

                  this.onError(var6);
                  return;
               }

               this.onError(var6);
            }
         }
      }

      public open fun onNext(items: List<ByteBuffer>) {
         val `$this$forEach$iv`: java.lang.Iterable;
         for (Object element$iv : $this$forEach$iv) {
            val it: ByteBuffer = `element$iv` as ByteBuffer;
            if ((`element$iv` as ByteBuffer).hasRemaining()) {
               ChannelResult.isSuccess-impl(this.queue.trySend-JP2dKIU(it));
            }
         }
      }

      public override fun onError(cause: Throwable) {
         this.close(cause);
      }

      public override fun onComplete() {
         subscription$FU.getAndSet(this, null);
         SendChannel.DefaultImpls.close$default(this.queue, null, 1, null);
      }

      public override fun getBody(): CompletionStage<HttpResponseData> {
         val var10000: CompletionStage = CompletableFuture.completedStage(this.httpResponse);
         return var10000;
      }

      private fun close(cause: Throwable) {
         if (closed$FU.compareAndSet(this, 0, 1)) {
            label30: {
               try {
                  this.queue.close(cause);
                  val var10000: Subscription = subscription$FU.getAndSet(this, null) as Subscription;
                  if (var10000 != null) {
                     var10000.cancel();
                  }
               } catch (var3: java.lang.Throwable) {
                  this.consumerJob.completeExceptionally(cause);
                  this.responseChannel.cancel(cause);
               }

               this.consumerJob.completeExceptionally(cause);
               this.responseChannel.cancel(cause);
            }
         }
      }

      @JvmStatic
      fun `lambda$2$lambda$1`(`this$0`: JavaHttpResponseBodyHandler.JavaHttpResponseBodySubscriber, it: java.lang.Throwable): Unit {
         ByteWriteChannelOperationsKt.close(`this$0`.responseChannel, it);
         `this$0`.consumerJob.complete();
         return Unit.INSTANCE;
      }
   }
}
