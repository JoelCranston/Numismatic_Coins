package io.ktor.http

import io.ktor.utils.io.JvmSerializer

internal object UrlJvmSerializer : JvmSerializer<Url> {
   public open fun jvmSerialize(value: Url): ByteArray {
      return StringsKt.encodeToByteArray(value.toString());
   }

   public open fun jvmDeserialize(value: ByteArray): Url {
      return URLUtilsKt.Url(StringsKt.decodeToString(value));
   }
}
