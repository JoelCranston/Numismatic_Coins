package io.ktor.network.sockets

public interface ServerSocket : ASocket, ABoundSocket, Acceptable<Socket> {
   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun dispose(`$this`: ServerSocket) {
         ServerSocket.access$dispose$jd(`$this`);
      }
   }
}
