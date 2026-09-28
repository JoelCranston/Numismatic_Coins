package io.ktor.client

import io.ktor.client.engine.HttpClientEngineFactory

public interface HttpClientEngineContainer {
   public val factory: HttpClientEngineFactory<*>
}
