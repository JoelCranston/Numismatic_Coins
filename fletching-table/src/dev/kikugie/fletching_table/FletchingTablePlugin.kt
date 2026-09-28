package dev.kikugie.fletching_table

import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.api.services.BuildServiceRegistry

@SourceDebugExtension(["SMAP\nFletchingTablePlugin.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FletchingTablePlugin.kt\ndev/kikugie/fletching_table/FletchingTablePlugin\n+ 2 GradleApiKotlinDslExtensions_4xzlmchnjfhf5wwk67zexqao1.kt\norg/gradle/kotlin/dsl/GradleApiKotlinDslExtensions_4xzlmchnjfhf5wwk67zexqao1Kt\n+ 3 ExtensionContainerExtensions.kt\norg/gradle/kotlin/dsl/ExtensionContainerExtensionsKt\n*L\n1#1,28:1\n41#2:29\n96#3:30\n96#3:31\n*S KotlinDebug\n*F\n+ 1 FletchingTablePlugin.kt\ndev/kikugie/fletching_table/FletchingTablePlugin\n*L\n18#1:29\n25#1:30\n26#1:31\n*E\n"])
public open class FletchingTablePlugin : Plugin<Project> {
   public open fun apply(target: Project) {
      val var10000: BuildServiceRegistry = target.getGradle().getSharedServices();
      val var17: ExtensionContainer = target.getExtensions();
      var var13: Array<Any> = new Object[]{target};
      val var18: ExtensionContainer = target.getDependencies().getExtensions();
      var13 = new Object[]{target};
   }

   public companion object {
      public const val VERSION: String
   }
}
