package dev.kikugie.fletching_table

import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.DomainObjectCollection
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.PluginContainer

public sealed interface FTPluginVariant : Plugin<Project> {
   @SourceDebugExtension(["SMAP\nFTPluginVariant.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTPluginVariant.kt\ndev/kikugie/fletching_table/FTPluginVariant$Companion\n+ 2 DomainObjectCollectionExtensions.kt\norg/gradle/kotlin/dsl/DomainObjectCollectionExtensionsKt\n*L\n1#1,11:1\n47#2:12\n*S KotlinDebug\n*F\n+ 1 FTPluginVariant.kt\ndev/kikugie/fletching_table/FTPluginVariant$Companion\n*L\n9#1:12\n*E\n"])
   public companion object {
      public fun loaded(project: Project): List<FTPluginVariant> {
         val var10000: PluginContainer = project.getPlugins();
         val var4: DomainObjectCollection = (var10000 as DomainObjectCollection).withType(FTPluginVariant.class);
         return CollectionsKt.toList(var4 as MutableIterable<FTPluginVariant>);
      }
   }
}
