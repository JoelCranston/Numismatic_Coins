package io.ktor.network.sockets

import kotlin.coroutines.Continuation

public interface DatagramReadWriteChannel : DatagramReadChannel, DatagramWriteChannel {
   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @JvmStatic
      fun receive(`$this`: DatagramReadWriteChannel, `$completion`: Continuation<? super Datagram>): Any {
         return DatagramReadWriteChannel.access$receive$jd(`$this`, `$completion`);
      }

      @Deprecated
      @JvmStatic
      fun send(`$this`: DatagramReadWriteChannel, datagram: Datagram, `$completion`: Continuation<? super Unit>): Any {
         return DatagramReadWriteChannel.access$send$jd(`$this`, datagram, `$completion`);
      }
   }
}
