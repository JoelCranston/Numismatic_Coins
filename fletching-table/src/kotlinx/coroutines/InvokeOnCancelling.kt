package kotlinx.coroutines

import kotlinx.atomicfu.AtomicBoolean

private class InvokeOnCancelling(handler: (Throwable?) -> Unit) : JobNode {
   private final val handler: (Throwable?) -> Unit
   private final val _invoked: AtomicBoolean

   public open val onCancelling: Boolean
      public open get() {
         return true;
      }


   init {
      this.handler = handler;
   }

   public override fun invoke(cause: Throwable?) {
      if (get_invoked$volatile$FU().compareAndSet(this, 0, 1)) {
         this.handler.invoke(cause);
      }
   }
}
