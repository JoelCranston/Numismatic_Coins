package dev.kikugie.fletching_table.transformer.language

import dev.kikugie.commons.collections.PresentationKt
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer

@SourceDebugExtension(["SMAP\nJsonLanguageVisitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/RichTranslationPropertiesSerializer\n+ 2 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,167:1\n29#2,3:168\n1#3:171\n*S KotlinDebug\n*F\n+ 1 JsonLanguageVisitor.kt\ndev/kikugie/fletching_table/transformer/language/RichTranslationPropertiesSerializer\n*L\n136#1:168,3\n*E\n"])
private object RichTranslationPropertiesSerializer : JsonTransformingSerializer(RichTranslationProperties.Companion.serializer()) {
   protected override fun transformDeserialize(element: JsonElement): JsonElement {
      if (element !is JsonObject) {
         throw new SerializationException("Unable to convert ${(element.getClass()::class).getSimpleName()} to rich translation notation");
      } else {
         val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
         val `$this$transformDeserialize_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;

         for (Entry var7 : INSTANCE.transformObject((JsonObject)element).entrySet()) {
            `$this$transformDeserialize_u24lambda_u240`.put(var7.getKey() as java.lang.String, var7.getValue() as JsonPrimitive);
         }

         return `builder$iv`.build();
      }
   }

   private fun transformObject(obj: JsonObject): Map<String, JsonPrimitive> {
      val var2: java.util.Map = MapsKt.createMapBuilder();
      var color: JsonPrimitive = (JsonPrimitive)obj.get("text");
      val text: JsonPrimitive = color as? JsonPrimitive;
      if ((color as? JsonPrimitive) == null) {
         throw new IllegalStateException("Missing text component".toString());
      } else {
         var2.put("text", text);
         var bold: JsonPrimitive = (JsonPrimitive)obj.get("color");
         color = bold as? JsonPrimitive;
         if ((bold as? JsonPrimitive) != null) {
            var2.put("color", color);
         }

         var italic: JsonPrimitive = (JsonPrimitive)obj.get("bold");
         bold = italic as? JsonPrimitive;
         if ((italic as? JsonPrimitive) != null) {
            var2.put("bold", bold);
         }

         var underlines: JsonPrimitive = (JsonPrimitive)obj.get("italic");
         italic = underlines as? JsonPrimitive;
         if ((underlines as? JsonPrimitive) != null) {
            var2.put("italic", italic);
         }

         var strikethrough: JsonPrimitive = (JsonPrimitive)obj.get("underlines");
         underlines = strikethrough as? JsonPrimitive;
         if ((strikethrough as? JsonPrimitive) != null) {
            var2.put("underlines", underlines);
         }

         var remaining: java.util.Set = (java.util.Set)obj.get("strikethrough");
         strikethrough = remaining as? JsonPrimitive;
         if ((remaining as? JsonPrimitive) != null) {
            var2.put("strikethrough", strikethrough);
         }

         remaining = SetsKt.minus(obj.keySet(), var2.keySet());
         if (!remaining.isEmpty()) {
            throw new IllegalStateException(("Unsupported rich translation properties: ${PresentationKt.present$default(remaining, 0, 1, null)}").toString());
         } else {
            return MapsKt.build(var2);
         }
      }
   }
}
