package kotlinx.coroutines

import java.util.concurrent.Future

private class CancelFutureOnCancel(future: Future<*>) : CancelHandler {
   private final val future: Future<*>

   init {
      this.future = future;
   }

   public override fun invoke(cause: Throwable?) {
      this.future.cancel(false);
   }

   public override fun toString(): String {
      return "CancelFutureOnCancel[${this.future}]";
   }
}
