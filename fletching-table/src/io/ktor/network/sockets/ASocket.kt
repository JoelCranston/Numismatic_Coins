package io.ktor.network.sockets

import java.io.Closeable
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.Job

public interface ASocket : Closeable, DisposableHandle {
   public val socketContext: Job

   public override fun dispose() {
      try {
         this.close();
      } catch (var2: java.lang.Throwable) {
      }
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun dispose(`$this`: ASocket) {
         ASocket.access$dispose$jd(`$this`);
      }
   }
}
