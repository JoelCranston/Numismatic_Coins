package io.ktor.client.plugins

import io.ktor.utils.io.KtorDsl

@KtorDsl
public class UserAgentConfig(agent: String = "Ktor http-client") {
   public final var agent: String

   init {
      this.agent = agent;
   }

   fun UserAgentConfig() {
      this(null, 1, null);
   }
}
