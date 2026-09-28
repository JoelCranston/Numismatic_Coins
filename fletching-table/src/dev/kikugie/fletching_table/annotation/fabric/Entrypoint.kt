package dev.kikugie.fletching_table.annotation.fabric

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.FIELD])
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target([ElementType.TYPE, ElementType.FIELD, ElementType.METHOD])
annotation class Entrypoint(
   val value: Array<out String> = {}
) {
   public companion object {
      public const val MAIN: String = "main"
      public const val CLIENT: String = "client"
      public const val SERVER: String = "server"
      public const val PRE_LAUNCH: String = "preLaunch"
   }
}
