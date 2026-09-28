package io.ktor.utils.io.pool

import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

public class ByteBufferPool(capacity: Int = 2000, bufferSize: Int = 4096) : DefaultPool(capacity) {
   public final val bufferSize: Int

   init {
      this.bufferSize = bufferSize;
   }

   protected open fun produceInstance(): ByteBuffer {
      val var10000: ByteBuffer = ByteBuffer.allocate(this.bufferSize);
      return var10000;
   }

   protected open fun clearInstance(instance: ByteBuffer): ByteBuffer {
      ((Buffer)instance).clear();
      instance.order(ByteOrder.BIG_ENDIAN);
      return instance;
   }

   protected open fun validateInstance(instance: ByteBuffer) {
      if (instance.capacity() != this.bufferSize) {
         throw new IllegalStateException("Check failed.");
      } else if (instance.isDirect()) {
         throw new IllegalStateException("Check failed.");
      }
   }

   fun ByteBufferPool() {
      this(0, 0, 3, null);
   }
}
