package kotlinx.coroutines.flow

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi

@SubclassOptInRequired(markerClass = [ExperimentalForInheritanceCoroutinesApi::class])
public interface MutableStateFlow<T> : StateFlow<T>, MutableSharedFlow<T> {
   public var value: Any
      internal final set

   public abstract fun compareAndSet(expect: Any, update: Any): Boolean {
   }
}
