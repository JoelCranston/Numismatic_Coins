package io.ktor.client

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.HttpClientPluginKt
import io.ktor.util.AttributeKey
import io.ktor.util.Attributes
import io.ktor.util.AttributesJvmKt
import io.ktor.util.PlatformUtils
import io.ktor.utils.io.KtorDsl
import java.util.LinkedHashMap
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

@KtorDsl
@SourceDebugExtension(["SMAP\nHttpClientConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HttpClientConfig.kt\nio/ktor/client/HttpClientConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,230:1\n1869#2,2:231\n1869#2,2:233\n*S KotlinDebug\n*F\n+ 1 HttpClientConfig.kt\nio/ktor/client/HttpClientConfig\n*L\n200#1:231,2\n201#1:233,2\n*E\n"])
public class HttpClientConfig<T extends HttpClientEngineConfig> {
   private final val plugins: MutableMap<AttributeKey<*>, (HttpClient) -> Unit> = (new LinkedHashMap()) as java.util.Map
   private final val pluginConfigurations: MutableMap<AttributeKey<*>, (Any) -> Unit> = (new LinkedHashMap()) as java.util.Map
   private final val customInterceptors: MutableMap<String, (HttpClient) -> Unit> = (new LinkedHashMap()) as java.util.Map
   internal final var engineConfig: (Any) -> Unit = HttpClientConfig::engineConfig$lambda$0
   public final var followRedirects: Boolean = true
   public final var useDefaultTransformers: Boolean = true
   public final var expectSuccess: Boolean

   @Deprecated(
      message = "Development mode is no longer required. The property will be removed in the future.",
      replaceWith = @ReplaceWith(
         expression = "",
         imports = {}
      ),
      level = DeprecationLevel.WARNING
   )
   public final var developmentMode: Boolean = PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE()

   public fun engine(block: (Any) -> Unit) {
      this.engineConfig = HttpClientConfig::engine$lambda$0;
   }

   public fun <TBuilder : Any, TPlugin : Any> install(plugin: HttpClientPlugin<Any, Any>, configure: (Any) -> Unit = HttpClientConfig::install$lambda$0) {
      this.pluginConfigurations.put(plugin.getKey(), HttpClientConfig::install$lambda$1);
      if (!this.plugins.containsKey(plugin.getKey())) {
         this.plugins.put(plugin.getKey(), HttpClientConfig::install$lambda$2);
      }
   }

   public fun install(key: String, block: (HttpClient) -> Unit) {
      this.customInterceptors.put(key, block);
   }

   public fun install(client: HttpClient) {
      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         (`element$iv` as Function1).invoke(client);
      }

      for (Object element$iv : $this$forEach$iv) {
         (var12 as Function1).invoke(client);
      }
   }

   public fun clone(): HttpClientConfig<Any> {
      val result: HttpClientConfig = new HttpClientConfig();
      result.plusAssign(this);
      return result;
   }

   public operator fun plusAssign(other: HttpClientConfig<out Any>) {
      this.followRedirects = other.followRedirects;
      this.useDefaultTransformers = other.useDefaultTransformers;
      this.expectSuccess = other.expectSuccess;
      this.plugins.putAll(other.plugins);
      this.pluginConfigurations.putAll(other.pluginConfigurations);
      this.customInterceptors.putAll(other.customInterceptors);
   }

   @JvmStatic
   fun `engineConfig$lambda$0`(var0: HttpClientEngineConfig): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `engine$lambda$0`(`$oldConfig`: Function1, `$block`: Function1, var2: HttpClientEngineConfig): Unit {
      `$oldConfig`.invoke(var2);
      `$block`.invoke(var2);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `install$lambda$0`(var0: Any): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `install$lambda$1`(`$previousConfigBlock`: Function1, `$configure`: Function1, var2: Any): Unit {
      if (`$previousConfigBlock` != null) {
         `$previousConfigBlock`.invoke(var2);
      }

      `$configure`.invoke(var2);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `install$lambda$2`(`$plugin`: HttpClientPlugin, scope: HttpClient): Unit {
      val attributes: Attributes = scope.getAttributes().computeIfAbsent(HttpClientPluginKt.getPLUGIN_INSTALLED_LIST(), HttpClientConfig::install$lambda$2$0);
      val var10000: Any = scope.getConfig$ktor_client_core().pluginConfigurations.get(`$plugin`.getKey());
      val pluginData: Any = `$plugin`.prepare(var10000 as Function1);
      `$plugin`.install(pluginData, scope);
      attributes.put(`$plugin`.getKey(), pluginData);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `install$lambda$2$0`(): Attributes {
      return AttributesJvmKt.Attributes(true);
   }
}
