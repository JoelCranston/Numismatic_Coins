package io.ktor.client.plugins.websocket

import io.ktor.client.engine.HttpClientEngineCapability

public data object WebSocketCapability : HttpClientEngineCapability<Unit> {
   public override fun toString(): String {
      return "WebSocketCapability";
   }

   public override fun hashCode(): Int {
      return -1146563391;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is WebSocketCapability;
      }
   }
}
