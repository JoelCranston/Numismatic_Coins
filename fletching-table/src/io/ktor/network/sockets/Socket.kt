package io.ktor.network.sockets

import kotlinx.coroutines.CoroutineScope

public interface Socket : ReadWriteSocket, ABoundSocket, AConnectedSocket, CoroutineScope {
   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun dispose(`$this`: Socket) {
         Socket.access$dispose$jd(`$this`);
      }
   }
}
