package dev.kikugie.fletching_table.extension.dependency

import dev.kikugie.fletching_table.extension.dependency.data.ModDependency
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency.DependencyType
import java.util.Locale
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.Property

@SourceDebugExtension(["SMAP\nFTModSpec.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTModSpec.kt\ndev/kikugie/fletching_table/extension/dependency/FTBundleSpec\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,40:1\n13472#2,2:41\n*S KotlinDebug\n*F\n+ 1 FTModSpec.kt\ndev/kikugie/fletching_table/extension/dependency/FTBundleSpec\n*L\n32#1:41,2\n*E\n"])
public interface FTBundleSpec : FTModSpec {
   public val recursive: Property<Boolean>
   public val includes: ListProperty<DependencyType>

   public open fun include(vararg types: String) {
      for (Object element$iv : types) {
         val var10000: ListProperty = this.getIncludes();
         val var10001: java.lang.String = `element$iv`.toUpperCase(Locale.ROOT);
         var10000.add(ModDependency.DependencyType.valueOf(var10001));
      }
   }
}
