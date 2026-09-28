package io.ktor.util

import io.ktor.utils.io.InternalAPI
import java.nio.charset.Charset
import kotlin.coroutines.Continuation

// $VF: Class flags could not be determined
internal class CryptoKt {
   @JvmStatic
   public int NONCE_SIZE_IN_BYTES = 16;

   @JvmStatic
   fun getDigestFunction(algorithm: java.lang.String, salt: (java.lang.String?) -> java.lang.String): (java.lang.String?) -> ByteArray {
      return CryptoKt__CryptoJvmKt.getDigestFunction(algorithm, salt);
   }

   @JvmStatic
   fun sha1(bytes: ByteArray): ByteArray {
      return CryptoKt__CryptoJvmKt.sha1(bytes);
   }

   @JvmStatic
   fun Digest(name: java.lang.String): Digest {
      return CryptoKt__CryptoJvmKt.Digest(name);
   }

   @JvmStatic
   fun generateNonce(): java.lang.String {
      return CryptoKt__CryptoJvmKt.generateNonce();
   }

   @JvmStatic
   fun hex(bytes: ByteArray): java.lang.String {
      return CryptoKt__CryptoKt.hex(bytes);
   }

   @JvmStatic
   fun hex(s: java.lang.String): ByteArray {
      return CryptoKt__CryptoKt.hex(s);
   }

   @JvmStatic
   fun generateNonce(size: Int): ByteArray {
      return CryptoKt__CryptoKt.generateNonce(size);
   }

   @InternalAPI
   @JvmStatic
   fun Digest.build(bytes: ByteArray, `$completion`: Continuation<ByteArray>): Any? {
      return CryptoKt__CryptoKt.build(`$this$build`, bytes, `$completion`);
   }

   @InternalAPI
   @JvmStatic
   fun Digest.build(string: java.lang.String, charset: Charset, `$completion`: Continuation<ByteArray>): Any? {
      return CryptoKt__CryptoKt.build(`$this$build`, string, charset, `$completion`);
   }
}
