package kotlinx.coroutines

import java.io.Closeable
import java.util.concurrent.Executor
import kotlin.coroutines.AbstractCoroutineContextKey
import kotlin.coroutines.CoroutineContext

public abstract class ExecutorCoroutineDispatcher : CoroutineDispatcher, Closeable, AutoCloseable {
   public abstract val executor: Executor

   public abstract override fun close() {
   }

   @ExperimentalStdlibApi
   public companion object Key : AbstractCoroutineContextKey(CoroutineDispatcher.Key, ExecutorCoroutineDispatcher.Key::_init_$lambda$0) {
      @JvmStatic
      fun `_init_$lambda$0`(it: CoroutineContext.Element): ExecutorCoroutineDispatcher {
         return it as? ExecutorCoroutineDispatcher;
      }
   }
}
