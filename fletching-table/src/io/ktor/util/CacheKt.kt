package io.ktor.util

import io.ktor.utils.io.InternalAPI
import java.util.Collections

private const val CACHE_INITIAL_CAPACITY: Int = 10
private const val CACHE_LOAD_FACTOR: Float = 0.75F

@InternalAPI
public fun <K, V> createLRUCache(supplier: (Any) -> Any, close: (Any) -> Unit, maxSize: Int): Map<Any, Any> {
   val var10000: java.util.Map = Collections.synchronizedMap(new LRUCache(supplier, close, maxSize));
   return var10000;
}
