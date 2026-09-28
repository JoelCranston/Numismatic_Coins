package kotlinx.serialization

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.PROPERTY])
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
annotation class Required(

)
