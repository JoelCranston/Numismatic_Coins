package dev.kikugie.fletching_table.extension.dependency

import dev.kikugie.fletching_table.FTPluginVariant
import dev.kikugie.fletching_table.FletchingTableFabricPlugin
import dev.kikugie.fletching_table.FletchingTableLexforgePlugin
import dev.kikugie.fletching_table.FletchingTableNeoforgePlugin
import org.gradle.api.Project

private fun String.components(): Set<String> {
   return if (`$this$components`.length() != 0 && !(`$this$components` == "*"))
      CollectionsKt.toSet(StringsKt.split$default(`$this$components`, new char[]{' '}, false, 0, 6, null))
      else
      SetsKt.emptySet();
}

private fun guessLoader(project: Project): String {
   val var1: FTPluginVariant = CollectionsKt.singleOrNull(FTPluginVariant.Companion.loaded(project));
   val var10000: java.lang.String;
   if (var1 is FletchingTableFabricPlugin) {
      var10000 = "fabric";
   } else if (var1 is FletchingTableNeoforgePlugin) {
      var10000 = "neoforge";
   } else {
      if (var1 !is FletchingTableLexforgePlugin) {
         throw new IllegalStateException("Unable to determine the project mod loader - please specify it explicitly.".toString());
      }

      var10000 = "forge";
   }

   return var10000;
}

@JvmSynthetic
fun `access$components`(`$receiver`: java.lang.String): java.util.Set {
   return components(`$receiver`);
}

@JvmSynthetic
fun `access$guessLoader`(project: Project): java.lang.String {
   return guessLoader(project);
}
