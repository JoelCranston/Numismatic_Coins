package io.ktor.util

import kotlin.coroutines.jvm.internal.Boxing

public object GenerateOnlyNonceManager : NonceManager {
   public override suspend fun newNonce(): String {
      return CryptoKt.generateNonce();
   }

   public override suspend fun verifyNonce(nonce: String): Boolean {
      return Boxing.boxBoolean(true);
   }
}
