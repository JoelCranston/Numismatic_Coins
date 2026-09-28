package io.ktor.client.plugins.cookies

import io.ktor.http.Cookie
import io.ktor.http.IpParserKt
import io.ktor.http.URLProtocolKt
import io.ktor.http.URLUtilsKt
import io.ktor.http.Url
import io.ktor.util.TextKt
import kotlin.coroutines.intrinsics.IntrinsicsKt

public suspend fun CookiesStorage.addCookie(urlString: String, cookie: Cookie) {
   val var10000: Any = `$this$addCookie`.addCookie(URLUtilsKt.Url(urlString), cookie, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public fun Cookie.matches(requestUrl: Url): Boolean {
   var var10000: java.lang.String = `$this$matches`.getDomain();
   if (var10000 != null) {
      var10000 = TextKt.toLowerCasePreservingASCIIRules(var10000);
      if (var10000 != null) {
         var10000 = StringsKt.trimStart(var10000, new char[]{'.'});
         if (var10000 != null) {
            var requestPath: java.lang.String = `$this$matches`.getPath();
            var10000 = `$this$matches`.getPath();
            if (var10000 == null) {
               throw new IllegalStateException("Path field should have the default value".toString());
            }

            val path: java.lang.String = if (StringsKt.endsWith$default(var10000, '/', false, 2, null)) var10000 else "${`$this$matches`.getPath()}/";
            val host: java.lang.String = TextKt.toLowerCasePreservingASCIIRules(requestUrl.getHost());
            val pathInRequest: java.lang.String = requestUrl.getEncodedPath();
            requestPath = if (StringsKt.endsWith$default(pathInRequest, '/', false, 2, null)) pathInRequest else "$pathInRequest/";
            if (host == var10000 || !IpParserKt.hostIsIp(host) && StringsKt.endsWith$default(host, ".$var10000", false, 2, null)) {
               if (!(path == "/") && !(requestPath == path) && !StringsKt.startsWith$default(requestPath, path, false, 2, null)) {
                  return false;
               }

               return !`$this$matches`.getSecure() || URLProtocolKt.isSecure(requestUrl.getProtocol());
            }

            return false;
         }
      }
   }

   throw new IllegalStateException("Domain field should have the default value".toString());
}

public fun Cookie.fillDefaults(requestUrl: Url): Cookie {
   var result: Cookie = `$this$fillDefaults`;
   val var10000: java.lang.String = `$this$fillDefaults`.getPath();
   if (var10000 == null || !StringsKt.startsWith$default(var10000, "/", false, 2, null)) {
      result = Cookie.copy$default(`$this$fillDefaults`, null, null, null, null, null, null, requestUrl.getEncodedPath(), false, false, null, 959, null);
   }

   val var3: java.lang.CharSequence = result.getDomain();
   if (var3 == null || StringsKt.isBlank(var3)) {
      result = Cookie.copy$default(result, null, null, null, null, null, requestUrl.getHost(), null, false, false, null, 991, null);
   }

   return result;
}
