@file:SourceDebugExtension(["SMAP\nCollections.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Collections.kt\ndev/kikugie/commons/collections/CollectionsKt\n+ 2 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n*L\n1#1,9:1\n23#2:10\n*S KotlinDebug\n*F\n+ 1 Collections.kt\ndev/kikugie/commons/collections/CollectionsKt\n*L\n7#1:10\n*E\n"])

package dev.kikugie.commons.collections

import kotlin.jvm.internal.SourceDebugExtension

public inline fun <T, C : Collection<T>> C.ifNotEmpty(action: (C) -> Unit): C {
   val var10000: java.util.Collection;
   if (!`$this$ifNotEmpty`.isEmpty()) {
      action.invoke(`$this$ifNotEmpty`);
      var10000 = `$this$ifNotEmpty`;
   } else {
      var10000 = `$this$ifNotEmpty`;
   }

   return (C)var10000;
}
