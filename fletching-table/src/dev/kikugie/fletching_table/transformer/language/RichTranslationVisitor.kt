package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.fletching_table.transformer.language.visitor.JsonVisitor
import dev.kikugie.fletching_table.transformer.language.visitor.UtilKt
import dev.kikugie.fletching_table.util.GradleUtilKt
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

private class RichTranslationVisitor(joiner: String) : JsonVisitor<java.lang.String> {
   public final val joiner: String

   init {
      this.joiner = joiner;
   }

   public open fun visitNull(it: JsonNull): String {
      throw new SerializationException("Unable to convert null to a translation");
   }

   public open fun visitPrimitive(it: JsonPrimitive): String {
      return it.getContent();
   }

   public open fun visitArray(it: JsonArray): String {
      return CollectionsKt.joinToString$default(it, this.joiner, null, null, 0, null, RichTranslationVisitor::visitArray$lambda$0, 30, null);
   }

   public open fun visitObject(it: JsonObject): String {
      return GradleUtilKt.getJSON().decodeFromJsonElement(RichTranslationPropertiesSerializer.INSTANCE, it).flatten();
   }

   @JvmStatic
   fun `visitArray$lambda$0`(`this$0`: RichTranslationVisitor, it: JsonElement): java.lang.CharSequence {
      return UtilKt.accept(it, `this$0`);
   }
}
