package io.ktor.util.collections

import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.MutableMap.MutableEntry
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.markers.KMutableMap

public class ConcurrentMap<Key, Value>(initialCapacity: Int = 32) : java.util.Map<Key, Value>, KMutableMap {
   private final val delegate: ConcurrentHashMap<Any, Any>

   public open val size: Int
      public open get() {
         return this.delegate.size();
      }


   public open val entries: MutableSet<MutableEntry<Any, Any>>
      public open get() {
         val var10000: java.util.Set = this.delegate.entrySet();
         return var10000;
      }


   public open val keys: MutableSet<Any>
      public open get() {
         val var10000: java.util.Set = this.delegate.keySet();
         return var10000;
      }


   public open val values: MutableCollection<Any>
      public open get() {
         val var10000: java.util.Collection = this.delegate.values();
         return var10000;
      }


   init {
      this.delegate = new ConcurrentHashMap<>(initialCapacity);
   }

   public fun computeIfAbsent(key: Any, block: () -> Any): Any {
      return this.delegate.computeIfAbsent((Key)key, ConcurrentMap::computeIfAbsent$lambda$1);
   }

   public override fun containsKey(key: Any): Boolean {
      return this.delegate.containsKey(key);
   }

   public override fun containsValue(value: Any): Boolean {
      return this.delegate.containsValue(value);
   }

   public override operator fun get(key: Any): Any? {
      return this.delegate.get(key);
   }

   public override fun isEmpty(): Boolean {
      return this.delegate.isEmpty();
   }

   public override fun clear() {
      this.delegate.clear();
   }

   public override fun put(key: Any, value: Any): Any? {
      return this.delegate.put((Key)key, (Value)value);
   }

   public override fun putAll(from: Map<out Any, Any>) {
      this.delegate.putAll(from);
   }

   public override fun remove(key: Any): Any? {
      return this.delegate.remove(key);
   }

   public override fun remove(key: Any, value: Any): Boolean {
      return this.delegate.remove(key, value);
   }

   public override fun hashCode(): Int {
      return this.delegate.hashCode();
   }

   public override operator fun equals(other: Any?): Boolean {
      return other is java.util.Map && other == this.delegate;
   }

   public override fun toString(): String {
      return "ConcurrentMapJvm by ${this.delegate}";
   }

   @JvmStatic
   fun `computeIfAbsent$lambda$0`(`$block`: Function0, it: Any): Any {
      return `$block`.invoke();
   }

   @JvmStatic
   fun `computeIfAbsent$lambda$1`(`$tmp0`: Function1, p0: Any): Any {
      return (Value)`$tmp0`.invoke(p0);
   }

   fun ConcurrentMap() {
      this(0, 1, null);
   }
}
