package io.ktor.http.websocket

import io.ktor.util.Base64Kt
import io.ktor.util.CryptoKt
import io.ktor.utils.io.core.StringsKt

private const val WEBSOCKET_SERVER_ACCEPT_TAIL: String = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11"

public fun websocketServerAccept(nonce: String): String {
   return Base64Kt.encodeBase64(
      CryptoKt.sha1(StringsKt.toByteArray("${kotlin.text.StringsKt.trim(nonce).toString()}258EAFA5-E914-47DA-95CA-C5AB0DC85B11", Charsets.ISO_8859_1))
   );
}
