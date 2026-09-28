package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.ksp.entrypoint.FTEntrypointModel
import dev.kikugie.fletching_table.util.GradleUtilKt
import java.io.Closeable
import java.io.InputStream
import java.io.Reader
import java.io.StringReader
import java.nio.file.Files
import java.nio.file.LinkOption
import java.nio.file.OpenOption
import java.nio.file.Path
import java.util.ArrayList
import java.util.Arrays
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonArrayBuilder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.JvmStreamsKt

@SourceDebugExtension(["SMAP\nFabricModJsonTransformer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FabricModJsonTransformer.kt\ndev/kikugie/fletching_table/transformer/FabricModJsonTransformer\n+ 2 Json.kt\nkotlinx/serialization/json/Json\n+ 3 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,83:1\n222#2:84\n205#2:85\n29#3,3:86\n52#3,3:104\n29#3,3:107\n52#3,3:110\n1#4:89\n1491#5:90\n1516#5,3:91\n1519#5,3:101\n382#6,7:94\n*S KotlinDebug\n*F\n+ 1 FabricModJsonTransformer.kt\ndev/kikugie/fletching_table/transformer/FabricModJsonTransformer\n*L\n28#1:84\n30#1:85\n33#1:86,3\n51#1:104,3\n58#1:107,3\n67#1:110,3\n49#1:90\n49#1:91,3\n49#1:101,3\n49#1:94,7\n*E\n"])
internal class FabricModJsonTransformer(input: Reader) : TransformerReader(input) {
   protected open fun run(reader: Reader, args: dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.TransformerArgs): Reader {
      val complete: Json = GradleUtilKt.getJSON();
      val `this_$iv`: java.lang.String = TextStreamsKt.readText(reader);
      complete.getSerializersModule();
      val var8: JsonObject = this.completeFMJ(complete.decodeFromString(JsonObject.Companion.serializer(), `this_$iv`), args);
      val var9: Json = GradleUtilKt.getJSON();
      var9.getSerializersModule();
      return new StringReader(var9.encodeToString(JsonObject.Companion.serializer(), var8));
   }

   private fun completeFMJ(config: JsonObject, args: dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.TransformerArgs): JsonObject {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$completeFMJ_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;

      for (Entry newMixins : config.entrySet()) {
         `$this$completeFMJ_u24lambda_u240`.put(newMixins.getKey() as java.lang.String, newMixins.getValue() as JsonElement);
      }

      val var11: java.util.Map = this.readEntrypointConfig(args.getEntrypointConfig());
      if (!var11.isEmpty()) {
         val var10001: JsonElement = config.get("entrypoints") as JsonElement;
         val var12: JsonObject = this.completeEntrypoints(if (var10001 != null) JsonElementKt.getJsonObject(var10001) else null, var11);
         if (!var12.isEmpty()) {
            `$this$completeFMJ_u24lambda_u240`.put("entrypoints", var12);
         }
      }

      if (args.getMixins() != null) {
         val var14: JsonElement = config.get("mixins") as JsonElement;
         val var13: JsonArray = this.completeMixinConfigs(if (var14 != null) JsonElementKt.getJsonArray(var14) else null, args.getMixins());
         if (!var13.isEmpty()) {
            `$this$completeFMJ_u24lambda_u240`.put("mixins", var13);
         }
      }

      return `builder$iv`.build();
   }

   private fun readEntrypointConfig(path: Path): Map<String, List<FTEntrypointModel>> {
      label37: {
         val var10001: Array<LinkOption> = new LinkOption[0];
         val var10000: java.util.Map;
         if (!Files.exists(path, Arrays.copyOf(var10001, var10001.length))) {
            var10000 = MapsKt.emptyMap();
         } else {
            val var25: Array<OpenOption> = new OpenOption[0];
            val var23: InputStream = Files.newInputStream(path, Arrays.copyOf(var25, var25.length));
            val `$this$groupBy$iv`: Closeable = var23;
            var `$i$f$groupBy`: java.lang.Throwable = null;

            var `destination$iv$iv`: Int;
            try {
               try {
                  val `$this$groupByTo$iv$iv`: InputStream = `$this$groupBy$iv` as InputStream;
                  `destination$iv$iv` = 0;
                  val var20: java.util.List = JvmStreamsKt.decodeFromStream(
                     GradleUtilKt.getJSON(), FTEntrypointModel.Companion.getLIST_SERIALIZER(), `$this$groupByTo$iv$iv`
                  );
               } catch (var16: java.lang.Throwable) {
                  `$i$f$groupBy` = var16;
                  throw var16;
               }
            } catch (var17: java.lang.Throwable) {
               CloseableKt.closeFinally(`$this$groupBy$iv`, `$i$f$groupBy`);
            }

            CloseableKt.closeFinally(`$this$groupBy$iv`, null);

            val var7: <unknown>;
            while (var7.hasNext()) {
               val `element$iv$iv`: Any = var7.next();
               val `key$iv$iv`: Any = (`element$iv$iv` as FTEntrypointModel).getKind();
               val `value$iv$iv$iv`: Any = `destination$iv$iv`.get(`key$iv$iv`);
               val var24: Any;
               if (`value$iv$iv$iv` == null) {
                  val var22: Any = new ArrayList();
                  `destination$iv$iv`.put(`key$iv$iv`, var22);
                  var24 = var22;
               } else {
                  var24 = `value$iv$iv$iv`;
               }

               (var24 as java.util.List).add(`element$iv$iv`);
            }

            var10000 = `destination$iv$iv`;
         }

         return var10000;
      }
   }

   private fun completeMixinConfigs(host: JsonArray?, args: dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.MixinArgs): JsonArray {
      val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
      var var10000: java.util.List = host;
      if (host == null) {
         var10000 = CollectionsKt.emptyList();
      }

      val definitions: java.util.Set = CollectionsKt.toMutableSet(var10000);

      for (Entry var9 : args.getConfigs().entrySet()) {
         definitions.add(
            FabricModJsonTransformerKt.access$toMixin(var9.getValue() as java.lang.String, var9.getKey() as java.lang.String, args.getEnvironments())
         );
      }

      `builder$iv`.addAll(definitions);
      return `builder$iv`.build();
   }

   private fun completeEntrypoints(host: JsonObject?, parameters: Map<String, List<FTEntrypointModel>>): JsonObject {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$completeEntrypoints_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;
      var var10000: java.util.Map = host;
      if (host == null) {
         var10000 = MapsKt.emptyMap();
      }

      val definitions: java.util.Map = MapsKt.toMutableMap(var10000);

      for (Entry var9 : parameters.entrySet()) {
         val k: java.lang.String = var9.getKey() as java.lang.String;
         val v: java.util.List = var9.getValue() as java.util.List;
         val var12: Any = definitions.get(k);
         val newEntrypoints: JsonArray = this.completeCategory(var12 as? JsonArray, v);
         if (!newEntrypoints.isEmpty()) {
            definitions.put(k, newEntrypoints);
         }
      }

      for (Entry var15 : definitions.entrySet()) {
         `$this$completeEntrypoints_u24lambda_u240`.put(var15.getKey() as java.lang.String, var15.getValue() as JsonElement);
      }

      return `builder$iv`.build();
   }

   private fun completeCategory(host: JsonArray?, entrypoints: List<FTEntrypointModel>): JsonArray {
      val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
      var var10000: java.util.List = host;
      if (host == null) {
         var10000 = CollectionsKt.emptyList();
      }

      val definitions: java.util.Set = CollectionsKt.toMutableSet(var10000);

      for (FTEntrypointModel it : entrypoints) {
         definitions.add(FabricModJsonTransformerKt.access$toEntrypoint(it));
      }

      `builder$iv`.addAll(definitions);
      return `builder$iv`.build();
   }

   public class MixinArgs(configs: Map<String, String>, environments: Map<String, String>) {
      public final val configs: Map<String, String>
      public final val environments: Map<String, String>

      init {
         this.configs = configs;
         this.environments = environments;
      }
   }

   public class TransformerArgs(mixins: dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.MixinArgs?, entrypointConfig: Path) {
      public final val mixins: dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.MixinArgs?
      public final val entrypointConfig: Path

      init {
         this.mixins = mixins;
         this.entrypointConfig = entrypointConfig;
      }
   }
}
