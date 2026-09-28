package io.ktor.client.plugins.sse

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.ContentType
import io.ktor.http.HeaderValueWithParametersKt
import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.HttpHeaders
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext
import kotlin.time.Duration

@InternalAPI
public class SSEClientContent(reconnectionTime: Duration,
   showCommentEvents: Boolean,
   showRetryEvents: Boolean,
   maxReconnectionAttempts: Int,
   bufferPolicy: SSEBufferPolicy,
   callContext: CoroutineContext,
   initialRequest: HttpRequestBuilder,
   requestBody: OutgoingContent
) : SSEClientContent(reconnectionTime, showCommentEvents, showRetryEvents, maxReconnectionAttempts, bufferPolicy, callContext, initialRequest, requestBody) {
   public final val reconnectionTime: Duration
   public final val showCommentEvents: Boolean
   public final val showRetryEvents: Boolean
   public final val maxReconnectionAttempts: Int
   public final val bufferPolicy: SSEBufferPolicy
   public final val callContext: CoroutineContext
   public final val initialRequest: HttpRequestBuilder
   public open val headers: Headers

   fun SSEClientContent(
      reconnectionTime: Long,
      showCommentEvents: Boolean,
      showRetryEvents: Boolean,
      maxReconnectionAttempts: Int,
      bufferPolicy: SSEBufferPolicy,
      callContext: CoroutineContext,
      initialRequest: HttpRequestBuilder,
      requestBody: OutgoingContent
   ) {
      super(requestBody);
      this.reconnectionTime = reconnectionTime;
      this.showCommentEvents = showCommentEvents;
      this.showRetryEvents = showRetryEvents;
      this.maxReconnectionAttempts = maxReconnectionAttempts;
      this.bufferPolicy = bufferPolicy;
      this.callContext = callContext;
      this.initialRequest = initialRequest;
      val var10: HeadersBuilder = new HeadersBuilder(0, 1, null);
      var10.appendAll(requestBody.getHeaders());
      HeaderValueWithParametersKt.append(var10, HttpHeaders.INSTANCE.getAccept(), ContentType.Text.INSTANCE.getEventStream());
      var10.append(HttpHeaders.INSTANCE.getCacheControl(), "no-store");
      this.headers = var10.build();
   }

   public override fun toString(): String {
      return "SSEClientContent";
   }

   public open fun copy(delegate: OutgoingContent): SSEClientContent {
      return new SSEClientContent(
         this.reconnectionTime,
         this.showCommentEvents,
         this.showRetryEvents,
         this.maxReconnectionAttempts,
         this.bufferPolicy,
         this.callContext,
         this.initialRequest,
         delegate,
         null
      );
   }
}
