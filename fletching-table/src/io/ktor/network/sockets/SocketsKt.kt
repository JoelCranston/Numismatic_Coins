@file:SourceDebugExtension(["SMAP\nSockets.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Sockets.kt\nio/ktor/network/sockets/SocketsKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,206:1\n1#2:207\n*E\n"])

package io.ktor.network.sockets

import io.ktor.network.sockets.SocketsKt.awaitClosed.1
import io.ktor.utils.io.ByteChannel
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.ByteWriteChannel
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.Job

public final val isClosed: Boolean
   public final get() {
      return `$this$isClosed`.getSocketContext().isCompleted();
   }


public final val port: Int
   public final get() {
      return SocketAddressKt.port(`$this$port`.getLocalAddress());
   }


public suspend fun ASocket.awaitClosed() {
   var `$continuation`: Continuation;
   label24: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label24;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var4: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         val var10000: Job = `$this$awaitClosed`.getSocketContext();
         `$continuation`.L$0 = `$this$awaitClosed`;
         `$continuation`.label = 1;
         if (var10000.join(`$continuation`) === var4) {
            return var4;
         }
         break;
      case 1:
         `$this$awaitClosed` = `$continuation`.L$0 as ASocket;
         ResultKt.throwOnFailure(`$result`);
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   if (`$this$awaitClosed`.getSocketContext().isCancelled()) {
      throw `$this$awaitClosed`.getSocketContext().getCancellationException();
   } else {
      return Unit.INSTANCE;
   }
}

public fun AReadable.openReadChannel(): ByteReadChannel {
   val var1: ByteChannel = new ByteChannel(false);
   `$this$openReadChannel`.attachForReading(var1);
   return var1;
}

public fun AWritable.openWriteChannel(autoFlush: Boolean = false): ByteWriteChannel {
   val var2: ByteChannel = new ByteChannel(autoFlush);
   `$this$openWriteChannel`.attachForWriting(var2);
   return var2;
}

@JvmSynthetic
fun `openWriteChannel$default`(var0: AWritable, var1: Boolean, var2: Int, var3: Any): ByteWriteChannel {
   if ((var2 and 1) != 0) {
      var1 = false;
   }

   return openWriteChannel(var0, var1);
}

public fun Socket.connection(): Connection {
   return new Connection(`$this$connection`, openReadChannel(`$this$connection`), openWriteChannel$default(`$this$connection`, false, 1, null));
}
