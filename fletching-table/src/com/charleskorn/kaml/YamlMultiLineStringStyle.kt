package com.charleskorn.kaml

import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target([])
@SerialInfo
annotation class YamlMultiLineStringStyle(
   val multiLineStringStyle: MultiLineStringStyle
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : YamlMultiLineStringStyle {
      fun Impl(multiLineStringStyle: MultiLineStringStyle) {
         this.multiLineStringStyle = multiLineStringStyle;
      }
   }
}
