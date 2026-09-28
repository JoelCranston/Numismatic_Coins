package io.ktor.client.engine.java

import io.ktor.client.call.UnsupportedContentTypeException
import io.ktor.client.engine.UtilsKt
import io.ktor.client.engine.java.JavaHttpRequestKt.convertToHttpRequestBody.2.1
import io.ktor.client.plugins.HttpTimeoutCapability
import io.ktor.client.plugins.HttpTimeoutConfig
import io.ktor.client.request.HttpRequestData
import io.ktor.http.URLUtilsJvmKt
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublisher
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpRequest.Builder
import java.time.Duration
import java.util.TreeSet
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.GlobalScope

internal final val DISALLOWED_HEADERS: TreeSet<String>

internal fun HttpRequestData.convertToHttpRequest(callContext: CoroutineContext): HttpRequest {
   val builder: Builder = HttpRequest.newBuilder(URLUtilsJvmKt.toURI(`$this$convertToHttpRequest`.getUrl()));
   val var10000: HttpTimeoutConfig = `$this$convertToHttpRequest`.getCapabilityOrNull(HttpTimeoutCapability.INSTANCE);
   if (var10000 != null) {
      val var10: java.lang.Long = var10000.getRequestTimeoutMillis();
      if (var10 != null) {
         val it: Long = var10.longValue();
         if (!JavaHttpEngineKt.isTimeoutInfinite$default(it, null, 2, null)) {
            builder.timeout(Duration.ofMillis(it));
         }
      }
   }

   UtilsKt.mergeHeaders(
      `$this$convertToHttpRequest`.getHeaders(), `$this$convertToHttpRequest`.getBody(), JavaHttpRequestKt::convertToHttpRequest$lambda$4$lambda$3
   );
   builder.method(`$this$convertToHttpRequest`.getMethod().getValue(), convertToHttpRequestBody(`$this$convertToHttpRequest`.getBody(), callContext));
   val var11: HttpRequest = builder.build();
   return var11;
}

internal fun OutgoingContent.convertToHttpRequestBody(callContext: CoroutineContext): BodyPublisher {
   val var10000: BodyPublisher;
   if (`$this$convertToHttpRequestBody` is OutgoingContent.ByteArrayContent) {
      var10000 = BodyPublishers.ofByteArray((`$this$convertToHttpRequestBody` as OutgoingContent.ByteArrayContent).bytes());
   } else if (`$this$convertToHttpRequestBody` is OutgoingContent.ReadChannelContent) {
      val var10003: java.lang.Long = `$this$convertToHttpRequestBody`.getContentLength();
      var10000 = new JavaHttpRequestBodyPublisher(callContext, var10003 ?: -1L, JavaHttpRequestKt::convertToHttpRequestBody$lambda$5);
   } else if (`$this$convertToHttpRequestBody` is OutgoingContent.WriteChannelContent) {
      val var3: java.lang.Long = `$this$convertToHttpRequestBody`.getContentLength();
      var10000 = new JavaHttpRequestBodyPublisher(callContext, var3 ?: -1L, JavaHttpRequestKt::convertToHttpRequestBody$lambda$6);
   } else if (`$this$convertToHttpRequestBody` is OutgoingContent.NoContent) {
      var10000 = BodyPublishers.noBody();
   } else {
      if (`$this$convertToHttpRequestBody` !is OutgoingContent.ContentWrapper) {
         if (`$this$convertToHttpRequestBody` is OutgoingContent.ProtocolUpgrade) {
            throw new UnsupportedContentTypeException(`$this$convertToHttpRequestBody`);
         }

         throw new NoWhenBranchMatchedException();
      }

      var10000 = convertToHttpRequestBody((`$this$convertToHttpRequestBody` as OutgoingContent.ContentWrapper).delegate(), callContext);
   }

   return var10000;
}

fun `convertToHttpRequest$lambda$4$lambda$3`(`$this_with`: Builder, key: java.lang.String, value: java.lang.String): Unit {
   if (!DISALLOWED_HEADERS.contains(key)) {
      `$this_with`.header(key, value);
   }

   return Unit.INSTANCE;
}

fun `convertToHttpRequestBody$lambda$5`(`$this_convertToHttpRequestBody`: OutgoingContent): ByteReadChannel {
   return (`$this_convertToHttpRequestBody` as OutgoingContent.ReadChannelContent).readFrom();
}

fun `convertToHttpRequestBody$lambda$6`(`$callContext`: CoroutineContext, `$this_convertToHttpRequestBody`: OutgoingContent): ByteReadChannel {
   return ByteWriteChannelOperationsKt.writer$default(GlobalScope.INSTANCE, `$callContext`, false, new 1(`$this_convertToHttpRequestBody`, null), 2, null)
      .getChannel();
}
