package io.ktor.util

import java.security.MessageDigest
import kotlin.coroutines.Continuation

@JvmInline
private inline class DigestImpl : Digest {
   public final val delegate: MessageDigest

   @JvmStatic
   public open operator fun plusAssign(bytes: ByteArray) {
      var0.update(bytes);
   }

   override fun plusAssign(bytes: ByteArray) {
      plusAssign-impl(this.delegate, bytes);
   }

   @JvmStatic
   public open fun reset() {
      var0.reset();
   }

   override fun reset() {
      reset-impl(this.delegate);
   }

   @JvmStatic
   public open suspend fun build(): ByteArray {
      val var10000: ByteArray = var0.digest();
      return var10000;
   }

   override fun build(`$completion`: Continuation<ByteArray>): Any? {
      return build-impl(this.delegate, `$completion`);
   }

   @JvmStatic
   public open fun toString(): String {
      return "DigestImpl(delegate=$var0)";
   }

   override fun toString(): java.lang.String {
      return toString-impl(this.delegate);
   }

   @JvmStatic
   public open fun hashCode(): Int {
      return var0.hashCode();
   }

   override fun hashCode(): Int {
      return hashCode-impl(this.delegate);
   }

   @JvmStatic
   public open operator fun equals(other: Any?): Boolean {
      if (other !is DigestImpl) {
         return false;
      } else {
         return var0 == (other as DigestImpl).unbox-impl();
      }
   }

   override fun equals(other: Any): Boolean {
      return equals-impl(this.delegate, other);
   }

   @JvmStatic
   fun `constructor-impl`(delegate: MessageDigest): MessageDigest {
      return delegate;
   }

   @JvmStatic
   fun `equals-impl0`(p1: MessageDigest, p2: MessageDigest): Boolean {
      return p1 == p2;
   }
}
