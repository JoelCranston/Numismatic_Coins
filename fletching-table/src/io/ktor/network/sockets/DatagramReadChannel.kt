package io.ktor.network.sockets

import kotlin.coroutines.Continuation
import kotlinx.coroutines.channels.ReceiveChannel

public interface DatagramReadChannel {
   public val incoming: ReceiveChannel<Datagram>

   public open suspend fun receive(): Datagram {
      return receive$suspendImpl(this, `$completion`);
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun receive(`$this`: DatagramReadChannel, `$completion`: Continuation<? super Datagram>): Any {
         return DatagramReadChannel.access$receive$jd(`$this`, `$completion`);
      }
   }
}
