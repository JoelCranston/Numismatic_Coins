package dev.kikugie.fletching_table.annotation

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.enums.EnumEntries

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target([ElementType.TYPE])
annotation class MixinEnvironment(
   val value: String = "default",
   val type: dev.kikugie.fletching_table.annotation.MixinEnvironment.Env = MixinEnvironment.Env.DEFAULT
) {
   public enum class Env {
      DEFAULT,
      MAIN,
      CLIENT,
      SERVER
      @JvmStatic
      fun getEntries(): EnumEntries<MixinEnvironment.Env> {
         return $ENTRIES;
      }
   }
}
