package kotlinx.io

import java.lang.annotation.Documented
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@RequiresOptIn(message = "This is an internal API and its use requires care. It is subject to change or removal and is not intended for use outside the library.Make sure you fully read and understand documentation of the declaration that is marked as an internal API.", level = RequiresOptIn.Level.ERROR)
annotation class InternalIoApi(

)
