package kotlinx.serialization.json

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@ExperimentalSerializationApi
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@SerialInfo
annotation class JsonNames(
   val names: Array<out String>
) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : JsonNames {
      fun Impl(names: Array<java.lang.String>) {
         this.names = names;
      }
   }
}
