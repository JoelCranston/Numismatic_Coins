package kotlin.experimental

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(AnnotationRetention.SOURCE)
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target([ElementType.TYPE])
@SinceKotlin(version = "2.2")
@ExperimentalMultiplatform
annotation class ExpectRefinement(

)
