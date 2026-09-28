package io.ktor.util

import io.ktor.utils.io.InternalAPI
import io.ktor.utils.io.core.BytePacketBuilderKt
import java.nio.charset.Charset
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.SourcesKt

@SourceDebugExtension(["SMAP\nCrypto.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Crypto.kt\nio/ktor/util/CryptoKt__CryptoKt\n+ 2 Builder.kt\nio/ktor/utils/io/core/BuilderKt\n*L\n1#1,137:1\n21#2,3:138\n*S KotlinDebug\n*F\n+ 1 Crypto.kt\nio/ktor/util/CryptoKt__CryptoKt\n*L\n67#1:138,3\n*E\n"])
@JvmSynthetic
internal class CryptoKt__CryptoKt {
   private final val digits: CharArray = CharsetKt.toCharArray("0123456789abcdef")
   internal const val NONCE_SIZE_IN_BYTES: Int

   @JvmStatic
   public fun hex(bytes: ByteArray): String {
      val result: CharArray = new char[bytes.length * 2];
      var resultIndex: Int = 0;
      val digits: CharArray = digits;

      for (byte element : bytes) {
         result[resultIndex++] = digits[(element and 255) shr 4];
         result[resultIndex++] = digits[element and 255 and 15];
      }

      return StringsKt.concatToString(result);
   }

   @JvmStatic
   public fun hex(s: String): ByteArray {
      val result: ByteArray = new byte[s.length() / 2];
      var idx: Int = 0;

      for (int var3 = result.length; idx < var3; idx++) {
         result[idx] = (byte)(
            Integer.parseInt(java.lang.String.valueOf(s.charAt(idx * 2)), CharsKt.checkRadix(16)) shl 4 or Integer.parseInt(
               java.lang.String.valueOf(s.charAt(idx * 2 + 1)), CharsKt.checkRadix(16)
            )
         );
      }

      return result;
   }

   @JvmStatic
   public fun generateNonce(size: Int): ByteArray {
      val `builder$iv`: Buffer = new Buffer();
      val `$this$generateNonce_u24lambda_u240`: Sink = `builder$iv`;

      while (BytePacketBuilderKt.getSize($this$generateNonce_u24lambda_u240) < size) {
         io.ktor.utils.io.core.StringsKt.writeText$default(`$this$generateNonce_u24lambda_u240`, CryptoKt.generateNonce(), 0, 0, null, 14, null);
      }

      return SourcesKt.readByteArray(`builder$iv`, size);
   }

   @InternalAPI
   @JvmStatic
   public suspend fun Digest.build(bytes: ByteArray): ByteArray {
      `$this$build`.plusAssign(bytes);
      return `$this$build`.build(`$completion`);
   }

   @InternalAPI
   @JvmStatic
   public suspend fun Digest.build(string: String, charset: Charset = ...): ByteArray {
      `$this$build`.plusAssign(io.ktor.utils.io.core.StringsKt.toByteArray(string, charset));
      return `$this$build`.build(`$completion`);
   }
}
