package kotlinx.coroutines.flow

import kotlinx.coroutines.ExperimentalForInheritanceCoroutinesApi

@SubclassOptInRequired(markerClass = [ExperimentalForInheritanceCoroutinesApi::class])
public interface SharedFlow<T> : Flow<T> {
   public val replayCache: List<Any>

   public abstract override suspend fun collect(collector: FlowCollector<Any>): Nothing {
   }
}
