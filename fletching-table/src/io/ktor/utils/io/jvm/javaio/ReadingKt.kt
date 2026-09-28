package io.ktor.utils.io.jvm.javaio

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.pool.ByteArrayPoolKt
import io.ktor.utils.io.pool.ObjectPool
import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.io.JvmCoreKt

public fun InputStream.toByteReadChannel(context: CoroutineContext = Dispatchers.getIO() as CoroutineContext, pool: ObjectPool<ByteBuffer>): ByteReadChannel {
   return new RawSourceChannel(JvmCoreKt.asSource(`$this$toByteReadChannel`), context);
}

@JvmSynthetic
fun `toByteReadChannel$default`(var0: InputStream, var1: CoroutineContext, var2: ObjectPool, var3: Int, var4: Any): ByteReadChannel {
   if ((var3 and 1) != 0) {
      var1 = Dispatchers.getIO();
   }

   return toByteReadChannel(var0, var1, var2);
}

@JvmName(name = "toByteReadChannelWithArrayPool")
public fun InputStream.toByteReadChannel(context: CoroutineContext = Dispatchers.getIO() as CoroutineContext, pool: ObjectPool<ByteArray> = ...): ByteReadChannel {
   return new RawSourceChannel(JvmCoreKt.asSource(`$this$toByteReadChannel`), context);
}

@JvmSynthetic
fun `toByteReadChannelWithArrayPool$default`(var0: InputStream, var1: CoroutineContext, var2: ObjectPool, var3: Int, var4: Any): ByteReadChannel {
   if ((var3 and 1) != 0) {
      var1 = Dispatchers.getIO();
   }

   if ((var3 and 2) != 0) {
      var2 = ByteArrayPoolKt.getByteArrayPool();
   }

   return toByteReadChannelWithArrayPool(var0, var1, var2);
}
