package io.ktor.client.plugins.sse

import io.ktor.client.engine.HttpClientEngineCapability

public data object SSECapability : HttpClientEngineCapability<Unit> {
   public override fun toString(): String {
      return "SSECapability";
   }

   public override fun hashCode(): Int {
      return -177755299;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is SSECapability;
      }
   }
}
