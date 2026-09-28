package io.ktor.client.plugins.sse

import io.ktor.sse.ServerSentEvent
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

public interface SSESession : CoroutineScope {
   public val incoming: Flow<ServerSentEvent>

   @InternalAPI
   public open fun bodyBuffer(): ByteArray {
      return SSEBufferPolicyKt.getEMPTY();
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @InternalAPI
      @JvmStatic
      fun bodyBuffer(`$this`: SSESession): ByteArray {
         return SSESession.access$bodyBuffer$jd(`$this`);
      }
   }
}
