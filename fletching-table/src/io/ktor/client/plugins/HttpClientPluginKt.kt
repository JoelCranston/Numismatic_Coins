@file:SourceDebugExtension(["SMAP\nHttpClientPlugin.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientPlugin.kt\nio/ktor/client/plugins/HttpClientPluginKt\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,62:1\n21#2:63\n69#3:64\n84#3,8:65\n*S KotlinDebug\n*F\n+ 1 HttpClientPlugin.kt\nio/ktor/client/plugins/HttpClientPluginKt\n*L\n11#1:63\n11#1:64\n11#1:65,8\n*E\n"])

package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import kotlin.jvm.internal.SourceDebugExtension

internal final val PLUGIN_INSTALLED_LIST: AttributeKey<Attributes>

public fun <B : Any, F : Any> HttpClient.pluginOrNull(plugin: HttpClientPlugin<Any, Any>): Any? {
   val var10000: Attributes = `$this$pluginOrNull`.getAttributes().getOrNull(PLUGIN_INSTALLED_LIST);
   return (F)(if (var10000 != null) var10000.getOrNull(plugin.getKey()) else null);
}

public fun <B : Any, F : Any> HttpClient.plugin(plugin: HttpClientPlugin<Any, Any>): Any {
   val var10000: Any = pluginOrNull(`$this$plugin`, plugin);
   if (var10000 == null) {
      throw new IllegalStateException("Plugin $plugin is not installed. Consider using `install(${plugin.getKey()})` in client config first.");
   } else {
      return (F)var10000;
   }
}
