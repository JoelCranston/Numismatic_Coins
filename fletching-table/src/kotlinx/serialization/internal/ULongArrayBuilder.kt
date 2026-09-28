package kotlinx.serialization.internal

import java.util.Arrays
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal class ULongArrayBuilder internal constructor(bufferWithData: ULongArray) : ULongArrayBuilder(bufferWithData) {
   private final var buffer: ULongArray

   internal open var position: Int
      private set

   fun ULongArrayBuilder(bufferWithData: LongArray) {
      this.buffer = bufferWithData;
      this.position = ULongArray.getSize-impl(bufferWithData);
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (ULongArray.getSize-impl(this.buffer) < requiredCapacity) {
         val var10001: LongArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, ULongArray.getSize-impl(this.buffer) * 2));
         this.buffer = ULongArray.constructor-impl(var10001);
      }
   }

   internal fun append(c: ULong) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: LongArray = this.buffer;
      val var3: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var3 + 1;
      ULongArray.set-k8EXiF4(var10000, var3, c);
   }

   internal open fun build(): ULongArray {
      val var10000: LongArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return ULongArray.constructor-impl(var10000);
   }
}
