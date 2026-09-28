package kotlin.internal

import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.FILE])
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target([])
annotation class SuppressBytecodeGeneration(

)
