package io.ktor.client.plugins.websocket

import io.ktor.client.engine.HttpClientEngineCapability

public data object WebSocketExtensionsCapability : HttpClientEngineCapability<Unit> {
   public override fun toString(): String {
      return "WebSocketExtensionsCapability";
   }

   public override fun hashCode(): Int {
      return 806573237;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is WebSocketExtensionsCapability;
      }
   }
}
