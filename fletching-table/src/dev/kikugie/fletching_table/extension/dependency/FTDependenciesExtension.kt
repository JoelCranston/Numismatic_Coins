package dev.kikugie.fletching_table.extension.dependency

import dev.kikugie.fletching_table.extension.dependency.FTDependenciesExtension.modrinth.1
import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property

@SourceDebugExtension(["SMAP\nFTDependenciesExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTDependenciesExtension.kt\ndev/kikugie/fletching_table/extension/dependency/FTDependenciesExtension\n+ 2 ObjectFactoryExtensions.kt\norg/gradle/kotlin/dsl/ObjectFactoryExtensionsKt\n*L\n1#1,109:1\n59#2:110\n*S KotlinDebug\n*F\n+ 1 FTDependenciesExtension.kt\ndev/kikugie/fletching_table/extension/dependency/FTDependenciesExtension\n*L\n30#1:110\n*E\n"])
public abstract class FTDependenciesExtension {
   private final val project: Project
   public final val minecraft: Property<String>

   open fun FTDependenciesExtension(project: Project) {
      this.project = project;
      val var10001: ObjectFactory = this.project.getObjects();
      val var4: Property = var10001.property(java.lang.String.class);
      this.minecraft = var4;
   }

   @JvmOverloads
   public fun modrinth(
      id: String,
      minecraft: String = this.minecraft.get(),
      loaders: String = FTDependenciesExtensionKt.access$guessLoader(this.project),
      config: Action<FTModSpec> = 1.INSTANCE as Action
   ): Dependency {
      return ModLookupFunctionsKt.single(
         this.project,
         new ModQuery(id, "modrinth", FTDependenciesExtensionKt.access$components(minecraft), FTDependenciesExtensionKt.access$components(loaders)),
         config
      );
   }

   @JvmOverloads
   public fun curseforge(
      id: String,
      minecraft: String = this.minecraft.get(),
      loaders: String = FTDependenciesExtensionKt.access$guessLoader(this.project),
      config: Action<FTModSpec> = dev.kikugie.fletching_table.extension.dependency.FTDependenciesExtension.curseforge.1.INSTANCE as Action
   ): Dependency {
      return ModLookupFunctionsKt.single(
         this.project,
         new ModQuery(id, "curseforge", FTDependenciesExtensionKt.access$components(minecraft), FTDependenciesExtensionKt.access$components(loaders)),
         config
      );
   }

   public fun modrinthBundle(
      id: String,
      minecraft: String = this.minecraft.get(),
      loaders: String = FTDependenciesExtensionKt.access$guessLoader(this.project),
      config: Action<FTBundleSpec> = dev.kikugie.fletching_table.extension.dependency.FTDependenciesExtension.modrinthBundle.1.INSTANCE as Action
   ): List<Dependency> {
      return ModLookupFunctionsKt.bundle(
         this.project,
         new ModQuery(id, "modrinth", FTDependenciesExtensionKt.access$components(minecraft), FTDependenciesExtensionKt.access$components(loaders)),
         config
      );
   }

   public fun curseforgeBundle(
      id: String,
      minecraft: String = this.minecraft.get(),
      loaders: String = FTDependenciesExtensionKt.access$guessLoader(this.project),
      config: Action<FTBundleSpec> = dev.kikugie.fletching_table.extension.dependency.FTDependenciesExtension.curseforgeBundle.1.INSTANCE as Action
   ): List<Dependency> {
      return ModLookupFunctionsKt.bundle(
         this.project,
         new ModQuery(id, "curseforge", FTDependenciesExtensionKt.access$components(minecraft), FTDependenciesExtensionKt.access$components(loaders)),
         config
      );
   }

   @JvmOverloads
   fun modrinth(id: java.lang.String, minecraft: java.lang.String, loaders: java.lang.String): Dependency {
      return modrinth$default(this, id, minecraft, loaders, null, 8, null);
   }

   @JvmOverloads
   fun modrinth(id: java.lang.String, minecraft: java.lang.String): Dependency {
      return modrinth$default(this, id, minecraft, null, null, 12, null);
   }

   @JvmOverloads
   fun modrinth(id: java.lang.String): Dependency {
      return modrinth$default(this, id, null, null, null, 14, null);
   }

   @JvmOverloads
   fun curseforge(id: java.lang.String, minecraft: java.lang.String, loaders: java.lang.String): Dependency {
      return curseforge$default(this, id, minecraft, loaders, null, 8, null);
   }

   @JvmOverloads
   fun curseforge(id: java.lang.String, minecraft: java.lang.String): Dependency {
      return curseforge$default(this, id, minecraft, null, null, 12, null);
   }

   @JvmOverloads
   fun curseforge(id: java.lang.String): Dependency {
      return curseforge$default(this, id, null, null, null, 14, null);
   }
}
