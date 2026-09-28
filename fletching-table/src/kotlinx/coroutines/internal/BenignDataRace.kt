package kotlinx.coroutines.internal

import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Retention(AnnotationRetention.SOURCE)
@Target(allowedTargets = [AnnotationTarget.FIELD])
@java.lang.annotation.Retention(RetentionPolicy.SOURCE)
@java.lang.annotation.Target([ElementType.FIELD])
annotation class BenignDataRace(

)
