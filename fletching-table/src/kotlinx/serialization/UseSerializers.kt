package kotlinx.serialization

import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(allowedTargets = [AnnotationTarget.FILE])
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target([])
annotation class UseSerializers(
   val serializerClasses: Array<out KClass<out KSerializer<*>>>
)
