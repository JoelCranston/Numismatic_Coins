package kotlinx.serialization

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.PROPERTY, AnnotationTarget.CLASS])
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
annotation class SerialName(
   val value: String
)
