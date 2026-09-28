package io.ktor.client.plugins.sse

import io.ktor.sse.TypedServerSentEvent
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.InternalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

public interface SSESessionWithDeserialization : CoroutineScope {
   public val incoming: Flow<TypedServerSentEvent<String>>
   public val deserializer: (TypeInfo, String) -> Any?

   @InternalAPI
   public open fun bodyBuffer(): ByteArray {
      return SSEBufferPolicyKt.getEMPTY();
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls {
      @Deprecated
      @InternalAPI
      @JvmStatic
      fun bodyBuffer(`$this`: SSESessionWithDeserialization): ByteArray {
         return SSESessionWithDeserialization.access$bodyBuffer$jd(`$this`);
      }
   }
}
