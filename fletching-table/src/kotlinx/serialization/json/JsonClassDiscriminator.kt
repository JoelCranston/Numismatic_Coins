package kotlinx.serialization.json

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InheritableSerialInfo

@Target(allowedTargets = [AnnotationTarget.CLASS])
@ExperimentalSerializationApi
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
@InheritableSerialInfo
annotation class JsonClassDiscriminator(
   val discriminator: String
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : JsonClassDiscriminator {
      fun Impl(discriminator: java.lang.String) {
         this.discriminator = discriminator;
      }
   }
}
