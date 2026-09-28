package io.ktor.util

import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nStringValues.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValuesImpl\n+ 2 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,524:1\n216#2:525\n217#2:527\n1#3:526\n*S KotlinDebug\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValuesImpl\n*L\n187#1:525\n187#1:527\n*E\n"])
public open class StringValuesImpl(caseInsensitiveName: Boolean = false, values: Map<String, List<String>> = MapsKt.emptyMap()) : StringValues {
   public final val caseInsensitiveName: Boolean
   protected final val values: Map<String, List<String>>

   init {
      this.caseInsensitiveName = caseInsensitiveName;
      val newMap: java.util.Map = if (this.caseInsensitiveName) CollectionsKt.caseInsensitiveMap() else new LinkedHashMap();

      for (java.util.Map.Entry element$iv : values.entrySet()) {
         val key: java.lang.String = `element$iv`.getKey() as java.lang.String;
         val value: java.util.List = `element$iv`.getValue() as java.util.List;
         val var13: Int = value.size();
         val var14: ArrayList = new ArrayList(var13);

         for (int var15 = 0; var15 < var13; var15++) {
            var14.add(value.get(var15) as java.lang.String);
         }

         newMap.put(key, var14);
      }

      this.values = newMap;
   }

   public override operator fun get(name: String): String? {
      val var10000: java.util.List = this.listForKey(name);
      return if (var10000 != null) kotlin.collections.CollectionsKt.firstOrNull(var10000) else null;
   }

   public override fun getAll(name: String): List<String>? {
      return this.listForKey(name);
   }

   public override operator fun contains(name: String): Boolean {
      return this.listForKey(name) != null;
   }

   public override fun contains(name: String, value: String): Boolean {
      val var10000: java.util.List = this.listForKey(name);
      return var10000 != null && var10000.contains(value);
   }

   public override fun names(): Set<String> {
      return CollectionsJvmKt.unmodifiable(this.values.keySet());
   }

   public override fun isEmpty(): Boolean {
      return this.values.isEmpty();
   }

   public override fun entries(): Set<kotlin.collections.Map.Entry<String, List<String>>> {
      return CollectionsJvmKt.unmodifiable(this.values.entrySet());
   }

   public override fun forEach(body: (String, List<String>) -> Unit) {
      for (java.util.Map.Entry var3 : this.values.entrySet()) {
         body.invoke(var3.getKey() as java.lang.String, var3.getValue() as java.util.List);
      }
   }

   private fun listForKey(name: String): List<String>? {
      return this.values.get(name);
   }

   public override fun toString(): String {
      return "StringValues(case=${!this.caseInsensitiveName}) ${this.entries()}";
   }

   public override operator fun equals(other: Any?): Boolean {
      if (this === other) {
         return true;
      } else if (other !is StringValues) {
         return false;
      } else {
         return this.caseInsensitiveName == (other as StringValues).getCaseInsensitiveName()
            && StringValuesKt.access$entriesEquals(this.entries(), (other as StringValues).entries());
      }
   }

   public override fun hashCode(): Int {
      return StringValuesKt.access$entriesHashCode(this.entries(), 31 * java.lang.Boolean.hashCode(this.caseInsensitiveName));
   }

   open fun StringValuesImpl() {
      this(false, null, 3, null);
   }
}
