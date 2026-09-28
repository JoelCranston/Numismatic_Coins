package kotlinx.coroutines

import kotlin.coroutines.CoroutineContext

internal object Unconfined : CoroutineDispatcher {
   public override fun limitedParallelism(parallelism: Int, name: String?): CoroutineDispatcher {
      throw new UnsupportedOperationException("limitedParallelism is not supported for Dispatchers.Unconfined");
   }

   public override fun isDispatchNeeded(context: CoroutineContext): Boolean {
      return false;
   }

   public override fun dispatch(context: CoroutineContext, block: Runnable) {
      val yieldContext: YieldContext = context.get(YieldContext.Key);
      if (yieldContext != null) {
         yieldContext.dispatcherWasUnconfined = true;
      } else {
         throw new UnsupportedOperationException(
            "Dispatchers.Unconfined.dispatch function can only be used by the yield function. If you wrap Unconfined dispatcher in your code, make sure you properly delegate isDispatchNeeded and dispatch calls."
         );
      }
   }

   public override fun toString(): String {
      return "Dispatchers.Unconfined";
   }
}
