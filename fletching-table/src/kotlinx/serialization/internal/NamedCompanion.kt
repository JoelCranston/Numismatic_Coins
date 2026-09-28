package kotlinx.serialization.internal

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy
import kotlinx.serialization.InternalSerializationApi

@InternalSerializationApi
@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(AnnotationRetention.RUNTIME)
@java.lang.annotation.Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
annotation class NamedCompanion(

)
