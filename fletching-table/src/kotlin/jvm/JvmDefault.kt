package kotlin.jvm

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY])
@Deprecated(message = "Switch to new -jvm-default modes: `enable` or `no-compatibility`", level = DeprecationLevel.HIDDEN)
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.METHOD])
@SinceKotlin(version = "1.2")
annotation class JvmDefault(

)
