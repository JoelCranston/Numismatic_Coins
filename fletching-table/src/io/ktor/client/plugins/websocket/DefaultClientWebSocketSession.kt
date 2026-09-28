package io.ktor.client.plugins.websocket

import io.ktor.client.call.HttpClientCall
import io.ktor.utils.io.InternalAPI
import io.ktor.websocket.CloseReason
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketExtension
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel

public class DefaultClientWebSocketSession(call: HttpClientCall, delegate: DefaultWebSocketSession) : ClientWebSocketSession, DefaultWebSocketSession {
   public open val call: HttpClientCall
   public open val closeReason: Deferred<CloseReason?>
   public open val coroutineContext: CoroutineContext
   public open val extensions: List<WebSocketExtension<*>>
   public open val incoming: ReceiveChannel<Frame>
   public open var masking: Boolean
   public open var maxFrameSize: Long
   public open val outgoing: SendChannel<Frame>
   public open var pingIntervalMillis: Long
   public open var timeoutMillis: Long

   init {
      this.$$delegate_0 = delegate;
      this.call = call;
   }

   public override suspend fun send(frame: Frame) {
      return this.$$delegate_0.send(frame, `$completion`);
   }

   public override suspend fun flush() {
      return this.$$delegate_0.flush(`$completion`);
   }

   @Deprecated(message = "Use cancel() instead.", replaceWith = @ReplaceWith(expression = "cancel()", imports = ["kotlinx.coroutines.cancel"]), level = DeprecationLevel.ERROR)
   public override fun terminate() {
      this.$$delegate_0.terminate();
   }

   @InternalAPI
   public override fun start(negotiatedExtensions: List<WebSocketExtension<*>>) {
      this.$$delegate_0.start(negotiatedExtensions);
   }
}
