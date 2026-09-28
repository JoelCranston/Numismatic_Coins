package io.ktor.client.plugins.sse

import io.ktor.client.call.HttpClientCall
import io.ktor.sse.ServerSentEvent
import io.ktor.utils.io.InternalAPI
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.flow.Flow

public class ClientSSESession(call: HttpClientCall, delegate: SSESession) : SSESession {
   public final val call: HttpClientCall
   public open val coroutineContext: CoroutineContext
   public open val incoming: Flow<ServerSentEvent>

   init {
      this.$$delegate_0 = delegate;
      this.call = call;
   }

   @InternalAPI
   public override fun bodyBuffer(): ByteArray {
      return this.$$delegate_0.bodyBuffer();
   }
}
