@file:SourceDebugExtension(["SMAP\nFTMixinExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTMixinExtension.kt\ndev/kikugie/fletching_table/extension/mixin/FTMixinExtensionKt\n+ 2 TaskContainerExtensions.kt\norg/gradle/kotlin/dsl/TaskContainerExtensionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,80:1\n227#2:81\n1#3:82\n37#4:83\n36#4,3:84\n*S KotlinDebug\n*F\n+ 1 FTMixinExtension.kt\ndev/kikugie/fletching_table/extension/mixin/FTMixinExtensionKt\n*L\n27#1:81\n41#1:83\n41#1:84,3\n*E\n"])

package dev.kikugie.fletching_table.extension.mixin

import com.google.devtools.ksp.gradle.KspAATask
import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.extension.mixin.FTMixinExtensionKt.configureResources.1.1
import dev.kikugie.fletching_table.util.GradleUtilKt
import java.nio.file.Path
import java.util.Map.Entry
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Project
import org.gradle.api.internal.TaskInputsInternal
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.language.jvm.tasks.ProcessResources

private fun Project.configureKsp(src: SourceSet, extension: FTMixinExtension) {
   if (`$this$configureKsp`.getTasks().getNames().contains(GradleUtilKt.getKspKotlinTask(src))) {
      val var10000: TaskContainer = `$this$configureKsp`.getTasks();
   }
}

private fun Project.configureResources(src: SourceSet, extension: FTMixinExtension): TaskProvider<ProcessResources> {
   return GradleUtilKt.processResources(`$this$configureResources`, src, FTMixinExtensionKt::configureResources$lambda$0);
}

fun `configureKsp$lambda$0`(`$extension`: FTMixinExtension, `$this$named`: KspAATask): Unit {
   val config: java.util.Map = `$extension`.getConfigs().get() as java.util.Map;
   if (config.isEmpty()) {
      return Unit.INSTANCE;
   } else {
      `$this$named`.getKspConfig()
         .getProcessorOptions()
         .put("fletching-table.automatic", java.lang.String.valueOf((`$extension`.getAutomatic().getOrElse(true) as java.lang.Boolean).booleanValue()));

      for (Object var10000 : config.values()) {
         var10000 = (var10000 as FTMixinConfig).getOverrides().get();

         for (Entry var6 : ((java.util.Map)var10000).entrySet()) {
            var10000 = var6.getKey();
            val it: java.lang.String = var10000 as java.lang.String;
            var10000 = var6.getValue();
            `$this$named`.getKspConfig().getProcessorOptions().put("fletching-table.package.$it", (var10000 as MixinEnvironment.Env).toString());
         }
      }

      return Unit.INSTANCE;
   }
}

fun `configureResources$lambda$0`(`$extension`: FTMixinExtension, `$src`: SourceSet, `$this$processResources`: ProcessResources): Unit {
   val keys: java.util.Map = `$extension`.getConfigs().get() as java.util.Map;
   if (keys.isEmpty()) {
      return Unit.INSTANCE;
   } else {
      val mixins: java.util.Map = keys;
      val var10000: TaskInputsInternal = `$this$processResources`.getInputs();
      var var10001: Project = `$this$processResources`.getProject();
      var10000.dir(GradleUtilKt.getKspSources(var10001));
      val var15: Project = `$this$processResources`.getProject();
      val config: Path = FilesKt.resolve(GradleUtilKt.getKspSources(var15), "${`$src`.getName()}/resources/fletching-table.mixins.config.json").toPath();
      val var11: Array<java.lang.String> = keys.keySet().toArray(new java.lang.String[0]);
      var var12: Int = 0;

      for (int var14 = keys.length; var12 < var14; var12++) {
         val var10: java.lang.String = var11[var12];
         var10001 = (Project)mixins.get(var10);
         `$this$processResources`.filesMatching((var10001 as FTMixinConfig).getConfig().get() as java.lang.String, new 1(var12, var11, config, mixins, var10));
      }

      return Unit.INSTANCE;
   }
}

@JvmSynthetic
fun `access$configureKsp`(`$receiver`: Project, src: SourceSet, extension: FTMixinExtension) {
   configureKsp(`$receiver`, src, extension);
}

@JvmSynthetic
fun `access$configureResources`(`$receiver`: Project, src: SourceSet, extension: FTMixinExtension): TaskProvider {
   return configureResources(`$receiver`, src, extension);
}
