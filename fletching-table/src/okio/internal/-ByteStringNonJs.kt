@file:JvmName(name = "-ByteStringNonJs")

@file:SourceDebugExtension(["SMAP\nByteStringNonJs.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteStringNonJs.kt\nokio/internal/-ByteStringNonJs\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,44:1\n1#2:45\n*E\n"])

package okio.internal

import kotlin.jvm.internal.SourceDebugExtension
import okio.ByteString

internal inline fun String.commonDecodeHex(): ByteString {
   if (`$this$commonDecodeHex`.length() % 2 != 0) {
      throw new IllegalArgumentException(("Unexpected hex string: $`$this$commonDecodeHex`").toString());
   } else {
      val result: ByteArray = new byte[`$this$commonDecodeHex`.length() / 2];
      var i: Int = 0;

      for (int var4 = result.length; i < var4; i++) {
         result[i] = (byte)(
            (access$decodeHexDigit(`$this$commonDecodeHex`.charAt(i * 2)) shl 4) + access$decodeHexDigit(`$this$commonDecodeHex`.charAt(i * 2 + 1))
         );
      }

      return new ByteString(result);
   }
}

private fun decodeHexDigit(c: Char): Int {
   val var10000: Int;
   if ('0' <= c && c < ':') {
      var10000 = c - '0';
   } else if ('a' <= c && c < 'g') {
      var10000 = c - 'a' + 10;
   } else {
      if ('A' > c || c >= 'G') {
         throw new IllegalArgumentException("Unexpected hex digit: $c");
      }

      var10000 = c - 'A' + 10;
   }

   return var10000;
}

@JvmSynthetic
fun `access$decodeHexDigit`(c: Char): Int {
   return decodeHexDigit(c);
}
