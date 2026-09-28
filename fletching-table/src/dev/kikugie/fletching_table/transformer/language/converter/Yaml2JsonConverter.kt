package dev.kikugie.fletching_table.transformer.language.converter

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import com.charleskorn.kaml.YamlList
import com.charleskorn.kaml.YamlMap
import com.charleskorn.kaml.YamlNode
import com.charleskorn.kaml.YamlNull
import com.charleskorn.kaml.YamlScalar
import com.charleskorn.kaml.YamlTaggedNode
import dev.kikugie.fletching_table.transformer.language.JsonConverter
import dev.kikugie.fletching_table.transformer.language.visitor.UtilKt
import dev.kikugie.fletching_table.transformer.language.visitor.YamlVisitor
import java.io.Reader
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.JsonArrayBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObjectBuilder

@SourceDebugExtension(["SMAP\nYaml2JsonConverter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Yaml2JsonConverter.kt\ndev/kikugie/fletching_table/transformer/language/converter/Yaml2JsonConverter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n*L\n1#1,32:1\n1#2:33\n52#3,3:34\n29#3,3:37\n*S KotlinDebug\n*F\n+ 1 Yaml2JsonConverter.kt\ndev/kikugie/fletching_table/transformer/language/converter/Yaml2JsonConverter\n*L\n23#1:34,3\n27#1:37,3\n*E\n"])
public object Yaml2JsonConverter : YamlVisitor<JsonElement>, JsonConverter {
   public final val YAML: Yaml =
      new Yaml(null, new YamlConfiguration(false, false, null, null, null, 0, 0, null, null, null, null, 0, null, null, null, false, 65533, null), 1, null)

   public override fun read(input: Reader): JsonElement {
      return UtilKt.accept(YAML.parseToYamlNode(TextStreamsKt.readText(input)), this as YamlVisitor<JsonElement>);
   }

   public open fun visitNull(it: YamlNull): JsonElement {
      return JsonNull.INSTANCE;
   }

   public open fun visitScalar(it: YamlScalar): JsonElement {
      val var10000: java.lang.Boolean = UtilKt.toBooleanOrNull(it);
      if (var10000 != null) {
         return JsonElementKt.JsonPrimitive(var10000);
      } else {
         val var8: java.lang.Double = UtilKt.toDoubleOrNull(it);
         label13:
         if (var8 != null) {
            return JsonElementKt.JsonPrimitive(var8.doubleValue());
         } else {
            val var9: java.lang.Long = UtilKt.toLongOrNull(it);
            return if (var9 != null) JsonElementKt.JsonPrimitive(var9.longValue()) else JsonElementKt.JsonPrimitive(it.getContent());
         }
      }
   }

   public open fun visitList(it: YamlList): JsonElement {
      val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
      val `$this$visitList_u24lambda_u240`: JsonArrayBuilder = `builder$iv`;

      for (YamlNode item : it.getItems()) {
         `$this$visitList_u24lambda_u240`.add(UtilKt.accept(item, INSTANCE));
      }

      return `builder$iv`.build();
   }

   public open fun visitMap(it: YamlMap): JsonElement {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$visitMap_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;

      for (Entry var7 : it.getEntries().entrySet()) {
         `$this$visitMap_u24lambda_u240`.put((var7.getKey() as YamlScalar).getContent(), UtilKt.accept(var7.getValue() as YamlNode, INSTANCE));
      }

      return `builder$iv`.build();
   }

   public open fun visitTagged(it: YamlTaggedNode): JsonElement {
      throw new IllegalStateException(("Unable to convert tagged YAML node ${it.getPath()} to JSON").toString());
   }
}
