package io.ktor.util

import io.ktor.util.CryptoKt__CryptoJvmKt.generateNonceBlocking.1
import java.security.MessageDigest
import kotlin.jvm.functions.Function1
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.channels.ChannelResult

@JvmSynthetic
internal class CryptoKt__CryptoJvmKt {
   @JvmStatic
   public fun getDigestFunction(algorithm: String, salt: (String) -> String): (String) -> ByteArray {
      return CryptoKt__CryptoJvmKt::getDigestFunction$lambda$0$CryptoKt__CryptoJvmKt;
   }

   @JvmStatic
   private fun getDigest(text: String, algorithm: String, salt: (String) -> String): ByteArray {
      val `$this$getDigest_u24lambda_u240`: MessageDigest = MessageDigest.getInstance(algorithm);
      var var10001: ByteArray = (salt.invoke(text) as java.lang.String).getBytes(Charsets.UTF_8);
      `$this$getDigest_u24lambda_u240`.update(var10001);
      var10001 = text.getBytes(Charsets.UTF_8);
      val var10000: ByteArray = `$this$getDigest_u24lambda_u240`.digest(var10001);
      return var10000;
   }

   @JvmStatic
   public fun sha1(bytes: ByteArray): ByteArray {
      val var10000: ByteArray = MessageDigest.getInstance("SHA1").digest(bytes);
      return var10000;
   }

   @JvmStatic
   public fun Digest(name: String): Digest {
      val var10000: MessageDigest = MessageDigest.getInstance(name);
      return DigestImpl.box-impl(DigestImpl.constructor-impl(var10000));
   }

   @JvmStatic
   public fun generateNonce(): String {
      val nonce: java.lang.String = ChannelResult.getOrNull-impl(NonceKt.getSeedChannel().tryReceive-PtdJZtk()) as java.lang.String;
      return nonce ?: generateNonceBlocking$CryptoKt__CryptoJvmKt();
   }

   @JvmStatic
   private fun generateNonceBlocking(): String {
      NonceKt.ensureNonceGeneratorRunning();
      return BuildersKt.runBlocking$default(null, new 1(null), 1, null) as java.lang.String;
   }

   @JvmStatic
   fun `getDigestFunction$lambda$0$CryptoKt__CryptoJvmKt`(`$algorithm`: java.lang.String, `$salt`: Function1, e: java.lang.String): ByteArray {
      return getDigest$CryptoKt__CryptoJvmKt(e, `$algorithm`, `$salt`);
   }
}
