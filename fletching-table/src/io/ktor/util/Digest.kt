package io.ktor.util

public interface Digest {
   public abstract operator fun plusAssign(bytes: ByteArray) {
   }

   public abstract fun reset() {
   }

   public abstract suspend fun build(): ByteArray {
   }
}
