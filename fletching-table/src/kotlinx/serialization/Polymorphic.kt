package kotlinx.serialization

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.PROPERTY, AnnotationTarget.TYPE, AnnotationTarget.CLASS])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE, ElementType.TYPE_USE])
annotation class Polymorphic(

)
