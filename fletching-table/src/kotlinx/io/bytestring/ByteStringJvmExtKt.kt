@file:SourceDebugExtension(["SMAP\nByteStringJvmExt.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteStringJvmExt.kt\nkotlinx/io/bytestring/ByteStringJvmExtKt\n+ 2 UnsafeByteStringOperations.kt\nkotlinx/io/bytestring/unsafe/UnsafeByteStringOperations\n*L\n1#1,137:1\n42#2,2:138\n42#2,2:140\n*S KotlinDebug\n*F\n+ 1 ByteStringJvmExt.kt\nkotlinx/io/bytestring/ByteStringJvmExtKt\n*L\n37#1:138,2\n101#1:140,2\n*E\n"])

package kotlinx.io.bytestring

import java.nio.ByteBuffer
import java.nio.charset.Charset
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.io.bytestring.unsafe.UnsafeByteStringOperations

public fun ByteString.decodeToString(charset: Charset): String {
   return new java.lang.String(`$this$decodeToString`.getBackingArrayReference(), charset);
}

public fun String.encodeToByteString(charset: Charset): ByteString {
   val var10000: ByteString.Companion = ByteString.Companion;
   val var10001: ByteArray = `$this$encodeToByteString`.getBytes(charset);
   return var10000.wrap$kotlinx_io_bytestring(var10001);
}

public fun ByteString.asReadOnlyByteBuffer(): ByteBuffer {
   val `this_$iv`: UnsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
   val var10000: ByteBuffer = ByteBuffer.wrap(`$this$asReadOnlyByteBuffer`.getBackingArrayReference()).asReadOnlyBuffer();
   return var10000;
}

public fun ByteBuffer.getByteString(length: Int = `$this$getByteString`.remaining()): ByteString {
   if (length < 0) {
      throw new IndexOutOfBoundsException("length should be non-negative (was $length)");
   } else if (`$this$getByteString`.remaining() < length) {
      throw new IndexOutOfBoundsException("length ($length) exceeds remaining bytes count ({${`$this$getByteString`.remaining()}})");
   } else {
      val bytes: ByteArray = new byte[length];
      `$this$getByteString`.get(bytes);
      return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bytes);
   }
}

@JvmSynthetic
fun `getByteString$default`(var0: ByteBuffer, var1: Int, var2: Int, var3: Any): ByteString {
   if ((var2 and 1) != 0) {
      var1 = var0.remaining();
   }

   return getByteString(var0, var1);
}

public fun ByteBuffer.getByteString(at: Int, length: Int): ByteString {
   checkIndexAndCapacity(`$this$getByteString`, at, length);
   val bytes: ByteArray = new byte[length];

   for (int i = 0; i < length; i++) {
      bytes[i] = `$this$getByteString`.get(at + i);
   }

   return UnsafeByteStringOperations.INSTANCE.wrapUnsafe(bytes);
}

public fun ByteBuffer.putByteString(string: ByteString) {
   val `this_$iv`: UnsafeByteStringOperations = UnsafeByteStringOperations.INSTANCE;
   `$this$putByteString`.put(string.getBackingArrayReference());
}

public fun ByteBuffer.putByteString(at: Int, string: ByteString) {
   checkIndexAndCapacity(`$this$putByteString`, at, string.getSize());
   val var3: IntRange = ByteStringKt.getIndices(string);
   var idx: Int = var3.getFirst();
   val var5: Int = var3.getLast();
   if (idx <= var5) {
      while (true) {
         `$this$putByteString`.put(at + idx, string.get(idx));
         if (idx == var5) {
            break;
         }

         idx++;
      }
   }
}

private fun ByteBuffer.checkIndexAndCapacity(idx: Int, length: Int) {
   if (idx < 0 || idx >= `$this$checkIndexAndCapacity`.limit()) {
      throw new IndexOutOfBoundsException("Index $idx is out of this ByteBuffer's bounds: [0, ${`$this$checkIndexAndCapacity`.limit()})");
   } else if (length < 0) {
      throw new IndexOutOfBoundsException("length should be non-negative (was $length)");
   } else if (idx + length > `$this$checkIndexAndCapacity`.limit()) {
      throw new IndexOutOfBoundsException("There's not enough space to put ByteString of length $length starting from index $idx");
   }
}
