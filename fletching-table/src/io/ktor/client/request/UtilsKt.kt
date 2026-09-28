@file:SourceDebugExtension(["SMAP\nutils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 utils.kt\nio/ktor/client/request/UtilsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,111:1\n1#2:112\n*E\n"])

package io.ktor.client.request

import io.ktor.http.ContentType
import io.ktor.http.Cookie
import io.ktor.http.CookieKt
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMessageBuilder
import io.ktor.util.Base64Kt
import io.ktor.util.date.GMTDate
import kotlin.jvm.internal.SourceDebugExtension

public final var host: String
   public final get() {
      return `$this$host`.getUrl().getHost();
   }

   public final set(value) {
      `$this$host`.getUrl().setHost(value);
   }


public final var port: Int
   public final get() {
      return `$this$port`.getUrl().getPort();
   }

   public final set(value) {
      `$this$port`.getUrl().setPort(value);
   }


public fun HttpMessageBuilder.header(key: String, value: Any?) {
   if (value != null) {
      `$this$header`.getHeaders().append(key, value.toString());
   }
}

public fun HttpMessageBuilder.cookie(
   name: String,
   value: String,
   maxAge: Int = 0,
   expires: GMTDate? = null,
   domain: String? = null,
   path: String? = null,
   secure: Boolean = false,
   httpOnly: Boolean = false,
   extensions: Map<String, String?> = MapsKt.emptyMap()
) {
   val renderedCookie: java.lang.String = CookieKt.renderCookieHeader(
      new Cookie(name, value, null, maxAge, expires, domain, path, secure, httpOnly, extensions, 4, null)
   );
   if (!`$this$cookie`.getHeaders().contains(HttpHeaders.INSTANCE.getCookie())) {
      `$this$cookie`.getHeaders().append(HttpHeaders.INSTANCE.getCookie(), renderedCookie);
   } else {
      `$this$cookie`.getHeaders()
         .set(HttpHeaders.INSTANCE.getCookie(), "${`$this$cookie`.getHeaders().get(HttpHeaders.INSTANCE.getCookie())}; $renderedCookie");
   }
}

@JvmSynthetic
fun `cookie$default`(
   var0: HttpMessageBuilder,
   var1: java.lang.String,
   var2: java.lang.String,
   var3: Int,
   var4: GMTDate,
   var5: java.lang.String,
   var6: java.lang.String,
   var7: Boolean,
   var8: Boolean,
   var9: java.util.Map,
   var10: Int,
   var11: Any
) {
   if ((var10 and 4) != 0) {
      var3 = 0;
   }

   if ((var10 and 8) != 0) {
      var4 = null;
   }

   if ((var10 and 16) != 0) {
      var5 = null;
   }

   if ((var10 and 32) != 0) {
      var6 = null;
   }

   if ((var10 and 64) != 0) {
      var7 = false;
   }

   if ((var10 and 128) != 0) {
      var8 = false;
   }

   if ((var10 and 256) != 0) {
      var9 = MapsKt.emptyMap();
   }

   cookie(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9);
}

public fun HttpRequestBuilder.parameter(key: String, value: Any?) {
   if (value != null) {
      `$this$parameter`.getUrl().getParameters().append(key, value.toString());
   }
}

public fun HttpMessageBuilder.accept(contentType: ContentType) {
   `$this$accept`.getHeaders().append(HttpHeaders.INSTANCE.getAccept(), contentType.toString());
}

public fun HttpMessageBuilder.basicAuth(username: String, password: String) {
   header(`$this$basicAuth`, HttpHeaders.INSTANCE.getAuthorization(), "Basic ${Base64Kt.encodeBase64("$username:$password")}");
}

public fun HttpMessageBuilder.bearerAuth(token: String) {
   header(`$this$bearerAuth`, HttpHeaders.INSTANCE.getAuthorization(), "Bearer $token");
}
