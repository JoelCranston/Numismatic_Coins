package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.util.AttributeKey
import io.ktor.utils.io.InternalAPI
import java.io.Closeable
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nClientPluginInstance.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ClientPluginInstance.kt\nio/ktor/client/plugins/api/ClientPluginInstance\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,36:1\n1869#2,2:37\n*S KotlinDebug\n*F\n+ 1 ClientPluginInstance.kt\nio/ktor/client/plugins/api/ClientPluginInstance\n*L\n29#1:37,2\n*E\n"])
public class ClientPluginInstance<PluginConfig> internal constructor(key: AttributeKey<ClientPluginInstance<Any>>,
      config: Any,
      body: (ClientPluginBuilder<Any>) -> Unit
   ) :
   Closeable {
   private final val key: AttributeKey<ClientPluginInstance<Any>>
   private final val config: Any
   private final val body: (ClientPluginBuilder<Any>) -> Unit
   private final var onClose: () -> Unit

   init {
      this.key = key;
      this.config = (PluginConfig)config;
      this.body = body;
      this.onClose = ClientPluginInstance::onClose$lambda$0;
   }

   @InternalAPI
   public fun install(scope: HttpClient) {
      val `$this$forEach$iv`: ClientPluginBuilder = new ClientPluginBuilder<>(this.key, scope, this.config);
      this.body.invoke(`$this$forEach$iv`);
      this.onClose = `$this$forEach$iv`.getOnClose$ktor_client_core();

      for (Object element$iv : var9) {
         (`element$iv` as HookHandler).install(scope);
      }
   }

   public override fun close() {
      this.onClose.invoke();
   }

   @JvmStatic
   fun `onClose$lambda$0`(): Unit {
      return Unit.INSTANCE;
   }
}
