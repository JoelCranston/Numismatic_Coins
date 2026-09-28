package net.peanuuutz.tomlkt

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class TomlComment(
   val text: String
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlComment {
      fun Impl(text: java.lang.String) {
         this.text = text;
      }
   }
}
