package kotlinx.io

import java.lang.annotation.Documented
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@RequiresOptIn(message = "This is a delicate API and its use requires care. Make sure you fully read and understand documentation of the declaration that is marked as a delicate API.", level = RequiresOptIn.Level.WARNING)
annotation class DelicateIoApi(

)
