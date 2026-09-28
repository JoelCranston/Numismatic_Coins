package kotlinx.coroutines.internal

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.DefaultExecutorKt
import kotlinx.coroutines.Delay
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.InternalCoroutinesApi

internal class NamedDispatcher(dispatcher: CoroutineDispatcher, name: String) : CoroutineDispatcher, Delay {
   private final val dispatcher: CoroutineDispatcher
   private final val name: String

   init {
      var var10001: Delay = dispatcher as? Delay;
      if ((dispatcher as? Delay) == null) {
         var10001 = DefaultExecutorKt.getDefaultDelay();
      }

      this.$$delegate_0 = var10001;
      this.dispatcher = dispatcher;
      this.name = name;
   }

   public override fun isDispatchNeeded(context: CoroutineContext): Boolean {
      return this.dispatcher.isDispatchNeeded(context);
   }

   public override fun dispatch(context: CoroutineContext, block: Runnable) {
      this.dispatcher.dispatch(context, block);
   }

   @InternalCoroutinesApi
   public override fun dispatchYield(context: CoroutineContext, block: Runnable) {
      this.dispatcher.dispatchYield(context, block);
   }

   public override fun toString(): String {
      return this.name;
   }

   @Deprecated(message = "Deprecated without replacement as an internal method never intended for public use", level = DeprecationLevel.ERROR)
   public override suspend fun delay(time: Long) {
      return this.$$delegate_0.delay(time, `$completion`);
   }

   public override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
      this.$$delegate_0.scheduleResumeAfterDelay(timeMillis, continuation);
   }

   public override fun invokeOnTimeout(timeMillis: Long, block: Runnable, context: CoroutineContext): DisposableHandle {
      return this.$$delegate_0.invokeOnTimeout(timeMillis, block, context);
   }
}
