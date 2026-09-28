package io.ktor.util

import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.internal.SourceDebugExtension

@SourceDebugExtension(["SMAP\nStringValues.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValuesBuilderImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,524:1\n1869#2,2:525\n774#2:527\n865#2,2:528\n536#3:530\n521#3,6:531\n*S KotlinDebug\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValuesBuilderImpl\n*L\n272#1:525,2\n280#1:527\n280#1:528,2\n288#1:530\n288#1:531,6\n*E\n"])
public open class StringValuesBuilderImpl(caseInsensitiveName: Boolean = false, size: Int = 8) : StringValuesBuilder {
   public final val caseInsensitiveName: Boolean
   protected final val values: MutableMap<String, MutableList<String>>

   init {
      this.caseInsensitiveName = caseInsensitiveName;
      this.values = (java.util.Map<java.lang.String, java.util.List<java.lang.String>>)(if (this.caseInsensitiveName)
         CollectionsKt.caseInsensitiveMap()
         else
         new LinkedHashMap<>(size));
   }

   public override fun getAll(name: String): List<String>? {
      return this.values.get(name);
   }

   public override operator fun contains(name: String): Boolean {
      return this.values.containsKey(name);
   }

   public override fun contains(name: String, value: String): Boolean {
      val var10000: java.util.List = this.values.get(name);
      return var10000 != null && var10000.contains(value);
   }

   public override fun names(): Set<String> {
      return this.values.keySet();
   }

   public override fun isEmpty(): Boolean {
      return this.values.isEmpty();
   }

   public override fun entries(): Set<kotlin.collections.Map.Entry<String, List<String>>> {
      return CollectionsJvmKt.unmodifiable(this.values.entrySet());
   }

   public override operator fun set(name: String, value: String) {
      this.validateValue(value);
      val list: java.util.List = this.ensureListForKey(name);
      list.clear();
      list.add(value);
   }

   public override operator fun get(name: String): String? {
      val var10000: java.util.List = this.getAll(name);
      return if (var10000 != null) kotlin.collections.CollectionsKt.firstOrNull(var10000) else null;
   }

   public override fun append(name: String, value: String) {
      this.validateValue(value);
      this.ensureListForKey(name).add(value);
   }

   public override fun appendAll(stringValues: StringValues) {
      stringValues.forEach(StringValuesBuilderImpl::appendAll$lambda$0);
   }

   public override fun appendMissing(stringValues: StringValues) {
      stringValues.forEach(StringValuesBuilderImpl::appendMissing$lambda$0);
   }

   public override fun appendAll(name: String, values: Iterable<String>) {
      for (Object element$iv : values) {
         this.validateValue(`element$iv` as java.lang.String);
      }

      kotlin.collections.CollectionsKt.addAll(this.ensureListForKey(name), values);
   }

   public override fun appendMissing(name: String, values: Iterable<String>) {
      var var16: java.util.Set;
      label25: {
         val var10000: java.util.List = this.values.get(name);
         if (var10000 != null) {
            var16 = kotlin.collections.CollectionsKt.toSet(var10000);
            if (var16 != null) {
               break label25;
            }
         }

         var16 = SetsKt.emptySet();
      }

      val existing: java.util.Set = var16;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : values) {
         if (!existing.contains(`element$iv$iv` as java.lang.String)) {
            `destination$iv$iv`.add(`element$iv$iv`);
         }
      }

      this.appendAll(name, `destination$iv$iv`);
   }

   public override fun remove(name: String) {
      this.values.remove(name);
   }

   public override fun removeKeysWithNoEntries() {
      val `$this$filter$iv`: java.util.Map = this.values;
      val `destination$iv$iv`: java.util.Map = new LinkedHashMap();

      for (java.util.Map.Entry element$iv$iv : $this$filter$iv.entrySet()) {
         if ((`element$iv$iv`.getValue() as java.util.List).isEmpty()) {
            `destination$iv$iv`.put(`element$iv$iv`.getKey(), `element$iv$iv`.getValue());
         }
      }

      val var1: java.util.Iterator = `destination$iv$iv`.entrySet().iterator();

      while (var1.hasNext()) {
         this.remove((var1.next() as java.util.Map.Entry).getKey() as java.lang.String);
      }
   }

   public override fun remove(name: String, value: String): Boolean {
      val var10000: java.util.List = this.values.get(name);
      return var10000 != null && var10000.remove(value);
   }

   public override fun clear() {
      this.values.clear();
   }

   public override fun build(): StringValues {
      return new StringValuesImpl(this.caseInsensitiveName, this.values);
   }

   protected open fun validateName(name: String) {
   }

   protected open fun validateValue(value: String) {
   }

   private fun ensureListForKey(name: String): MutableList<String> {
      var var10000: java.util.List = this.values.get(name);
      if (var10000 == null) {
         val var2: java.util.List = new ArrayList();
         this.validateName(name);
         this.values.put(name, var2);
         var10000 = var2;
      }

      return var10000;
   }

   @JvmStatic
   fun `appendAll$lambda$0`(`this$0`: StringValuesBuilderImpl, name: java.lang.String, values: java.util.List): Unit {
      `this$0`.appendAll(name, values);
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `appendMissing$lambda$0`(`this$0`: StringValuesBuilderImpl, name: java.lang.String, values: java.util.List): Unit {
      `this$0`.appendMissing(name, values);
      return Unit.INSTANCE;
   }

   open fun StringValuesBuilderImpl() {
      this(false, 0, 3, null);
   }
}
