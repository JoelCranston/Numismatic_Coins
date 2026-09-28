package io.ktor.http

public interface ContentTypeMatcher {
   public abstract fun contains(contentType: ContentType): Boolean {
   }
}
