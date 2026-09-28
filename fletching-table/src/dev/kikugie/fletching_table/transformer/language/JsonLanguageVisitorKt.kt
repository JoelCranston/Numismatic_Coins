@file:SourceDebugExtension(["SMAP\nJsonLanguageVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/JsonLanguageVisitorKt\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ControlFlow.kt\ndev/kikugie/commons/ControlFlowKt\n*L\n1#1,167:1\n29#2,3:168\n29#2,3:171\n1#3:174\n15#4:175\n*S KotlinDebug\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/JsonLanguageVisitorKt\n*L\n33#1:168,3\n34#1:171,3\n42#1:175\n*E\n"])

package dev.kikugie.fletching_table.transformer.language

import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementBuildersKt
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder

private fun jsonObject(key: String, value: JsonElement): JsonObject {
   val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
   `builder$iv`.put(key, value);
   return `builder$iv`.build();
}

private fun jsonObject(key: String, value: String): JsonObject {
   val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
   JsonElementBuildersKt.put(`builder$iv`, key, value);
   return `builder$iv`.build();
}

private operator fun JsonObjectBuilder.plusAssign(it: JsonObject) {
   for (Entry var3 : it.entrySet()) {
      `$this$plusAssign`.put(var3.getKey() as java.lang.String, var3.getValue() as JsonElement);
   }
}

private inline fun <T> KeyStack.with(key: Key, action: () -> T): T {
   val var10000: Any;
   if (key is StringKey && (key as StringKey).unbox-impl() == ".") {
      var10000 = action.invoke();
   } else {
      `$this$with`.push(key);
      val `next$iv`: Any = action.invoke();
      val last: Key = `$this$with`.pop();
      if (!(key == last)) {
         throw new IllegalStateException(("Unbalanced stack; expected: $key, received: $last").toString());
      }

      var10000 = `next$iv`;
   }

   return (T)var10000;
}

@JvmSynthetic
fun `access$jsonObject`(key: java.lang.String, value: JsonElement): JsonObject {
   return jsonObject(key, value);
}

@JvmSynthetic
fun `access$jsonObject`(key: java.lang.String, value: java.lang.String): JsonObject {
   return jsonObject(key, value);
}

@JvmSynthetic
fun `access$plusAssign`(`$receiver`: JsonObjectBuilder, it: JsonObject) {
   plusAssign(`$receiver`, it);
}
