@file:SourceDebugExtension(["SMAP\nFabricModJsonTransformer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FabricModJsonTransformer.kt\ndev/kikugie/fletching_table/transformer/FabricModJsonTransformerKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,83:1\n1#2:84\n29#3,3:85\n29#3,3:88\n*S KotlinDebug\n*F\n+ 1 FabricModJsonTransformer.kt\ndev/kikugie/fletching_table/transformer/FabricModJsonTransformerKt\n*L\n19#1:85,3\n24#1:88,3\n*E\n"])

package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.ksp.entrypoint.FTEntrypointModel
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementBuildersKt
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonObjectBuilder

private fun String.toMixin(source: String, lookup: Map<String, String>): JsonElement {
   var var10000: java.lang.String = lookup.get(`$this$toMixin`) as java.lang.String;
   if (var10000 == null) {
      var10000 = source;
   }

   val environment: java.lang.String = if (var10000 == "client" || var10000 == "server") var10000 else null;
   val var9: JsonElement;
   if (environment == null) {
      var9 = JsonElementKt.JsonPrimitive(`$this$toMixin`);
   } else {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      JsonElementBuildersKt.put(`builder$iv`, "environment", environment);
      JsonElementBuildersKt.put(`builder$iv`, "config", `$this$toMixin`);
      var9 = `builder$iv`.build();
   }

   return var9;
}

private fun FTEntrypointModel.toEntrypoint(): JsonElement {
   val var10000: JsonElement;
   if (`$this$toEntrypoint`.getAdapter() == "java") {
      var10000 = JsonElementKt.JsonPrimitive(`$this$toEntrypoint`.getReference());
   } else {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      JsonElementBuildersKt.put(`builder$iv`, "adapter", `$this$toEntrypoint`.getAdapter());
      JsonElementBuildersKt.put(`builder$iv`, "value", `$this$toEntrypoint`.getReference());
      var10000 = `builder$iv`.build();
   }

   return var10000;
}

@JvmSynthetic
fun `access$toMixin`(`$receiver`: java.lang.String, source: java.lang.String, lookup: java.util.Map): JsonElement {
   return toMixin(`$receiver`, source, lookup);
}

@JvmSynthetic
fun `access$toEntrypoint`(`$receiver`: FTEntrypointModel): JsonElement {
   return toEntrypoint(`$receiver`);
}
