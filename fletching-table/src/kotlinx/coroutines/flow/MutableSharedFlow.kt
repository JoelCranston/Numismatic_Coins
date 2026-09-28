package kotlinx.coroutines.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi

@SubclassOptInRequired(markerClass = [ExperimentalForInheritanceCoroutinesApi::class])
public interface MutableSharedFlow<T> : SharedFlow<T>, FlowCollector<T> {
   public val subscriptionCount: StateFlow<Int>

   public abstract override suspend fun emit(value: Any) {
   }

   public abstract fun tryEmit(value: Any): Boolean {
   }

   @ExperimentalCoroutinesApi
   public abstract fun resetReplayCache() {
   }
}
