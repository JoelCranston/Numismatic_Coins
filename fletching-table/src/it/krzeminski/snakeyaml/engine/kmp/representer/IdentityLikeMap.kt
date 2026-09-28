package it.krzeminski.snakeyaml.engine.kmp.representer

import it.krzeminski.snakeyaml.engine.kmp.internal.IdentityHashCode
import it.krzeminski.snakeyaml.engine.kmp.internal.SystemKt
import java.util.HashMap
import kotlin.collections.MutableMap.MutableEntry
import kotlin.jvm.internal.markers.KMutableMap

private open class IdentityLikeMap<T> private constructor(contents: MutableMap<Any?, Any>) : java.util.Map<Object, T>, KMutableMap {
   private final val contents: MutableMap<Any?, Any>
   public open val entries: MutableSet<MutableEntry<Any?, Any>>
   public open val keys: MutableSet<Any?>
   public open val size: Int
   public open val values: MutableCollection<Any>

   init {
      this.contents = contents;
   }

   public constructor() : this(new HashMap<>())
   public override fun containsKey(key: Any?): Boolean {
      return this.contents.containsKey(this.toIdentityKey(key));
   }

   public override operator fun get(key: Any?): Any? {
      return this.contents.get(this.toIdentityKey(key));
   }

   public override fun put(key: Any?, value: Any): Any? {
      return this.contents.put(this.toIdentityKey(key), (T)value);
   }

   private fun Any?.toIdentityKey(): Any? {
      return if (SystemKt.hasIdentityHashCode(`$this$toIdentityKey`))
         IdentityHashCode.box-impl(SystemKt.identityHashCode(`$this$toIdentityKey`))
         else
         `$this$toIdentityKey`;
   }

   public override fun remove(key: Any?): Any? {
      return this.contents.remove(key);
   }

   public override fun putAll(from: Map<out Any?, Any>) {
      this.contents.putAll(from);
   }

   public override fun clear() {
      this.contents.clear();
   }

   public override fun isEmpty(): Boolean {
      return this.contents.isEmpty();
   }

   public override fun containsValue(value: Any): Boolean {
      return this.contents.containsValue(value);
   }
}
