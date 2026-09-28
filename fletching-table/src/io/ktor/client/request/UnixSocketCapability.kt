package io.ktor.client.request

import io.ktor.client.engine.HttpClientEngineCapability

public data object UnixSocketCapability : HttpClientEngineCapability<UnixSocketSettings> {
   public override fun toString(): String {
      return "UnixSocketCapability";
   }

   public override fun hashCode(): Int {
      return 620284891;
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else {
         return other is UnixSocketCapability;
      }
   }
}
