package io.ktor.network.sockets

public interface ReadWriteSocket : ASocket, AReadable, AWritable {
   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun dispose(`$this`: ReadWriteSocket) {
         ReadWriteSocket.access$dispose$jd(`$this`);
      }
   }
}
