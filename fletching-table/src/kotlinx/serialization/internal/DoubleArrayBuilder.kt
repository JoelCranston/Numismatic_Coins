package kotlinx.serialization.internal

import java.util.Arrays

@PublishedApi
internal class DoubleArrayBuilder internal constructor(bufferWithData: DoubleArray) : PrimitiveArrayBuilder<double[]> {
   private final var buffer: DoubleArray

   internal open var position: Int
      private set

   init {
      this.buffer = bufferWithData;
      this.position = bufferWithData.length;
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (this.buffer.length < requiredCapacity) {
         val var10001: DoubleArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, this.buffer.length * 2));
         this.buffer = var10001;
      }
   }

   internal fun append(c: Double) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: DoubleArray = this.buffer;
      val var3: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var3 + 1;
      var10000[var3] = c;
   }

   internal open fun build(): DoubleArray {
      val var10000: DoubleArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return var10000;
   }
}
