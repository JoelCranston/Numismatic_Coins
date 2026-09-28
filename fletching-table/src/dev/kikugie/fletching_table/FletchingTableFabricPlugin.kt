package dev.kikugie.fletching_table

import dev.kikugie.fletching_table.FletchingTableFabricPlugin.apply..inlined.getByType.1
import dev.kikugie.fletching_table.extension.FletchingTableExtension
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer

@SourceDebugExtension(["SMAP\nFletchingTableFabricPlugin.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FletchingTableFabricPlugin.kt\ndev/kikugie/fletching_table/FletchingTableFabricPlugin\n+ 2 ExtensionContainerExtensions.kt\norg/gradle/kotlin/dsl/ExtensionContainerExtensionsKt\n+ 3 TypeOfExtensions.kt\norg/gradle/kotlin/dsl/TypeOfExtensionsKt\n*L\n1#1,18:1\n110#2:19\n96#2:21\n28#3:20\n*S KotlinDebug\n*F\n+ 1 FletchingTableFabricPlugin.kt\ndev/kikugie/fletching_table/FletchingTableFabricPlugin\n*L\n14#1:19\n15#1:21\n14#1:20\n*E\n"])
public open class FletchingTableFabricPlugin : Plugin<Project>, FTPluginVariant {
   public open fun apply(target: Project) {
      target.getPlugins().apply(FletchingTablePlugin::class.java);
      val var10000: ExtensionContainer = target.getExtensions();
      var var11: Any = var10000.getByType(new 1());
      val var9: FletchingTableExtension = var11 as FletchingTableExtension;
      var11 = (var11 as FletchingTableExtension).getExtensions();
      val `constructionArguments$iv`: Array<Any> = new Object[]{target, var9};
   }
}
