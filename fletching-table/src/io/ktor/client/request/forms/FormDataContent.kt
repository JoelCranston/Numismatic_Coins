package io.ktor.client.request.forms

import io.ktor.http.ContentType
import io.ktor.http.ContentTypesKt
import io.ktor.http.HttpUrlEncodedKt
import io.ktor.http.Parameters
import io.ktor.http.content.OutgoingContent
import io.ktor.utils.io.core.StringsKt

public class FormDataContent(formData: Parameters) : OutgoingContent.ByteArrayContent {
   public final val formData: Parameters
   private final val content: ByteArray
   public open val contentLength: Long
   public open val contentType: ContentType

   init {
      this.formData = formData;
      this.content = StringsKt.toByteArray$default(HttpUrlEncodedKt.formUrlEncode(this.formData), null, 1, null);
      this.contentLength = this.content.length;
      this.contentType = ContentTypesKt.withCharset(ContentType.Application.INSTANCE.getFormUrlEncoded(), Charsets.UTF_8);
   }

   public override fun bytes(): ByteArray {
      return this.content;
   }
}
