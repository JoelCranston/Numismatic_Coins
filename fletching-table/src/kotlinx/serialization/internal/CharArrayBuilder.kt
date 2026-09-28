package kotlinx.serialization.internal

import java.util.Arrays

@PublishedApi
internal class CharArrayBuilder internal constructor(bufferWithData: CharArray) : PrimitiveArrayBuilder<char[]> {
   private final var buffer: CharArray

   internal open var position: Int
      private set

   init {
      this.buffer = bufferWithData;
      this.position = bufferWithData.length;
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (this.buffer.length < requiredCapacity) {
         val var10001: CharArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, this.buffer.length * 2));
         this.buffer = var10001;
      }
   }

   internal fun append(c: Char) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: CharArray = this.buffer;
      val var2: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var2 + 1;
      var10000[var2] = c;
   }

   internal open fun build(): CharArray {
      val var10000: CharArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return var10000;
   }
}
