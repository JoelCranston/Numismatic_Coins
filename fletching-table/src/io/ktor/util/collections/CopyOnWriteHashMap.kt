package io.ktor.util.collections

import io.ktor.utils.io.InternalAPI
import java.util.HashMap
import kotlin.jvm.internal.SourceDebugExtension

@InternalAPI
@SourceDebugExtension(["SMAP\nCopyOnWriteHashMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CopyOnWriteHashMap.kt\nio/ktor/util/collections/CopyOnWriteHashMap\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"])
public class CopyOnWriteHashMap<K, V> {
   public fun put(key: Any, value: Any): Any? {
      val old: java.util.Map;
      val copy: HashMap;
      do {
         old = this.current as java.util.Map;
         if ((this.current as java.util.Map).get(key) === value) {
            return (V)value;
         }

         copy = new HashMap(old);
      } while (!current$FU.compareAndSet(this, old, copy));

      return (V)copy.put(key, value);
   }

   public operator fun get(key: Any): Any? {
      return (V)(this.current as java.util.Map).get(key);
   }

   public operator fun set(key: Any, value: Any) {
      this.put((K)key, (V)value);
   }

   public fun remove(key: Any): Any? {
      val old: java.util.Map;
      val copy: HashMap;
      do {
         old = this.current as java.util.Map;
         if ((this.current as java.util.Map).get(key) == null) {
            return null;
         }

         copy = new HashMap(old);
      } while (!current$FU.compareAndSet(this, old, copy));

      return (V)copy.remove(key);
   }

   public fun computeIfAbsent(key: Any, producer: (Any) -> Any): Any {
      val old: java.util.Map;
      val newValue: Any;
      var var8: HashMap;
      do {
         old = this.current as java.util.Map;
         var8 = (HashMap)(this.current as java.util.Map).get(key);
         if (var8 != null) {
            return (V)var8;
         }

         var8 = new HashMap(old);
         newValue = producer.invoke(key);
         var8.put(key, newValue);
      } while (!current$FU.compareAndSet(this, old, copy));

      return (V)newValue;
   }
}
