package kotlinx.coroutines

private class DisposeOnCompletion(handle: DisposableHandle) : JobNode {
   private final val handle: DisposableHandle

   public open val onCancelling: Boolean
      public open get() {
         return false;
      }


   init {
      this.handle = handle;
   }

   public override fun invoke(cause: Throwable?) {
      this.handle.dispose();
   }
}
