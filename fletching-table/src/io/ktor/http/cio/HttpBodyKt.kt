@file:SourceDebugExtension(["SMAP\nHttpBody.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpBody.kt\nio/ktor/http/cio/HttpBodyKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,196:1\n1869#2,2:197\n*S KotlinDebug\n*F\n+ 1 HttpBody.kt\nio/ktor/http/cio/HttpBodyKt\n*L\n177#1:197,2\n*E\n"])

package io.ktor.http.cio

import io.ktor.http.HttpMethod
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.cio.internals.CharsKt
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteReadChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import java.util.Locale
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension

public fun expectHttpUpgrade(method: HttpMethod, upgrade: CharSequence?, connectionOptions: ConnectionOptions?): Boolean {
   return method == HttpMethod.Companion.getGet() && upgrade != null && connectionOptions != null && connectionOptions.getUpgrade();
}

public fun expectHttpUpgrade(request: Request): Boolean {
   return expectHttpUpgrade(request.getMethod(), request.getHeaders().get("Upgrade"), ConnectionOptions.Companion.parse(request.getHeaders().get("Connection")));
}

public fun expectHttpBody(
   method: HttpMethod,
   contentLength: Long,
   transferEncoding: CharSequence?,
   connectionOptions: ConnectionOptions?,
   contentType: CharSequence?
): Boolean {
   if (transferEncoding != null) {
      isTransferEncodingChunked(transferEncoding);
      return true;
   } else if (contentLength != -1L) {
      return contentLength > 0L;
   } else if (!(method == HttpMethod.Companion.getGet()) && !(method == HttpMethod.Companion.getHead()) && !(method == HttpMethod.Companion.getOptions())) {
      return connectionOptions != null && connectionOptions.getClose();
   } else {
      return false;
   }
}

public fun expectHttpBody(request: Request): Boolean {
   val var10001: java.lang.CharSequence = request.getHeaders().get("Content-Length");
   return expectHttpBody(
      request.getMethod(),
      if (var10001 != null) CharsKt.parseDecLong(var10001) else -1L,
      request.getHeaders().get("Transfer-Encoding"),
      ConnectionOptions.Companion.parse(request.getHeaders().get("Connection")),
      request.getHeaders().get("Content-Type")
   );
}

public suspend fun parseHttpBody(
   version: HttpProtocolVersion?,
   contentLength: Long,
   transferEncoding: CharSequence?,
   connectionOptions: ConnectionOptions?,
   input: ByteReadChannel,
   out: ByteWriteChannel
) {
   if (transferEncoding != null && isTransferEncodingChunked(transferEncoding)) {
      val var10: Any = ChunkedTransferEncodingKt.decodeChunked(input, out, `$completion`);
      return if (var10 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10 else Unit.INSTANCE;
   } else if (contentLength != -1L) {
      val var9: Any = ByteReadChannelOperationsKt.copyTo(input, out, contentLength, `$completion`);
      return if (var9 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var9 else Unit.INSTANCE;
   } else if (connectionOptions != null && connectionOptions.getClose() || connectionOptions == null && version == HttpProtocolVersion.Companion.getHTTP_1_0()) {
      val var10000: Any = ByteReadChannelOperationsKt.copyTo(input, out, java.lang.Long.MAX_VALUE, `$completion`);
      return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
   } else {
      ByteWriteChannelOperationsKt.close(
         out,
         new IllegalStateException(
            "Failed to parse request body: request body length should be specified,\nchunked transfer encoding should be used or\nkeep-alive should be disabled (connection: close)"
         )
      );
      return Unit.INSTANCE;
   }
}

@Deprecated(message = "Please use method with version parameter", level = DeprecationLevel.ERROR)
public suspend fun parseHttpBody(
   contentLength: Long,
   transferEncoding: CharSequence?,
   connectionOptions: ConnectionOptions?,
   input: ByteReadChannel,
   out: ByteWriteChannel
) {
   val var10000: Any = parseHttpBody(null, contentLength, transferEncoding, connectionOptions, input, out, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

public suspend fun parseHttpBody(headers: HttpHeadersMap, input: ByteReadChannel, out: ByteWriteChannel) {
   val var10001: java.lang.CharSequence = headers.get("Content-Length");
   val var10000: Any = parseHttpBody(
      null,
      if (var10001 != null) CharsKt.parseDecLong(var10001) else -1L,
      headers.get("Transfer-Encoding"),
      ConnectionOptions.Companion.parse(headers.get("Connection")),
      input,
      out,
      `$completion`
   );
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

private fun isTransferEncodingChunked(transferEncoding: CharSequence): Boolean {
   if (CharsKt.equalsLowerCase$default(transferEncoding, 0, 0, "chunked", 3, null)) {
      return true;
   } else if (CharsKt.equalsLowerCase$default(transferEncoding, 0, 0, "identity", 3, null)) {
      return false;
   } else {
      var chunked: Boolean = false;

      val var9: java.lang.Iterable;
      for (Object element$iv : var9) {
         val var10000: java.lang.String = StringsKt.trim(`element$iv` as java.lang.String).toString().toLowerCase(Locale.ROOT);
         if (var10000 == "chunked") {
            if (chunked) {
               throw new IllegalArgumentException("Double-chunked TE is not supported: $transferEncoding");
            }

            chunked = true;
         } else if (!(var10000 == "identity")) {
            throw new IllegalArgumentException("Unsupported transfer encoding $var10000");
         }
      }

      return chunked;
   }
}
