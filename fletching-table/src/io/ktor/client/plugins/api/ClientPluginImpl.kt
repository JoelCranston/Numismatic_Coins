package io.ktor.client.plugins.api

import io.ktor.client.HttpClient
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType
import kotlin.reflect.KTypeParameter
import kotlin.reflect.KTypeProjection
import kotlin.reflect.KVariance

@SourceDebugExtension(["SMAP\nCreatePluginUtils.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CreatePluginUtils.kt\nio/ktor/client/plugins/api/ClientPluginImpl\n+ 2 Attributes.kt\nio/ktor/util/AttributesKt\n+ 3 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,102:1\n21#2:103\n69#3:104\n84#3,8:105\n*S KotlinDebug\n*F\n+ 1 CreatePluginUtils.kt\nio/ktor/client/plugins/api/ClientPluginImpl\n*L\n64#1:103\n64#1:104\n64#1:105,8\n*E\n"])
private class ClientPluginImpl<PluginConfigT>(name: String, createConfiguration: () -> Any, body: (ClientPluginBuilder<Any>) -> Unit) :
   ClientPlugin<PluginConfigT> {
   private final val createConfiguration: () -> Any
   private final val body: (ClientPluginBuilder<Any>) -> Unit
   public open val key: AttributeKey<ClientPluginInstance<Any>>

   init {
      this.createConfiguration = createConfiguration;
      this.body = body;

      var var10: KType;
      try {
         val var10001: KTypeProjection.Companion = KTypeProjection.Companion;
         val var10002: KTypeParameter = Reflection.typeParameter(ClientPluginImpl::class, "PluginConfigT", KVariance.INVARIANT, false);
         Reflection.setUpperBounds(var10002, Reflection.typeOf(Object.class));
         var10 = Reflection.typeOf(ClientPluginInstance.class, var10001.invariant(Reflection.typeOf(var10002)));
      } catch (var17: java.lang.Throwable) {
         var10 = null;
      }

      this.key = new AttributeKey<>(name, new TypeInfo(ClientPluginInstance::class, var10));
   }

   public open fun prepare(block: (Any) -> Unit): ClientPluginInstance<Any> {
      val var3: Any = this.createConfiguration.invoke();
      block.invoke(var3);
      return new ClientPluginInstance<>(this.getKey(), (PluginConfigT)var3, this.body);
   }

   public open fun install(plugin: ClientPluginInstance<Any>, scope: HttpClient) {
      plugin.install(scope);
   }
}
