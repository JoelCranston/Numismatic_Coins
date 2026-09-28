package io.ktor.client.plugins.sse

import io.ktor.client.call.HttpClientCall
import io.ktor.sse.TypedServerSentEvent
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow

public class ClientSSESessionWithDeserialization(call: HttpClientCall, delegate: SSESessionWithDeserialization) : SSESessionWithDeserialization {
   public final val call: HttpClientCall
   public open val coroutineContext: CoroutineContext
   public open val deserializer: (TypeInfo, String) -> Any?
   public open val incoming: Flow<TypedServerSentEvent<String>>

   init {
      this.$$delegate_0 = delegate;
      this.call = call;
   }

   @InternalAPI
   public override fun bodyBuffer(): ByteArray {
      return this.$$delegate_0.bodyBuffer();
   }
}
