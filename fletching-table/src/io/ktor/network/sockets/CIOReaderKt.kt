package io.ktor.network.sockets

import io.ktor.network.selector.SelectInterest
import io.ktor.network.selector.Selectable
import io.ktor.network.selector.SelectorManager
import io.ktor.network.sockets.CIOReaderKt.attachForReadingImpl.1
import io.ktor.network.sockets.SocketOptions.TCPClientSocketOptions
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteWriteChannel
import io.ktor.utils.io.ByteWriteChannelOperationsKt
import io.ktor.utils.io.ByteWriteChannelOperations_jvmKt
import io.ktor.utils.io.WriterJob
import io.ktor.utils.io.pool.ObjectPool
import java.nio.ByteBuffer
import java.nio.channels.ReadableByteChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.Boxing
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Ref
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

internal fun CoroutineScope.attachForReadingImpl(
   channel: ByteChannel,
   nioChannel: ReadableByteChannel,
   selectable: Selectable,
   selector: SelectorManager,
   pool: ObjectPool<ByteBuffer>,
   socketOptions: TCPClientSocketOptions? = null
): WriterJob {
   return ByteWriteChannelOperationsKt.writer(
      `$this$attachForReadingImpl`,
      Dispatchers.getIO().plus(new CoroutineName("cio-from-nio-reader")),
      channel,
      new 1(socketOptions, channel, selectable, pool.borrow() as ByteBuffer, pool, nioChannel, selector, null)
   );
}

@JvmSynthetic
fun `attachForReadingImpl$default`(
   var0: CoroutineScope,
   var1: ByteChannel,
   var2: ReadableByteChannel,
   var3: Selectable,
   var4: SelectorManager,
   var5: ObjectPool,
   var6: SocketOptions.TCPClientSocketOptions,
   var7: Int,
   var8: Any
): WriterJob {
   if ((var7 and 32) != 0) {
      var6 = null;
   }

   return attachForReadingImpl(var0, var1, var2, var3, var4, var5, var6);
}

internal fun CoroutineScope.attachForReadingDirectImpl(
   channel: ByteChannel,
   nioChannel: ReadableByteChannel,
   selectable: Selectable,
   selector: SelectorManager,
   socketOptions: TCPClientSocketOptions? = null
): WriterJob {
   return ByteWriteChannelOperationsKt.writer(
      `$this$attachForReadingDirectImpl`,
      Dispatchers.getIO().plus(new CoroutineName("cio-from-nio-reader")),
      channel,
      new io.ktor.network.sockets.CIOReaderKt.attachForReadingDirectImpl.1(selectable, socketOptions, channel, nioChannel, selector, null)
   );
}

@JvmSynthetic
fun `attachForReadingDirectImpl$default`(
   var0: CoroutineScope,
   var1: ByteChannel,
   var2: ReadableByteChannel,
   var3: Selectable,
   var4: SelectorManager,
   var5: SocketOptions.TCPClientSocketOptions,
   var6: Int,
   var7: Any
): WriterJob {
   if ((var6 and 16) != 0) {
      var5 = null;
   }

   return attachForReadingDirectImpl(var0, var1, var2, var3, var4, var5);
}

private suspend fun ByteWriteChannel.readFrom(nioChannel: ReadableByteChannel): Int {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is io.ktor.network.sockets.CIOReaderKt.readFrom.1) {
         `$continuation` = `$completion` as io.ktor.network.sockets.CIOReaderKt.readFrom.1;
         if (((`$completion` as io.ktor.network.sockets.CIOReaderKt.readFrom.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new io.ktor.network.sockets.CIOReaderKt.readFrom.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var count: Ref.IntRef;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         count = new Ref.IntRef();
         val var10002: Function1 = CIOReaderKt::readFrom$lambda$0;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$readFrom`);
         `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(nioChannel);
         `$continuation`.L$2 = count;
         `$continuation`.label = 1;
         if (ByteWriteChannelOperations_jvmKt.write$default(`$this$readFrom`, 0, var10002, `$continuation`, 1, null) === var6) {
            return var6;
         }
         break;
      case 1:
         count = `$continuation`.L$2 as Ref.IntRef;
         nioChannel = `$continuation`.L$1 as ReadableByteChannel;
         `$this$readFrom` = `$continuation`.L$0 as ByteWriteChannel;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return Boxing.boxInt(count.element);
}

private suspend fun selectForRead(selectable: Selectable, selector: SelectorManager) {
   selectable.interestOp(SelectInterest.READ, true);
   val var10000: Any = selector.select(selectable, SelectInterest.READ, `$completion`);
   return if (var10000 === IntrinsicsKt.getCOROUTINE_SUSPENDED()) var10000 else Unit.INSTANCE;
}

fun `readFrom$lambda$0`(`$count`: Ref.IntRef, `$nioChannel`: ReadableByteChannel, buffer: ByteBuffer): Unit {
   `$count`.element = `$nioChannel`.read(buffer);
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$readFrom`(`$receiver`: ByteWriteChannel, nioChannel: ReadableByteChannel, `$completion`: Continuation): Any {
   return readFrom(`$receiver`, nioChannel, `$completion`);
}

@JvmSynthetic
fun `access$selectForRead`(selectable: Selectable, selector: SelectorManager, `$completion`: Continuation): Any {
   return selectForRead(selectable, selector, `$completion`);
}
