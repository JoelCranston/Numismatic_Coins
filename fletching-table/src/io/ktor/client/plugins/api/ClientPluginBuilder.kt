package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.statement.HttpResponse
import io.ktor.http.content.OutgoingContent
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.KtorDsl
import java.util.ArrayList
import kotlin.coroutines.Continuation

@KtorDsl
public class ClientPluginBuilder<PluginConfig> internal constructor(key: AttributeKey<ClientPluginInstance<Any>>, client: HttpClient, pluginConfig: Any) {
   internal final val key: AttributeKey<ClientPluginInstance<Any>>
   public final val client: HttpClient
   public final val pluginConfig: Any
   internal final val hooks: MutableList<HookHandler<*>>
   internal final var onClose: () -> Unit

   init {
      this.key = key;
      this.client = client;
      this.pluginConfig = (PluginConfig)pluginConfig;
      this.hooks = new ArrayList<>();
      this.onClose = ClientPluginBuilder::onClose$lambda$0;
   }

   public fun onRequest(block: (OnRequestContext, HttpRequestBuilder, Any, Continuation<Unit>) -> Any?) {
      this.on(RequestHook.INSTANCE, block);
   }

   public fun onResponse(block: (OnResponseContext, HttpResponse, Continuation<Unit>) -> Any?) {
      this.on(ResponseHook.INSTANCE, block);
   }

   public fun transformRequestBody(block: (TransformRequestBodyContext, HttpRequestBuilder, Any, TypeInfo?, Continuation<OutgoingContent?>) -> Any?) {
      this.on(TransformRequestBodyHook.INSTANCE, block);
   }

   public fun transformResponseBody(block: (TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, Continuation<Any?>) -> Any?) {
      this.on(TransformResponseBodyHook.INSTANCE, block);
   }

   public fun onClose(block: () -> Unit) {
      this.onClose = block;
   }

   public fun <HookHandler> on(hook: ClientHook<Any>, handler: Any) {
      this.hooks.add(new HookHandler<>(hook, handler));
   }

   @JvmStatic
   fun `onClose$lambda$0`(): Unit {
      return Unit.INSTANCE;
   }
}
