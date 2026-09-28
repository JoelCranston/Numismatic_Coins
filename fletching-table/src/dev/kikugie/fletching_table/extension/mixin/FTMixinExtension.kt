package dev.kikugie.fletching_table.extension.mixin

import dev.kikugie.fletching_table.extension.mixin.FTMixinExtension.mixin.1
import java.util.Arrays
import javax.inject.Inject
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.Action
import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

@SourceDebugExtension(["SMAP\nFTMixinExtension.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTMixinExtension.kt\ndev/kikugie/fletching_table/extension/mixin/FTMixinExtension\n+ 2 GradleUtil.kt\ndev/kikugie/fletching_table/util/GradleUtilKt\n+ 3 ObjectFactoryExtensions.kt\norg/gradle/kotlin/dsl/ObjectFactoryExtensionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,80:1\n45#2:81\n50#3:82\n1#4:83\n*S KotlinDebug\n*F\n+ 1 FTMixinExtension.kt\ndev/kikugie/fletching_table/extension/mixin/FTMixinExtension\n*L\n70#1:81\n70#1:82\n*E\n"])
public abstract class FTMixinExtension : Named {
   private final val objects: ObjectFactory
   public abstract val automatic: Property<Boolean>
   public abstract val configs: MapProperty<String, FTMixinConfig>

   @Inject
   open fun FTMixinExtension(objects: ObjectFactory) {
      this.objects = objects;
   }

   public fun mixin(alias: String, file: String) {
      this.mixin(alias, file, 1.INSTANCE);
   }

   public fun mixin(alias: String, file: String, action: Action<FTMixinConfig>) {
      val `$this$newInstance$iv`: ObjectFactory = this.objects;
      val it: Array<Any> = new Object[0];
      val `parameters$iv$iv`: Array<Any> = Arrays.copyOf(it, it.length);
      val var10000: Any = `$this$newInstance$iv`.newInstance(FTMixinConfig.class, Arrays.copyOf(`parameters$iv$iv`, `parameters$iv$iv`.length));
      val `$this$mixin_u24lambda_u240`: FTMixinConfig = var10000 as FTMixinConfig;
      (var10000 as FTMixinConfig).getAlias().value(alias).disallowChanges();
      `$this$mixin_u24lambda_u240`.getConfig().set(file);
      action.execute(`$this$mixin_u24lambda_u240`);
      this.getConfigs().put(alias, var10000 as FTMixinConfig);
   }

   internal fun init(project: Project) {
      project.afterEvaluate(new dev.kikugie.fletching_table.extension.mixin.FTMixinExtension.init.1(project, this));
   }
}
