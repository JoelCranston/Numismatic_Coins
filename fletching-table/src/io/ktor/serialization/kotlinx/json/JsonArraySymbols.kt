package io.ktor.serialization.kotlinx.json

import io.ktor.utils.io.core.StringsKt
import java.nio.charset.Charset

private class JsonArraySymbols(charset: Charset) {
   public final val beginArray: ByteArray
   public final val endArray: ByteArray
   public final val objectSeparator: ByteArray

   init {
      this.beginArray = StringsKt.toByteArray("[", charset);
      this.endArray = StringsKt.toByteArray("]", charset);
      this.objectSeparator = StringsKt.toByteArray(",", charset);
   }
}
