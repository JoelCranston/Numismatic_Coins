package io.ktor.util

import java.util.concurrent.TimeUnit
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.coroutines.jvm.internal.Boxing

public class StatelessHmacNonceManager(keySpec: SecretKeySpec,
      algorithm: String = "HmacSHA256",
      timeoutMillis: Long = 60000L,
      nonceGenerator: () -> String = StatelessHmacNonceManager::_init_$lambda$0
   ) :
   NonceManager {
   public final val keySpec: SecretKeySpec
   public final val algorithm: String
   public final val timeoutMillis: Long
   public final val nonceGenerator: () -> String
   private final val macLength: Int

   init {
      this.keySpec = keySpec;
      this.algorithm = algorithm;
      this.timeoutMillis = timeoutMillis;
      this.nonceGenerator = nonceGenerator;
      val mac: Mac = Mac.getInstance(this.algorithm);
      mac.init(this.keySpec);
      this.macLength = mac.getMacLength();
   }

   public constructor(key: ByteArray,
      algorithm: String = "HmacSHA256",
      timeoutMillis: Long = 60000L,
      nonceGenerator: () -> String = StatelessHmacNonceManager::_init_$lambda$1
   ) : this(new SecretKeySpec(key, algorithm), algorithm, timeoutMillis, nonceGenerator)
   public override suspend fun newNonce(): String {
      val random: java.lang.String = this.nonceGenerator.invoke();
      val var10000: java.lang.String = java.lang.Long.toString(System.nanoTime(), CharsKt.checkRadix(16));
      val time: java.lang.String = StringsKt.padStart(var10000, 16, '0');
      val var5: Mac = Mac.getInstance(this.algorithm);
      var5.init(this.keySpec);
      val var10001: ByteArray = ("$random:$time").getBytes(Charsets.ISO_8859_1);
      var5.update(var10001);
      val var8: ByteArray = var5.doFinal();
      return "$random+$time+${CryptoKt.hex(var8)}";
   }

   public override suspend fun verifyNonce(nonce: String): Boolean {
      val parts: java.util.List = StringsKt.split$default(nonce, new char[]{'+'}, false, 0, 6, null);
      if (parts.size() != 3) {
         return Boxing.boxBoolean(false);
      } else {
         val var14: java.lang.String = parts.get(0) as java.lang.String;
         val time: java.lang.String = parts.get(1) as java.lang.String;
         val mac: java.lang.String = parts.get(2) as java.lang.String;
         if (var14.length() < 8) {
            return Boxing.boxBoolean(false);
         } else if (mac.length() != this.macLength * 2) {
            return Boxing.boxBoolean(false);
         } else if (time.length() != 16) {
            return Boxing.boxBoolean(false);
         } else if (java.lang.Long.parseLong(time, CharsKt.checkRadix(16)) + TimeUnit.MILLISECONDS.toNanos(this.timeoutMillis) < System.nanoTime()) {
            return Boxing.boxBoolean(false);
         } else {
            val i: Mac = Mac.getInstance(this.algorithm);
            i.init(this.keySpec);
            val var10001: ByteArray = ("$var14:$time").getBytes(Charsets.ISO_8859_1);
            i.update(var10001);
            val var16: ByteArray = i.doFinal();
            val computedMac: java.lang.String = CryptoKt.hex(var16);
            var validCount: Int = 0;
            var var15: Int = 0;

            for (int $this$verifyNonce_u24lambda_u240 = Math.min(computedMac.length(), mac.length()); i < $this$verifyNonce_u24lambda_u240; i++) {
               if (computedMac.charAt(var15) == mac.charAt(var15)) {
                  validCount++;
               }
            }

            return Boxing.boxBoolean(validCount == this.macLength * 2);
         }
      }
   }

   @JvmStatic
   fun `_init_$lambda$0`(): java.lang.String {
      return CryptoKt.generateNonce();
   }

   @JvmStatic
   fun `_init_$lambda$1`(): java.lang.String {
      return CryptoKt.generateNonce();
   }
}
