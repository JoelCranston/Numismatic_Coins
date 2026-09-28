package kotlinx.coroutines

private class ChildContinuation(child: CancellableContinuationImpl<*>) : JobNode {
   public final val child: CancellableContinuationImpl<*>

   public open val onCancelling: Boolean
      public open get() {
         return true;
      }


   init {
      this.child = child;
   }

   public override fun invoke(cause: Throwable?) {
      this.child.parentCancelled$kotlinx_coroutines_core(this.child.getContinuationCancellationCause(this.getJob()));
   }
}
