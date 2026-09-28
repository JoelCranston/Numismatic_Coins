@file:SourceDebugExtension(["SMAP\nWebSocketExtensionHeader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketExtensionHeader.kt\nio/ktor/websocket/WebSocketExtensionHeaderKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,52:1\n1563#2:53\n1634#2,2:54\n1563#2:56\n1634#2,3:57\n1636#2:60\n*S KotlinDebug\n*F\n+ 1 WebSocketExtensionHeader.kt\nio/ktor/websocket/WebSocketExtensionHeaderKt\n*L\n46#1:53\n46#1:54,2\n49#1:56\n49#1:57,3\n46#1:60\n*E\n"])

package io.ktor.websocket

import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension

public fun parseWebSocketExtensions(value: String): List<WebSocketExtensionHeader> {
   val var24: java.lang.Iterable = StringsKt.split$default(value, new java.lang.String[]{","}, false, 0, 6, null);
   val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var24, 10));

   for (Object item$iv$iv : var24) {
      val extension: java.util.List = StringsKt.split$default(`item$iv$iv` as java.lang.String, new java.lang.String[]{";"}, false, 0, 6, null);
      val var25: java.lang.String = StringsKt.trim(CollectionsKt.first(extension) as java.lang.String).toString();
      val `$this$map$iv`: java.lang.Iterable = CollectionsKt.drop(extension, 1);
      val `destination$iv$ivx`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

      for (Object item$iv$ivx : $this$map$iv) {
         `destination$iv$ivx`.add(StringsKt.trim(`item$iv$ivx` as java.lang.String).toString());
      }

      `destination$iv$iv`.add(new WebSocketExtensionHeader(var25, `destination$iv$ivx` as MutableList<java.lang.String>));
   }

   return `destination$iv$iv` as MutableList<WebSocketExtensionHeader>;
}
