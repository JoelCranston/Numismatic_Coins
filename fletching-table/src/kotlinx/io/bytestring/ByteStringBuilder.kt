package kotlinx.io.bytestring

import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nByteStringBuilder.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteStringBuilder.kt\nkotlinx/io/bytestring/ByteStringBuilder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,129:1\n1#2:130\n*E\n"])
public class ByteStringBuilder(initialCapacity: Int = 0) {
   private final var buffer: ByteArray
   private final var offset: Int

   public final val size: Int
      public final get() {
         return this.offset;
      }


   public final val capacity: Int
      public final get() {
         return this.buffer.length;
      }


   init {
      this.buffer = new byte[initialCapacity];
   }

   public fun toByteString(): ByteString {
      if (this.getSize() == 0) {
         return ByteStringKt.ByteString();
      } else {
         return if (this.buffer.length == this.getSize())
            ByteString.Companion.wrap$kotlinx_io_bytestring(this.buffer)
            else
            new ByteString(this.buffer, 0, this.getSize());
      }
   }

   public fun append(byte: Byte) {
      this.ensureCapacity(this.getSize() + 1);
      this.buffer[this.offset++] = var1;
   }

   public fun append(array: ByteArray, startIndex: Int = 0, endIndex: Int = array.length) {
      if (startIndex > endIndex) {
         throw new IllegalArgumentException(("startIndex ($startIndex) > endIndex ($endIndex)").toString());
      } else if (startIndex >= 0 && endIndex <= array.length) {
         this.ensureCapacity(this.offset + endIndex - startIndex);
         ArraysKt.copyInto(array, this.buffer, this.offset, startIndex, endIndex);
         this.offset += endIndex - startIndex;
      } else {
         throw new IndexOutOfBoundsException(
            "startIndex ($startIndex) and endIndex ($endIndex) represents an interval out of array's bounds [0..${array.length})."
         );
      }
   }

   private fun ensureCapacity(requiredCapacity: Int) {
      if (this.buffer.length < requiredCapacity) {
         val newBuffer: ByteArray = new byte[Math.max(if (this.buffer.length == 0) 16 else (int)((double)this.buffer.length * 1.5), requiredCapacity)];
         ArraysKt.copyInto$default(this.buffer, newBuffer, 0, 0, 0, 14, null);
         this.buffer = newBuffer;
      }
   }

   fun ByteStringBuilder() {
      this(0, 1, null);
   }
}
