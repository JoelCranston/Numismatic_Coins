package kotlinx.coroutines.future

import java.util.concurrent.Future
import kotlinx.coroutines.JobNode

private class CancelFutureOnCompletion(future: Future<*>) : JobNode {
   private final val future: Future<*>

   public open val onCancelling: Boolean
      public open get() {
         return false;
      }


   init {
      this.future = future;
   }

   public override fun invoke(cause: Throwable?) {
      if (cause != null && !this.future.isDone()) {
         this.future.cancel(false);
      }
   }
}
