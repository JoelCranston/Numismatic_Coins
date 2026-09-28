package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt

public class ChannelWriterContent(body: (ByteWriteChannel, Continuation<Unit>) -> Any?,
      contentType: ContentType?,
      status: HttpStatusCode? = null,
      contentLength: Long? = null
   )
   : OutgoingContent.WriteChannelContent {
   private final val body: (ByteWriteChannel, Continuation<Unit>) -> Any?
   public open val contentType: ContentType?
   public open val status: HttpStatusCode?
   public open val contentLength: Long?

   init {
      this.body = body;
      this.contentType = contentType;
      this.status = status;
      this.contentLength = contentLength;
   }

   public override suspend fun writeTo(channel: ByteWriteChannel) {
      val var10000: Any = this.body.invoke(channel, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
