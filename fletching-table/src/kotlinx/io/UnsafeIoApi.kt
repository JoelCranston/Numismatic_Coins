package kotlinx.io

import java.lang.annotation.RetentionPolicy

@Retention(AnnotationRetention.BINARY)
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@RequiresOptIn(message = "This is an unsafe API and its use requires care. Make sure you fully understand documentation of the declaration marked as UnsafeIoApi", level = RequiresOptIn.Level.WARNING)
annotation class UnsafeIoApi(

)
