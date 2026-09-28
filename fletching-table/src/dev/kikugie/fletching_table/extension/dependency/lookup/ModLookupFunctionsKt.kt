@file:SourceDebugExtension(["SMAP\nModLookupFunctions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ModLookupFunctions.kt\ndev/kikugie/fletching_table/extension/dependency/lookup/ModLookupFunctionsKt\n+ 2 GradleUtil.kt\ndev/kikugie/fletching_table/util/GradleUtilKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n42#2:75\n1#3:76\n*S KotlinDebug\n*F\n+ 1 ModLookupFunctions.kt\ndev/kikugie/fletching_table/extension/dependency/lookup/ModLookupFunctionsKt\n*L\n20#1:75\n*E\n"])

package dev.kikugie.fletching_table.extension.dependency.lookup

import dev.kikugie.commons.collections.PresentationKt
import dev.kikugie.fletching_table.extension.dependency.FTBundleSpec
import dev.kikugie.fletching_table.extension.dependency.FTModSpec
import dev.kikugie.fletching_table.extension.dependency.ModVersionPredicate
import dev.kikugie.fletching_table.extension.dependency.data.ModDependency
import dev.kikugie.fletching_table.extension.dependency.data.ModQuery
import dev.kikugie.fletching_table.extension.dependency.data.ModVersion
import dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.lookup.1
import java.util.NoSuchElementException
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.IntrinsicsKt
import kotlin.coroutines.jvm.internal.SpillingKt
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.coroutines.BuildersKt
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectCollection
import org.gradle.api.NamedDomainObjectSet
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.invocation.Gradle
import org.gradle.api.services.BuildServiceRegistration
import org.gradle.kotlin.dsl.NamedDomainObjectCollectionExtensionsKt

private final val lookupService: LookupBuildService
   private final get() {
      var var10000: Gradle = `$this$lookupService`.getGradle();
      val var4: NamedDomainObjectSet = var10000.getSharedServices().getRegistrations();
      var10000 = (Gradle)(NamedDomainObjectCollectionExtensionsKt.get(var4 as NamedDomainObjectCollection, "LookupBuildService") as BuildServiceRegistration)
         .getService()
         .get();
      if (var10000 == null) {
         throw new NullPointerException("null cannot be cast to non-null type dev.kikugie.fletching_table.extension.dependency.lookup.LookupBuildService");
      } else {
         return (var10000 as LookupBuildService) as LookupBuildService;
      }
   }


private fun Project.log(version: ModVersion) {
   if (!(version.getFileUrl() == "%CACHED%")) {
      `$this$log`.getLogger()
         .lifecycle(
            "Resolved mod ${version.getFileUrl()} for ${PresentationKt.present$default(
               SetsKt.plus(version.getModLoaders(), version.getGameVersions()), 0, 1, null
            )}"
         );
   }
}

private fun List<ModVersion>.constraint(query: ModQuery, spec: FTModSpec): ModVersion? {
   if (`$this$constraint`.isEmpty()) {
      throw new NoSuchElementException("Failed to find any results for $query");
   } else {
      val constraint: ModVersionPredicate = spec.getConstraint().getOrNull() as ModVersionPredicate;
      val var10000: ModVersion;
      if (constraint == null) {
         var10000 = CollectionsKt.first(`$this$constraint`);
      } else {
         val var4: java.lang.Iterable = `$this$constraint`;
         val var5: ModVersionPredicate = constraint;
         val var7: java.util.Iterator = var4.iterator();

         while (true) {
            if (!var7.hasNext()) {
               var11 = null;
               break;
            }

            val var8: Any = var7.next();
            if (var5.check(var8 as ModVersion)) {
               var11 = var8;
               break;
            }
         }

         var10000 = var11 as ModVersion;
      }

      return var10000;
   }
}

private suspend fun Project.lookup(query: ModQuery, spec: FTModSpec): ModVersion? {
   var `$continuation`: Continuation;
   label20: {
      if (`$completion` is 1) {
         `$continuation` = `$completion` as 1;
         if (((`$completion` as 1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label20;
         }
      }

      `$continuation` = new 1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var6: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         var10000 = getLookupService(`$this$lookup`);
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$lookup`);
         `$continuation`.L$1 = query;
         `$continuation`.L$2 = spec;
         `$continuation`.label = 1;
         var10000 = (LookupBuildService)var10000.get(query, spec, `$continuation`);
         if (var10000 === var6) {
            return var6;
         }
         break;
      case 1:
         spec = `$continuation`.L$2 as FTModSpec;
         query = `$continuation`.L$1 as ModQuery;
         `$this$lookup` = `$continuation`.L$0 as Project;
         ResultKt.throwOnFailure(`$result`);
         var10000 = (LookupBuildService)`$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return constraint(var10000 as MutableList<ModVersion>, query, spec);
}

private suspend fun Project.dependency(query: ModQuery, dependency: ModDependency, version: ModVersion, spec: FTBundleSpec): ModVersion? {
   var `$continuation`: Continuation;
   label36: {
      if (`$completion` is dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.dependency.1) {
         `$continuation` = `$completion` as dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.dependency.1;
         if (((`$completion` as dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.dependency.1).label and Integer.MIN_VALUE) != 0) {
            `$continuation`.label -= Integer.MIN_VALUE;
            break label36;
         }
      }

      `$continuation` = new dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.dependency.1(`$completion`);
   }

   val `$result`: Any = `$continuation`.result;
   val var11: Any = IntrinsicsKt.getCOROUTINE_SUSPENDED();
   var var10000: Any;
   switch ($continuation.label) {
      case 0:
         ResultKt.throwOnFailure(`$result`);
         if (dependency is ModDependency.Project) {
            val var21: ModQuery = query.of(dependency as ModDependency.Project, version);
            val var22: FTModSpec = spec;
            `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$dependency`);
            `$continuation`.L$1 = SpillingKt.nullOutSpilledVariable(query);
            `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(dependency);
            `$continuation`.L$3 = SpillingKt.nullOutSpilledVariable(version);
            `$continuation`.L$4 = SpillingKt.nullOutSpilledVariable(spec);
            `$continuation`.label = 1;
            var10000 = lookup(`$this$dependency`, var21, var22, `$continuation`);
            if (var10000 === var11) {
               return var11;
            }

            return var10000;
         }

         if (dependency !is ModDependency.Version) {
            throw new NoWhenBranchMatchedException();
         }

         var10000 = getLookupService(`$this$dependency`);
         val var10001: ModDependency.Version = dependency as ModDependency.Version;
         val var10002: FTModSpec = spec;
         `$continuation`.L$0 = SpillingKt.nullOutSpilledVariable(`$this$dependency`);
         `$continuation`.L$1 = query;
         `$continuation`.L$2 = SpillingKt.nullOutSpilledVariable(dependency);
         `$continuation`.L$3 = version;
         `$continuation`.L$4 = spec;
         `$continuation`.label = 2;
         var10000 = (LookupBuildService)var10000.get(var10001, var10002, `$continuation`);
         if (var10000 === var11) {
            return var11;
         }
         break;
      case 1:
         spec = `$continuation`.L$4 as FTBundleSpec;
         version = `$continuation`.L$3 as ModVersion;
         dependency = `$continuation`.L$2 as ModDependency;
         query = `$continuation`.L$1 as ModQuery;
         `$this$dependency` = `$continuation`.L$0 as Project;
         ResultKt.throwOnFailure(`$result`);
         return `$result`;
      case 2:
         spec = `$continuation`.L$4 as FTBundleSpec;
         version = `$continuation`.L$3 as ModVersion;
         dependency = `$continuation`.L$2 as ModDependency;
         query = `$continuation`.L$1 as ModQuery;
         `$this$dependency` = `$continuation`.L$0 as Project;
         ResultKt.throwOnFailure(`$result`);
         var10000 = (LookupBuildService)`$result`;
         break;
      default:
         throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
   }

   return constraint(CollectionsKt.listOf(var10000 as ModVersion), query.of((var10000 as ModVersion).getProject(), version), spec);
}

internal fun Project.single(query: ModQuery, config: Action<FTModSpec>): Dependency {
   val var3: Any = BuildersKt.runBlocking$default(
      null, new dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.single.1(`$this$single`, config, query, null), 1, null
   );
   return var3 as Dependency;
}

internal fun Project.bundle(query: ModQuery, config: Action<FTBundleSpec>): List<Dependency> {
   return BuildersKt.runBlocking$default(
      null, new dev.kikugie.fletching_table.extension.dependency.lookup.ModLookupFunctionsKt.bundle.1(`$this$bundle`, config, query, null), 1, null
   ) as MutableList<Dependency>;
}

@JvmSynthetic
fun `access$lookup`(`$receiver`: Project, query: ModQuery, spec: FTModSpec, `$completion`: Continuation): Any {
   return lookup(`$receiver`, query, spec, `$completion`);
}

@JvmSynthetic
fun `access$dependency`(`$receiver`: Project, query: ModQuery, dependency: ModDependency, version: ModVersion, spec: FTBundleSpec, `$completion`: Continuation): Any {
   return dependency(`$receiver`, query, dependency, version, spec, `$completion`);
}

@JvmSynthetic
fun `access$log`(`$receiver`: Project, version: ModVersion) {
   log(`$receiver`, version);
}
