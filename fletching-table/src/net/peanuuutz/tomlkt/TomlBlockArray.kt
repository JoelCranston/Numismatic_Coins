package net.peanuuutz.tomlkt

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class TomlBlockArray(
   val itemsPerLine: Int = 1
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlBlockArray {
      fun Impl(itemsPerLine: Int) {
         this.itemsPerLine = itemsPerLine;
      }
   }
}
