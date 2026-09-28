package kotlinx.coroutines.selects

import kotlin.coroutines.Continuation
import kotlin.time.Duration
import kotlinx.coroutines.DelayKt
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
public fun <R> SelectBuilder<R>.onTimeout(timeMillis: Long, block: (Continuation<R>) -> Any?) {
   `$this$onTimeout`.invoke(new OnTimeout(timeMillis).getSelectClause(), block);
}

@ExperimentalCoroutinesApi
public fun <R> SelectBuilder<R>.onTimeout(timeout: Duration, block: (Continuation<R>) -> Any?) {
   onTimeout(`$this$onTimeout_u2d8Mi8wO0`, DelayKt.toDelayMillis-LRDsOJo(timeout), block);
}
