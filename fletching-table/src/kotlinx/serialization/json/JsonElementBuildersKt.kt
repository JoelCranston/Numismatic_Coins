@file:SourceDebugExtension(["SMAP\nJsonElementBuilders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,231:1\n29#1,3:232\n52#1,3:235\n29#1,3:238\n52#1,3:241\n1563#2:244\n1634#2,3:245\n1563#2:248\n1634#2,3:249\n1563#2:252\n1634#2,3:253\n*S KotlinDebug\n*F\n+ 1 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n82#1:232,3\n90#1:235,3\n189#1:238,3\n197#1:241,3\n207#1:244\n207#1:245,3\n217#1:248\n217#1:249,3\n227#1:252\n227#1:253,3\n*E\n"])

package kotlinx.serialization.json

import java.util.ArrayList
import kotlin.contracts.InvocationKind
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.ExperimentalSerializationApi

public inline fun buildJsonObject(builderAction: (JsonObjectBuilder) -> Unit): JsonObject {
   contract {
      callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE)
   }

   val builder: JsonObjectBuilder = new JsonObjectBuilder();
   builderAction.invoke(builder);
   return builder.build();
}

public inline fun buildJsonArray(builderAction: (JsonArrayBuilder) -> Unit): JsonArray {
   contract {
      callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE)
   }

   val builder: JsonArrayBuilder = new JsonArrayBuilder();
   builderAction.invoke(builder);
   return builder.build();
}

public fun JsonObjectBuilder.putJsonObject(key: String, builderAction: (JsonObjectBuilder) -> Unit): JsonElement? {
   val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
   builderAction.invoke(`builder$iv`);
   return `$this$putJsonObject`.put(key, `builder$iv`.build());
}

public fun JsonObjectBuilder.putJsonArray(key: String, builderAction: (JsonArrayBuilder) -> Unit): JsonElement? {
   val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
   builderAction.invoke(`builder$iv`);
   return `$this$putJsonArray`.put(key, `builder$iv`.build());
}

public fun JsonObjectBuilder.put(key: String, value: Boolean?): JsonElement? {
   return `$this$put`.put(key, JsonElementKt.JsonPrimitive(value));
}

public fun JsonObjectBuilder.put(key: String, value: Number?): JsonElement? {
   return `$this$put`.put(key, JsonElementKt.JsonPrimitive(value));
}

public fun JsonObjectBuilder.put(key: String, value: String?): JsonElement? {
   return `$this$put`.put(key, JsonElementKt.JsonPrimitive(value));
}

@ExperimentalSerializationApi
public fun JsonObjectBuilder.put(key: String, value: Nothing?): JsonElement? {
   return `$this$put`.put(key, JsonNull.INSTANCE);
}

public fun JsonArrayBuilder.add(value: Boolean?): Boolean {
   return `$this$add`.add(JsonElementKt.JsonPrimitive(value));
}

public fun JsonArrayBuilder.add(value: Number?): Boolean {
   return `$this$add`.add(JsonElementKt.JsonPrimitive(value));
}

public fun JsonArrayBuilder.add(value: String?): Boolean {
   return `$this$add`.add(JsonElementKt.JsonPrimitive(value));
}

@ExperimentalSerializationApi
public fun JsonArrayBuilder.add(value: Nothing?): Boolean {
   return `$this$add`.add(JsonNull.INSTANCE);
}

public fun JsonArrayBuilder.addJsonObject(builderAction: (JsonObjectBuilder) -> Unit): Boolean {
   val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
   builderAction.invoke(`builder$iv`);
   return `$this$addJsonObject`.add(`builder$iv`.build());
}

public fun JsonArrayBuilder.addJsonArray(builderAction: (JsonArrayBuilder) -> Unit): Boolean {
   val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
   builderAction.invoke(`builder$iv`);
   return `$this$addJsonArray`.add(`builder$iv`.build());
}

@ExperimentalSerializationApi
@JvmName(name = "addAllStrings")
public fun JsonArrayBuilder.addAll(values: Collection<String?>): Boolean {
   val `$this$map$iv`: java.lang.Iterable = values;
   val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));

   for (Object item$iv$iv : $this$map$iv) {
      `destination$iv$iv`.add(JsonElementKt.JsonPrimitive(`item$iv$iv` as java.lang.String));
   }

   return `$this$addAll`.addAll(`destination$iv$iv`);
}

@ExperimentalSerializationApi
@JvmName(name = "addAllBooleans")
public fun JsonArrayBuilder.addAll(values: Collection<Boolean?>): Boolean {
   val `$this$map$iv`: java.lang.Iterable = values;
   val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));

   for (Object item$iv$iv : $this$map$iv) {
      `destination$iv$iv`.add(JsonElementKt.JsonPrimitive(`item$iv$iv` as java.lang.Boolean));
   }

   return `$this$addAll`.addAll(`destination$iv$iv`);
}

@ExperimentalSerializationApi
@JvmName(name = "addAllNumbers")
public fun JsonArrayBuilder.addAll(values: Collection<Number?>): Boolean {
   val `$this$map$iv`: java.lang.Iterable = values;
   val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));

   for (Object item$iv$iv : $this$map$iv) {
      `destination$iv$iv`.add(JsonElementKt.JsonPrimitive(`item$iv$iv` as java.lang.Number));
   }

   return `$this$addAll`.addAll(`destination$iv$iv`);
}
