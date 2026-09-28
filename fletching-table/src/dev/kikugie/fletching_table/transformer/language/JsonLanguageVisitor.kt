package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.fletching_table.transformer.LanguageFileTransformer.TransformArgs
import dev.kikugie.fletching_table.transformer.language.visitor.JsonVisitor
import kotlinx.serialization.json.JsonObject

internal interface JsonLanguageVisitor : JsonVisitor<JsonObject> {
   public val args: TransformArgs
   public val keys: KeyStack

   public companion object {
      public fun create(args: TransformArgs): JsonLanguageVisitor {
         return new RegularJsonLanguageVisitor(args, null, 2, null);
      }
   }
}
