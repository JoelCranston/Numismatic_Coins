package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.utils.io.KtorDsl

@KtorDsl
public interface ClientHook<HookHandler> {
   public abstract fun install(client: HttpClient, handler: Any) {
   }
}
