package kotlinx.serialization.internal

import java.util.Arrays

@PublishedApi
internal class ByteArrayBuilder internal constructor(bufferWithData: ByteArray) : PrimitiveArrayBuilder<byte[]> {
   private final var buffer: ByteArray

   internal open var position: Int
      private set

   init {
      this.buffer = bufferWithData;
      this.position = bufferWithData.length;
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (this.buffer.length < requiredCapacity) {
         val var10001: ByteArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, this.buffer.length * 2));
         this.buffer = var10001;
      }
   }

   internal fun append(c: Byte) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: ByteArray = this.buffer;
      val var2: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var2 + 1;
      var10000[var2] = c;
   }

   internal open fun build(): ByteArray {
      val var10000: ByteArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return var10000;
   }
}
