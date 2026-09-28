package dev.kikugie.fletching_table

import dev.kikugie.fletching_table.FletchingTableNeoforgePlugin.apply..inlined.getByType.1
import dev.kikugie.fletching_table.extension.FletchingTableExtension
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer

@SourceDebugExtension(["SMAP\nFletchingTableNeoforgePlugin.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FletchingTableNeoforgePlugin.kt\ndev/kikugie/fletching_table/FletchingTableNeoforgePlugin\n+ 2 ExtensionContainerExtensions.kt\norg/gradle/kotlin/dsl/ExtensionContainerExtensionsKt\n+ 3 TypeOfExtensions.kt\norg/gradle/kotlin/dsl/TypeOfExtensionsKt\n*L\n1#1,17:1\n110#2:18\n96#2:20\n28#3:19\n*S KotlinDebug\n*F\n+ 1 FletchingTableNeoforgePlugin.kt\ndev/kikugie/fletching_table/FletchingTableNeoforgePlugin\n*L\n13#1:18\n14#1:20\n13#1:19\n*E\n"])
public class FletchingTableNeoforgePlugin : Plugin<Project>, FTPluginVariant {
   public open fun apply(target: Project) {
      target.getPlugins().apply(FletchingTablePlugin::class.java);
      val var10000: ExtensionContainer = target.getExtensions();
      var var11: Any = var10000.getByType(new 1());
      val var9: FletchingTableExtension = var11 as FletchingTableExtension;
      var11 = (var11 as FletchingTableExtension).getExtensions();
      val `constructionArguments$iv`: Array<Any> = new Object[]{target, var9};
   }
}
