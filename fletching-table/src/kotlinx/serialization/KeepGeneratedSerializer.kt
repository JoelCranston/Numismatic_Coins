package kotlinx.serialization

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@ExperimentalSerializationApi
@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
annotation class KeepGeneratedSerializer(

)
