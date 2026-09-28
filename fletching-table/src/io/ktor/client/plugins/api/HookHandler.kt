package io.ktor.client.plugins.api

import io.ktor.client.HttpClient

internal class HookHandler<T>(hook: ClientHook<Any>, handler: Any) {
   private final val hook: ClientHook<Any>
   private final val handler: Any

   init {
      this.hook = hook;
      this.handler = (T)handler;
   }

   public fun install(client: HttpClient) {
      this.hook.install(client, this.handler);
   }
}
