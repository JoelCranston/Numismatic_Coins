package kotlinx.coroutines

import java.util.concurrent.Executor
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.internal.DispatchedContinuationKt

private class DispatcherExecutor(dispatcher: CoroutineDispatcher) : Executor {
   public final val dispatcher: CoroutineDispatcher

   init {
      this.dispatcher = dispatcher;
   }

   public override fun execute(block: Runnable) {
      if (DispatchedContinuationKt.safeIsDispatchNeeded(this.dispatcher, EmptyCoroutineContext.INSTANCE)) {
         DispatchedContinuationKt.safeDispatch(this.dispatcher, EmptyCoroutineContext.INSTANCE, block);
      } else {
         block.run();
      }
   }

   public override fun toString(): String {
      return this.dispatcher.toString();
   }
}
