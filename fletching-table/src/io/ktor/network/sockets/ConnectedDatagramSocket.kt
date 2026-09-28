package io.ktor.network.sockets

import kotlin.coroutines.Continuation

public interface ConnectedDatagramSocket : ASocket, ABoundSocket, AConnectedSocket, DatagramReadWriteChannel {
   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun dispose(`$this`: ConnectedDatagramSocket) {
         ConnectedDatagramSocket.access$dispose$jd(`$this`);
      }

      @Deprecated
      @JvmStatic
      fun receive(`$this`: ConnectedDatagramSocket, `$completion`: Continuation<? super Datagram>): Any {
         return ConnectedDatagramSocket.access$receive$jd(`$this`, `$completion`);
      }

      @Deprecated
      @JvmStatic
      fun send(`$this`: ConnectedDatagramSocket, datagram: Datagram, `$completion`: Continuation<? super Unit>): Any {
         return ConnectedDatagramSocket.access$send$jd(`$this`, datagram, `$completion`);
      }
   }
}
