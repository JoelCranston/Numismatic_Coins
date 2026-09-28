package io.ktor.websocket

import java.util.ArrayList
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nWebSocketExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketExtension.kt\nio/ktor/websocket/WebSocketExtensionsConfig\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n1563#2:168\n1634#2,3:169\n1#3:172\n*S KotlinDebug\n*F\n+ 1 WebSocketExtension.kt\nio/ktor/websocket/WebSocketExtensionsConfig\n*L\n157#1:168\n157#1:169,3\n*E\n"])
public class WebSocketExtensionsConfig {
   private final val installers: MutableList<() -> WebSocketExtension<*>> = (new ArrayList()) as java.util.List
   private final val rcv: Array<Boolean>

   public fun <ConfigType : Any> install(extension: WebSocketExtensionFactory<Any, *>, config: (Any) -> Unit = WebSocketExtensionsConfig::install$lambda$0) {
      this.checkConflicts(extension);
      this.installers.add(WebSocketExtensionsConfig::install$lambda$1);
   }

   public fun build(): List<WebSocketExtension<*>> {
      val `$this$map$iv`: java.lang.Iterable = this.installers;
      val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(this.installers, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$iv`.add((`item$iv$iv` as Function0).invoke() as WebSocketExtension);
      }

      return `destination$iv$iv` as MutableList<WebSocketExtension<?>>;
   }

   private fun checkConflicts(extensionFactory: WebSocketExtensionFactory<*, *>) {
      if (extensionFactory.getRsv1() && this.rcv[1] || extensionFactory.getRsv2() && this.rcv[2] || extensionFactory.getRsv3() && this.rcv[3]) {
         throw new IllegalStateException("Failed to install extension. Please check configured extensions for conflicts.".toString());
      }
   }

   @JvmStatic
   fun `install$lambda$0`(var0: Any): Unit {
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `install$lambda$1`(`$extension`: WebSocketExtensionFactory, `$config`: Function1): WebSocketExtension {
      return `$extension`.install(`$config`);
   }
}
