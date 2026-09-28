package kotlinx.serialization.internal

import java.util.Arrays
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
@PublishedApi
@ExperimentalUnsignedTypes
internal class UByteArrayBuilder internal constructor(bufferWithData: UByteArray) : UByteArrayBuilder(bufferWithData) {
   private final var buffer: UByteArray

   internal open var position: Int
      private set

   fun UByteArrayBuilder(bufferWithData: ByteArray) {
      this.buffer = bufferWithData;
      this.position = UByteArray.getSize-impl(bufferWithData);
      this.ensureCapacity$kotlinx_serialization_core(10);
   }

   internal override fun ensureCapacity(requiredCapacity: Int) {
      if (UByteArray.getSize-impl(this.buffer) < requiredCapacity) {
         val var10001: ByteArray = Arrays.copyOf(this.buffer, RangesKt.coerceAtLeast(requiredCapacity, UByteArray.getSize-impl(this.buffer) * 2));
         this.buffer = UByteArray.constructor-impl(var10001);
      }
   }

   internal fun append(c: UByte) {
      PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, null);
      val var10000: ByteArray = this.buffer;
      val var2: Int = this.getPosition$kotlinx_serialization_core();
      this.position = var2 + 1;
      UByteArray.set-VurrAj0(var10000, var2, c);
   }

   internal open fun build(): UByteArray {
      val var10000: ByteArray = Arrays.copyOf(this.buffer, this.getPosition$kotlinx_serialization_core());
      return UByteArray.constructor-impl(var10000);
   }
}
