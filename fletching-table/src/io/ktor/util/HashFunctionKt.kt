package io.ktor.util

internal fun HashFunction.digest(input: ByteArray, offset: Int = 0, length: Int = input.length): ByteArray {
   `$this$digest`.update(input, offset, length);
   return `$this$digest`.digest();
}

@JvmSynthetic
fun `digest$default`(var0: HashFunction, var1: ByteArray, var2: Int, var3: Int, var4: Int, var5: Any): ByteArray {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length;
   }

   return digest(var0, var1, var2, var3);
}

private infix fun Int.leftRotate(bitCount: Int): Int {
   return `$this$leftRotate` shl bitCount or `$this$leftRotate` ushr 32 - bitCount;
}

@JvmSynthetic
fun `access$leftRotate`(`$receiver`: Int, bitCount: Int): Int {
   return leftRotate(`$receiver`, bitCount);
}
