package kotlinx.io.bytestring.unsafe

import java.lang.annotation.Documented
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Retention(AnnotationRetention.BINARY)
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@RequiresOptIn(message = "This is a unsafe API and its use may corrupt the data stored in a byte string. Make sure you fully read and understand documentation of the declaration that is marked as an unsafe API.", level = RequiresOptIn.Level.ERROR)
annotation class UnsafeByteStringApi(

)
