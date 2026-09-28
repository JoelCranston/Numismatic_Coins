package io.ktor.client.content

import io.ktor.http.ContentType
import io.ktor.http.FileContentTypeJvmKt
import io.ktor.http.content.OutgoingContent
import io.ktor.util.cio.FileChannelsKt
import io.ktor.utils.io.ByteReadChannel
import java.io.File

public class LocalFileContent(file: File, contentType: ContentType = FileContentTypeJvmKt.defaultForFile(ContentType.Companion, file))
   : OutgoingContent.ReadChannelContent {
   public final val file: File
   public open val contentType: ContentType

   public open val contentLength: Long
      public open get() {
         return this.file.length();
      }


   init {
      this.file = file;
      this.contentType = contentType;
   }

   public override fun readFrom(): ByteReadChannel {
      return FileChannelsKt.readChannel$default(this.file, 0L, 0L, null, 7, null);
   }

   public override fun readFrom(range: LongRange): ByteReadChannel {
      return FileChannelsKt.readChannel$default(this.file, range.getFirst(), range.getLast(), null, 4, null);
   }
}
