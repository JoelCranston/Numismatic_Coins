@file:SourceDebugExtension(["SMAP\nFletchingTableExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FletchingTableExtension.kt\ndev/kikugie/fletching_table/extension/FletchingTableExtensionKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 FletchingTableExtension.kt\ndev/kikugie/fletching_table/extension/FletchingTableExtension$Companion\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,171:1\n1#2:172\n162#3,8:173\n162#3,8:181\n37#4:189\n36#4,3:190\n*S KotlinDebug\n*F\n+ 1 FletchingTableExtension.kt\ndev/kikugie/fletching_table/extension/FletchingTableExtensionKt\n*L\n54#1:173,8\n68#1:181,8\n87#1:189\n87#1:190,3\n*E\n"])

package dev.kikugie.fletching_table.extension

import dev.kikugie.fletching_table.extension.FletchingTableExtensionKt.configureLanguages..inlined.relocate.1
import dev.kikugie.fletching_table.transformer.J52JFileTransformer
import dev.kikugie.fletching_table.transformer.LanguageFileTransformer
import dev.kikugie.fletching_table.transformer.language.JsonConverter
import dev.kikugie.fletching_table.util.GradleUtilKt
import dev.kikugie.fletching_table.util.StringUtilKt
import java.util.Comparator
import java.util.Locale
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import kotlin.jvm.internal.SpreadBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonKt
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import org.gradle.api.file.RelativePath
import org.gradle.api.tasks.AbstractCopyTask
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskProvider
import org.gradle.language.jvm.tasks.ProcessResources

private fun Project.configureResources(src: SourceSet, extension: FletchingTableExtension): TaskProvider<ProcessResources> {
   return GradleUtilKt.processResources(`$this$configureResources`, src, FletchingTableExtensionKt::configureResources$lambda$0);
}

private fun ProcessResources.configureLanguages(src: String, lang: NamedDomainObjectContainer<LanguageConfigContainer>) {
   var var10000: LanguageConfigContainer = lang.findByName(src) as LanguageConfigContainer;
   if (var10000 != null) {
      var10000 = if (!(var10000.getPatterns().get() as java.util.List).isEmpty()) var10000 else null;
      if (var10000 != null) {
         val var27: Any = var10000.getFlatteningMode().get();
         val var28: java.lang.String = (var27 as java.lang.String).toUpperCase(Locale.ROOT);
         val `extension$iv`: LanguageFileTransformer.ArrayBehaviour = LanguageFileTransformer.ArrayBehaviour.valueOf(var28);
         val `$i$f$relocate`: Json = if (var10000.getPrettyPrint().get())
            JsonKt.Json$default(null, FletchingTableExtensionKt::configureLanguages$lambda$1$0, 1, null)
            else
            Json.Default;
         val var21: Comparator = if (var10000.getSortKeys().get()) StringUtilKt.getKEY_COMPARATOR() else null;
         var10000 = (LanguageConfigContainer)var10000.getFormatConverters().get();
         val args: LanguageFileTransformer.TransformArgs = new LanguageFileTransformer.TransformArgs(
            `extension$iv`, `$i$f$relocate`, var21, var10000 as MutableMap<java.lang.String, JsonConverter>
         );

         for (var10000 : (java.util.List)var10000.getPatterns().get()) {
            val var18: java.lang.String = var10000 as java.lang.String;
            val var19: FletchingTableExtension.Companion = FletchingTableExtension.Companion;
            val var22: AbstractCopyTask = `$this$configureLanguages` as AbstractCopyTask;
            val var15: java.lang.String = PatternEntry.constructor-impl(var18);
            var22.filesMatching(PatternEntry.component1-impl(var15), new 1("json", PatternEntry.component2-impl(var15), args));
         }

         return;
      }
   }
}

private fun ProcessResources.configureJ52J(src: String, j52j: NamedDomainObjectContainer<J52JConfigContainer>) {
   var var10000: J52JConfigContainer = j52j.findByName(src) as J52JConfigContainer;
   if (var10000 != null) {
      var10000 = if (!(var10000.getPatterns().get() as java.util.Map).isEmpty()) var10000 else null;
      if (var10000 != null) {
         val var22: Json = if (var10000.getPrettyPrint().get())
            JsonKt.Json$default(null, FletchingTableExtensionKt::configureJ52J$lambda$1$0, 1, null)
            else
            Json.Default;
         val var10003: Any = var10000.getConverter().get();
         val args: J52JFileTransformer.TransformArgs = new J52JFileTransformer.TransformArgs(var22, var10003 as JsonConverter);
         var10000 = (J52JConfigContainer)var10000.getPatterns().get();

         for (Entry var20 : ((java.util.Map)var10000).entrySet()) {
            var10000 = (J52JConfigContainer)var20.getKey();
            val var21: java.lang.String = var10000 as java.lang.String;
            var10000 = (J52JConfigContainer)var20.getValue();
            val var23: java.lang.String = var10000 as java.lang.String;
            val `this_$iv`: FletchingTableExtension.Companion = FletchingTableExtension.Companion;
            val `$this$relocate$iv`: AbstractCopyTask = `$this$configureJ52J` as AbstractCopyTask;
            val var17: java.lang.String = PatternEntry.constructor-impl(var21);
            `$this$relocate$iv`.filesMatching(
               PatternEntry.component1-impl(var17),
               new dev.kikugie.fletching_table.extension.FletchingTableExtensionKt.configureJ52J..inlined.relocate.1(
                  var23, PatternEntry.component2-impl(var17), args
               )
            );
         }

         return;
      }
   }
}

private fun ProcessResources.configureAw2At(src: String, converter: NamedDomainObjectContainer<FTAccessConverterContainer>) {
   var var10000: FTAccessConverterContainer = converter.findByName(src) as FTAccessConverterContainer;
   if (var10000 != null) {
      var10000 = if (!(var10000.getConverters$fletching_table_two().get() as java.util.Map).isEmpty()) var10000 else null;
      if (var10000 != null) {
         var10000 = (FTAccessConverterContainer)var10000.getConverters$fletching_table_two().get();

         for (Entry var5 : ((java.util.Map)var10000).entrySet()) {
            var10000 = (FTAccessConverterContainer)var5.getKey();
            val var9: java.lang.String = var10000 as java.lang.String;
            var10000 = (FTAccessConverterContainer)var5.getValue();
            `$this$configureAw2At`.filesMatching(
               var10000 as java.lang.String, new dev.kikugie.fletching_table.extension.FletchingTableExtensionKt.configureAw2At.1(var9)
            );
         }

         return;
      }
   }
}

@PublishedApi
internal fun resolve(path: RelativePath, destination: String, filename: String): RelativePath {
   val var10000: RelativePath;
   if (destination.length() == 0) {
      var10000 = path.replaceLastName(filename);
   } else if (StringsKt.startsWith$default(destination, '/', false, 2, null)) {
      var10000 = RelativePath.parse(false, StringsKt.drop(destination, 1)).append(true, new java.lang.String[]{filename});
   } else {
      val var3: SpreadBuilder = new SpreadBuilder(3);
      val var10004: Array<java.lang.String> = path.getSegments();
      var3.addSpread(ArraysKt.dropLast(var10004, 1).toArray(new java.lang.String[0]));
      var3.add(destination);
      var3.add(filename);
      var10000 = new RelativePath(true, var3.toArray(new java.lang.String[var3.size()]) as Array<java.lang.String>);
   }

   return var10000;
}

fun `configureResources$lambda$0`(`$src`: SourceSet, `$extension`: FletchingTableExtension, `$this$processResources`: ProcessResources): Unit {
   `$this$processResources`.exclude(new java.lang.String[]{"fletching-table.mixins.config.json", "fletching-table.entrypoints.config.json"});
   val `$this$configureResources_u24lambda_u240_u240`: java.lang.String = `$src`.getName();
   configureJ52J(`$this$processResources`, `$this$configureResources_u24lambda_u240_u240`, `$extension`.getJ52j());
   configureLanguages(`$this$processResources`, `$this$configureResources_u24lambda_u240_u240`, `$extension`.getLang());
   configureAw2At(`$this$processResources`, `$this$configureResources_u24lambda_u240_u240`, `$extension`.getAccessConverter());
   return Unit.INSTANCE;
}

fun JsonBuilder.`configureLanguages$lambda$1$0`(): Unit {
   `$this$Json`.setPrettyPrint(true);
   return Unit.INSTANCE;
}

fun JsonBuilder.`configureJ52J$lambda$1$0`(): Unit {
   `$this$Json`.setPrettyPrint(true);
   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$configureResources`(`$receiver`: Project, src: SourceSet, extension: FletchingTableExtension): TaskProvider {
   return configureResources(`$receiver`, src, extension);
}
