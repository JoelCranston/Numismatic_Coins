package io.ktor.client.plugins.websocket

import io.ktor.client.request.ClientUpgradeContent
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.websocket.UtilsKt

internal class WebSocketContent : ClientUpgradeContent {
   private final val nonce: String
   public open val headers: Headers

   public override fun verify(headers: Headers) {
      val var10000: java.lang.String = headers.get(HttpHeaders.INSTANCE.getSecWebSocketAccept());
      if (var10000 == null) {
         throw new IllegalStateException(("Server should specify header ${HttpHeaders.INSTANCE.getSecWebSocketAccept()}").toString());
      } else {
         val expectedAccept: java.lang.String = UtilsKt.websocketServerAccept(this.nonce);
         if (!(expectedAccept == var10000)) {
            throw new IllegalStateException(("Failed to verify server accept header. Expected: $expectedAccept, received: $var10000").toString());
         }
      }
   }

   public override fun toString(): String {
      return "WebSocketContent";
   }
}
