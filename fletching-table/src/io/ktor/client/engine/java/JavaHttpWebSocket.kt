package io.ktor.client.engine.java

import io.ktor.client.engine.UtilsKt
import io.ktor.client.engine.java.JavaHttpWebSocket.1
import io.ktor.client.engine.java.JavaHttpWebSocket.2
import io.ktor.client.plugins.HttpTimeoutCapability
import io.ktor.client.plugins.HttpTimeoutConfig
import io.ktor.client.plugins.websocket.WebSocketException
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.HttpResponseData
import io.ktor.http.Headers
import io.ktor.http.HeadersKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLUtilsJvmKt
import io.ktor.util.date.DateJvmKt
import io.ktor.util.date.GMTDate
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketExtension
import io.ktor.websocket.WebSocketSession
import java.net.http.HttpClient
import java.net.http.WebSocket
import java.net.http.WebSocketHandshakeException
import java.net.http.WebSocket.Builder
import java.net.http.WebSocket.Listener
import java.nio.ByteBuffer
import java.time.Duration
import java.util.Arrays
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CompletableJob
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.future.FutureKt

@SourceDebugExtension(["SMAP\nJavaHttpWebSocket.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JavaHttpWebSocket.kt\nio/ktor/client/engine/java/JavaHttpWebSocket\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,240:1\n37#2:241\n36#2,3:242\n1#3:245\n*S KotlinDebug\n*F\n+ 1 JavaHttpWebSocket.kt\nio/ktor/client/engine/java/JavaHttpWebSocket\n*L\n154#1:241\n154#1:242,3\n*E\n"])
internal class JavaHttpWebSocket(callContext: CoroutineContext,
      httpClient: HttpClient,
      requestData: HttpRequestData,
      requestTime: GMTDate = DateJvmKt.GMTDate$default(null, 1, null)
   ) :
   Listener,
   WebSocketSession {
   private final val callContext: CoroutineContext
   private final val httpClient: HttpClient
   private final val requestData: HttpRequestData
   private final val requestTime: GMTDate
   private final lateinit var webSocket: WebSocket
   private final val socketJob: CompletableJob
   private final val _incoming: Channel<Frame>
   private final val _outgoing: Channel<Frame>

   public open val coroutineContext: CoroutineContext
      public open get() {
         return this.callContext.plus(this.socketJob).plus(new CoroutineName("java-ws"));
      }


   public open var masking: Boolean
      public open get() {
         return true;
      }

      public open set(_) {
      }


   public open var maxFrameSize: Long
      public open get() {
         return java.lang.Long.MAX_VALUE;
      }

      public open set(_) {
      }


   public open val incoming: ReceiveChannel<Frame>
      public open get() {
         return this._incoming;
      }


   public open val outgoing: SendChannel<Frame>
      public open get() {
         return this._outgoing;
      }


   public open val extensions: List<WebSocketExtension<*>>
      public open get() {
         return CollectionsKt.emptyList();
      }


   init {
      this.callContext = callContext;
      this.httpClient = httpClient;
      this.requestData = requestData;
      this.requestTime = requestTime;
      this.socketJob = JobKt.Job(this.callContext.get(Job.Key));
      this._incoming = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
      this._outgoing = ChannelKt.Channel$default(Integer.MAX_VALUE, null, null, 6, null);
      BuildersKt.launch$default(this, null, null, new 1(this, null), 3, null);
      BuildersKt.launch(GlobalScope.INSTANCE, this.callContext, CoroutineStart.ATOMIC, new 2(this, null));
   }

   public suspend fun getResponse(): HttpResponseData {
      var `$continuation`: Continuation;
      label110: {
         if (`$completion` is io.ktor.client.engine.java.JavaHttpWebSocket.getResponse.1) {
            `$continuation` = `$completion` as io.ktor.client.engine.java.JavaHttpWebSocket.getResponse.1;
            if (((`$completion` as io.ktor.client.engine.java.JavaHttpWebSocket.getResponse.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label110;
            }
         }

         `$continuation` = new io.ktor.client.engine.java.JavaHttpWebSocket.getResponse.1(this, `$completion`);
      }

      var cause: WebSocketHandshakeException;
      label102: {
         val `$result`: Any = `$continuation`.result;
         val var15: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         var status: HttpStatusCode;
         var var12: JavaHttpWebSocket;
         var var10000: Any;
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               val builder: Builder = this.httpClient.newWebSocketBuilder();
               var10000 = this.requestData.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
               if (var10000 != null) {
                  val var31: java.lang.Long = var10000.getConnectTimeoutMillis();
                  if (var31 != null) {
                     builder.connectTimeout(Duration.ofMillis(var31.longValue()));
                  }
               }

               UtilsKt.mergeHeaders(this.requestData.getHeaders(), this.requestData.getBody(), JavaHttpWebSocket::getResponse$lambda$4$lambda$2);
               val var32: java.util.List = this.requestData.getHeaders().getAll(HttpHeaders.INSTANCE.getSecWebSocketProtocol());
               if (var32 != null) {
                  val var33: Array<java.lang.String> = var32.toArray(new java.lang.String[0]);
                  if (var33 != null && var33.length != 0) {
                     val mostPreferred: java.lang.String = ArraysKt.first(var33);
                     val var28: Array<java.lang.String> = ArraysKt.sliceArray(var33, RangesKt.until(1, var33.length));
                     builder.subprotocols(mostPreferred, Arrays.copyOf(var28, var28.length));
                  }
               }

               status = HttpStatusCode.Companion.getSwitchingProtocols();

               try {
                  var12 = this;
                  val var23: CompletableFuture = builder.buildAsync(URLUtilsJvmKt.toURI(this.requestData.getUrl()), this);
                  val var34: CompletionStage = var23;
                  `$continuation`.L$0 = status;
                  `$continuation`.L$1 = this;
                  `$continuation`.label = 1;
                  var10000 = (HttpTimeoutConfig)FutureKt.await(var34, `$continuation`);
               } catch (var17: WebSocketHandshakeException) {
                  cause = var17;
                  if (var17.getResponse().statusCode() != HttpStatusCode.Companion.getUnauthorized().getValue()) {
                     throw var17;
                  }
                  break label102;
               }

               if (var10000 === var15) {
                  return var15;
               }
               break;
            case 1:
               var12 = `$continuation`.L$1 as JavaHttpWebSocket;
               status = `$continuation`.L$0 as HttpStatusCode;

               try {
                  ResultKt.throwOnFailure(`$result`);
                  var10000 = (HttpTimeoutConfig)`$result`;
                  break;
               } catch (var18: WebSocketHandshakeException) {
                  cause = var18;
                  if (var18.getResponse().statusCode() != HttpStatusCode.Companion.getUnauthorized().getValue()) {
                     throw var18;
                  }
                  break label102;
               }
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         try {
            var12.webSocket = var10000 as WebSocket;
            var var36: WebSocket = this.webSocket;
            if (this.webSocket == null) {
               Intrinsics.throwUninitializedPropertyAccessException("webSocket");
               var36 = null;
            }

            val var37: java.lang.String = var36.getSubprotocol();
            val var38: java.lang.String = if (var37 != null) (if (var37.length() > 0) var37 else null) else null;
            return new HttpResponseData(
               status,
               this.requestTime,
               if (var38 != null) HeadersKt.headersOf(HttpHeaders.INSTANCE.getSecWebSocketProtocol(), var38) else Headers.Companion.getEmpty(),
               HttpProtocolVersion.Companion.getHTTP_1_1(),
               this,
               this.callContext
            );
         } catch (var16: WebSocketHandshakeException) {
            cause = var16;
            if (var16.getResponse().statusCode() != HttpStatusCode.Companion.getUnauthorized().getValue()) {
               throw var16;
            }
         }
      }

      val status: HttpStatusCode = HttpStatusCode.Companion.getUnauthorized();
      val var35: java.util.Map = cause.getResponse().headers().map();
      return new HttpResponseData(
         status, this.requestTime, JavaHttpWebSocketKt.access$headersOf(var35), HttpProtocolVersion.Companion.getHTTP_1_1(), this, this.callContext
      );
   }

   public override fun onOpen(webSocket: WebSocket) {
      webSocket.request(1L);
   }

   public override fun onText(webSocket: WebSocket, data: CharSequence, last: Boolean): CompletionStage<*> {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(this, null, null, new io.ktor.client.engine.java.JavaHttpWebSocket.onText.1(this, last, data, webSocket, null), 3, null)
      );
   }

   public override fun onBinary(webSocket: WebSocket, data: ByteBuffer, last: Boolean): CompletionStage<*> {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(this, null, null, new io.ktor.client.engine.java.JavaHttpWebSocket.onBinary.1(this, last, data, webSocket, null), 3, null)
      );
   }

   public override fun onPong(webSocket: WebSocket, message: ByteBuffer): CompletionStage<*> {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(this, null, null, new io.ktor.client.engine.java.JavaHttpWebSocket.onPong.1(this, message, webSocket, null), 3, null)
      );
   }

   public override fun onError(webSocket: WebSocket, error: Throwable) {
      val var10000: WebSocketException = new WebSocketException;
      var var10002: java.lang.String = error.getMessage();
      if (var10002 == null) {
         var10002 = "web socket failed";
      }

      var10000./* $VF: Unable to resugar constructor */<init>(var10002, error);
      this._incoming.close(var10000);
      ReceiveChannel.DefaultImpls.cancel$default(this._outgoing, null, 1, null);
      this.socketJob.complete();
   }

   public override fun onClose(webSocket: WebSocket, statusCode: Int, reason: String): CompletionStage<*> {
      return FutureKt.asCompletableFuture(
         BuildersKt.async$default(this, null, null, new io.ktor.client.engine.java.JavaHttpWebSocket.onClose.1(statusCode, reason, this, null), 3, null)
      );
   }

   public override suspend fun flush() {
      return Unit.INSTANCE;
   }

   @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
   public override fun terminate() {
      Job.DefaultImpls.cancel$default(this.socketJob, null, 1, null);
   }

   override fun send(frame: Frame, `$completion`: Continuation<? super Unit>): Any? {
      return WebSocketSession.DefaultImpls.send(this, frame, `$completion`);
   }

   @JvmStatic
   fun `getResponse$lambda$4$lambda$2`(`$this_with`: Builder, key: java.lang.String, value: java.lang.String): Unit {
      if (!JavaHttpWebSocketKt.access$getILLEGAL_HEADERS$p().contains(key) && !JavaHttpRequestKt.getDISALLOWED_HEADERS().contains(key)) {
         `$this_with`.header(key, value);
      }

      return Unit.INSTANCE;
   }
}
