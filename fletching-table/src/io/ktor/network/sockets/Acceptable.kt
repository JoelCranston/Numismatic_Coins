package io.ktor.network.sockets

public interface Acceptable<S extends ASocket> : ASocket {
   public abstract suspend fun accept(): Any {
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun <S extends ASocket> dispose(`$this`: Acceptable<? extends S>) {
         Acceptable.access$dispose$jd(`$this`);
      }
   }
}
