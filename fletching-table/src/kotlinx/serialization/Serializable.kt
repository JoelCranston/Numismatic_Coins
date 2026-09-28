package kotlinx.serialization

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.PROPERTY, AnnotationTarget.CLASS, AnnotationTarget.TYPE])
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE, ElementType.TYPE_USE])
annotation class Serializable(
   val with: KClass<out KSerializer<*>> = KSerializer::class.java
)
