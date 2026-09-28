package io.ktor.util

import io.ktor.utils.io.pool.ObjectPool
import java.nio.Buffer
import java.nio.ByteBuffer
import java.nio.charset.Charset

public fun ByteBuffer.moveTo(destination: ByteBuffer, limit: Int = Integer.MAX_VALUE): Int {
   val size: Int = Math.min(limit, Math.min(`$this$moveTo`.remaining(), destination.remaining()));
   if (size == `$this$moveTo`.remaining()) {
      val var10000: Buffer = destination.put(`$this$moveTo`);
   } else {
      val l: Int = `$this$moveTo`.limit();
      ((Buffer)`$this$moveTo`).limit(`$this$moveTo`.position() + size);
      destination.put(`$this$moveTo`);
      ((Buffer)`$this$moveTo`).limit(l);
   }

   return size;
}

@JvmSynthetic
fun `moveTo$default`(var0: ByteBuffer, var1: ByteBuffer, var2: Int, var3: Int, var4: Any): Int {
   if ((var3 and 2) != 0) {
      var2 = Integer.MAX_VALUE;
   }

   return moveTo(var0, var1, var2);
}

public fun ByteBuffer.moveToByteArray(): ByteArray {
   val array: ByteArray = new byte[`$this$moveToByteArray`.remaining()];
   `$this$moveToByteArray`.get(array);
   return array;
}

public fun ByteBuffer.decodeString(charset: Charset = Charsets.UTF_8): String {
   val var10000: java.lang.String = charset.decode(`$this$decodeString`).toString();
   return var10000;
}

@JvmSynthetic
fun `decodeString$default`(var0: ByteBuffer, var1: Charset, var2: Int, var3: Any): java.lang.String {
   if ((var2 and 1) != 0) {
      var1 = Charsets.UTF_8;
   }

   return decodeString(var0, var1);
}

public fun ByteBuffer.copy(size: Int = `$this$copy`.remaining()): ByteBuffer {
   val var2: ByteBuffer = ByteBuffer.allocate(size);
   val var10000: ByteBuffer = `$this$copy`.slice();
   moveTo$default(var10000, var2, 0, 2, null);
   ((Buffer)var2).clear();
   return var2;
}

@JvmSynthetic
fun `copy$default`(var0: ByteBuffer, var1: Int, var2: Int, var3: Any): ByteBuffer {
   if ((var2 and 1) != 0) {
      var1 = var0.remaining();
   }

   return copy(var0, var1);
}

public fun ByteBuffer.copy(pool: ObjectPool<ByteBuffer>, size: Int = `$this$copy`.remaining()): ByteBuffer {
   val var3: Any = pool.borrow();
   val `$this$copy_u24lambda_u241`: ByteBuffer = var3 as ByteBuffer;
   (var3 as ByteBuffer).limit(size);
   val var10000: ByteBuffer = `$this$copy`.slice();
   moveTo$default(var10000, `$this$copy_u24lambda_u241`, 0, 2, null);
   ((Buffer)`$this$copy_u24lambda_u241`).flip();
   return var3 as ByteBuffer;
}

@JvmSynthetic
fun `copy$default`(var0: ByteBuffer, var1: ObjectPool, var2: Int, var3: Int, var4: Any): ByteBuffer {
   if ((var3 and 2) != 0) {
      var2 = var0.remaining();
   }

   return copy(var0, var1, var2);
}
