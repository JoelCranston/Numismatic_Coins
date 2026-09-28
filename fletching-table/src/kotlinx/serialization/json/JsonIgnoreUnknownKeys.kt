package kotlinx.serialization.json

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo

@Target(allowedTargets = [AnnotationTarget.CLASS])
@ExperimentalSerializationApi
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
@SerialInfo
annotation class JsonIgnoreUnknownKeys(

) {
   // $VF: Class flags could not be determined
   @JvmSynthetic
   internal class Impl : JsonIgnoreUnknownKeys
}
