package kotlinx.coroutines

private class InvokeOnCompletion(handler: (Throwable?) -> Unit) : JobNode {
   private final val handler: (Throwable?) -> Unit

   public open val onCancelling: Boolean
      public open get() {
         return false;
      }


   init {
      this.handler = handler;
   }

   public override fun invoke(cause: Throwable?) {
      this.handler.invoke(cause);
   }
}
