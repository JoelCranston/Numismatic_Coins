package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode

public class ByteArrayContent(bytes: ByteArray, contentType: ContentType? = null, status: HttpStatusCode? = null) : OutgoingContent.ByteArrayContent {
   private final val bytes: ByteArray
   public open val contentType: ContentType?
   public open val status: HttpStatusCode?

   public open val contentLength: Long
      public open get() {
         return (long)this.bytes.length;
      }


   init {
      this.bytes = bytes;
      this.contentType = contentType;
      this.status = status;
   }

   public override fun bytes(): ByteArray {
      return this.bytes;
   }
}
