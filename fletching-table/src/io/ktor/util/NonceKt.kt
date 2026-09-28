@file:SourceDebugExtension(["SMAP\nNonce.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Nonce.kt\nio/ktor/util/NonceKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,120:1\n1#2:121\n*E\n"])

package io.ktor.util

import io.ktor.util.NonceKt.nonceGeneratorJob.1
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ChannelKt
import org.slf4j.LoggerFactory

private const val SHA1PRNG: String = "SHA1PRNG"
private final val SECURE_RANDOM_PROVIDERS: List<String> =
   kotlin.collections.CollectionsKt.listOf(new java.lang.String[]{"NativePRNGNonBlocking", "WINDOWS-PRNG", "DRBG"})
   private const val SECURE_RESEED_PERIOD: Int = 30000
private const val SECURE_NONCE_COUNT: Int = 8
private const val INSECURE_NONCE_COUNT_FACTOR: Int = 4
internal final val seedChannel: Channel<String> = ChannelKt.Channel$default(1024, null, null, 6, null)
private final val NonceGeneratorCoroutineName: CoroutineName = new CoroutineName("nonce-generator")
private final val nonceGeneratorJob: Job =
   BuildersKt.launch(
      GlobalScope.INSTANCE, Dispatchers.getDefault().plus(NonCancellable.INSTANCE).plus(NonceGeneratorCoroutineName), CoroutineStart.LAZY, new 1(null)
   )

internal fun ensureNonceGeneratorRunning() {
   nonceGeneratorJob.start();
}

private fun lookupSecureRandom(): SecureRandom {
   val var10000: java.lang.String = System.getProperty("io.ktor.random.secure.random.provider");
   if (var10000 != null) {
      val var8: SecureRandom = getInstanceOrNull(var10000);
      if (var8 != null) {
         return var8;
      }
   }

   for (java.lang.String name : SECURE_RANDOM_PROVIDERS) {
      val var6: SecureRandom = getInstanceOrNull(name);
      if (var6 != null) {
         return var6;
      }
   }

   LoggerFactory.getLogger("io.ktor.util.random")
      .warn(
         "None of the ${kotlin.collections.CollectionsKt.joinToString$default(SECURE_RANDOM_PROVIDERS, ", ", null, null, 0, null, null, 62, null)} found, fallback to default"
      );
   val var9: SecureRandom = getInstanceOrNull$default(null, 1, null);
   if (var9 == null) {
      throw new IllegalStateException("No SecureRandom implementation found".toString());
   } else {
      return var9;
   }
}

private fun getInstanceOrNull(name: String? = null): SecureRandom? {
   var var1: SecureRandom;
   try {
      var1 = if (name != null) SecureRandom.getInstance(name) else new SecureRandom();
   } catch (var3: NoSuchAlgorithmException) {
      var1 = null;
   }

   return var1;
}

@JvmSynthetic
fun `getInstanceOrNull$default`(var0: java.lang.String, var1: Int, var2: Any): SecureRandom {
   if ((var1 and 1) != 0) {
      var0 = null;
   }

   return getInstanceOrNull(var0);
}

@JvmSynthetic
fun `access$lookupSecureRandom`(): SecureRandom {
   return lookupSecureRandom();
}
