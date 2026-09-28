package io.ktor.client.plugins.contentnegotiation

import io.ktor.http.ContentType
import io.ktor.http.ContentTypeMatcher

public object JsonContentTypeMatcher : ContentTypeMatcher {
   public override fun contains(contentType: ContentType): Boolean {
      if (contentType.match(ContentType.Application.INSTANCE.getJson())) {
         return true;
      } else {
         val value: java.lang.String = contentType.withoutParameters().toString();
         return ContentType.Application.INSTANCE.contains(value) && StringsKt.endsWith(value, "+json", true);
      }
   }
}
