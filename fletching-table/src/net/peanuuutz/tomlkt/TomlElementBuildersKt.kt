@file:SourceDebugExtension(["SMAP\nTomlElementBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TomlElementBuilders.kt\nnet/peanuuutz/tomlkt/TomlElementBuildersKt\n*L\n1#1,783:1\n53#1,7:784\n53#1,7:791\n380#1,7:798\n380#1,7:805\n53#1,7:812\n53#1,7:819\n380#1,7:826\n380#1,7:833\n*S KotlinDebug\n*F\n+ 1 TomlElementBuilders.kt\nnet/peanuuutz/tomlkt/TomlElementBuildersKt\n*L\n291#1:784,7\n304#1:791,7\n317#1:798,7\n330#1:805,7\n690#1:812,7\n706#1:819,7\n722#1:826,7\n738#1:833,7\n*E\n"])

package net.peanuuutz.tomlkt

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.util.Map.Entry
import kotlin.contracts.InvocationKind
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension

public inline fun buildTomlArray(initialCapacity: Int = 8, block: (TomlArrayBuilder) -> Unit): TomlArray {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var3: TomlArrayBuilder = new TomlArrayBuilder(initialCapacity);
   block.invoke(var3);
   return var3.build();
}

@JvmSynthetic
fun `buildTomlArray$default`(initialCapacity: Int, block: Function1, `$i$f$buildTomlArray`: Int, var3: Any): TomlArray {
   if ((`$i$f$buildTomlArray` and 1) != 0) {
      initialCapacity = 8;
   }

   var3 = new TomlArrayBuilder(initialCapacity);
   block.invoke(var3);
   return var3.build();
}

public fun TomlArrayBuilder.element(element: TomlElement, vararg elementAnnotations: Annotation) {
   `$this$element`.element(element, ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(boolean: Boolean, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(var1), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: Boolean, var2: java.util.List, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2);
}

public fun TomlArrayBuilder.literal(boolean: Boolean, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(var1), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(integer: Long, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(integer), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: Long, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var3);
}

public fun TomlArrayBuilder.literal(integer: Long, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(integer), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(float: Double, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(var1), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: Double, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var3);
}

public fun TomlArrayBuilder.literal(float: Double, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(var1), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(string: String, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(string), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: java.lang.String, var2: java.util.List, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2);
}

public fun TomlArrayBuilder.literal(string: String, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(string), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(localDateTime: LocalDateTime, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(localDateTime), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: LocalDateTime, var2: java.util.List, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2);
}

public fun TomlArrayBuilder.literal(localDateTime: LocalDateTime, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(localDateTime), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(offsetDateTime: OffsetDateTime, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(offsetDateTime), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: OffsetDateTime, var2: java.util.List, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2);
}

public fun TomlArrayBuilder.literal(offsetDateTime: OffsetDateTime, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(offsetDateTime), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(localDate: LocalDate, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(localDate), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: LocalDate, var2: java.util.List, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2);
}

public fun TomlArrayBuilder.literal(localDate: LocalDate, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(localDate), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.literal(localTime: LocalTime, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(localTime), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlArrayBuilder, var1: LocalTime, var2: java.util.List, var3: Int, var4: Any) {
   if ((var3 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2);
}

public fun TomlArrayBuilder.literal(localTime: LocalTime, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(TomlElementKt.TomlLiteral(localTime), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.array(elementAnnotations: List<Annotation> = CollectionsKt.emptyList(), block: (TomlArrayBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var5: TomlArrayBuilder = new TomlArrayBuilder(8);
   block.invoke(var5);
   `$this$array`.element(var5.build(), elementAnnotations);
}

@JvmSynthetic
fun `array$default`(var0: TomlArrayBuilder, var1: java.util.List, var2: Function1, var3: Int, var4: Any) {
   if ((var3 and 1) != 0) {
      var1 = CollectionsKt.emptyList();
   }

   array(var0, var1, var2);
}

public fun TomlArrayBuilder.array(vararg elementAnnotations: Annotation, block: (TomlArrayBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var5: TomlArrayBuilder = new TomlArrayBuilder(8);
   block.invoke(var5);
   `$this$array`.element(var5.build(), ArraysKt.asList(elementAnnotations));
}

public fun TomlArrayBuilder.table(elementAnnotations: List<Annotation> = CollectionsKt.emptyList(), block: (TomlTableBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var5: TomlTableBuilder = new TomlTableBuilder(8);
   block.invoke(var5);
   `$this$table`.element(var5.build(), elementAnnotations);
}

@JvmSynthetic
fun `table$default`(var0: TomlArrayBuilder, var1: java.util.List, var2: Function1, var3: Int, var4: Any) {
   if ((var3 and 1) != 0) {
      var1 = CollectionsKt.emptyList();
   }

   table(var0, var1, var2);
}

public fun TomlArrayBuilder.table(vararg elementAnnotations: Annotation, block: (TomlTableBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var5: TomlTableBuilder = new TomlTableBuilder(8);
   block.invoke(var5);
   `$this$table`.element(var5.build(), ArraysKt.asList(elementAnnotations));
}

public inline fun buildTomlTable(initialCapacity: Int = 8, block: (TomlTableBuilder) -> Unit): TomlTable {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var3: TomlTableBuilder = new TomlTableBuilder(initialCapacity);
   block.invoke(var3);
   return var3.build();
}

@JvmSynthetic
fun `buildTomlTable$default`(initialCapacity: Int, block: Function1, `$i$f$buildTomlTable`: Int, var3: Any): TomlTable {
   if ((`$i$f$buildTomlTable` and 1) != 0) {
      initialCapacity = 8;
   }

   var3 = new TomlTableBuilder(initialCapacity);
   block.invoke(var3);
   return var3.build();
}

public fun TomlTableBuilder.element(key: Any?, element: TomlElement, vararg elementAnnotations: Annotation) {
   `$this$element`.element(key, element, ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, boolean: Boolean, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(var2), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: Boolean, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var3);
}

public fun TomlTableBuilder.literal(key: Any?, boolean: Boolean, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(var2), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, integer: Long, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(integer), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: Long, var4: java.util.List, var5: Int, var6: Any) {
   if ((var5 and 4) != 0) {
      var4 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var4);
}

public fun TomlTableBuilder.literal(key: Any?, integer: Long, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(integer), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, float: Double, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(var2), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: Double, var4: java.util.List, var5: Int, var6: Any) {
   if ((var5 and 4) != 0) {
      var4 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var4);
}

public fun TomlTableBuilder.literal(key: Any?, float: Double, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(var2), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, string: String, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(string), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: java.lang.String, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var3);
}

public fun TomlTableBuilder.literal(key: Any?, string: String, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(string), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, localDateTime: LocalDateTime, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(localDateTime), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: LocalDateTime, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var3);
}

public fun TomlTableBuilder.literal(key: Any?, localDateTime: LocalDateTime, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(localDateTime), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, offsetDateTime: OffsetDateTime, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(offsetDateTime), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: OffsetDateTime, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var3);
}

public fun TomlTableBuilder.literal(key: Any?, offsetDateTime: OffsetDateTime, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(offsetDateTime), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, localDate: LocalDate, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(localDate), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: LocalDate, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var3);
}

public fun TomlTableBuilder.literal(key: Any?, localDate: LocalDate, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(localDate), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.literal(key: Any?, localTime: LocalTime, elementAnnotations: List<Annotation> = CollectionsKt.emptyList()) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(localTime), elementAnnotations);
}

@JvmSynthetic
fun `literal$default`(var0: TomlTableBuilder, var1: Any, var2: LocalTime, var3: java.util.List, var4: Int, var5: Any) {
   if ((var4 and 4) != 0) {
      var3 = CollectionsKt.emptyList();
   }

   literal(var0, var1, var2, var3);
}

public fun TomlTableBuilder.literal(key: Any?, localTime: LocalTime, vararg elementAnnotations: Annotation) {
   `$this$literal`.element(key, TomlElementKt.TomlLiteral(localTime), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.array(key: Any?, elementAnnotations: List<Annotation> = CollectionsKt.emptyList(), block: (TomlArrayBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var6: TomlArrayBuilder = new TomlArrayBuilder(8);
   block.invoke(var6);
   `$this$array`.element(key, var6.build(), elementAnnotations);
}

@JvmSynthetic
fun `array$default`(var0: TomlTableBuilder, var1: Any, var2: java.util.List, var3: Function1, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   array(var0, var1, var2, var3);
}

public fun TomlTableBuilder.array(key: Any?, vararg elementAnnotations: Annotation, block: (TomlArrayBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var6: TomlArrayBuilder = new TomlArrayBuilder(8);
   block.invoke(var6);
   `$this$array`.element(key, var6.build(), ArraysKt.asList(elementAnnotations));
}

public fun TomlTableBuilder.table(key: Any?, elementAnnotations: List<Annotation> = CollectionsKt.emptyList(), block: (TomlTableBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var6: TomlTableBuilder = new TomlTableBuilder(8);
   block.invoke(var6);
   `$this$table`.element(key, var6.build(), elementAnnotations);
}

@JvmSynthetic
fun `table$default`(var0: TomlTableBuilder, var1: Any, var2: java.util.List, var3: Function1, var4: Int, var5: Any) {
   if ((var4 and 2) != 0) {
      var2 = CollectionsKt.emptyList();
   }

   table(var0, var1, var2, var3);
}

public fun TomlTableBuilder.table(key: Any?, vararg elementAnnotations: Annotation, block: (TomlTableBuilder) -> Unit) {
   contract {
      callsInPlace(block, InvocationKind.EXACTLY_ONCE)
   }

   val var6: TomlTableBuilder = new TomlTableBuilder(8);
   block.invoke(var6);
   `$this$table`.element(key, var6.build(), ArraysKt.asList(elementAnnotations));
}

private fun alignElementAnnotations(elements: List<TomlElement>, annotations: List<List<Annotation>>): List<List<Annotation>> {
   val elementCount: Int = elements.size();
   val annotationsCount: Int = annotations.size();
   val var10000: java.util.List;
   if (elementCount == annotationsCount) {
      var10000 = annotations;
   } else if (elementCount > annotationsCount) {
      val var4: java.util.List = CollectionsKt.createListBuilder(elementCount);
      val `$this$alignElementAnnotations_u24lambda_u241`: java.util.List = var4;
      var4.addAll(annotations);
      val var7: Int = elementCount - annotationsCount;

      for (int var8 = 0; var8 < var7; var8++) {
         `$this$alignElementAnnotations_u24lambda_u241`.add(CollectionsKt.emptyList());
      }

      var10000 = CollectionsKt.build(var4);
   } else {
      var10000 = annotations.subList(0, elementCount);
   }

   return var10000;
}

private fun <V> Map<*, V>.toTomlMap(): Map<String, V> {
   val size: Int = `$this$toTomlMap`.size();
   var var10000: java.util.Map;
   switch (size) {
      case 0:
         var10000 = MapsKt.emptyMap();
         break;
      case 1:
         val var9: Entry = `$this$toTomlMap`.entrySet().iterator().next() as Entry;
         var10000 = MapsKt.mapOf(TuplesKt.to(TomlElementKt.toTomlKey(var9.getKey()), var9.getValue()));
         break;
      default:
         val var2: java.util.Map = MapsKt.createMapBuilder(size);
         val `$this$toTomlMap_u24lambda_u242`: java.util.Map = var2;

         for (Entry var6 : $this$toTomlMap.entrySet()) {
            `$this$toTomlMap_u24lambda_u242`.put(TomlElementKt.toTomlKey(var6.getKey()), var6.getValue());
         }

         var10000 = MapsKt.build(var2);
   }

   return var10000;
}

@JvmSynthetic
fun `access$alignElementAnnotations`(elements: java.util.List, annotations: java.util.List): java.util.List {
   return alignElementAnnotations(elements, annotations);
}

@JvmSynthetic
fun `access$toTomlMap`(`$receiver`: java.util.Map): java.util.Map {
   return toTomlMap(`$receiver`);
}
