package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.OutputStreamContent.writeTo.2
import io.ktor.utils.io.ByteWriteChannel
import java.io.OutputStream
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt

public class OutputStreamContent(body: (OutputStream, Continuation<Unit>) -> Any?,
      contentType: ContentType,
      status: HttpStatusCode? = null,
      contentLength: Long? = null
   )
   : OutgoingContent.WriteChannelContent {
   private final val body: (OutputStream, Continuation<Unit>) -> Any?
   public open val contentType: ContentType
   public open val status: HttpStatusCode?
   public open val contentLength: Long?

   init {
      this.body = body;
      this.contentType = contentType;
      this.status = status;
      this.contentLength = contentLength;
   }

   public override suspend fun writeTo(channel: ByteWriteChannel) {
      val var10000: Any = BlockingBridgeKt.withBlocking(new 2(channel, this, null), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
