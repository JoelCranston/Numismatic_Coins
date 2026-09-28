package io.ktor.util

import io.ktor.util.ByteChannelsKt.split.1
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import kotlinx.coroutines.BuildersKt
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope

private const val CHUNK_BUFFER_SIZE: Long = 4096L

public fun ByteReadChannel.split(coroutineScope: CoroutineScope): Pair<ByteReadChannel, ByteReadChannel> {
   val first: ByteChannel = new ByteChannel(true);
   val second: ByteChannel = new ByteChannel(true);
   BuildersKt.launch$default(coroutineScope, null, null, new 1(`$this$split`, first, second, null), 3, null).invokeOnCompletion(ByteChannelsKt::split$lambda$0);
   return TuplesKt.to(first, second);
}

public fun ByteReadChannel.copyToBoth(first: ByteWriteChannel, second: ByteWriteChannel) {
   BuildersKt.launch$default(
         GlobalScope.INSTANCE, Dispatchers.getDefault(), null, new io.ktor.util.ByteChannelsKt.copyToBoth.1(`$this$copyToBoth`, first, second, null), 2, null
      )
      .invokeOnCompletion(ByteChannelsKt::copyToBoth$lambda$0);
}

fun `split$lambda$0`(`$first`: ByteChannel, `$second`: ByteChannel, it: java.lang.Throwable): Unit {
   if (it == null) {
      return Unit.INSTANCE;
   } else {
      `$first`.cancel(it);
      `$second`.cancel(it);
      return Unit.INSTANCE;
   }
}

fun `copyToBoth$lambda$0`(`$first`: ByteWriteChannel, `$second`: ByteWriteChannel, it: java.lang.Throwable): Unit {
   if (it == null) {
      return Unit.INSTANCE;
   } else {
      ByteWriteChannelOperationsKt.close(`$first`, it);
      ByteWriteChannelOperationsKt.close(`$second`, it);
      return Unit.INSTANCE;
   }
}
