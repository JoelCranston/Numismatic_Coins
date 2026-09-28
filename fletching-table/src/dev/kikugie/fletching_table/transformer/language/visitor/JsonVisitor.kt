package dev.kikugie.fletching_table.transformer.language.visitor

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

internal interface JsonVisitor<T> {
   public abstract fun visitNull(it: JsonNull): Any {
   }

   public abstract fun visitPrimitive(it: JsonPrimitive): Any {
   }

   public abstract fun visitArray(it: JsonArray): Any {
   }

   public abstract fun visitObject(it: JsonObject): Any {
   }
}
