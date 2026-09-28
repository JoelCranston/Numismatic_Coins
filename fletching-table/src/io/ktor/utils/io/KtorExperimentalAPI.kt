package io.ktor.utils.io

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.TYPEALIAS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR])
@Deprecated(message = "This annotation is no longer used and there is no need to opt-in into it.", level = DeprecationLevel.ERROR)
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.CONSTRUCTOR])
@RequiresOptIn(message = "This API is experimental. It could be removed or changed in future releases, or its behaviour may be different.", level = RequiresOptIn.Level.WARNING)
annotation class KtorExperimentalAPI(

)
