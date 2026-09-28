package io.ktor.websocket

import io.ktor.util.AttributeKey

public interface WebSocketExtensionFactory<ConfigType, ExtensionType extends WebSocketExtension<ConfigType>> {
   public val key: AttributeKey<Any>
   public val rsv1: Boolean
   public val rsv2: Boolean
   public val rsv3: Boolean

   public abstract fun install(config: (Any) -> Unit): Any {
   }
}
