@file:SourceDebugExtension(["SMAP\nStringValues.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValuesKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,524:1\n1869#2,2:525\n1252#2,4:527\n1374#2:531\n1460#2,2:532\n1563#2:534\n1634#2,3:535\n1462#2,3:538\n1869#2:541\n865#2,2:542\n1870#2:544\n1869#2,2:545\n1869#2,2:555\n865#2,2:557\n13805#3,2:547\n13805#3,2:549\n216#4,2:551\n216#4,2:553\n*S KotlinDebug\n*F\n+ 1 StringValues.kt\nio/ktor/util/StringValuesKt\n*L\n364#1:525,2\n374#1:527,4\n381#1:531\n381#1:532,2\n382#1:534\n382#1:535,3\n381#1:538,3\n406#1:541\n407#1:542,2\n406#1:544\n442#1:545,2\n391#1:555,2\n429#1:557,2\n476#1:547,2\n489#1:549,2\n502#1:551,2\n514#1:553,2\n*E\n"])

package io.ktor.util

import java.util.ArrayList
import java.util.LinkedHashMap
import kotlin.jvm.functions.Function2
import kotlin.jvm.internal.SourceDebugExtension

public fun valuesOf(vararg pairs: Pair<String, List<String>>, caseInsensitiveKey: Boolean = false): StringValues {
   return new StringValuesImpl(caseInsensitiveKey, MapsKt.toMap(ArraysKt.asList(pairs)));
}

@JvmSynthetic
fun `valuesOf$default`(var0: Array<Pair>, var1: Boolean, var2: Int, var3: Any): StringValues {
   if ((var2 and 2) != 0) {
      var1 = false;
   }

   return valuesOf(var0, var1);
}

public fun valuesOf(name: String, value: String, caseInsensitiveKey: Boolean = false): StringValues {
   return new StringValuesSingleImpl(caseInsensitiveKey, name, kotlin.collections.CollectionsKt.listOf(value));
}

@JvmSynthetic
fun `valuesOf$default`(var0: java.lang.String, var1: java.lang.String, var2: Boolean, var3: Int, var4: Any): StringValues {
   if ((var3 and 4) != 0) {
      var2 = false;
   }

   return valuesOf(var0, var1, var2);
}

public fun valuesOf(name: String, values: List<String>, caseInsensitiveKey: Boolean = false): StringValues {
   return new StringValuesSingleImpl(caseInsensitiveKey, name, values);
}

@JvmSynthetic
fun `valuesOf$default`(var0: java.lang.String, var1: java.util.List, var2: Boolean, var3: Int, var4: Any): StringValues {
   if ((var3 and 4) != 0) {
      var2 = false;
   }

   return valuesOf(var0, var1, var2);
}

public fun valuesOf(): StringValues {
   return StringValues.Companion.getEmpty();
}

public fun valuesOf(map: Map<String, Iterable<String>>, caseInsensitiveKey: Boolean = false): StringValues {
   val size: Int = map.size();
   if (size == 1) {
      val var10: java.util.Map.Entry = kotlin.collections.CollectionsKt.single(map.entrySet());
      return new StringValuesSingleImpl(
         caseInsensitiveKey, var10.getKey() as java.lang.String, kotlin.collections.CollectionsKt.toList(var10.getValue() as MutableIterable<java.lang.String>)
      );
   } else {
      val values: java.util.Map = if (caseInsensitiveKey) CollectionsKt.caseInsensitiveMap() else new LinkedHashMap(size);

      val `$this$forEach$iv`: java.lang.Iterable;
      for (Object element$iv : $this$forEach$iv) {
         values.put(
            (`element$iv` as java.util.Map.Entry).getKey(),
            kotlin.collections.CollectionsKt.toList((`element$iv` as java.util.Map.Entry).getValue() as java.lang.Iterable)
         );
      }

      return new StringValuesImpl(caseInsensitiveKey, values);
   }
}

@JvmSynthetic
fun `valuesOf$default`(var0: java.util.Map, var1: Boolean, var2: Int, var3: Any): StringValues {
   if ((var2 and 2) != 0) {
      var1 = false;
   }

   return valuesOf(var0, var1);
}

public fun StringValues.toMap(): Map<String, List<String>> {
   val `$this$associateByTo$iv`: java.lang.Iterable = `$this$toMap`.entries();
   val `destination$iv`: java.util.Map = new LinkedHashMap();

   for (Object element$iv : $this$associateByTo$iv) {
      `destination$iv`.put(
         (`element$iv` as java.util.Map.Entry).getKey() as java.lang.String,
         kotlin.collections.CollectionsKt.toList((`element$iv` as java.util.Map.Entry).getValue() as java.lang.Iterable)
      );
   }

   return `destination$iv`;
}

public fun StringValues.flattenEntries(): List<Pair<String, String>> {
   val `$this$flatMap$iv`: java.lang.Iterable = `$this$flattenEntries`.entries();
   val `destination$iv$iv`: java.util.Collection = new ArrayList();

   for (Object element$iv$iv : $this$flatMap$iv) {
      val `list$iv$iv`: java.util.Map.Entry = `element$iv$iv` as java.util.Map.Entry;
      val `$this$map$iv`: java.lang.Iterable = (`element$iv$iv` as java.util.Map.Entry).getValue() as java.lang.Iterable;
      val `destination$iv$ivx`: java.util.Collection = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(`$this$map$iv`, 10));

      for (Object item$iv$iv : $this$map$iv) {
         `destination$iv$ivx`.add(TuplesKt.to(`list$iv$iv`.getKey(), `item$iv$iv` as java.lang.String));
      }

      kotlin.collections.CollectionsKt.addAll(`destination$iv$iv`, `destination$iv$ivx` as java.util.List);
   }

   return `destination$iv$iv` as MutableList<Pair<java.lang.String, java.lang.String>>;
}

public fun StringValues.flattenForEach(block: (String, String) -> Unit) {
   `$this$flattenForEach`.forEach(StringValuesKt::flattenForEach$lambda$0);
}

public fun StringValues.filter(keepEmpty: Boolean = false, predicate: (String, String) -> Boolean): StringValues {
   val values: java.util.Map = if (`$this$filter`.getCaseInsensitiveName())
      CollectionsKt.caseInsensitiveMap()
      else
      new LinkedHashMap(`$this$filter`.entries().size());

   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      val entry: java.util.Map.Entry = `element$iv` as java.util.Map.Entry;
      val `$this$filterTo$iv`: java.lang.Iterable = (`element$iv` as java.util.Map.Entry).getValue() as java.lang.Iterable;
      val `destination$iv`: java.util.Collection = new ArrayList(((`element$iv` as java.util.Map.Entry).getValue() as java.util.List).size());

      for (Object element$ivx : $this$filterTo$iv) {
         if (predicate.invoke(entry.getKey(), `element$ivx` as java.lang.String) as java.lang.Boolean) {
            `destination$iv`.add(`element$ivx`);
         }
      }

      val list: ArrayList = `destination$iv` as ArrayList;
      if (keepEmpty || !(`destination$iv` as ArrayList).isEmpty()) {
         values.put(entry.getKey(), list);
      }
   }

   return new StringValuesImpl(`$this$filter`.getCaseInsensitiveName(), values);
}

@JvmSynthetic
fun `filter$default`(var0: StringValues, var1: Boolean, var2: Function2, var3: Int, var4: Any): StringValues {
   if ((var3 and 1) != 0) {
      var1 = false;
   }

   return filter(var0, var1, var2);
}

public fun StringValuesBuilder.appendFiltered(source: StringValues, keepEmpty: Boolean = false, predicate: (String, String) -> Boolean) {
   source.forEach(StringValuesKt::appendFiltered$lambda$0);
}

@JvmSynthetic
fun `appendFiltered$default`(var0: StringValuesBuilder, var1: StringValues, var2: Boolean, var3: Function2, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = false;
   }

   appendFiltered(var0, var1, var2, var3);
}

public fun StringValuesBuilder.appendAll(builder: StringValuesBuilder): StringValuesBuilder {
   val `$this$appendAll_u24lambda_u240`: StringValuesBuilder = `$this$appendAll`;

   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      `$this$appendAll_u24lambda_u240`.appendAll(
         (`element$iv` as java.util.Map.Entry).getKey() as java.lang.String, (`element$iv` as java.util.Map.Entry).getValue() as java.util.List
      );
   }

   return `$this$appendAll`;
}

public fun StringValuesBuilder.appendIfNameAbsent(name: String, value: String): StringValuesBuilder {
   if (!`$this$appendIfNameAbsent`.contains(name)) {
      `$this$appendIfNameAbsent`.append(name, value);
   }

   return `$this$appendIfNameAbsent`;
}

public fun StringValuesBuilder.appendIfNameAndValueAbsent(name: String, value: String): StringValuesBuilder {
   if (!`$this$appendIfNameAndValueAbsent`.contains(name, value)) {
      `$this$appendIfNameAndValueAbsent`.append(name, value);
   }

   return `$this$appendIfNameAndValueAbsent`;
}

public fun StringValuesBuilder.appendAll(vararg values: Pair<String, String>): StringValuesBuilder {
   val `$this$appendAll_u24lambda_u241`: StringValuesBuilder = `$this$appendAll`;

   for (Object element$iv : values) {
      `$this$appendAll_u24lambda_u241`.append(((Pair)`element$iv`).component1() as java.lang.String, ((Pair)`element$iv`).component2() as java.lang.String);
   }

   return `$this$appendAll`;
}

@JvmName(name = "appendAllIterable")
public fun StringValuesBuilder.appendAll(vararg values: Pair<String, Iterable<String>>): StringValuesBuilder {
   val `$this$appendAll_u24lambda_u242`: StringValuesBuilder = `$this$appendAll`;

   for (Object element$iv : values) {
      `$this$appendAll_u24lambda_u242`.appendAll(
         ((Pair)`element$iv`).component1() as java.lang.String, ((Pair)`element$iv`).component2() as MutableIterable<java.lang.String>
      );
   }

   return `$this$appendAll`;
}

@JvmName(name = "appendAllIterable")
public fun StringValuesBuilder.appendAll(values: Map<String, Iterable<String>>): StringValuesBuilder {
   val `$this$appendAll_u24lambda_u243`: StringValuesBuilder = `$this$appendAll`;

   for (java.util.Map.Entry element$iv : values.entrySet()) {
      `$this$appendAll_u24lambda_u243`.appendAll(`element$iv`.getKey() as java.lang.String, `element$iv`.getValue() as MutableIterable<java.lang.String>);
   }

   return `$this$appendAll`;
}

public fun StringValuesBuilder.appendAll(values: Map<String, String>): StringValuesBuilder {
   val `$this$appendAll_u24lambda_u244`: StringValuesBuilder = `$this$appendAll`;

   for (java.util.Map.Entry element$iv : values.entrySet()) {
      `$this$appendAll_u24lambda_u244`.append(`element$iv`.getKey() as java.lang.String, `element$iv`.getValue() as java.lang.String);
   }

   return `$this$appendAll`;
}

private fun entriesEquals(a: Set<kotlin.collections.Map.Entry<String, List<String>>>, b: Set<kotlin.collections.Map.Entry<String, List<String>>>): Boolean {
   return a == b;
}

private fun entriesHashCode(entries: Set<kotlin.collections.Map.Entry<String, List<String>>>, seed: Int): Int {
   return seed * 31 + entries.hashCode();
}

fun `flattenForEach$lambda$0`(`$block`: Function2, name: java.lang.String, items: java.util.List): Unit {
   val `$this$forEach$iv`: java.lang.Iterable;
   for (Object element$iv : $this$forEach$iv) {
      `$block`.invoke(name, `element$iv` as java.lang.String);
   }

   return Unit.INSTANCE;
}

fun `appendFiltered$lambda$0`(
   `$keepEmpty`: Boolean, `$this_appendFiltered`: StringValuesBuilder, `$predicate`: Function2, name: java.lang.String, value: java.util.List
): Unit {
   val `$this$filterTo$iv`: java.lang.Iterable = value;
   val `destination$iv`: java.util.Collection = new ArrayList(value.size());

   for (Object element$iv : $this$filterTo$iv) {
      if (`$predicate`.invoke(name, `element$iv` as java.lang.String) as java.lang.Boolean) {
         `destination$iv`.add(`element$iv`);
      }
   }

   val list: ArrayList = `destination$iv` as ArrayList;
   if (`$keepEmpty` || !(`destination$iv` as ArrayList).isEmpty()) {
      `$this_appendFiltered`.appendAll(name, list);
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$entriesHashCode`(entries: java.util.Set, seed: Int): Int {
   return entriesHashCode(entries, seed);
}

@JvmSynthetic
fun `access$entriesEquals`(a: java.util.Set, b: java.util.Set): Boolean {
   return entriesEquals(a, b);
}
