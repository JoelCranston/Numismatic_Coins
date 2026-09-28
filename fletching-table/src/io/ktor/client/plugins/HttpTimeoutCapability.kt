package io.ktor.client.plugins

import io.ktor.client.engine.HttpClientEngineCapability

public data object HttpTimeoutCapability : HttpClientEngineCapability<HttpTimeoutConfig> {
   public override fun toString(): String {
      return "HttpTimeoutCapability";
   }

   public override fun hashCode(): Int {
      return 2058496954;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is HttpTimeoutCapability;
      }
   }
}
