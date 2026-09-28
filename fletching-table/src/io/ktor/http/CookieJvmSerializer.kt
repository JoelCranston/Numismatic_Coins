package io.ktor.http

import io.ktor.utils.io.JvmSerializer

internal object CookieJvmSerializer : JvmSerializer<Cookie> {
   public open fun jvmSerialize(value: Cookie): ByteArray {
      return StringsKt.encodeToByteArray(CookieKt.renderSetCookieHeader(value));
   }

   public open fun jvmDeserialize(value: ByteArray): Cookie {
      return CookieKt.parseServerSetCookieHeader(StringsKt.decodeToString(value));
   }
}
