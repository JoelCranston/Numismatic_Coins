package io.ktor.util.cio

import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.jvm.javaio.ReadingKt
import io.ktor.utils.io.pool.ObjectPool
import java.io.InputStream
import java.nio.ByteBuffer
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.JobKt

@Deprecated(message = "Use variant from 'ktor-io' module instead", replaceWith = @ReplaceWith(expression = "this.toByteReadChannel(context + parent, pool)", imports = ["io.ktor.utils.io.jvm.javaio.toByteReadChannel"]))
public fun InputStream.toByteReadChannel(
   pool: ObjectPool<ByteBuffer> = ByteBufferPoolKt.getKtorDefaultPool(),
   context: CoroutineContext = Dispatchers.getUnconfined() as CoroutineContext,
   parent: Job = JobKt.Job$default(null, 1, null) as Job
): ByteReadChannel {
   return ReadingKt.toByteReadChannel(`$this$toByteReadChannel`, context.plus(parent), pool);
}

/** @deprecated */
@JvmSynthetic
fun `toByteReadChannel$default`(var0: InputStream, var1: ObjectPool, var2: CoroutineContext, var3: Job, var4: Int, var5: Any): ByteReadChannel {
   if ((var4 and 1) != 0) {
      var1 = ByteBufferPoolKt.getKtorDefaultPool();
   }

   if ((var4 and 2) != 0) {
      var2 = Dispatchers.getUnconfined();
   }

   if ((var4 and 4) != 0) {
      var3 = JobKt.Job$default(null, 1, null);
   }

   return toByteReadChannel(var0, var1, var2, var3);
}
