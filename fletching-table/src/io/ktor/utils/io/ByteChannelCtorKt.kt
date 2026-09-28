package io.ktor.utils.io

import io.ktor.utils.io.core.StringsKt
import java.nio.charset.Charset
import kotlinx.io.Buffer
import kotlinx.io.Source

public fun ByteReadChannel(content: ByteArray, offset: Int = 0, length: Int = content.length): ByteReadChannel {
   val var4: Buffer = new Buffer();
   var4.write(content, offset, offset + length);
   return ByteReadChannel(var4);
}

@JvmSynthetic
fun `ByteReadChannel$default`(var0: ByteArray, var1: Int, var2: Int, var3: Int, var4: Any): ByteReadChannel {
   if ((var3 and 2) != 0) {
      var1 = 0;
   }

   if ((var3 and 4) != 0) {
      var2 = var0.length;
   }

   return ByteReadChannel(var0, var1, var2);
}

public fun ByteReadChannel(text: String, charset: Charset = Charsets.UTF_8): ByteReadChannel {
   return ByteReadChannel$default(StringsKt.toByteArray(text, charset), 0, 0, 6, null);
}

@JvmSynthetic
fun `ByteReadChannel$default`(var0: java.lang.String, var1: Charset, var2: Int, var3: Any): ByteReadChannel {
   if ((var2 and 2) != 0) {
      var1 = Charsets.UTF_8;
   }

   return ByteReadChannel(var0, var1);
}

public fun ByteReadChannel(source: Source): ByteReadChannel {
   return new SourceByteReadChannel(source);
}
