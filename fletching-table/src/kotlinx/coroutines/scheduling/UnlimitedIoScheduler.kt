package kotlinx.coroutines.scheduling

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.LimitedDispatcherKt

private object UnlimitedIoScheduler : CoroutineDispatcher {
   @InternalCoroutinesApi
   public override fun dispatchYield(context: CoroutineContext, block: Runnable) {
      DefaultScheduler.INSTANCE.dispatchWithContext$kotlinx_coroutines_core(block, true, true);
   }

   public override fun dispatch(context: CoroutineContext, block: Runnable) {
      DefaultScheduler.INSTANCE.dispatchWithContext$kotlinx_coroutines_core(block, true, false);
   }

   public override fun limitedParallelism(parallelism: Int, name: String?): CoroutineDispatcher {
      LimitedDispatcherKt.checkParallelism(parallelism);
      return if (parallelism >= TasksKt.MAX_POOL_SIZE) LimitedDispatcherKt.namedOrThis(this, name) else super.limitedParallelism(parallelism, name);
   }

   public override fun toString(): String {
      return "Dispatchers.IO";
   }
}
