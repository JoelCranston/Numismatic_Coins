package io.ktor.network.sockets

import kotlin.coroutines.Continuation
import kotlinx.coroutines.channels.SendChannel

public interface DatagramWriteChannel {
   public val outgoing: SendChannel<Datagram>

   public open suspend fun send(datagram: Datagram) {
      return send$suspendImpl(this, datagram, `$completion`);
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun send(`$this`: DatagramWriteChannel, datagram: Datagram, `$completion`: Continuation<? super Unit>): Any {
         return DatagramWriteChannel.access$send$jd(`$this`, datagram, `$completion`);
      }
   }
}
