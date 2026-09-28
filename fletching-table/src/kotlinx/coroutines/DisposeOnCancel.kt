package kotlinx.coroutines

private class DisposeOnCancel(handle: DisposableHandle) : CancelHandler {
   private final val handle: DisposableHandle

   init {
      this.handle = handle;
   }

   public override fun invoke(cause: Throwable?) {
      this.handle.dispose();
   }

   public override fun toString(): String {
      return "DisposeOnCancel[${this.handle}]";
   }
}
