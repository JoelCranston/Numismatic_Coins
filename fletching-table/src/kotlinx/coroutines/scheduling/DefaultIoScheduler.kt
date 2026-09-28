package kotlinx.coroutines.scheduling

import java.util.concurrent.Executor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SystemPropsKt

internal object DefaultIoScheduler : ExecutorCoroutineDispatcher, Executor {
   private final val default: CoroutineDispatcher =
      CoroutineDispatcher.limitedParallelism$default(
         UnlimitedIoScheduler.INSTANCE,
         SystemPropsKt.systemProp$default(
            "kotlinx.coroutines.io.parallelism", RangesKt.coerceAtLeast(64, SystemPropsKt.getAVAILABLE_PROCESSORS()), 0, 0, 12, null
         ),
         null,
         2,
         null
      )

   public open val executor: Executor
      public open get() {
         return this;
      }


   public override fun execute(command: Runnable) {
      this.dispatch(EmptyCoroutineContext.INSTANCE, command);
   }

   public override fun limitedParallelism(parallelism: Int, name: String?): CoroutineDispatcher {
      return UnlimitedIoScheduler.INSTANCE.limitedParallelism(parallelism, name);
   }

   public override fun dispatch(context: CoroutineContext, block: Runnable) {
      default.dispatch(context, block);
   }

   @InternalCoroutinesApi
   public override fun dispatchYield(context: CoroutineContext, block: Runnable) {
      default.dispatchYield(context, block);
   }

   public override fun close() {
      throw new IllegalStateException("Cannot be invoked on Dispatchers.IO".toString());
   }

   public override fun toString(): String {
      return "Dispatchers.IO";
   }
}
