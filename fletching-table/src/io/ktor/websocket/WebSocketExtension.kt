package io.ktor.websocket

public interface WebSocketExtension<ConfigType> {
   public val factory: WebSocketExtensionFactory<Any, out WebSocketExtension<Any>>
   public val protocols: List<WebSocketExtensionHeader>

   public abstract fun clientNegotiation(negotiatedProtocols: List<WebSocketExtensionHeader>): Boolean {
   }

   public abstract fun serverNegotiation(requestedProtocols: List<WebSocketExtensionHeader>): List<WebSocketExtensionHeader> {
   }

   public abstract fun processOutgoingFrame(frame: Frame): Frame {
   }

   public abstract fun processIncomingFrame(frame: Frame): Frame {
   }
}
