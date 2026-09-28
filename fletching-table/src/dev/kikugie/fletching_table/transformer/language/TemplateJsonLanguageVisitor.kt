package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.fletching_table.transformer.LanguageFileTransformer.TransformArgs
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder

@SourceDebugExtension(["SMAP\nJsonLanguageVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/TemplateJsonLanguageVisitor\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,167:1\n29#2,3:168\n*S KotlinDebug\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/TemplateJsonLanguageVisitor\n*L\n71#1:168,3\n*E\n"])
private class TemplateJsonLanguageVisitor(args: TransformArgs, keys: KeyStack) : RegularJsonLanguageVisitor(args, keys) {
   public override fun visitArray(it: JsonArray): JsonObject {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$visitArray_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;
      val var6: java.util.Iterator = it.iterator();
      var var7: Int = 0;

      while (var6.hasNext()) {
         JsonLanguageVisitorKt.access$plusAssign(`$this$visitArray_u24lambda_u240`, this.process(java.lang.String.valueOf(var7++), var6.next() as JsonElement));
      }

      return `builder$iv`.build();
   }
}
