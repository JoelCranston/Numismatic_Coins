package io.ktor.client.plugins.sse

import io.ktor.client.HttpClient
import io.ktor.client.plugins.sse.DefaultClientSSESession._incoming.1
import io.ktor.client.plugins.sse.DefaultClientSSESession._incoming.2
import io.ktor.client.plugins.sse.DefaultClientSSESession._incoming.3
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpHeaders
import io.ktor.sse.ServerSentEvent
import io.ktor.util.ThrowableKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelKt
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ClosedByteChannelException
import java.net.SocketTimeoutException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.Ref
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.JobKt
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowKt

/** @deprecated */
@Deprecated(message = "It should be marked with `@InternalAPI`, please use `ClientSSESession` instead")
public class DefaultClientSSESession(content: SSEClientContent, input: ByteReadChannel, coroutineContext: CoroutineContext) : SSESession {
   private final var input: ByteReadChannel
   public open val coroutineContext: CoroutineContext
   private final var lastEventId: String?
   private final var reconnectionTimeMillis: Long
   private final val showCommentEvents: Boolean
   private final val showRetryEvents: Boolean
   private final val maxReconnectionAttempts: Int
   private final var needToReconnect: Boolean
   private final var bodyBuffer: BodyBuffer
   private final val initialRequest: HttpRequestBuilder
   private final val clientForReconnection: HttpClient
   private final val callContext: CoroutineContext
   private final var _incoming: Flow<ServerSentEvent>

   public open val incoming: Flow<ServerSentEvent>
      public open get() {
         return this._incoming;
      }


   init {
      this.input = input;
      this.coroutineContext = coroutineContext;
      this.reconnectionTimeMillis = Duration.getInWholeMilliseconds-impl(content.getReconnectionTime-UwyO8pc());
      this.showCommentEvents = content.getShowCommentEvents();
      this.showRetryEvents = content.getShowRetryEvents();
      this.maxReconnectionAttempts = content.getMaxReconnectionAttempts();
      this.needToReconnect = this.maxReconnectionAttempts > 0;
      this.bodyBuffer = SSEBufferPolicyKt.toBodyBuffer(content.getBufferPolicy());
      this.initialRequest = content.getInitialRequest();
      this.clientForReconnection = this.initialRequest.getAttributes().get(SSEKt.getSSEClientForReconnectionAttr());
      this.callContext = content.getCallContext();
      this._incoming = FlowKt.onCompletion(FlowKt.catch(FlowKt.flow(new 1(this, null)), new 2(this, null)), new 3(this, null));
      JobKt.getJob(this.getCoroutineContext()).invokeOnCompletion(DefaultClientSSESession::_init_$lambda$0);
   }

   public override fun bodyBuffer(): ByteArray {
      return this.bodyBuffer.toByteArray();
   }

   public constructor(content: SSEClientContent, input: ByteReadChannel) : this(
         content, input, content.getCallContext().plus(JobKt.Job$default(null, 1, null)).plus(new CoroutineName("DefaultClientSSESession"))
      )
   private suspend fun doReconnection() {
      val var10000: Any = kotlinx.coroutines.BuildersKt.withContext(
         this.getCoroutineContext(), new io.ktor.client.plugins.sse.DefaultClientSSESession.doReconnection.2(this, null), `$completion`
      );
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }

   private fun getRequestForReconnection(): HttpRequestBuilder {
      val var1: HttpRequestBuilder = new HttpRequestBuilder().takeFrom(this.initialRequest);
      var1.getAttributes().remove(BuildersKt.getSseRequestAttr());
      var1.getAttributes().put(SSEKt.getSSEReconnectionRequestAttr(), true);
      if (this.lastEventId != null) {
         val it: java.lang.String = this.lastEventId;
         var1.getHeaders().append(HttpHeaders.INSTANCE.getLastEventID(), it);
      }

      return var1;
   }

   private fun close() {
      JobKt.cancel$default(this.getCoroutineContext(), null, 1, null);
      ByteReadChannelKt.cancel(this.input);
      JobKt.cancel$default(this.callContext, null, 1, null);
   }

   private suspend fun ByteReadChannel.tryParseEvent(): ServerSentEvent? {
      var `$continuation`: Continuation;
      label53: {
         if (`$completion` is io.ktor.client.plugins.sse.DefaultClientSSESession.tryParseEvent.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.sse.DefaultClientSSESession.tryParseEvent.1;
            if (((`$completion` as io.ktor.client.plugins.sse.DefaultClientSSESession.tryParseEvent.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label53;
            }
         }

         `$continuation` = new io.ktor.client.plugins.sse.DefaultClientSSESession.tryParseEvent.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var8: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);

            try {
               `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$tryParseEvent`);
               `$continuation`.label = 1;
               var10000 = this.parseEvent(`$this$tryParseEvent`, `$continuation`);
            } catch (var10: ClosedByteChannelException) {
               val rootCause: java.lang.Throwable = ThrowableKt.getRootCause(var10);
               if (rootCause is SocketTimeoutException) {
                  throw rootCause;
               }

               return null;
            }

            if (var10000 === var8) {
               return var8;
            }
            break;
         case 1:
            `$this$tryParseEvent` = `$continuation`.L$0 as ByteReadChannel;

            try {
               ResultKt.throwOnFailure(`$result`);
               var10000 = `$result`;
               break;
            } catch (var11: ClosedByteChannelException) {
               val rootCause: java.lang.Throwable = ThrowableKt.getRootCause(var11);
               if (rootCause is SocketTimeoutException) {
                  throw rootCause;
               }

               return null;
            }
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      try {
         return var10000 as ServerSentEvent;
      } catch (var9: ClosedByteChannelException) {
         val rootCause: java.lang.Throwable = ThrowableKt.getRootCause(var9);
         if (rootCause is SocketTimeoutException) {
            throw rootCause;
         } else {
            return null;
         }
      }
   }

   private suspend fun ByteReadChannel.parseEvent(): ServerSentEvent? {
      var `$continuation`: Continuation;
      label117: {
         if (`$completion` is io.ktor.client.plugins.sse.DefaultClientSSESession.parseEvent.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.sse.DefaultClientSSESession.parseEvent.1;
            if (((`$completion` as io.ktor.client.plugins.sse.DefaultClientSSESession.parseEvent.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label117;
            }
         }

         `$continuation` = new io.ktor.client.plugins.sse.DefaultClientSSESession.parseEvent.1(this, `$completion`);
      }

      var data: StringBuilder;
      var comments: StringBuilder;
      var eventType: java.lang.String;
      var curRetry: Ref.ObjectRef;
      var lastEventId: java.lang.String;
      var wasData: Boolean;
      var wasComments: Boolean;
      var var19: Any;
      var var20: java.lang.String;
      label111: {
         val `$result`: Any = `$continuation`.result;
         var19 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
         switch ($continuation.label) {
            case 0:
               ResultKt.throwOnFailure(`$result`);
               data = new StringBuilder();
               comments = new StringBuilder();
               eventType = null;
               curRetry = new Ref.ObjectRef();
               lastEventId = this.lastEventId;
               wasData = false;
               wasComments = false;
               `$continuation`.L$0 = `$this$parseEvent`;
               `$continuation`.L$1 = data;
               `$continuation`.L$2 = comments;
               `$continuation`.L$3 = curRetry;
               `$continuation`.L$4 = lastEventId;
               `$continuation`.I$0 = false;
               `$continuation`.I$1 = false;
               `$continuation`.label = 1;
               var var25: Any = this.readUTF8LineWithSave(`$this$parseEvent`, `$continuation`);
               if (var25 === var19) {
                  return var19;
               }

               var25 = var25 as java.lang.String;
               if (var25 as java.lang.String == null) {
                  return null;
               }

               var20 = (java.lang.String)var25;
               break;
            case 1:
               wasComments = (boolean)`$continuation`.I$1;
               wasData = (boolean)`$continuation`.I$0;
               lastEventId = `$continuation`.L$4 as java.lang.String;
               curRetry = `$continuation`.L$3 as Ref.ObjectRef;
               eventType = null;
               comments = `$continuation`.L$2 as StringBuilder;
               data = `$continuation`.L$1 as StringBuilder;
               `$this$parseEvent` = `$continuation`.L$0 as ByteReadChannel;
               ResultKt.throwOnFailure(`$result`);
               val var24: java.lang.String = `$result` as java.lang.String;
               if (`$result` as java.lang.String == null) {
                  return null;
               }

               var20 = var24;
               break;
            case 2:
               wasComments = (boolean)`$continuation`.I$1;
               wasData = (boolean)`$continuation`.I$0;
               var20 = `$continuation`.L$5 as java.lang.String;
               lastEventId = `$continuation`.L$4 as java.lang.String;
               curRetry = `$continuation`.L$3 as Ref.ObjectRef;
               eventType = null;
               comments = `$continuation`.L$2 as StringBuilder;
               data = `$continuation`.L$1 as StringBuilder;
               `$this$parseEvent` = `$continuation`.L$0 as ByteReadChannel;
               ResultKt.throwOnFailure(`$result`);
               val var23: java.lang.String = `$result` as java.lang.String;
               if (`$result` as java.lang.String == null) {
                  return null;
               }

               var20 = var23;
               break;
            case 3:
               wasComments = (boolean)`$continuation`.I$1;
               wasData = (boolean)`$continuation`.I$0;
               var20 = `$continuation`.L$6 as java.lang.String;
               lastEventId = `$continuation`.L$5 as java.lang.String;
               curRetry = `$continuation`.L$4 as Ref.ObjectRef;
               eventType = `$continuation`.L$3 as java.lang.String;
               comments = `$continuation`.L$2 as StringBuilder;
               data = `$continuation`.L$1 as StringBuilder;
               `$this$parseEvent` = `$continuation`.L$0 as ByteReadChannel;
               ResultKt.throwOnFailure(`$result`);
               val var10000: java.lang.String = `$result` as java.lang.String;
               if (`$result` as java.lang.String == null) {
                  return null;
               }

               var20 = var10000;
               break label111;
            default:
               throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
         }

         while (StringsKt.isBlank(line)) {
            `$continuation`.L$0 = `$this$parseEvent`;
            `$continuation`.L$1 = data;
            `$continuation`.L$2 = comments;
            `$continuation`.L$3 = curRetry;
            `$continuation`.L$4 = lastEventId;
            `$continuation`.L$5 = SpillingKt.nullOutSpilledVariable(var20);
            `$continuation`.I$0 = wasData;
            `$continuation`.I$1 = wasComments;
            `$continuation`.label = 2;
            var var27: Any = this.readUTF8LineWithSave(`$this$parseEvent`, `$continuation`);
            if (var27 === var19) {
               return var19;
            }

            var27 = var27 as java.lang.String;
            if (var27 as java.lang.String == null) {
               return null;
            }

            var20 = (java.lang.String)var27;
         }
      }

      while (true) {
         if (StringsKt.isBlank(var20)) {
            this.lastEventId = lastEventId;
            val field: ServerSentEvent = new ServerSentEvent(
               if (wasData != 0) this.toText(data) else null,
               eventType,
               lastEventId,
               curRetry.element as java.lang.Long,
               if (wasComments != 0) this.toText(comments) else null
            );
            if (!this.isEmpty(field)) {
               return field;
            }
         } else if (StringsKt.startsWith$default(var20, ":", false, 2, null)) {
            wasComments = (boolean)1;
            this.appendComment(comments, var20);
         } else {
            val var22: java.lang.String = StringsKt.substringBefore$default(var20, ":", null, 2, null);
            val value: java.lang.String = StringsKt.removePrefix(StringsKt.substringAfter(var20, ":", ""), " ");
            switch (field.hashCode()) {
               case 3355:
                  if (var22.equals("id") && !StringsKt.contains$default(value, "\u0000", false, 2, null)) {
                     lastEventId = value;
                  }
                  break;
               case 3076010:
                  if (var22.equals("data")) {
                     wasData = (boolean)1;
                     data.append(value).append("\r\n");
                  }
                  break;
               case 96891546:
                  if (var22.equals("event")) {
                     eventType = value;
                  }
                  break;
               case 108405416:
                  if (var22.equals("retry")) {
                     val var29: java.lang.Long = StringsKt.toLongOrNull(value);
                     if (var29 != null) {
                        val it: Long = var29.longValue();
                        this.reconnectionTimeMillis = it;
                        curRetry.element = (T)Boxing.boxLong(it);
                     }
                  }
               default:
            }
         }

         `$continuation`.L$0 = `$this$parseEvent`;
         `$continuation`.L$1 = data;
         `$continuation`.L$2 = comments;
         `$continuation`.L$3 = eventType;
         `$continuation`.L$4 = curRetry;
         `$continuation`.L$5 = lastEventId;
         `$continuation`.L$6 = SpillingKt.nullOutSpilledVariable(var20);
         `$continuation`.I$0 = wasData;
         `$continuation`.I$1 = wasComments;
         `$continuation`.label = 3;
         var var30: Any = this.readUTF8LineWithSave(`$this$parseEvent`, `$continuation`);
         if (var30 === var19) {
            return var19;
         }

         var30 = var30 as java.lang.String;
         if (var30 as java.lang.String == null) {
            return null;
         }

         var20 = (java.lang.String)var30;
      }
   }

   private fun StringBuilder.appendComment(comment: String) {
      `$this$appendComment`.append(StringsKt.removePrefix(StringsKt.removePrefix(comment, ":"), " ")).append("\r\n");
   }

   private suspend fun ByteReadChannel.readUTF8LineWithSave(): String? {
      var `$continuation`: Continuation;
      label24: {
         if (`$completion` is io.ktor.client.plugins.sse.DefaultClientSSESession.readUTF8LineWithSave.1) {
            `$continuation` = `$completion` as io.ktor.client.plugins.sse.DefaultClientSSESession.readUTF8LineWithSave.1;
            if (((`$completion` as io.ktor.client.plugins.sse.DefaultClientSSESession.readUTF8LineWithSave.1).label and Integer.MIN_VALUE) != 0) {
               `$continuation`.label -= Integer.MIN_VALUE;
               break label24;
            }
         }

         `$continuation` = new io.ktor.client.plugins.sse.DefaultClientSSESession.readUTF8LineWithSave.1(this, `$completion`);
      }

      val `$result`: Any = `$continuation`.result;
      val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
      var var10000: Any;
      switch ($continuation.label) {
         case 0:
            ResultKt.throwOnFailure(`$result`);
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readUTF8LineWithSave`);
            `$continuation`.label = 1;
            var10000 = ByteReadChannelOperationsKt.readUTF8Line$default(`$this$readUTF8LineWithSave`, 0, `$continuation`, 1, null);
            if (var10000 === var6) {
               return var6;
            }
            break;
         case 1:
            `$this$readUTF8LineWithSave` = `$continuation`.L$0 as ByteReadChannel;
            ResultKt.throwOnFailure(`$result`);
            var10000 = `$result`;
            break;
         default:
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
      }

      var10000 = var10000 as java.lang.String;
      if (var10000 as java.lang.String == null) {
         return null;
      } else {
         this.bodyBuffer.appendLine((java.lang.String)var10000);
         return var10000;
      }
   }

   private fun StringBuilder.toText(): String {
      val var10000: java.lang.String = `$this$toText`.toString();
      return StringsKt.removeSuffix(var10000, "\r\n");
   }

   private fun ServerSentEvent.isEmpty(): Boolean {
      return `$this$isEmpty`.getData() == null
         && `$this$isEmpty`.getId() == null
         && `$this$isEmpty`.getEvent() == null
         && `$this$isEmpty`.getRetry() == null
         && `$this$isEmpty`.getComments() == null;
   }

   private fun ServerSentEvent.isCommentsEvent(): Boolean {
      return `$this$isCommentsEvent`.getData() == null
         && `$this$isCommentsEvent`.getEvent() == null
         && `$this$isCommentsEvent`.getId() == null
         && `$this$isCommentsEvent`.getRetry() == null
         && `$this$isCommentsEvent`.getComments() != null;
   }

   private fun ServerSentEvent.isRetryEvent(): Boolean {
      return `$this$isRetryEvent`.getData() == null
         && `$this$isRetryEvent`.getEvent() == null
         && `$this$isRetryEvent`.getId() == null
         && `$this$isRetryEvent`.getComments() == null
         && `$this$isRetryEvent`.getRetry() != null;
   }

   @JvmStatic
   fun `_init_$lambda$0`(`this$0`: DefaultClientSSESession, it: java.lang.Throwable): Unit {
      `this$0`.close();
      return Unit.INSTANCE;
   }
}
