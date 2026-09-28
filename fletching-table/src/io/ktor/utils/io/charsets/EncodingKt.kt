@file:SourceDebugExtension(["SMAP\nEncoding.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Encoding.kt\nio/ktor/utils/io/charsets/EncodingKt\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,126:1\n21#2,3:127\n*S KotlinDebug\n*F\n+ 1 Encoding.kt\nio/ktor/utils/io/charsets/EncodingKt\n*L\n54#1:127,3\n*E\n"])

package io.ktor.utils.io.charsets

import io.ktor.utils.io.core.internal.CharArraySequence
import java.nio.charset.CharsetDecoder
import java.nio.charset.CharsetEncoder
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source

public fun CharsetEncoder.encode(input: CharSequence, fromIndex: Int = 0, toIndex: Int = input.length()): Source {
   val `builder$iv`: Buffer = new Buffer();
   encodeToImpl(`$this$encode`, `builder$iv`, input, fromIndex, toIndex);
   return `builder$iv`;
}

@JvmSynthetic
fun `encode$default`(var0: CharsetEncoder, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Int, var5: Any): Source {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   return encode(var0, var1, var2, var3);
}

public fun CharsetEncoder.encode(input: CharArray, fromIndex: Int, toIndex: Int, dst: Sink) {
   encodeArrayImpl(`$this$encode`, input, fromIndex, toIndex, dst);
}

public fun CharsetDecoder.decode(input: Source, max: Int = Integer.MAX_VALUE): String {
   val var4: StringBuilder = new StringBuilder((int)Math.min((long)max, input.getBuffer().getSize()));
   CharsetJVMKt.decode(`$this$decode`, input, var4, max);
   return var4.toString();
}

@JvmSynthetic
fun `decode$default`(var0: CharsetDecoder, var1: Source, var2: Int, var3: Int, var4: Any): java.lang.String {
   if ((var3 and 2) != 0) {
      var2 = Integer.MAX_VALUE;
   }

   return decode(var0, var1, var2);
}

internal fun CharsetEncoder.encodeArrayImpl(input: CharArray, fromIndex: Int, toIndex: Int, dst: Sink): Int {
   return CharsetJVMKt.encodeImpl(`$this$encodeArrayImpl`, new CharArraySequence(input, fromIndex, toIndex - fromIndex), 0, toIndex - fromIndex, dst);
}

internal fun CharsetEncoder.encodeToImpl(destination: Sink, input: CharSequence, fromIndex: Int, toIndex: Int) {
   var start: Int = fromIndex;
   if (fromIndex < toIndex) {
      do {
         val rc: Int = CharsetJVMKt.encodeImpl(`$this$encodeToImpl`, input, start, toIndex, destination);
         if (rc < 0) {
            throw new IllegalStateException("Check failed.");
         }

         start += rc;
      } while (start < toIndex);
   }
}
