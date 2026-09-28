@file:SourceDebugExtension(["SMAP\nFTNeoforgeAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTNeoforgeAdapter.kt\ndev/kikugie/fletching_table/adapter/FTNeoforgeAdapterKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,51:1\n1563#2:52\n1634#2,3:53\n*S KotlinDebug\n*F\n+ 1 FTNeoforgeAdapter.kt\ndev/kikugie/fletching_table/adapter/FTNeoforgeAdapterKt\n*L\n29#1:52\n29#1:53,3\n*E\n"])

package dev.kikugie.fletching_table.adapter

import dev.kikugie.fletching_table.adapter.FTNeoforgeAdapterKt.applyFilter.1
import dev.kikugie.fletching_table.adapter.FTNeoforgeAdapterKt.configureResources.1.2
import dev.kikugie.fletching_table.adapter.FTNeoforgeAdapterKt.configureResources.1.3
import dev.kikugie.fletching_table.extension.FletchingTableExtension
import dev.kikugie.fletching_table.extension.mixin.FTMixinConfig
import dev.kikugie.fletching_table.extension.mixin.FTMixinExtension
import dev.kikugie.fletching_table.transformer.NeoforgeModTomlTransformer
import dev.kikugie.fletching_table.transformer.NeoforgeModTomlTransformer.TransformerArgs
import dev.kikugie.fletching_table.util.GradleUtilKt
import java.util.ArrayList
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Project
import org.gradle.api.file.CopySpec
import org.gradle.api.provider.MapProperty
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskProvider
import org.gradle.language.jvm.tasks.ProcessResources

private const val NEOFORGE_MODS_TOML: String = "META-INF/neoforge.mods.toml"
private const val FORGE_MODS_TOML: String = "META-INF/mods.toml"

private fun CopySpec.applyFilter(args: TransformerArgs): CopySpec {
   val var10000: CopySpec = `$this$applyFilter`.filesMatching(
      CollectionsKt.listOf(new java.lang.String[]{"META-INF/neoforge.mods.toml", "META-INF/mods.toml"}), new 1(args)
   );
   return var10000;
}

private fun Project.configureResources(src: SourceSet, host: FletchingTableExtension): TaskProvider<ProcessResources> {
   return GradleUtilKt.processResources(`$this$configureResources`, src, FTNeoforgeAdapterKt::configureResources$lambda$0);
}

fun `configureResources$lambda$0`(`$host`: FletchingTableExtension, `$src`: SourceSet, `$this$processResources`: ProcessResources): Unit {
   val args: FTMixinExtension = `$host`.getMixins().findByName(`$src`.getName()) as FTMixinExtension;
   if (args != null) {
      val `$this$map$iv`: MapProperty = args.getConfigs();
      if (`$this$map$iv` != null) {
         val `$i$f$map`: java.util.Map = `$this$map$iv`.get() as java.util.Map;
         if (`$i$f$map` != null) {
            val var17: java.lang.Iterable = `$i$f$map`.values();
            val `destination$iv$iv`: java.util.Collection = new ArrayList(CollectionsKt.collectionSizeOrDefault(var17, 10));

            for (Object item$iv$iv : var17) {
               val var10000: Any = (`item$iv$iv` as FTMixinConfig).getConfig().get();
               `destination$iv$iv`.add(var10000 as java.lang.String);
            }

            val var16: NeoforgeModTomlTransformer.TransformerArgs = new NeoforgeModTomlTransformer.TransformerArgs(`destination$iv$iv`);
            if (!`$i$f$map`.isEmpty()) {
               `$this$processResources`.getInputs().files(new Object[]{`$i$f$map`.keySet()});
            }

            if (GradleUtilKt.isMain(`$src`)) {
               applyFilter(`$this$processResources` as CopySpec, var16);
            } else {
               val var10001: Project = `$this$processResources`.getProject();
               val var21: CopySpec = `$this$processResources`.from(
                  GradleUtilKt.processResources$default(var10001, null, 1, null)
                     .map(dev.kikugie.fletching_table.adapter.FTNeoforgeAdapterKt.configureResources.1.1.INSTANCE)
                     .map(new 2(`$this$processResources`)),
                  new 3(var16)
               ) as CopySpec;
            }

            return Unit.INSTANCE;
         }
      }
   }

   return Unit.INSTANCE;
}

@JvmSynthetic
fun `access$configureResources`(`$receiver`: Project, src: SourceSet, host: FletchingTableExtension): TaskProvider {
   return configureResources(`$receiver`, src, host);
}

@JvmSynthetic
fun `access$applyFilter`(`$receiver`: CopySpec, args: NeoforgeModTomlTransformer.TransformerArgs): CopySpec {
   return applyFilter(`$receiver`, args);
}
