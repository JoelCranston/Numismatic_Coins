package kotlinx.serialization.internal

import java.util.Arrays
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal class UIntArrayBuilder internal constructor(bufferWithData: UIntArray) : UIntArrayBuilder(bufferWithData) {
   private final var buffer: UIntArray

   internal open var position: Int
      private set

   fun UIntArrayBuilder(bufferWithData: IntArray) {
      this.buffer = bufferWithData;
      this.position = UIntArray.getSize-impl(bufferWithData);
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (UIntArray.getSize-impl(this.buffer) < requiredCapacity) {
         val var10001: IntArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, UIntArray.getSize-impl(this.buffer) * 2));
         this.buffer = UIntArray.constructor-impl(var10001);
      }
   }

   internal fun append(c: UInt) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: IntArray = this.buffer;
      val var2: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var2 + 1;
      UIntArray.set-VXSXFK8(var10000, var2, c);
   }

   internal open fun build(): UIntArray {
      val var10000: IntArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return UIntArray.constructor-impl(var10000);
   }
}
