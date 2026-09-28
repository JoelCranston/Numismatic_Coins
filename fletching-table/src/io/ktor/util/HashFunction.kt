package io.ktor.util

internal interface HashFunction {
   public abstract fun update(input: ByteArray, offset: Int = 0, length: Int = var1.length) {
   }

   public abstract fun digest(): ByteArray {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
