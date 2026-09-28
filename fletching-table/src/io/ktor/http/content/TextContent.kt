package io.ktor.http.content

import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.HttpStatusCode
import io.ktor.utils.io.core.StringsKt
import java.nio.charset.Charset

public class TextContent(text: String, contentType: ContentType, status: HttpStatusCode? = null) : OutgoingContent.ByteArrayContent {
   public final val text: String
   public open val contentType: ContentType
   public open val status: HttpStatusCode?
   private final val bytes: ByteArray

   public open val contentLength: Long
      public open get() {
         return (long)this.bytes.length;
      }


   init {
      this.text = text;
      this.contentType = contentType;
      this.status = status;
      val var10001: java.lang.String = this.text;
      var var10002: Charset = ContentTypesKt.charset(this.getContentType());
      if (var10002 == null) {
         var10002 = Charsets.UTF_8;
      }

      this.bytes = StringsKt.toByteArray(var10001, var10002);
   }

   public override fun bytes(): ByteArray {
      return this.bytes;
   }

   public override fun toString(): String {
      return "TextContent[${this.getContentType()}] \"${kotlin.text.StringsKt.take(this.text, 30)}"";
   }
}
