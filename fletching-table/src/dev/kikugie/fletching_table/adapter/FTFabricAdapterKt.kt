package dev.kikugie.fletching_table.adapter

import com.google.devtools.ksp.gradle.KspAATask
import dev.kikugie.fletching_table.adapter.FTFabricAdapterKt.applyFilter.1
import dev.kikugie.fletching_table.adapter.FTFabricAdapterKt.configureResources.1.2
import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.annotation.MixinEnvironment.Env
import dev.kikugie.fletching_table.extension.FletchingTableExtension
import dev.kikugie.fletching_table.extension.mixin.FTMixinConfig
import dev.kikugie.fletching_table.extension.mixin.FTMixinExtension
import dev.kikugie.fletching_table.transformer.FabricModJsonTransformer
import dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.MixinArgs
import dev.kikugie.fletching_table.transformer.FabricModJsonTransformer.TransformerArgs
import dev.kikugie.fletching_table.util.GradleUtilKt
import java.nio.file.Path
import java.util.LinkedHashMap
import java.util.Locale
import java.util.Map.Entry
import org.gradle.api.Project
import org.gradle.api.file.CopySpec
import org.gradle.api.provider.MapProperty
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.language.jvm.tasks.ProcessResources

private const val MAIN_INIT_FQ: String = "net.fabricmc.api.ModInitializer"
private const val CLIENT_INIT_FQ: String = "net.fabricmc.api.ClientModInitializer"
private const val SERVER_INIT_FQ: String = "net.fabricmc.api.DedicatedServerModInitializer"
private const val PRE_INIT_FQ: String = "net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint"
private const val FABRIC_MOD_JSON: String = "fabric.mod.json"

private fun buildEntrypointArgs(mappings: MapProperty<String, String>): Map<String, String> {
   val var1: java.util.Map = MapsKt.createMapBuilder();
   val `$this$buildEntrypointArgs_u24lambda_u240`: java.util.Map = var1;
   var var10000: Any = mappings.get();

   for (Entry var5 : ((java.util.Map)var10000).entrySet()) {
      var10000 = var5.getKey();
      val alias: java.lang.String = var10000 as java.lang.String;
      var10000 = var5.getValue();
      `$this$buildEntrypointArgs_u24lambda_u240`.put("fletching-table.entrypoint.$alias", var10000 as java.lang.String);
   }

   return MapsKt.build(var1);
}

private fun Project.configureKsp(src: SourceSet, mappings: MapProperty<String, String>) {
   if (`$this$configureKsp`.getTasks().getNames().contains(GradleUtilKt.getKspKotlinTask(src))) {
      val var10000: TaskContainer = `$this$configureKsp`.getTasks();
   }
}

private fun CopySpec.applyFilter(args: TransformerArgs): CopySpec {
   val var10000: CopySpec = `$this$applyFilter`.filesMatching("fabric.mod.json", new 1(args));
   return var10000;
}

private fun buildMixinArgs(src: SourceSet, configs: Map<String, FTMixinConfig>): MixinArgs {
   val mixinEnvironments: java.lang.Iterable = configs.entrySet();
   var `destination$iv$iv`: java.util.Map = new LinkedHashMap(
      RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(mixinEnvironments, 10)), 16)
   );

   for (Object element$iv$iv : $this$associate$iv) {
      val var27: Pair = TuplesKt.to(
         (`$i$f$associateByTo` as Entry).getKey() as java.lang.String, ((`$i$f$associateByTo` as Entry).getValue() as FTMixinConfig).getConfig().get()
      );
      `destination$iv$iv`.put(var27.getFirst(), var27.getSecond());
   }

   `destination$iv$iv` = new LinkedHashMap(MapsKt.mapCapacity(`destination$iv$iv`.size()));

   val var25: java.lang.Iterable;
   for (Object element$iv$iv$iv : var25) {
      var var32: java.lang.String;
      var var10001: Any;
      label19: {
         var10001 = (var28 as Entry).getKey();
         var32 = (java.lang.String)configs.get((var28 as Entry).getKey() as java.lang.String);
         val var31: MixinEnvironment.Env = (var32 as FTMixinConfig).getEnvironment().getOrNull() as MixinEnvironment.Env;
         if (var31 != null) {
            var32 = category(var31);
            if (var32 != null) {
               break label19;
            }
         }

         var32 = src.getName();
      }

      `destination$iv$iv`.put(var10001, var32);
   }

   return new FabricModJsonTransformer.MixinArgs(`destination$iv$iv`, `destination$iv$iv`);
}

private fun Env.category(): String? {
   val var10000: java.lang.String;
   if (FTFabricAdapterKt.WhenMappings.$EnumSwitchMapping$0[`$this$category`.ordinal()] == 1) {
      var10000 = null;
   } else {
      var10000 = `$this$category`.name().toLowerCase(Locale.ROOT);
   }

   return var10000;
}

private fun Project.configureResources(src: SourceSet, host: FletchingTableExtension): TaskProvider<ProcessResources> {
   return GradleUtilKt.processResources(`$this$configureResources`, src, FTFabricAdapterKt::configureResources$lambda$0);
}

fun `configureKsp$lambda$0`(`$mappings`: MapProperty, `$this$named`: KspAATask): Unit {
   `$this$named`.getKspConfig().getProcessorOptions().putAll(buildEntrypointArgs(`$mappings`));
   return Unit.INSTANCE;
}

fun `configureResources$lambda$0`(`$host`: FletchingTableExtension, `$src`: SourceSet, `$this$processResources`: ProcessResources): Unit {
   var var10000: FabricModJsonTransformer.MixinArgs;
   label17: {
      val entrypointConfig: FTMixinExtension = `$host`.getMixins().findByName(`$src`.getName()) as FTMixinExtension;
      if (entrypointConfig != null) {
         val var10001: Any = entrypointConfig.getConfigs().get();
         val args: FabricModJsonTransformer.MixinArgs = buildMixinArgs(`$src`, var10001 as MutableMap<java.lang.String, FTMixinConfig>);
         if (args != null) {
            `$this$processResources`.getInputs().files(new Object[]{args.getConfigs().keySet()});
            var10000 = args;
            break label17;
         }
      }

      var10000 = null;
   }

   val var14: Project = `$this$processResources`.getProject();
   val var10: Path = FilesKt.resolve(GradleUtilKt.getKspSources(var14), "${`$src`.getName()}/resources/fletching-table.entrypoints.config.json").toPath();
   val var11: FabricModJsonTransformer.TransformerArgs = new FabricModJsonTransformer.TransformerArgs(var10000, var10);
   if (GradleUtilKt.isMain(`$src`)) {
      applyFilter(`$this$processResources` as CopySpec, var11);
   } else {
      val var16: Project = `$this$processResources`.getProject();
      val var15: CopySpec = `$this$processResources`.from(
         GradleUtilKt.processResources$default(var16, null, 1, null).map(dev.kikugie.fletching_table.adapter.FTFabricAdapterKt.configureResources.1.1.INSTANCE),
         new 2(var11)
      ) as CopySpec;
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$configureKsp`(`$receiver`: Project, src: SourceSet, mappings: MapProperty) {
   configureKsp(`$receiver`, src, mappings);
}

@JvmSynthetic
fun `access$configureResources`(`$receiver`: Project, src: SourceSet, host: FletchingTableExtension): TaskProvider {
   return configureResources(`$receiver`, src, host);
}

@JvmSynthetic
fun `access$applyFilter`(`$receiver`: CopySpec, args: FabricModJsonTransformer.TransformerArgs): CopySpec {
   return applyFilter(`$receiver`, args);
}
// $VF: Class flags could not be determined
@JvmSynthetic
internal class WhenMappings {
   @JvmStatic
   fun {
      val var0: IntArray = new int[MixinEnvironment.Env.values().length];

      try {
         var0[MixinEnvironment.Env.DEFAULT.ordinal()] = 1;
      } catch (var2: NoSuchFieldError) {
      }

      $EnumSwitchMapping$0 = var0;
   }
}
