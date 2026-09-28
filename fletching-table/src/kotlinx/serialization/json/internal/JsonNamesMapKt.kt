@file:SourceDebugExtension(["SMAP\nJsonNamesMap.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,155:1\n808#2,11:156\n1761#2,3:170\n13472#3,2:167\n1#4:169\n*S KotlinDebug\n*F\n+ 1 JsonNamesMap.kt\nkotlinx/serialization/json/internal/JsonNamesMapKt\n*L\n35#1:156,11\n154#1:170,3\n35#1:167,2\n*E\n"])

package kotlinx.serialization.json.internal

import java.lang.annotation.Annotation
import java.util.ArrayList
import java.util.LinkedHashMap
import java.util.Locale
import kotlin.jvm.functions.Function0
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.SerialKind
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import kotlinx.serialization.json.JsonNames
import kotlinx.serialization.json.JsonNamingStrategy
import kotlinx.serialization.json.JsonSchemaCacheKt
import kotlinx.serialization.json.internal.DescriptorSchemaCache.Key
import kotlinx.serialization.json.internal.JsonNamesMapKt.tryCoerceValue.1

internal final val JsonDeserializationNamesKey: Key<Map<String, Int>> = new DescriptorSchemaCache.Key()
internal final val JsonSerializationNamesKey: Key<Array<String>> = new DescriptorSchemaCache.Key()

private fun SerialDescriptor.buildDeserializationNamesMap(json: Json): Map<String, Int> {
   val builder: java.util.Map = new LinkedHashMap();
   val useLowercaseEnums: Boolean = decodeCaseInsensitive(json, `$this$buildDeserializationNamesMap`);
   val strategyForClasses: JsonNamingStrategy = namingStrategy(`$this$buildDeserializationNamesMap`, json);
   var i: Int = 0;

   for (int var6 = $this$buildDeserializationNamesMap.getElementsCount(); i < var6; i++) {
      val `$this$filterIsInstance$iv`: java.lang.Iterable = `$this$buildDeserializationNamesMap`.getElementAnnotations(i);
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv : $this$filterIsInstance$iv) {
         if (name is JsonNames) {
            `destination$iv$iv`.add(name);
         }
      }

      val var10000: JsonNames = CollectionsKt.singleOrNull(`destination$iv$iv` as MutableList<JsonNames>);
      if (var10000 != null && var10000.names() != null) {
         val var17: Any;
         for (Object element$iv : var17) {
            val var10002: Any;
            if (useLowercaseEnums) {
               var10002 = var21.toLowerCase(Locale.ROOT);
            } else {
               var10002 = var21;
            }

            buildDeserializationNamesMap$putOrThrow(builder, `$this$buildDeserializationNamesMap`, (java.lang.String)var10002, i);
         }
      }

      val var23: java.lang.String;
      if (useLowercaseEnums) {
         var23 = `$this$buildDeserializationNamesMap`.getElementName(i).toLowerCase(Locale.ROOT);
      } else {
         var23 = if (strategyForClasses != null)
            strategyForClasses.serialNameForJson(`$this$buildDeserializationNamesMap`, i, `$this$buildDeserializationNamesMap`.getElementName(i))
            else
            null;
      }

      if (var23 != null) {
         buildDeserializationNamesMap$putOrThrow(builder, `$this$buildDeserializationNamesMap`, var23, i);
      }
   }

   return if (builder.isEmpty()) MapsKt.emptyMap() else builder;
}

internal fun Json.deserializationNamesMap(descriptor: SerialDescriptor): Map<String, Int> {
   return JsonSchemaCacheKt.getSchemaCache(`$this$deserializationNamesMap`)
      .getOrPut(descriptor, JsonDeserializationNamesKey, JsonNamesMapKt::deserializationNamesMap$lambda$3);
}

internal fun SerialDescriptor.serializationNamesIndices(json: Json, strategy: JsonNamingStrategy): Array<String> {
   return JsonSchemaCacheKt.getSchemaCache(json)
      .getOrPut(`$this$serializationNamesIndices`, JsonSerializationNamesKey, JsonNamesMapKt::serializationNamesIndices$lambda$4);
}

internal fun SerialDescriptor.getJsonElementName(json: Json, index: Int): String {
   val strategy: JsonNamingStrategy = namingStrategy(`$this$getJsonElementName`, json);
   return if (strategy == null)
      `$this$getJsonElementName`.getElementName(index)
      else
      serializationNamesIndices(`$this$getJsonElementName`, json, strategy)[index];
}

internal fun SerialDescriptor.namingStrategy(json: Json): JsonNamingStrategy? {
   return if (`$this$namingStrategy`.getKind() == StructureKind.CLASS.INSTANCE) json.getConfiguration().getNamingStrategy() else null;
}

private fun SerialDescriptor.getJsonNameIndexSlowPath(json: Json, name: String): Int {
   val var10000: Int = deserializationNamesMap(json, `$this$getJsonNameIndexSlowPath`).get(name);
   return var10000 ?: -3;
}

private fun Json.decodeCaseInsensitive(descriptor: SerialDescriptor): Boolean {
   return `$this$decodeCaseInsensitive`.getConfiguration().getDecodeEnumsCaseInsensitive() && descriptor.getKind() == SerialKind.ENUM.INSTANCE;
}

internal fun SerialDescriptor.getJsonNameIndex(json: Json, name: String): Int {
   if (decodeCaseInsensitive(json, `$this$getJsonNameIndex`)) {
      val var10002: java.lang.String = name.toLowerCase(Locale.ROOT);
      return getJsonNameIndexSlowPath(`$this$getJsonNameIndex`, json, var10002);
   } else if (namingStrategy(`$this$getJsonNameIndex`, json) != null) {
      return getJsonNameIndexSlowPath(`$this$getJsonNameIndex`, json, name);
   } else {
      val index: Int = `$this$getJsonNameIndex`.getElementIndex(name);
      if (index != -3) {
         return index;
      } else {
         return if (!json.getConfiguration().getUseAlternativeNames()) index else getJsonNameIndexSlowPath(`$this$getJsonNameIndex`, json, name);
      }
   }
}

internal fun SerialDescriptor.getJsonNameIndexOrThrow(json: Json, name: String, suffix: String = ""): Int {
   val index: Int = getJsonNameIndex(`$this$getJsonNameIndexOrThrow`, json, name);
   if (index == -3) {
      throw new SerializationException("${`$this$getJsonNameIndexOrThrow`.getSerialName()} does not contain element with name '$name${39}$suffix");
   } else {
      return index;
   }
}

@JvmSynthetic
fun `getJsonNameIndexOrThrow$default`(var0: SerialDescriptor, var1: Json, var2: java.lang.String, var3: java.lang.String, var4: Int, var5: Any): Int {
   if ((var4 and 4) != 0) {
      var3 = "";
   }

   return getJsonNameIndexOrThrow(var0, var1, var2, var3);
}

internal inline fun Json.tryCoerceValue(
   descriptor: SerialDescriptor,
   index: Int,
   peekNull: (Boolean) -> Boolean,
   peekString: () -> String?,
   onEnumCoercing: () -> Unit = 1.INSTANCE as Function0
): Boolean {
   val isOptional: Boolean = descriptor.isElementOptional(index);
   val elementDescriptor: SerialDescriptor = descriptor.getElementDescriptor(index);
   if (isOptional && !elementDescriptor.isNullable() && peekNull.invoke(true) as java.lang.Boolean) {
      return true;
   } else {
      if (elementDescriptor.getKind() == SerialKind.ENUM.INSTANCE) {
         if (elementDescriptor.isNullable() && peekNull.invoke(false) as java.lang.Boolean) {
            return false;
         }

         val var10000: java.lang.String = peekString.invoke() as java.lang.String;
         if (var10000 == null) {
            return false;
         }

         if (getJsonNameIndex(elementDescriptor, `$this$tryCoerceValue`, var10000) == -3
            && (isOptional || !`$this$tryCoerceValue`.getConfiguration().getExplicitNulls() && elementDescriptor.isNullable())) {
            onEnumCoercing.invoke();
            return true;
         }
      }

      return false;
   }
}

@JvmSynthetic
fun Json.`tryCoerceValue$default`(
   descriptor: SerialDescriptor, index: Int, peekNull: Function1, peekString: Function0, onEnumCoercing: Function0, `$i$f$tryCoerceValue`: Int, isOptional: Any
): Boolean {
   if ((`$i$f$tryCoerceValue` and 16) != 0) {
      onEnumCoercing = 1.INSTANCE;
   }

   val var13: Boolean = descriptor.isElementOptional(index);
   val elementDescriptor: SerialDescriptor = descriptor.getElementDescriptor(index);
   if (var13 && !elementDescriptor.isNullable() && peekNull.invoke(true) as java.lang.Boolean) {
      return true;
   } else {
      if (elementDescriptor.getKind() == SerialKind.ENUM.INSTANCE) {
         if (elementDescriptor.isNullable() && peekNull.invoke(false) as java.lang.Boolean) {
            return false;
         }

         val var10000: java.lang.String = peekString.invoke() as java.lang.String;
         if (var10000 == null) {
            return false;
         }

         if (getJsonNameIndex(elementDescriptor, `$this$tryCoerceValue_u24default`, var10000) == -3
            && (var13 || !`$this$tryCoerceValue_u24default`.getConfiguration().getExplicitNulls() && elementDescriptor.isNullable())) {
            onEnumCoercing.invoke();
            return true;
         }
      }

      return false;
   }
}

internal fun SerialDescriptor.ignoreUnknownKeys(json: Json): Boolean {
   if (!json.getConfiguration().getIgnoreUnknownKeys()) {
      val `$this$any$iv`: java.lang.Iterable = `$this$ignoreUnknownKeys`.getAnnotations();
      var var10000: Boolean;
      if (`$this$any$iv` is java.util.Collection && (`$this$any$iv` as java.util.Collection).isEmpty()) {
         var10000 = false;
      } else {
         val var4: java.util.Iterator = `$this$any$iv`.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = false;
               break;
            }

            if (var4.next() as Annotation is JsonIgnoreUnknownKeys) {
               var10000 = true;
               break;
            }
         }
      }

      if (!var10000) {
         return false;
      }
   }

   return true;
}

fun MutableMap<java.lang.String, Int>.`buildDeserializationNamesMap$putOrThrow`(
   `$this_buildDeserializationNamesMap`: SerialDescriptor, name: java.lang.String, index: Int
) {
   val entity: java.lang.String = if (`$this_buildDeserializationNamesMap`.getKind() == SerialKind.ENUM.INSTANCE) "enum value" else "property";
   if (`$this$buildDeserializationNamesMap_u24putOrThrow`.containsKey(name)) {
      throw new JsonException(
         "The suggested name '$name' for $entity ${`$this_buildDeserializationNamesMap`.getElementName(index)} is already one of the names for $entity ${`$this_buildDeserializationNamesMap`.getElementName(
            MapsKt.<java.lang.String, java.lang.Number>getValue(`$this$buildDeserializationNamesMap_u24putOrThrow`, name).intValue()
         )} in $`$this_buildDeserializationNamesMap`"
      );
   } else {
      `$this$buildDeserializationNamesMap_u24putOrThrow`.put(name, index);
   }
}

fun `deserializationNamesMap$lambda$3`(`$descriptor`: SerialDescriptor, `$this_deserializationNamesMap`: Json): java.util.Map {
   return buildDeserializationNamesMap(`$descriptor`, `$this_deserializationNamesMap`);
}

fun `serializationNamesIndices$lambda$4`(`$this_serializationNamesIndices`: SerialDescriptor, `$strategy`: JsonNamingStrategy): Array<java.lang.String> {
   var var2: Int = 0;
   val var3: Int = `$this_serializationNamesIndices`.getElementsCount();

   val var4: Array<java.lang.String>;
   for (var4 = new java.lang.String[var3]; var2 < var3; var2++) {
      var4[var2] = `$strategy`.serialNameForJson(`$this_serializationNamesIndices`, var2, `$this_serializationNamesIndices`.getElementName(var2));
   }

   return var4;
}
