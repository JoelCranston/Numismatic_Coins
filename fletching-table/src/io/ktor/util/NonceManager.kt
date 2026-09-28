package io.ktor.util

public interface NonceManager {
   public abstract suspend fun newNonce(): String {
   }

   public abstract suspend fun verifyNonce(nonce: String): Boolean {
   }
}
