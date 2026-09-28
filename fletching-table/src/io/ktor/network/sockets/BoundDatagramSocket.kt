package io.ktor.network.sockets

import kotlin.coroutines.Continuation

public interface BoundDatagramSocket : ASocket, ABoundSocket, DatagramReadWriteChannel {
   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun dispose(`$this`: BoundDatagramSocket) {
         BoundDatagramSocket.access$dispose$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun receive(`$this`: BoundDatagramSocket, `$completion`: Continuation<? super Datagram>): Any {
         return BoundDatagramSocket.access$receive$jd(`$this`, `$completion`);
      }

      @Deprecated
      @JvmStatic
      fun send(`$this`: BoundDatagramSocket, datagram: Datagram, `$completion`: Continuation<? super Unit>): Any {
         return BoundDatagramSocket.access$send$jd(`$this`, datagram, `$completion`);
      }
   }
}
