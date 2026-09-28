package kotlinx.serialization

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@MustBeDocumented
@Target(allowedTargets = [])
@Documented
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([])
@RequiresOptIn(message = "This class or interface should not be inherited/implemented outside of kotlinx.serialization library. Note it is still permitted to use it directly. Read its documentation about inheritance for details.", level = RequiresOptIn.Level.ERROR)
annotation class SealedSerializationApi(

)
