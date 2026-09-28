package dev.kikugie.fletching_table.extension.mixin

import dev.kikugie.fletching_table.annotation.MixinEnvironment
import dev.kikugie.fletching_table.annotation.MixinEnvironment.Env
import java.util.Locale
import kotlin.jvm.internal.SourceDebugExtension
import org.gradle.api.provider.MapProperty
import org.gradle.api.provider.Property

@SourceDebugExtension(["SMAP\nFTMixinConfig.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FTMixinConfig.kt\ndev/kikugie/fletching_table/extension/mixin/FTMixinConfig\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,51:1\n13472#2,2:52\n13472#2,2:54\n*S KotlinDebug\n*F\n+ 1 FTMixinConfig.kt\ndev/kikugie/fletching_table/extension/mixin/FTMixinConfig\n*L\n44#1:52,2\n48#1:54,2\n*E\n"])
public interface FTMixinConfig {
   public val alias: Property<String>
   public val config: Property<String>
   public val environment: Property<Env>
   public val overrides: MapProperty<String, Env>

   public open fun env(env: String) {
      val var10000: Property = this.getEnvironment();
      val var10001: java.lang.String = env.toUpperCase(Locale.ROOT);
      var10000.set(MixinEnvironment.Env.valueOf(var10001));
   }

   public open fun env(env: String, vararg packages: String) {
      for (Object element$iv : packages) {
         val var10000: MapProperty = this.getOverrides();
         val var10002: java.lang.String = env.toUpperCase(Locale.ROOT);
         var10000.put(`element$iv`, MixinEnvironment.Env.valueOf(var10002));
      }
   }

   public open fun env(env: Env, vararg packages: String) {
      for (Object element$iv : packages) {
         this.getOverrides().put(`element$iv`, env);
      }
   }
}
