package com.charleskorn.kaml

import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target([])
@SerialInfo
annotation class YamlComment(
   val lines: Array<out String>
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : YamlComment {
      fun Impl(lines: Array<java.lang.String>) {
         this.lines = lines;
      }
   }
}
