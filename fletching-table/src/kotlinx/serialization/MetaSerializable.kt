package kotlinx.serialization

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Target(allowedTargets = [AnnotationTarget.ANNOTATION_CLASS])
@ExperimentalSerializationApi
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.ANNOTATION_TYPE])
annotation class MetaSerializable(

)
