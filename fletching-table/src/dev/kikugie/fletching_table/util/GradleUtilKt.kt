@file:SourceDebugExtension(["SMAP\nGradleUtil.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GradleUtil.kt\ndev/kikugie/fletching_table/util/GradleUtilKt\n+ 2 ObjectFactoryExtensions.kt\norg/gradle/kotlin/dsl/ObjectFactoryExtensionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 TaskContainerExtensions.kt\norg/gradle/kotlin/dsl/TaskContainerExtensionsKt\n*L\n1#1,72:1\n50#2:73\n50#2:74\n1#3:75\n195#4:76\n227#4:77\n*S KotlinDebug\n*F\n+ 1 GradleUtil.kt\ndev/kikugie/fletching_table/util/GradleUtilKt\n*L\n45#1:73\n48#1:74\n63#1:76\n66#1:77\n*E\n"])

package dev.kikugie.fletching_table.util

import dev.kikugie.fletching_table.util.GradleUtilKt.exclusiveMaven.1
import dev.kikugie.fletching_table.util.GradleUtilKt.inlined.sam.i.org_gradle_api_Action.0
import java.io.File
import java.util.Arrays
import kotlin.jvm.functions.Function1
import kotlin.jvm.internal.Intrinsics
import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonBuilder
import kotlinx.serialization.json.JsonKt
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectCollection
import org.gradle.api.NamedDomainObjectSet
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.invocation.Gradle
import org.gradle.api.model.ObjectFactory
import org.gradle.api.plugins.AppliedPlugin
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Provider
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceRegistration
import org.gradle.api.tasks.SourceSet
import org.gradle.api.tasks.TaskCollection
import org.gradle.api.tasks.TaskContainer
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.NamedDomainObjectCollectionExtensionsKt
import org.gradle.kotlin.dsl.TaskContainerExtensionsKt
import org.gradle.language.jvm.tasks.ProcessResources

public final val JSON: Json = JsonKt.Json$default(null, GradleUtilKt::JSON$lambda$0, 1, null)

internal final val kspSources: File
   internal final get() {
      val var10000: Any = `$this$kspSources`.getLayout().getBuildDirectory().getAsFile().get();
      return FilesKt.resolve(var10000 as File, "generated/ksp");
   }


internal final val isMain: Boolean
   internal final get() {
      return SourceSet.isMain(`$this$isMain`);
   }


internal final val taskModifier: String
   internal final get() {
      var var10000: java.lang.String;
      if (SourceSet.isMain(`$this$taskModifier`)) {
         var10000 = "";
      } else {
         var10000 = `$this$taskModifier`.getName();
         if (var10000.length() > 0) {
            val var5: Char = Character.toUpperCase(var10000.charAt(0));
            var10000 = var10000.substring(1);
            var10000 = "$var5$var10000";
         } else {
            var10000 = var10000;
         }
      }

      return var10000;
   }


internal final val kspKotlinTask: String
   internal final get() {
      if (`$this$kspKotlinTask` != null) {
         val var10000: java.lang.String = task(`$this$kspKotlinTask`, "ksp", "Kotlin");
         if (var10000 != null) {
            return var10000;
         }
      }

      return "kspKotlin";
   }


public operator fun <K : Any, V : Any> MapProperty<K, V>.set(key: K, value: V) {
   `$this$set`.put(key, value);
}

public operator fun <K : Any, V : Any> MapProperty<K, V>.set(key: K, provider: Provider<V>) {
   `$this$set`.put(key, provider);
}

public fun RepositoryHandler.exclusiveMaven(url: String, name: String, vararg groups: String) {
   `$this$exclusiveMaven`.exclusiveContent(new 1(`$this$exclusiveMaven`, url, name, groups));
}

@JvmSynthetic
internal inline fun <reified T : BuildService<*>> Gradle.service(name: String): T {
   var var10000: NamedDomainObjectSet = `$this$service`.getSharedServices().getRegistrations();
   var10000 = (NamedDomainObjectSet)(NamedDomainObjectCollectionExtensionsKt.get(var10000 as NamedDomainObjectCollection, name) as BuildServiceRegistration)
      .getService()
      .get();
   Intrinsics.reifiedOperationMarker(1, "T");
   return (T)(var10000 as BuildService);
}

@JvmSynthetic
internal inline fun <reified T : Any> ObjectFactory.newInstance(vararg parameters: Any, build: (T) -> Unit): T {
   val `parameters$iv`: Array<Any> = Arrays.copyOf(parameters, parameters.length);
   Intrinsics.reifiedOperationMarker(4, "T");
   val var10000: Any = `$this$newInstance`.newInstance(Object::class.java, Arrays.copyOf(`parameters$iv`, `parameters$iv`.length));
   build.invoke(var10000);
   return (T)var10000;
}

@JvmSynthetic
internal inline fun <reified T : Any> ObjectFactory.newInstance(build: Action<T>): T {
   val p0: Array<Any> = new Object[0];
   Intrinsics.reifiedOperationMarker(4, "T");
   val var10000: Any = `$this$newInstance`.newInstance(Object::class.java, Arrays.copyOf(p0, p0.length));
   build.execute(var10000);
   return (T)var10000;
}

internal fun SourceSet.task(verb: String?, target: String?): String {
   val var10000: java.lang.String;
   if (verb == "stonecutter") {
      var10000 = "$verb$target${getTaskModifier(`$this$task`)}";
   } else {
      var10000 = `$this$task`.getTaskName(verb, target);
   }

   return var10000;
}

internal fun Project.processResources(src: SourceSet? = null): TaskProvider<ProcessResources> {
   var `$this$named$iv`: TaskCollection;
   var var5: java.lang.String;
   label11: {
      val var10000: TaskContainer = `$this$processResources`.getTasks();
      `$this$named$iv` = var10000 as TaskCollection;
      if (src != null) {
         var5 = src.getProcessResourcesTaskName();
         if (var5 != null) {
            break label11;
         }
      }

      var5 = "processResources";
   }

   return TaskContainerExtensionsKt.named(`$this$named$iv`, var5, ProcessResources::class);
}

@JvmSynthetic
fun `processResources$default`(var0: Project, var1: SourceSet, var2: Int, var3: Any): TaskProvider {
   if ((var2 and 1) != 0) {
      var1 = null;
   }

   return processResources(var0, var1);
}

internal fun Project.processResources(src: SourceSet? = null, config: (ProcessResources) -> Unit): TaskProvider<ProcessResources> {
   var `$this$named$iv`: TaskCollection;
   var var7: java.lang.String;
   label11: {
      val var10000: TaskContainer = `$this$processResources`.getTasks();
      `$this$named$iv` = var10000 as TaskCollection;
      if (src != null) {
         var7 = src.getProcessResourcesTaskName();
         if (var7 != null) {
            break label11;
         }
      }

      var7 = "processResources";
   }

   val var8: TaskProvider = `$this$named$iv`.named(var7, ProcessResources.class, new 0(config));
   return var8;
}

@JvmSynthetic
fun `processResources$default`(var0: Project, var1: SourceSet, var2: Function1, var3: Int, var4: Any): TaskProvider {
   if ((var3 and 1) != 0) {
      var1 = null;
   }

   return processResources(var0, var1, var2);
}

internal fun Project.whenKspAdded(config: (AppliedPlugin) -> Unit) {
   `$this$whenKspAdded`.getPluginManager()
      .withPlugin("com.google.devtools.ksp", new dev.kikugie.fletching_table.util.GradleUtilKt.sam.org_gradle_api_Action.0(config));
}

fun JsonBuilder.`JSON$lambda$0`(): Unit {
   `$this$Json`.setLenient(true);
   `$this$Json`.setIgnoreUnknownKeys(true);
   `$this$Json`.setPrettyPrint(true);
   return Unit.INSTANCE;
}
