package net.peanuuutz.tomlkt

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.InheritableSerialInfo

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
@InheritableSerialInfo
annotation class TomlClassDiscriminator(
   val discriminator: String
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : TomlClassDiscriminator {
      fun Impl(discriminator: java.lang.String) {
         this.discriminator = discriminator;
      }
   }
}
