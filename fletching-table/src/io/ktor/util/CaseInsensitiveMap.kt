package io.ktor.util

import java.util.LinkedHashMap
import kotlin.collections.MutableMap.MutableEntry
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.markers.KMutableMap

@SourceDebugExtension(["SMAP\nCaseInsensitiveMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaseInsensitiveMap.kt\nio/ktor/util/CaseInsensitiveMap\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,80:1\n216#2,2:81\n*S KotlinDebug\n*F\n+ 1 CaseInsensitiveMap.kt\nio/ktor/util/CaseInsensitiveMap\n*L\n32#1:81,2\n*E\n"])
public class CaseInsensitiveMap<Value> : java.util.Map<java.lang.String, Value>, KMutableMap {
   private final val delegate: MutableMap<CaseInsensitiveString, Any> = (new LinkedHashMap()) as java.util.Map

   public open val size: Int
      public open get() {
         return this.delegate.size();
      }


   public open val keys: MutableSet<String>
      public open get() {
         return (new DelegatingMutableSet<>(this.delegate.keySet(), CaseInsensitiveMap::_get_keys_$lambda$0, CaseInsensitiveMap::_get_keys_$lambda$1)) as MutableSet<java.lang.String>;
      }


   public open val entries: MutableSet<MutableEntry<String, Any>>
      public open get() {
         return (new DelegatingMutableSet<>(this.delegate.entrySet(), CaseInsensitiveMap::_get_entries_$lambda$0, CaseInsensitiveMap::_get_entries_$lambda$1)) as MutableSet<MutableMap.MutableEntry<java.lang.String, Value>>;
      }


   public open val values: MutableCollection<Any>
      public open get() {
         return this.delegate.values();
      }


   public open fun containsKey(key: String): Boolean {
      return this.delegate.containsKey(new CaseInsensitiveString(key));
   }

   public override fun containsValue(value: Any): Boolean {
      return value != null && this.delegate.containsValue(value);
   }

   public open operator fun get(key: String): Any? {
      return this.delegate.get(TextKt.caseInsensitive(key));
   }

   public override fun isEmpty(): Boolean {
      return this.delegate.isEmpty();
   }

   public override fun clear() {
      this.delegate.clear();
   }

   public open fun put(key: String, value: Any): Any? {
      return this.delegate.put(TextKt.caseInsensitive(key), (Value)value);
   }

   public override fun putAll(from: Map<out String, Any>) {
      for (java.util.Map.Entry element$iv : from.entrySet()) {
         this.put(`element$iv`.getKey() as java.lang.String, (Value)`element$iv`.getValue());
      }
   }

   public open fun remove(key: String): Any? {
      return this.delegate.remove(TextKt.caseInsensitive(key));
   }

   public override operator fun equals(other: Any?): Boolean {
      return other != null && other is CaseInsensitiveMap && (other as CaseInsensitiveMap).delegate == this.delegate;
   }

   public override fun hashCode(): Int {
      return this.delegate.hashCode();
   }

   @JvmStatic
   fun CaseInsensitiveString.`_get_keys_$lambda$0`(): java.lang.String {
      return `$this$DelegatingMutableSet`.getContent();
   }

   @JvmStatic
   fun java.lang.String.`_get_keys_$lambda$1`(): CaseInsensitiveString {
      return TextKt.caseInsensitive(`$this$DelegatingMutableSet`);
   }

   @JvmStatic
   fun java.util.Map.Entry.`_get_entries_$lambda$0`(): java.util.Map.Entry {
      return new io.ktor.util.Entry<>(
         (`$this$DelegatingMutableSet`.getKey() as CaseInsensitiveString).getContent(), (Value)`$this$DelegatingMutableSet`.getValue()
      );
   }

   @JvmStatic
   fun java.util.Map.Entry.`_get_entries_$lambda$1`(): java.util.Map.Entry {
      return new io.ktor.util.Entry<>(
         TextKt.caseInsensitive(`$this$DelegatingMutableSet`.getKey() as java.lang.String), (Value)`$this$DelegatingMutableSet`.getValue()
      );
   }
}
