package io.ktor.http

import java.util.Arrays
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nHttpHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpHeaders.kt\nio/ktor/http/HttpHeaders\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 _Strings.kt\nkotlin/text/StringsKt___StringsKt\n*L\n1#1,222:1\n12970#2,2:223\n1188#3,3:225\n1188#3,3:228\n*S KotlinDebug\n*F\n+ 1 HttpHeaders.kt\nio/ktor/http/HttpHeaders\n*L\n134#1:223,2\n159#1:225,3\n172#1:228,3\n*E\n"])
public object HttpHeaders {
   public final val Accept: String = "Accept"
   public final val AcceptCharset: String = "Accept-Charset"
   public final val AcceptEncoding: String = "Accept-Encoding"
   public final val AcceptLanguage: String = "Accept-Language"
   public final val AcceptRanges: String = "Accept-Ranges"
   public final val Age: String = "Age"
   public final val Allow: String = "Allow"
   public final val ALPN: String = "ALPN"
   public final val AuthenticationInfo: String = "Authentication-Info"
   public final val Authorization: String = "Authorization"
   public final val CacheControl: String = "Cache-Control"
   public final val Connection: String = "Connection"
   public final val ContentDisposition: String = "Content-Disposition"
   public final val ContentEncoding: String = "Content-Encoding"
   public final val ContentLanguage: String = "Content-Language"
   public final val ContentLength: String = "Content-Length"
   public final val ContentLocation: String = "Content-Location"
   public final val ContentRange: String = "Content-Range"
   public final val ContentType: String = "Content-Type"
   public final val Cookie: String = "Cookie"
   public final val DASL: String = "DASL"
   public final val Date: String = "Date"
   public final val DAV: String = "DAV"
   public final val Depth: String = "Depth"
   public final val Destination: String = "Destination"
   public final val ETag: String = "ETag"
   public final val Expect: String = "Expect"
   public final val Expires: String = "Expires"
   public final val From: String = "From"
   public final val Forwarded: String = "Forwarded"
   public final val Host: String = "Host"
   public final val HTTP2Settings: String = "HTTP2-Settings"
   public final val If: String = "If"
   public final val IfMatch: String = "If-Match"
   public final val IfModifiedSince: String = "If-Modified-Since"
   public final val IfNoneMatch: String = "If-None-Match"
   public final val IfRange: String = "If-Range"
   public final val IfScheduleTagMatch: String = "If-Schedule-Tag-Match"
   public final val IfUnmodifiedSince: String = "If-Unmodified-Since"
   public final val LastModified: String = "Last-Modified"
   public final val Location: String = "Location"
   public final val LockToken: String = "Lock-Token"
   public final val Link: String = "Link"
   public final val MaxForwards: String = "Max-Forwards"
   public final val MIMEVersion: String = "MIME-Version"
   public final val OrderingType: String = "Ordering-Type"
   public final val Origin: String = "Origin"
   public final val Overwrite: String = "Overwrite"
   public final val Position: String = "Position"
   public final val Pragma: String = "Pragma"
   public final val Prefer: String = "Prefer"
   public final val PreferenceApplied: String = "Preference-Applied"
   public final val ProxyAuthenticate: String = "Proxy-Authenticate"
   public final val ProxyAuthenticationInfo: String = "Proxy-Authentication-Info"
   public final val ProxyAuthorization: String = "Proxy-Authorization"
   public final val PublicKeyPins: String = "Public-Key-Pins"
   public final val PublicKeyPinsReportOnly: String = "Public-Key-Pins-Report-Only"
   public final val Range: String = "Range"
   public final val Referrer: String = "Referer"
   public final val RetryAfter: String = "Retry-After"
   public final val ScheduleReply: String = "Schedule-Reply"
   public final val ScheduleTag: String = "Schedule-Tag"
   public final val SecWebSocketAccept: String = "Sec-WebSocket-Accept"
   public final val SecWebSocketExtensions: String = "Sec-WebSocket-Extensions"
   public final val SecWebSocketKey: String = "Sec-WebSocket-Key"
   public final val SecWebSocketProtocol: String = "Sec-WebSocket-Protocol"
   public final val SecWebSocketVersion: String = "Sec-WebSocket-Version"
   public final val Server: String = "Server"
   public final val SetCookie: String = "Set-Cookie"
   public final val SLUG: String = "SLUG"
   public final val StrictTransportSecurity: String = "Strict-Transport-Security"
   public final val TE: String = "TE"
   public final val Timeout: String = "Timeout"
   public final val Trailer: String = "Trailer"
   public final val TransferEncoding: String = "Transfer-Encoding"
   public final val Upgrade: String = "Upgrade"
   public final val UserAgent: String = "User-Agent"
   public final val Vary: String = "Vary"
   public final val Via: String = "Via"
   public final val Warning: String = "Warning"
   public final val WWWAuthenticate: String = "WWW-Authenticate"
   public final val AccessControlAllowOrigin: String = "Access-Control-Allow-Origin"
   public final val AccessControlAllowMethods: String = "Access-Control-Allow-Methods"
   public final val AccessControlAllowCredentials: String = "Access-Control-Allow-Credentials"
   public final val AccessControlAllowHeaders: String = "Access-Control-Allow-Headers"
   public final val AccessControlRequestMethod: String = "Access-Control-Request-Method"
   public final val AccessControlRequestHeaders: String = "Access-Control-Request-Headers"
   public final val AccessControlExposeHeaders: String = "Access-Control-Expose-Headers"
   public final val AccessControlMaxAge: String = "Access-Control-Max-Age"
   public final val XHttpMethodOverride: String = "X-Http-Method-Override"
   public final val XForwardedHost: String = "X-Forwarded-Host"
   public final val XForwardedServer: String = "X-Forwarded-Server"
   public final val XForwardedProto: String = "X-Forwarded-Proto"
   public final val XForwardedFor: String = "X-Forwarded-For"
   public final val XForwardedPort: String = "X-Forwarded-Port"
   public final val XRequestId: String = "X-Request-ID"
   public final val XCorrelationId: String = "X-Correlation-ID"
   public final val XTotalCount: String = "X-Total-Count"
   public final val LastEventID: String = "Last-Event-ID"
   private final val UnsafeHeadersArray: Array<String>

   @Deprecated(
      message = "Use UnsafeHeadersList instead.",
      replaceWith = @ReplaceWith(
         expression = "HttpHeaders.UnsafeHeadersList",
         imports = {}
      ),
      level = DeprecationLevel.ERROR
   )
   public final val UnsafeHeaders: Array<String>
      public final get() {
         val var10000: Array<Any> = Arrays.copyOf(UnsafeHeadersArray, UnsafeHeadersArray.length);
         return var10000 as Array<java.lang.String>;
      }


   public final val UnsafeHeadersList: List<String> = ArraysKt.asList(UnsafeHeadersArray)

   public fun isUnsafe(header: String): Boolean {
      val `$this$any$iv`: Array<Any> = UnsafeHeadersArray;
      var var4: Int = 0;
      val var5: Int = UnsafeHeadersArray.length;

      var var10000: Boolean;
      while (true) {
         if (var4 >= var5) {
            var10000 = false;
            break;
         }

         if (StringsKt.equals((java.lang.String)`$this$any$iv`[var4], header, true)) {
            var10000 = true;
            break;
         }

         var4++;
      }

      return var10000;
   }

   public fun checkHeaderName(name: String) {
      val `$this$forEachIndexed$iv`: java.lang.CharSequence = name;
      var `index$iv`: Int = 0;

      for (int var5 = 0; var5 < $this$forEachIndexed$iv.length(); var5++) {
         val `item$iv`: Char = `$this$forEachIndexed$iv`.charAt(var5);
         val index: Int = `index$iv`++;
         if (Intrinsics.compare(`item$iv`, 32) <= 0 || HttpHeadersKt.access$isDelimiter(`item$iv`)) {
            throw new IllegalHeaderNameException(name, index);
         }
      }
   }

   public fun checkHeaderValue(value: String) {
      val `$this$forEachIndexed$iv`: java.lang.CharSequence = value;
      var `index$iv`: Int = 0;

      for (int var5 = 0; var5 < $this$forEachIndexed$iv.length(); var5++) {
         val `item$iv`: Char = `$this$forEachIndexed$iv`.charAt(var5);
         val index: Int = `index$iv`++;
         if (Intrinsics.compare(`item$iv`, 32) < 0 && `item$iv` != '\t') {
            throw new IllegalHeaderValueException(value, index);
         }
      }
   }
}
