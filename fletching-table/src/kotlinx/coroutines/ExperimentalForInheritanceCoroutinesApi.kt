package kotlinx.coroutines

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(allowedTargets = [AnnotationTarget.CLASS])
@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target([ElementType.TYPE])
@RequiresOptIn(message = "Inheriting from this kotlinx.coroutines API is unstable. Either new methods may be added in the future, which would break the inheritance, or correctly inheriting from it requires fulfilling contracts that may change in the future.", level = RequiresOptIn.Level.WARNING)
annotation class ExperimentalForInheritanceCoroutinesApi(

)
