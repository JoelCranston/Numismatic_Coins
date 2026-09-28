package kotlinx.coroutines

import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

@JvmName(name = "from")
public fun ExecutorService.asCoroutineDispatcher(): ExecutorCoroutineDispatcher {
   return new ExecutorCoroutineDispatcherImpl(`$this$asCoroutineDispatcher`);
}

@JvmName(name = "from")
public fun Executor.asCoroutineDispatcher(): CoroutineDispatcher {
   return if ((`$this$asCoroutineDispatcher` as? DispatcherExecutor) != null && (`$this$asCoroutineDispatcher` as? DispatcherExecutor).dispatcher != null)
      (`$this$asCoroutineDispatcher` as? DispatcherExecutor).dispatcher
      else
      new ExecutorCoroutineDispatcherImpl(`$this$asCoroutineDispatcher`);
}

public fun CoroutineDispatcher.asExecutor(): Executor {
   val var10000: ExecutorCoroutineDispatcher = `$this$asExecutor` as? ExecutorCoroutineDispatcher;
   if ((`$this$asExecutor` as? ExecutorCoroutineDispatcher) != null) {
      val var1: Executor = var10000.getExecutor();
      if (var1 != null) {
         return var1;
      }
   }

   return new DispatcherExecutor(`$this$asExecutor`);
}

/** @deprecated */
@ExperimentalCoroutinesApi
@JvmSynthetic
fun `CloseableCoroutineDispatcher$annotations`() {
}
