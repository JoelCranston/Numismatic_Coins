package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.HttpStatusCode
import io.ktor.http.content.WriterContent.writeTo.2
import io.ktor.utils.io.ByteWriteChannel
import java.io.Writer
import java.nio.charset.Charset
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt

public class WriterContent(body: (Writer, Continuation<Unit>) -> Any?, contentType: ContentType, status: HttpStatusCode? = null, contentLength: Long? = null)
   : OutgoingContent.WriteChannelContent {
   private final val body: (Writer, Continuation<Unit>) -> Any?
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
      var var10000: Charset = ContentTypesKt.charset(this.getContentType());
      if (var10000 == null) {
         var10000 = Charsets.UTF_8;
      }

      var10000 = (Charset)BlockingBridgeKt.withBlocking(new 2(channel, var10000, this, null), `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   }
}
