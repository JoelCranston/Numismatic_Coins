package dev.kikugie.fletching_table.transformer

import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.annotation.MixinEnvironment.Env
import dev.kikugie.fletching_table.ksp.mixin.FTMixinModel
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
import java.util.LinkedHashMap
import java.util.NoSuchElementException
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

@SourceDebugExtension(["SMAP\nMixinConfigTransformer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MixinConfigTransformer.kt\ndev/kikugie/fletching_table/transformer/MixinConfigTransformer\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Json.kt\nkotlinx/serialization/json/Json\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 6 JsonElementBuilders.kt\nkotlinx/serialization/json/JsonElementBuildersKt\n+ 7 Maps.kt\ndev/kikugie/commons/collections/MapsKt\n*L\n1#1,67:1\n1#2:68\n1#2:72\n1#2:93\n222#3:69\n205#3:70\n2756#4:71\n774#4:73\n865#4,2:74\n1491#4:76\n1516#4,3:77\n1519#4,3:87\n382#5,7:80\n29#6,2:90\n31#6:94\n52#6,3:95\n22#7:92\n*S KotlinDebug\n*F\n+ 1 MixinConfigTransformer.kt\ndev/kikugie/fletching_table/transformer/MixinConfigTransformer\n*L\n46#1:72\n53#1:93\n38#1:69\n40#1:70\n46#1:71\n47#1:73\n47#1:74,2\n48#1:76\n48#1:77,3\n48#1:87,3\n48#1:80,7\n50#1:90,2\n50#1:94\n61#1:95,3\n53#1:92\n*E\n"])
internal class MixinConfigTransformer(input: Reader) : TransformerReader(input) {
   protected open fun run(reader: Reader, args: dev.kikugie.fletching_table.transformer.MixinConfigTransformer.TransformerArgs): Reader {
      val config: java.util.Map = this.readConfig(args);
      if (config.isEmpty()) {
         return reader;
      } else {
         val complete: Json = GradleUtilKt.getJSON();
         val `this_$iv`: java.lang.String = TextStreamsKt.readText(reader);
         complete.getSerializersModule();
         val var10: JsonObject = this.completeMixins(complete.decodeFromString(JsonObject.Companion.serializer(), `this_$iv`), args.getEnvironment(), config);
         val var12: Json = GradleUtilKt.getJSON();
         var12.getSerializersModule();
         return new StringReader(var12.encodeToString(JsonObject.Companion.serializer(), var10));
      }
   }

   public fun readConfig(args: dev.kikugie.fletching_table.transformer.MixinConfigTransformer.TransformerArgs): Map<Env, List<FTMixinModel>> {
      label61: {
         var var10000: Path = args.getConfig();
         val var10001: Array<LinkOption> = new LinkOption[0];
         val var37: java.util.Map;
         if (!Files.exists(var10000, Arrays.copyOf(var10001, var10001.length))) {
            var37 = MapsKt.emptyMap();
         } else {
            var10000 = args.getConfig();
            val var41: Array<OpenOption> = new OpenOption[0];
            val var39: InputStream = Files.newInputStream(var10000, Arrays.copyOf(var41, var41.length));
            val `$this$groupBy$iv`: Closeable = var39;
            var `$i$f$groupBy`: java.lang.Throwable = null;

            var var23: java.util.List;
            try {
               try {
                  var23 = JvmStreamsKt.decodeFromStream(GradleUtilKt.getJSON(), FTMixinModel.Companion.getLIST_SERIALIZER(), `$this$groupBy$iv` as InputStream);
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
               val `list$iv$iv`: FTMixinModel = var7.next() as FTMixinModel;
               if (!ArraysKt.contains(args.getAliases(), `list$iv$iv`.getDefinition())) {
                  throw new IllegalStateException(
                     ("Unknown alias '${`list$iv$iv`.getDefinition()}' for class '${`list$iv$iv`.getImplementation()}'").toString()
                  );
               }
            }

            val var24: java.util.Collection = new ArrayList();

            for (Object element$iv$iv : var23) {
               if ((var29 as FTMixinModel).getDefinition() == args.getAlias()) {
                  var24.add(var29);
               }
            }

            val var20: java.lang.Iterable = var24 as java.util.List;
            val `destination$iv$ivx`: java.util.Map = new LinkedHashMap();

            for (Object element$iv$ivx : var20) {
               val `key$iv$iv`: Any = (`element$iv$ivx` as FTMixinModel).getEnvironment();
               val `value$iv$iv$iv`: Any = `destination$iv$ivx`.get(`key$iv$iv`);
               if (`value$iv$iv$iv` == null) {
                  val var36: Any = new ArrayList();
                  `destination$iv$ivx`.put(`key$iv$iv`, var36);
                  var10000 = (Path)var36;
               } else {
                  var10000 = (Path)`value$iv$iv$iv`;
               }

               (var10000 as java.util.List).add(`element$iv$ivx`);
            }

            var37 = `destination$iv$ivx`;
         }

         return var37;
      }
   }

   public fun completeMixins(config: JsonObject, environment: Env, parameters: Map<Env, List<FTMixinModel>>): JsonObject {
      val `builder$iv`: JsonObjectBuilder = new JsonObjectBuilder();
      val `$this$completeMixins_u24lambda_u240`: JsonObjectBuilder = `builder$iv`;

      for (Entry $this$getOrThrow$iv : config.entrySet()) {
         `$this$completeMixins_u24lambda_u240`.put(`$this$getOrThrow$iv`.getKey() as java.lang.String, `$this$getOrThrow$iv`.getValue() as JsonElement);
      }

      val var10000: Any = config.get("package");
      if (var10000 == null) {
         throw new NoSuchElementException("No mixin package defined");
      } else {
         val var17: java.lang.String = JsonElementKt.getJsonPrimitive(var10000 as JsonElement).getContent();

         for (Entry var21 : parameters.entrySet()) {
            val var23: MixinEnvironment.Env = var21.getKey() as MixinEnvironment.Env;
            val mixins: java.util.List = var21.getValue() as java.util.List;
            val name: java.lang.String = MixinConfigTransformerKt.access$category(var23, environment);
            val var10002: JsonElement = config.get((Object)name) as JsonElement;
            val var14: JsonArray = this.completeEntries(var17, if (var10002 != null) JsonElementKt.getJsonArray(var10002) else null, mixins);
            if (!var14.isEmpty()) {
               `$this$completeMixins_u24lambda_u240`.put(name, var14);
            }
         }

         return `builder$iv`.build();
      }
   }

   public fun completeEntries(root: String, host: JsonArray?, models: List<FTMixinModel>): JsonArray {
      val `builder$iv`: JsonArrayBuilder = new JsonArrayBuilder();
      var var10000: java.util.List = host;
      if (host == null) {
         var10000 = CollectionsKt.emptyList();
      }

      val definitions: java.util.Set = CollectionsKt.toMutableSet(var10000);

      for (FTMixinModel it : models) {
         definitions.add(JsonElementKt.JsonPrimitive(StringsKt.trimStart(StringsKt.removePrefix(it.getImplementation(), root), new char[]{'.'})));
      }

      `builder$iv`.addAll(definitions);
      return `builder$iv`.build();
   }

   public class TransformerArgs(index: Int, vararg aliases: Any, config: Path, environment: Env) {
      public final val index: Int
      public final val aliases: Array<String>
      public final val config: Path
      public final val environment: Env

      public final val alias: String
         public final get() {
            return this.aliases[this.index];
         }


      init {
         this.index = index;
         this.aliases = aliases;
         this.config = config;
         this.environment = environment;
      }
   }
}
