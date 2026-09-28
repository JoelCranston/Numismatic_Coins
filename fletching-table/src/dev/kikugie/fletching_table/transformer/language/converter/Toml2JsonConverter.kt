package dev.kikugie.fletching_table.transformer.language.converter

import dev.kikugie.fletching_table.transformer.language.JsonConverter
import dev.kikugie.fletching_table.transformer.language.visitor.TomlVisitor
import dev.kikugie.fletching_table.transformer.language.visitor.UtilKt
import java.io.Reader
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonArrayBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObjectBuilder
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlLiteral
import net.peanuuutz.tomlkt.TomlNull
import net.peanuuutz.tomlkt.TomlStreamsKt
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.Toml.Default

@SourceDebugExtension(["SMAP\nToml2JsonConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Toml2JsonConverter.kt\ndev/kikugie/fletching_table/transformer/language/converter/Toml2JsonConverter\n+ 2 TomlStreams.kt\nnet/peanuuutz/tomlkt/TomlStreamsKt\n+ 3 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,32:1\n108#2:33\n52#3,3:34\n29#3,3:37\n*S KotlinDebug\n*F\n+ 1 Toml2JsonConverter.kt\ndev/kikugie/fletching_table/transformer/language/converter/Toml2JsonConverter\n*L\n14#1:33\n25#1:34,3\n29#1:37,3\n*E\n"])
public object Toml2JsonConverter : TomlVisitor<JsonElement>, JsonConverter {
   public final val TOML: Default = Toml.Default

   public override fun read(input: Reader): JsonElement {
      val `$this$decodeFromNativeReader$iv`: Toml = TOML;
      TOML.getSerializersModule();
      return UtilKt.accept(
         TomlStreamsKt.decodeFromNativeReader(`$this$decodeFromNativeReader$iv`, TomlElement.Companion.serializer(), input), this as TomlVisitor<JsonElement>
      );
   }

   public open fun visitNull(it: TomlNull): JsonElement {
      return JsonNull.INSTANCE;
   }

   public open fun visitLiteral(it: TomlLiteral): JsonElement {
      var var10000: JsonElement;
      switch (Toml2JsonConverter.WhenMappings.$EnumSwitchMapping$0[it.getType().ordinal()]) {
         case 1:
            var10000 = JsonElementKt.JsonPrimitive(java.lang.Boolean.parseBoolean(it.getContent()));
            break;
         case 2:
            var10000 = JsonElementKt.JsonPrimitive(Integer.parseInt(it.getContent()));
            break;
         case 3:
            var10000 = JsonElementKt.JsonPrimitive(java.lang.Float.parseFloat(it.getContent()));
            break;
         default:
            var10000 = JsonElementKt.JsonPrimitive(it.getContent());
      }

      return var10000;
   }

   public open fun visitArray(it: TomlArray): JsonElement {
      val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
      val `$this$visitArray_u24lambda_u240`: JsonArrayBuilder = `builder$iv`;

      for (TomlElement item : it) {
         `$this$visitArray_u24lambda_u240`.add(UtilKt.accept(item, INSTANCE));
      }

      return `builder$iv`.build();
   }

   public open fun visitTable(it: TomlTable): JsonElement {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$visitTable_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;

      for (Entry var7 : it.entrySet()) {
         `$this$visitTable_u24lambda_u240`.put(var7.getKey() as java.lang.String, UtilKt.accept(var7.getValue() as TomlElement, INSTANCE));
      }

      return `builder$iv`.build();
   }
}
