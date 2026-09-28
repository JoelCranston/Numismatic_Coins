package io.ktor.client.plugins.websocket

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.websocket.WebSockets.Plugin.install.1
import io.ktor.client.plugins.websocket.WebSockets.Plugin.install.2
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.UtilsKt
import io.ktor.client.statement.HttpResponsePipeline
import io.ktor.http.HttpHeaders
import io.ktor.serialization.WebsocketContentConverter
import io.ktor.util.AttributeKey
import io.ktor.util.reflect.TypeInfo
import io.ktor.utils.io.KtorDsl
import io.ktor.websocket.DefaultWebSocketSession
import io.ktor.websocket.DefaultWebSocketSessionKt
import io.ktor.websocket.WebSocketExtension
import io.ktor.websocket.WebSocketExtensionHeader
import io.ktor.websocket.WebSocketExtensionHeaderKt
import io.ktor.websocket.WebSocketExtensionsConfig
import io.ktor.websocket.WebSocketSession
import java.util.ArrayList
import kotlin.jvm.internal.Reflection
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.reflect.KType

@SourceDebugExtension(["SMAP\nWebSockets.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSockets.kt\nio/ktor/client/plugins/websocket/WebSockets\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Attributes.kt\nio/ktor/util/AttributesKt\n+ 5 Type.kt\nio/ktor/util/reflect/TypeKt\n*L\n1#1,253:1\n1374#2:254\n1460#2,5:255\n774#2:261\n865#2,2:262\n1#3:260\n21#4:264\n69#5:265\n84#5,8:266\n*S KotlinDebug\n*F\n+ 1 WebSockets.kt\nio/ktor/client/plugins/websocket/WebSockets\n*L\n83#1:254\n83#1:255,5\n96#1:261\n96#1:262,2\n162#1:264\n162#1:265\n162#1:266,8\n*E\n"])
public class WebSockets internal constructor(pingIntervalMillis: Long,
   maxFrameSize: Long,
   extensionsConfig: WebSocketExtensionsConfig,
   contentConverter: WebsocketContentConverter? = null
) {
   public final val pingIntervalMillis: Long
   public final val maxFrameSize: Long
   private final val extensionsConfig: WebSocketExtensionsConfig
   public final val contentConverter: WebsocketContentConverter?

   init {
      this.pingIntervalMillis = pingIntervalMillis;
      this.maxFrameSize = maxFrameSize;
      this.extensionsConfig = extensionsConfig;
      this.contentConverter = contentConverter;
   }

   public constructor(pingIntervalMillis: Long = 0L, maxFrameSize: Long = 2147483647L) : this(
         pingIntervalMillis, maxFrameSize, new WebSocketExtensionsConfig(), null, 8, null
      )
   public constructor() : this(0L, 2147483647L, new WebSocketExtensionsConfig(), null, 8, null)
   private fun installExtensions(context: HttpRequestBuilder) {
      val installed: java.util.List = this.extensionsConfig.build();
      context.getAttributes().put(WebSocketsKt.access$getREQUEST_EXTENSIONS_KEY$p(), installed);
      val `$this$flatMap$iv`: java.lang.Iterable = installed;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$flatMap$iv) {
         CollectionsKt.addAll(`destination$iv$iv`, (`element$iv$iv` as WebSocketExtension).getProtocols());
      }

      this.addNegotiatedProtocols(context, `destination$iv$iv` as MutableList<WebSocketExtensionHeader>);
   }

   private fun completeNegotiation(call: HttpClientCall): List<WebSocketExtension<*>> {
      var var10000: java.util.List;
      label21: {
         val clientExtensions: java.lang.String = call.getResponse().getHeaders().get(HttpHeaders.INSTANCE.getSecWebSocketExtensions());
         if (clientExtensions != null) {
            val `$this$filter$iv`: java.util.List = WebSocketExtensionHeaderKt.parseWebSocketExtensions(clientExtensions);
            if (`$this$filter$iv` != null) {
               var10000 = `$this$filter$iv`;
               break label21;
            }
         }

         var10000 = CollectionsKt.emptyList();
      }

      val serverExtensions: java.util.List = var10000;
      val var14: java.lang.Iterable = call.getAttributes().get(WebSocketsKt.access$getREQUEST_EXTENSIONS_KEY$p()) as java.util.List;
      val var15: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filter$iv) {
         if ((`element$iv$iv` as WebSocketExtension).clientNegotiation(serverExtensions)) {
            var15.add(`element$iv$iv`);
         }
      }

      return var15 as MutableList<WebSocketExtension<?>>;
   }

   private fun addNegotiatedProtocols(context: HttpRequestBuilder, protocols: List<WebSocketExtensionHeader>) {
      if (!protocols.isEmpty()) {
         UtilsKt.header(
            context, HttpHeaders.INSTANCE.getSecWebSocketExtensions(), CollectionsKt.joinToString$default(protocols, ",", null, null, 0, null, null, 62, null)
         );
      }
   }

   internal fun convertSessionToDefault(session: WebSocketSession): DefaultWebSocketSession {
      if (session is DefaultWebSocketSession) {
         return session as DefaultWebSocketSession;
      } else {
         val var2: DefaultWebSocketSession = DefaultWebSocketSessionKt.DefaultWebSocketSession(
            session, this.pingIntervalMillis, this.pingIntervalMillis * (long)2
         );
         var2.setMaxFrameSize(this.maxFrameSize);
         return var2;
      }
   }

   @JvmStatic
   fun {
      var var6: KType;
      try {
         var6 = Reflection.typeOf(WebSockets.class);
      } catch (var12: java.lang.Throwable) {
         var6 = null;
      }

      key = new AttributeKey<>("Websocket", new TypeInfo(WebSockets::class, var6));
   }

   @KtorDsl
   public class Config {
      internal final val extensionsConfig: WebSocketExtensionsConfig = new WebSocketExtensionsConfig()
      public final var pingIntervalMillis: Long
      public final var maxFrameSize: Long = 2147483647L
      public final var contentConverter: WebsocketContentConverter?

      public fun extensions(block: (WebSocketExtensionsConfig) -> Unit) {
         block.invoke(this.extensionsConfig);
      }
   }

   public companion object Plugin : HttpClientPlugin<WebSockets.Config, WebSockets> {
      public open val key: AttributeKey<WebSockets>

      public open fun prepare(block: (io.ktor.client.plugins.websocket.WebSockets.Config) -> Unit): WebSockets {
         val var3: WebSockets.Config = new WebSockets.Config();
         block.invoke(var3);
         return new WebSockets(var3.getPingIntervalMillis(), var3.getMaxFrameSize(), var3.getExtensionsConfig$ktor_client_core(), var3.getContentConverter());
      }

      public open fun install(plugin: WebSockets, scope: HttpClient) {
         val extensionsSupported: Boolean = scope.getEngine().getSupportedCapabilities().contains(WebSocketExtensionsCapability.INSTANCE);
         scope.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getRender(), new 1(extensionsSupported, plugin, null));
         scope.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getTransform(), new 2(plugin, extensionsSupported, null));
      }
   }
}
