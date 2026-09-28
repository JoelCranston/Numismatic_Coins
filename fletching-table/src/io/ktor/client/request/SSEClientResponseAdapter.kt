package io.ktor.client.request

import io.ktor.client.plugins.sse.DefaultClientSSESession
import io.ktor.client.plugins.sse.SSEClientContent
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext
import kotlin.jvm.internal.SourceDebugExtension

@InternalAPI
@SourceDebugExtension(["SMAP\nHttpRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpRequest.kt\nio/ktor/client/request/SSEClientResponseAdapter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,440:1\n1#2:441\n*E\n"])
public class SSEClientResponseAdapter : ResponseAdapter {
   public override fun adapt(
      data: HttpRequestData,
      status: HttpStatusCode,
      headers: Headers,
      responseBody: ByteReadChannel,
      outgoingContent: OutgoingContent,
      callContext: CoroutineContext
   ): Any? {
      val var10000: java.lang.String = headers.get(HttpHeaders.INSTANCE.getContentType());
      val var10: ContentType = if (var10000 != null) ContentType.Companion.parse(var10000) else null;
      return if (!HttpRequestKt.isSseRequest(data)
            || HttpRequestKt.isSseReconnectionRequest(data)
            || (
                  !(status == HttpStatusCode.Companion.getOK())
                     || !((if (var10 != null) var10.withoutParameters() else null) == ContentType.Text.INSTANCE.getEventStream())
               )
               && !(status == HttpStatusCode.Companion.getNoContent()))
         null
         else
         new DefaultClientSSESession(outgoingContent as SSEClientContent, responseBody);
   }
}
