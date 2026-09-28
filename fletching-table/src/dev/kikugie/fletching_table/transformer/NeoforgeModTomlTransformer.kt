package dev.kikugie.fletching_table.transformer

import java.io.Reader
import java.io.StringReader
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.StringFormat
import net.peanuuutz.tomlkt.Toml
import net.peanuuutz.tomlkt.TomlArray
import net.peanuuutz.tomlkt.TomlArrayBuilder
import net.peanuuutz.tomlkt.TomlElement
import net.peanuuutz.tomlkt.TomlElementBuildersKt
import net.peanuuutz.tomlkt.TomlElementKt
import net.peanuuutz.tomlkt.TomlStreamsKt
import net.peanuuutz.tomlkt.TomlTable
import net.peanuuutz.tomlkt.TomlTableBuilder

@SourceDebugExtension(["SMAP\nNeoforgeModTomlTransformer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NeoforgeModTomlTransformer.kt\ndev/kikugie/fletching_table/transformer/NeoforgeModTomlTransformer\n+ 2 TomlStreams.kt\nnet/peanuuutz/tomlkt/TomlStreamsKt\n+ 3 SerialFormat.kt\nkotlinx/serialization/SerialFormatKt\n+ 4 TomlElementBuilders.kt\nnet/peanuuutz/tomlkt/TomlElementBuildersKt\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,40:1\n108#2:41\n113#3:42\n380#4,7:43\n53#4,7:50\n1617#5,9:57\n1869#5:66\n1870#5:68\n1626#5:69\n1#6:67\n*S KotlinDebug\n*F\n+ 1 NeoforgeModTomlTransformer.kt\ndev/kikugie/fletching_table/transformer/NeoforgeModTomlTransformer\n*L\n20#1:41\n22#1:42\n25#1:43,7\n31#1:50,7\n32#1:57,9\n32#1:66\n32#1:68\n32#1:69\n32#1:67\n*E\n"])
internal class NeoforgeModTomlTransformer(input: Reader) : TransformerReader(input) {
   protected open fun run(reader: Reader, args: dev.kikugie.fletching_table.transformer.NeoforgeModTomlTransformer.TransformerArgs): Reader {
      if (args.getConfigs().isEmpty()) {
         return reader;
      } else {
         val complete: Toml = Toml.Default;
         Toml.Default.getSerializersModule();
         val var8: TomlTable = this.completeFMT(TomlStreamsKt.decodeFromNativeReader(complete, TomlTable.Companion.serializer(), reader), args);
         val `$this$encodeToString$iv`: StringFormat = Toml.Default;
         Toml.Default.getSerializersModule();
         return new StringReader(`$this$encodeToString$iv`.encodeToString(TomlTable.Companion.serializer(), var8));
      }
   }

   private fun completeFMT(config: TomlTable, args: dev.kikugie.fletching_table.transformer.NeoforgeModTomlTransformer.TransformerArgs): TomlTable {
      val var5: TomlTableBuilder = new TomlTableBuilder(8);
      TomlTableBuilder.elements$default(var5, config, null, 2, null);
      val var8: Any = config.get("mixins");
      val mixins: TomlArray = this.completeMixinConfigs(var8 as? TomlArray, args);
      if (!mixins.isEmpty()) {
         TomlTableBuilder.element$default(var5, "mixins", mixins, null, 4, null);
      }

      return var5.build();
   }

   private fun completeMixinConfigs(host: TomlArray?, args: dev.kikugie.fletching_table.transformer.NeoforgeModTomlTransformer.TransformerArgs): TomlArray {
      val var5: TomlArrayBuilder = new TomlArrayBuilder(8);
      val `$this$completeMixinConfigs_u24lambda_u240`: TomlArrayBuilder = var5;
      var var10000: java.util.List = host;
      if (host == null) {
         var10000 = CollectionsKt.emptyList();
      }

      val `$this$mapNotNull$iv`: java.lang.Iterable = var10000;
      val `destination$iv$iv`: java.util.Collection = new ArrayList();

      for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
         label35: {
            val it: TomlElement = `element$iv$iv$iv` as TomlElement;
            val var26: TomlElement = (it as TomlTable).get("config") as TomlElement;
            if (var26 != null) {
               var10000 = (java.util.List)var26.getContent();
               if (var10000 != null) {
                  var28 = var10000.toString();
                  break label35;
               }
            }

            var28 = null;
         }

         if (var28 != null) {
            `destination$iv$iv`.add(var28);
         }
      }

      val definitions: java.util.Set = CollectionsKt.toMutableSet(`destination$iv$iv` as java.util.List);
      CollectionsKt.addAll(definitions, args.getConfigs());

      for (Object var29 : definitions) {
         TomlElementBuildersKt.table$default(
            `$this$completeMixinConfigs_u24lambda_u240`, null, NeoforgeModTomlTransformer::completeMixinConfigs$lambda$0$1, 1, null
         );
      }

      return var5.build();
   }

   @JvmStatic
   fun `completeMixinConfigs$lambda$0$1`(`$it`: java.lang.String, `$this$table`: TomlTableBuilder): Unit {
      TomlTableBuilder.element$default(`$this$table`, "config", TomlElementKt.TomlLiteral(`$it`), null, 4, null);
      return Unit.INSTANCE;
   }

   public class TransformerArgs(configs: Collection<String>) {
      public final val configs: Collection<String>

      init {
         this.configs = configs;
      }
   }
}
