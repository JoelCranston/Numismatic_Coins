package kotlinx.serialization.json.internal

import java.util.concurrent.ConcurrentHashMap

internal fun <K, V> createMapForCache(initialCapacity: Int): MutableMap<K, V> {
   return new ConcurrentHashMap(initialCapacity);
}
