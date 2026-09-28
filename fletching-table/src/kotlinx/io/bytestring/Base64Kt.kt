package kotlinx.io.bytestring

import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

@ExperimentalEncodingApi
public fun Base64.encodeToByteArray(source: ByteString, startIndex: Int = 0, endIndex: Int = source.getSize()): ByteArray {
   return `$this$encodeToByteArray`.encodeToByteArray(source.getBackingArrayReference(), startIndex, endIndex);
}

@JvmSynthetic
fun `encodeToByteArray$default`(var0: Base64, var1: ByteString, var2: Int, var3: Int, var4: Int, var5: Any): ByteArray {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.getSize();
   }

   return encodeToByteArray(var0, var1, var2, var3);
}

@ExperimentalEncodingApi
public fun Base64.encodeIntoByteArray(
   source: ByteString,
   destination: ByteArray,
   destinationOffset: Int = 0,
   startIndex: Int = 0,
   endIndex: Int = source.getSize()
): Int {
   return `$this$encodeIntoByteArray`.encodeIntoByteArray(source.getBackingArrayReference(), destination, destinationOffset, startIndex, endIndex);
}

@JvmSynthetic
fun `encodeIntoByteArray$default`(var0: Base64, var1: ByteString, var2: ByteArray, var3: Int, var4: Int, var5: Int, var6: Int, var7: Any): Int {
   if ((var6 and 4) != 0) {
      var3 = 0;
   }

   if ((var6 and 8) != 0) {
      var4 = 0;
   }

   if ((var6 and 16) != 0) {
      var5 = var1.getSize();
   }

   return encodeIntoByteArray(var0, var1, var2, var3, var4, var5);
}

@ExperimentalEncodingApi
public fun Base64.encode(source: ByteString, startIndex: Int = 0, endIndex: Int = source.getSize()): String {
   return `$this$encode`.encode(source.getBackingArrayReference(), startIndex, endIndex);
}

@JvmSynthetic
fun `encode$default`(var0: Base64, var1: ByteString, var2: Int, var3: Int, var4: Int, var5: Any): java.lang.String {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.getSize();
   }

   return encode(var0, var1, var2, var3);
}

@ExperimentalEncodingApi
public fun <A : Appendable> Base64.encodeToAppendable(source: ByteString, destination: A, startIndex: Int = ..., endIndex: Int = ...): A {
   return (A)`$this$encodeToAppendable`.encodeToAppendable(source.getBackingArrayReference(), destination, startIndex, endIndex);
}

@JvmSynthetic
fun `encodeToAppendable$default`(var0: Base64, var1: ByteString, var2: Appendable, var3: Int, var4: Int, var5: Int, var6: Any): Appendable {
   if ((var5 and 4) != 0) {
      var3 = 0;
   }

   if ((var5 and 8) != 0) {
      var4 = var1.getSize();
   }

   return encodeToAppendable(var0, var1, var2, var3, var4);
}

@ExperimentalEncodingApi
public fun Base64.decode(source: ByteString, startIndex: Int = 0, endIndex: Int = source.getSize()): ByteArray {
   return `$this$decode`.decode(source.getBackingArrayReference(), startIndex, endIndex);
}

@JvmSynthetic
fun `decode$default`(var0: Base64, var1: ByteString, var2: Int, var3: Int, var4: Int, var5: Any): ByteArray {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.getSize();
   }

   return decode(var0, var1, var2, var3);
}

@ExperimentalEncodingApi
public fun Base64.decodeToByteString(source: CharSequence, startIndex: Int = 0, endIndex: Int = source.length()): ByteString {
   return ByteString.Companion.wrap$kotlinx_io_bytestring(`$this$decodeToByteString`.decode(source, startIndex, endIndex));
}

@JvmSynthetic
fun `decodeToByteString$default`(var0: Base64, var1: java.lang.CharSequence, var2: Int, var3: Int, var4: Int, var5: Any): ByteString {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length();
   }

   return decodeToByteString(var0, var1, var2, var3);
}

@ExperimentalEncodingApi
public fun Base64.decodeIntoByteArray(
   source: ByteString,
   destination: ByteArray,
   destinationOffset: Int = 0,
   startIndex: Int = 0,
   endIndex: Int = source.getSize()
): Int {
   return `$this$decodeIntoByteArray`.decodeIntoByteArray(source.getBackingArrayReference(), destination, destinationOffset, startIndex, endIndex);
}

@JvmSynthetic
fun `decodeIntoByteArray$default`(var0: Base64, var1: ByteString, var2: ByteArray, var3: Int, var4: Int, var5: Int, var6: Int, var7: Any): Int {
   if ((var6 and 4) != 0) {
      var3 = 0;
   }

   if ((var6 and 8) != 0) {
      var4 = 0;
   }

   if ((var6 and 16) != 0) {
      var5 = var1.getSize();
   }

   return decodeIntoByteArray(var0, var1, var2, var3, var4, var5);
}

@ExperimentalEncodingApi
public fun Base64.decodeToByteString(source: ByteArray, startIndex: Int = 0, endIndex: Int = source.length): ByteString {
   return ByteString.Companion.wrap$kotlinx_io_bytestring(`$this$decodeToByteString`.decode(source, startIndex, endIndex));
}

@JvmSynthetic
fun `decodeToByteString$default`(var0: Base64, var1: ByteArray, var2: Int, var3: Int, var4: Int, var5: Any): ByteString {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.length;
   }

   return decodeToByteString(var0, var1, var2, var3);
}

@ExperimentalEncodingApi
public fun Base64.decodeToByteString(source: ByteString, startIndex: Int = 0, endIndex: Int = source.getSize()): ByteString {
   return ByteString.Companion.wrap$kotlinx_io_bytestring(`$this$decodeToByteString`.decode(source.getBackingArrayReference(), startIndex, endIndex));
}

@JvmSynthetic
fun `decodeToByteString$default`(var0: Base64, var1: ByteString, var2: Int, var3: Int, var4: Int, var5: Any): ByteString {
   if ((var4 and 2) != 0) {
      var2 = 0;
   }

   if ((var4 and 4) != 0) {
      var3 = var1.getSize();
   }

   return decodeToByteString(var0, var1, var2, var3);
}
