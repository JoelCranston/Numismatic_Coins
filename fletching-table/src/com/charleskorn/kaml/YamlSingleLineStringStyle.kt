package com.charleskorn.kaml

import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target([])
@SerialInfo
annotation class YamlSingleLineStringStyle(
   val singleLineStringStyle: SingleLineStringStyle
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : YamlSingleLineStringStyle {
      fun Impl(singleLineStringStyle: SingleLineStringStyle) {
         this.singleLineStringStyle = singleLineStringStyle;
      }
   }
}
