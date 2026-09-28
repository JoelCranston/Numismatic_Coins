package io.ktor.client.plugins

import io.ktor.client.HttpClient
import io.ktor.util.AttributeKey

public interface HttpClientPlugin<TConfig, TPlugin> {
   public val key: AttributeKey<Any>

   public abstract fun prepare(block: (Any) -> Unit = HttpClientPlugin::prepare$lambda$0): Any {
   }

   public abstract fun install(plugin: Any, scope: HttpClient) {
   }

   @JvmDefault
   @JvmStatic
   fun `prepare$lambda$0`(var0: Any): Unit {
      return Unit.INSTANCE;
   }

   // $VF: Class flags could not be determined
   internal class DefaultImpls
}
