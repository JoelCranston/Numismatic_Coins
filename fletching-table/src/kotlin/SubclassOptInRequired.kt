package kotlin

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@java.lang.annotation.Target([ElementType.TYPE])
@SinceKotlin(version = "2.1")
@WasExperimental(markerClass = [ExperimentalSubclassOptIn::class])
annotation class SubclassOptInRequired(
   val markerClass: Array<out KClass<out Annotation>>
)
