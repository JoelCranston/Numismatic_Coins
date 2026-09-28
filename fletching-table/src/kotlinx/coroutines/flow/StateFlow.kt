package kotlinx.coroutines.flow

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi

@SubclassOptInRequired(markerClass = [ExperimentalForInheritanceCoroutinesApi::class])
public interface StateFlow<T> : SharedFlow<T> {
   public val value: Any
}
