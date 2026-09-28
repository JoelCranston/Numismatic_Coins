package io.ktor.client.plugins.cookies

import io.ktor.http.Cookie
import io.ktor.http.Url
import java.io.Closeable

public interface CookiesStorage : Closeable {
   public abstract suspend fun get(requestUrl: Url): List<Cookie> {
   }

   public abstract suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
   }
}
