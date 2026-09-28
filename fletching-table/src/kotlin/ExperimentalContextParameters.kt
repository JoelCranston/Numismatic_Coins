package kotlin

import java.lang.annotation.Documented
import java.lang.annotation.RetentionPolicy

@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@Documented
@java.lang.annotation.Retention(RetentionPolicy.CLASS)
@RequiresOptIn(message = "The API is related to the experimental feature \"context parameters\" (see KEEP-367) and may be changed or removed in any future release.", level = RequiresOptIn.Level.ERROR)
@SinceKotlin(version = "2.2")
annotation class ExperimentalContextParameters(

)
