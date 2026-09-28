package dev.kikugie.fletching_table.extension

import dev.kikugie.fletching_table.extension.FletchingTableExtension.1
import dev.kikugie.fletching_table.extension.FletchingTableExtension.2.3
import dev.kikugie.fletching_table.extension.FletchingTableExtension.Companion.relocate..inlined.relocate.default.2
import dev.kikugie.fletching_table.extension.FletchingTableExtensionKt.sam.org_gradle_api_Action.0
import dev.kikugie.fletching_table.extension.mixin.FTMixinExtension
import dev.kikugie.fletching_table.util.GradleUtilKt
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.file.FileCopyDetails
import org.gradle.api.plugins.AppliedPlugin
import org.gradle.api.plugins.ExtensionAware
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.AbstractCopyTask
import org.gradle.api.tasks.SourceSet
import org.gradle.kotlin.dsl.RepositoryHandlerExtensionsKt

public abstract class FletchingTableExtension : ExtensionAware {
   public abstract val mixins: NamedDomainObjectContainer<FTMixinExtension>
   public abstract val lang: NamedDomainObjectContainer<LanguageConfigContainer>
   public abstract val j52j: NamedDomainObjectContainer<J52JConfigContainer>
   public abstract val accessConverter: NamedDomainObjectContainer<FTAccessConverterContainer>

   open fun FletchingTableExtension(project: Project) {
      this.getMixins().whenObjectAdded(new 1(project));
      val var4: RepositoryHandler = project.getRepositories();
      RepositoryHandlerExtensionsKt.maven(var4, "https://maven.kikugie.dev/releases", FletchingTableExtension::lambda$0$0$0);
      RepositoryHandlerExtensionsKt.maven(var4, "https://maven.kikugie.dev/snapshots", FletchingTableExtension::lambda$0$0$1);
      RepositoryHandlerExtensionsKt.maven(var4, "https://maven.kikugie.dev/third-pary", FletchingTableExtension::lambda$0$0$2);
      GradleUtilKt.whenKspAdded(project, FletchingTableExtension::lambda$0$1);
      project.afterEvaluate(new 3(this));
   }

   public fun <T : Any> NamedDomainObjectContainer<T>.register(source: SourceSet, action: (T) -> Unit): NamedDomainObjectProvider<T> {
      val var10000: NamedDomainObjectProvider = `$this$register`.register(source.getName(), new 0(action));
      return var10000;
   }

   public fun <T : Any> NamedDomainObjectContainer<T>.register(source: Provider<SourceSet>, action: (T) -> Unit): NamedDomainObjectProvider<T> {
      val var10000: NamedDomainObjectProvider = `$this$register`.register((source.get() as SourceSet).getName(), new 0(action));
      return var10000;
   }

   @JvmStatic
   fun MavenArtifactRepository.`lambda$0$0$0`(): Unit {
      `$this$maven`.setName("KikuGie Releases");
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun MavenArtifactRepository.`lambda$0$0$1`(): Unit {
      `$this$maven`.setName("KikuGie Snapshots");
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun MavenArtifactRepository.`lambda$0$0$2`(): Unit {
      `$this$maven`.setName("KikuGie Third-Party");
      return Unit.INSTANCE;
   }

   @JvmStatic
   fun `lambda$0$1`(`$this_with`: Project, `$this$whenKspAdded`: AppliedPlugin): Unit {
      `$this_with`.getDependencies().add("ksp", "dev.kikugie:fletching-table:0.1.0-alpha.23:api");
      `$this_with`.getDependencies().add("compileOnly", "dev.kikugie:fletching-table:0.1.0-alpha.23:api");
      return Unit.INSTANCE;
   }

   @SourceDebugExtension(["SMAP\nFletchingTableExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FletchingTableExtension.kt\ndev/kikugie/fletching_table/extension/FletchingTableExtension$Companion\n*L\n1#1,171:1\n158#1,12:172\n*S KotlinDebug\n*F\n+ 1 FletchingTableExtension.kt\ndev/kikugie/fletching_table/extension/FletchingTableExtension$Companion\n*L\n-1#1:172,12\n*E\n"])
   public companion object {
      @JvmOverloads
      public inline fun <T : AbstractCopyTask> T.relocate(pattern: String, extension: String? = ..., crossinline action: (FileCopyDetails) -> Unit = ...): T {
         val var9: java.lang.String = PatternEntry.constructor-impl(pattern);
         `$this$relocate`.filesMatching(
            PatternEntry.component1-impl(var9),
            new dev.kikugie.fletching_table.extension.FletchingTableExtension.Companion.relocate.2.1(action, extension, PatternEntry.component2-impl(var9))
         );
         return (T)`$this$relocate`;
      }

      @JvmOverloads
      fun <T extends AbstractCopyTask> T.relocate(pattern: java.lang.String, extension: java.lang.String?): T {
         val var13: java.lang.String = PatternEntry.constructor-impl(pattern);
         `$this$relocate`.filesMatching(
            PatternEntry.component1-impl(var13),
            new dev.kikugie.fletching_table.extension.FletchingTableExtension.Companion.relocate..inlined.relocate.default.1(
               extension, PatternEntry.component2-impl(var13)
            )
         );
         return (T)`$this$relocate`;
      }

      @JvmOverloads
      fun <T extends AbstractCopyTask> T.relocate(pattern: java.lang.String): T {
         val var12: java.lang.String = PatternEntry.constructor-impl(pattern);
         `$this$relocate`.filesMatching(PatternEntry.component1-impl(var12), new 2(null, PatternEntry.component2-impl(var12)));
         return (T)`$this$relocate`;
      }
   }
}
