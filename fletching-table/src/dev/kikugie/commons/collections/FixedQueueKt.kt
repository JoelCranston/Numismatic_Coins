@file:SourceDebugExtension(["SMAP\nFixedQueue.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FixedQueue.kt\ndev/kikugie/commons/collections/FixedQueueKt\n*L\n1#1,124:1\n10#1:125\n*S KotlinDebug\n*F\n+ 1 FixedQueue.kt\ndev/kikugie/commons/collections/FixedQueueKt\n*L\n11#1:125\n*E\n"])

package dev.kikugie.commons.collections

import kotlin.jvm.internal.SourceDebugExtension

public inline fun <T> FixedQueue<T>.first(): T {
   return (T)`$this$first`.element();
}

public inline fun <T> FixedQueue<T>.firstOrNull(): T? {
   return (T)`$this$firstOrNull`.peek();
}

public inline fun <T> FixedQueue<T>.last(): T {
   return (T)`$this$last`.get(`$this$last`.size() - 1);
}

public inline fun <T> FixedQueue<T>.lastOrNull(): T? {
   return (T)(if (`$this$lastOrNull`.isEmpty()) null else `$this$lastOrNull`.get(`$this$lastOrNull`.size() - 1));
}

public inline operator fun <T> FixedQueue<T>.plusAssign(element: T) {
   `$this$plusAssign`.add(element);
}

public inline operator fun <T> FixedQueue<T>.plusAssign(elements: Collection<T>) {
   `$this$plusAssign`.addAll(elements);
}
