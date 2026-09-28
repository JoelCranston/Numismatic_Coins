package dev.kikugie.fletching_table.transformer.language.converter

import dev.kikugie.fletching_table.transformer.language.JsonConverter
import java.io.Reader
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonKt

@SourceDebugExtension(["SMAP\nJson2JsonConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Json2JsonConverter.kt\ndev/kikugie/fletching_table/transformer/language/converter/Json2JsonConverter\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n*L\n1#1,18:1\n222#2:19\n*S KotlinDebug\n*F\n+ 1 Json2JsonConverter.kt\ndev/kikugie/fletching_table/transformer/language/converter/Json2JsonConverter\n*L\n17#1:19\n*E\n"])
public object Json2JsonConverter : JsonConverter {
   public final val JSON: Json = JsonKt.Json$default(null, Json2JsonConverter::JSON$lambda$0, 1, null)

   public override fun read(input: Reader): JsonElement {
      val `this_$iv`: Json = JSON;
      val `string$iv`: java.lang.String = TextStreamsKt.readText(input);
      `this_$iv`.getSerializersModule();
      return `this_$iv`.decodeFromString(JsonElement.Companion.serializer(), `string$iv`);
   }

   @JvmStatic
   fun JsonBuilder.`JSON$lambda$0`(): Unit {
      `$this$Json`.setLenient(true);
      `$this$Json`.setAllowComments(true);
      `$this$Json`.setAllowTrailingComma(true);
      return Unit.INSTANCE;
   }
}
