package kotlinx.serialization.internal

import java.util.Arrays
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal class UShortArrayBuilder internal constructor(bufferWithData: UShortArray) : UShortArrayBuilder(bufferWithData) {
   private final var buffer: UShortArray

   internal open var position: Int
      private set

   fun UShortArrayBuilder(bufferWithData: ShortArray) {
      this.buffer = bufferWithData;
      this.position = UShortArray.getSize-impl(bufferWithData);
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (UShortArray.getSize-impl(this.buffer) < requiredCapacity) {
         val var10001: ShortArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, UShortArray.getSize-impl(this.buffer) * 2));
         this.buffer = UShortArray.constructor-impl(var10001);
      }
   }

   internal fun append(c: UShort) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: ShortArray = this.buffer;
      val var2: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var2 + 1;
      UShortArray.set-01HTLdE(var10000, var2, c);
   }

   internal open fun build(): UShortArray {
      val var10000: ShortArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return UShortArray.constructor-impl(var10000);
   }
}
